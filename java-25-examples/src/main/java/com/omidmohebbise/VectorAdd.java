package com.omidmohebbise;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

import java.time.Duration;
import java.time.Instant;
import java.util.SplittableRandom;

public class VectorAdd {

    // Pick the best SIMD width for the current CPU at runtime
    private static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

    public static void main(String[] args) {
        // Usage examples:
        //   add 10000000
        //   mul 10000000
        //   fma 10000000
        //   dot 10000000
        //   clamp 10000000
        String op = args.length > 0 ? args[0].toLowerCase() : "add";
        int size = args.length > 1 ? Integer.parseInt(args[1]) : 10_000_000;

        float[] a = randomFloatArray(size, 0.0f, 100.0f, 42L);
        float[] b = randomFloatArray(size, 0.0f, 100.0f, 43L);

        Instant t0 = Instant.now();

        switch (op) {
            case "add" -> {
                float[] c = new float[size];
                add(a, b, c);
                printSummary(op, size, t0, c);
            }
            case "mul" -> {
                float[] c = new float[size];
                mul(a, b, c);
                printSummary(op, size, t0, c);
            }
            case "fma" -> {
                // c[i] = a[i] * b[i] + scalar
                float scalar = 3.14f;
                float[] c = new float[size];
                fma(a, b, scalar, c);
                printSummary(op + "(scalar=" + scalar + ")", size, t0, c);
            }
            case "min" -> {
                float[] c = new float[size];
                min(a, b, c);
                printSummary(op, size, t0, c);
            }
            case "max" -> {
                float[] c = new float[size];
                max(a, b, c);
                printSummary(op, size, t0, c);
            }
            case "clamp" -> {
                // clamp a into [lo, hi]
                float lo = 10.0f;
                float hi = 90.0f;
                float[] c = new float[size];
                clamp(a, lo, hi, c);
                printSummary(op + "(lo=" + lo + ", hi=" + hi + ")", size, t0, c);
            }
            case "dot" -> {
                float result = dot(a, b);
                Instant t1 = Instant.now();
                IO.println("op=" + op + ", size=" + size + ", elapsed=" + Duration.between(t0, t1).toMillis() + "ms");
                IO.println("dot=" + result);
            }
            default -> {
                IO.println("Unknown op: " + op);
                IO.println("Try one of: add, mul, fma, min, max, clamp, dot");
            }
        }
    }

    private static void printSummary(String op, int size, Instant t0, float[] c) {
        Instant t1 = Instant.now();
        IO.println("op=" + op + ", size=" + size + ", elapsed=" + Duration.between(t0, t1).toMillis() + "ms");
        for (int i = 0; i < Math.min(8, c.length); i++) {
            IO.println("out[" + i + "]=" + c[i]);
        }
    }

    /**
     * Generates a float array filled with pseudo-random values in [minInclusive, maxExclusive).
     *
     * Uses SplittableRandom for speed and (optionally) reproducible runs via a seed.
     */
    static float[] randomFloatArray(int size, float minInclusive, float maxExclusive, long seed) {
        if (size < 0) throw new IllegalArgumentException("size must be >= 0");
        if (!(maxExclusive > minInclusive)) {
            throw new IllegalArgumentException("maxExclusive must be > minInclusive");
        }

        float[] out = new float[size];
        SplittableRandom rnd = new SplittableRandom(seed);
        float range = maxExclusive - minInclusive;
        for (int i = 0; i < out.length; i++) {
            // nextFloat() gives [0,1); scale to desired range
            out[i] = minInclusive + rnd.nextFloat() * range;
        }
        return out;
    }

    // --- Vector kernels ---

    // c[i] = a[i] + b[i]
    static void add(float[] a, float[] b, float[] c) {
        elementwise2(a, b, c, Op2.ADD);
    }

    // c[i] = a[i] * b[i]
    static void mul(float[] a, float[] b, float[] c) {
        elementwise2(a, b, c, Op2.MUL);
    }

    // c[i] = min(a[i], b[i])
    static void min(float[] a, float[] b, float[] c) {
        elementwise2(a, b, c, Op2.MIN);
    }

    // c[i] = max(a[i], b[i])
    static void max(float[] a, float[] b, float[] c) {
        elementwise2(a, b, c, Op2.MAX);
    }

    // c[i] = a[i] * b[i] + addend
    static void fma(float[] a, float[] b, float addend, float[] c) {
        int len = requireSameLength(a, b, c);

        int i = 0;
        int upperBound = SPECIES.loopBound(len);
        FloatVector vAdd = FloatVector.broadcast(SPECIES, addend);

        for (; i < upperBound; i += SPECIES.length()) {
            var va = FloatVector.fromArray(SPECIES, a, i);
            var vb = FloatVector.fromArray(SPECIES, b, i);
            // Prefer lanewise FMA to hint fused operation when supported
            va.lanewise(VectorOperators.FMA, vb, vAdd).intoArray(c, i);
        }

        if (i < len) {
            VectorMask<Float> m = SPECIES.indexInRange(i, len);
            var va = FloatVector.fromArray(SPECIES, a, i, m);
            var vb = FloatVector.fromArray(SPECIES, b, i, m);
            va.lanewise(VectorOperators.FMA, vb, vAdd, m).intoArray(c, i, m);
        }
    }

    // clamp: out[i] = min(max(in[i], lo), hi)
    static void clamp(float[] in, float lo, float hi, float[] out) {
        if (out.length != in.length) throw new IllegalArgumentException("Arrays must have same length");
        if (hi < lo) throw new IllegalArgumentException("hi must be >= lo");

        int len = in.length;
        int i = 0;
        int upperBound = SPECIES.loopBound(len);

        FloatVector vLo = FloatVector.broadcast(SPECIES, lo);
        FloatVector vHi = FloatVector.broadcast(SPECIES, hi);

        for (; i < upperBound; i += SPECIES.length()) {
            var v = FloatVector.fromArray(SPECIES, in, i);
            v = v.max(vLo);
            v = v.min(vHi);
            v.intoArray(out, i);
        }

        if (i < len) {
            VectorMask<Float> m = SPECIES.indexInRange(i, len);

            // Load only valid lanes; invalid lanes read as 0.0f
            var v = FloatVector.fromArray(SPECIES, in, i, m);

            // Compute clamped result for all lanes (invalid lanes don't matter)
            var r = v.max(vLo).min(vHi);

            // Store back only valid lanes.
            r.intoArray(out, i, m);
        }
    }

    // dot product: sum(a[i] * b[i])
    static float dot(float[] a, float[] b) {
        int len = requireSameLength(a, b);

        int i = 0;
        int upperBound = SPECIES.loopBound(len);

        FloatVector acc = FloatVector.zero(SPECIES);

        for (; i < upperBound; i += SPECIES.length()) {
            var va = FloatVector.fromArray(SPECIES, a, i);
            var vb = FloatVector.fromArray(SPECIES, b, i);
            acc = va.fma(vb, acc); // acc += va * vb
        }

        float sum = acc.reduceLanes(VectorOperators.ADD);

        // tail
        for (; i < len; i++) {
            sum += a[i] * b[i];
        }

        return sum;
    }

    // --- shared plumbing ---

    private enum Op2 { ADD, MUL, MIN, MAX }

    private static void elementwise2(float[] a, float[] b, float[] c, Op2 op) {
        int len = requireSameLength(a, b, c);

        int i = 0;
        int upperBound = SPECIES.loopBound(len);

        for (; i < upperBound; i += SPECIES.length()) {
            var va = FloatVector.fromArray(SPECIES, a, i);
            var vb = FloatVector.fromArray(SPECIES, b, i);
            FloatVector r = switch (op) {
                case ADD -> va.add(vb);
                case MUL -> va.mul(vb);
                case MIN -> va.min(vb);
                case MAX -> va.max(vb);
            };
            r.intoArray(c, i);
        }

        if (i < len) {
            VectorMask<Float> m = SPECIES.indexInRange(i, len);
            var va = FloatVector.fromArray(SPECIES, a, i, m);
            var vb = FloatVector.fromArray(SPECIES, b, i, m);

            FloatVector r = switch (op) {
                case ADD -> va.add(vb);
                case MUL -> va.mul(vb);
                case MIN -> va.min(vb);
                case MAX -> va.max(vb);
            };

            // Store only the valid lanes
            r.intoArray(c, i, m);
        }
    }

    private static int requireSameLength(float[] a, float[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays must have same length");
        }
        return a.length;
    }

    private static int requireSameLength(float[] a, float[] b, float[] c) {
        int len = requireSameLength(a, b);
        if (c.length != len) {
            throw new IllegalArgumentException("Arrays must have same length");
        }
        return len;
    }
}
