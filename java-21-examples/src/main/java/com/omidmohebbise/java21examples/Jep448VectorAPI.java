package com.omidmohebbise.java21examples;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

/**
 * The Vector API in Java 21 allows developers to perform vectorized computations,
 * which can significantly improve performance for data-parallel operations.
 * Below is an example of using the Vector API to perform element-wise addition of two float arrays.
 */
public class Jep448VectorAPI {
    static void main() {
        // Define the species for 256-bit vectors
        VectorSpecies<Float> SPECIES = FloatVector.SPECIES_256;

        // Compare scalar vs vector addition (simple timing + correctness check)
        compareScalarAndVector(SPECIES);
    }

    /**
     * Plain scalar implementation equivalent to:
     * for (int i = 0; i < n; i++) c[i] = a[i] + b[i];
     */
    private static void addScalar(float[] a, float[] b, float[] c) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            c[i] = a[i] + b[i];
        }
    }

    /**
     * Vectorized implementation using the Vector API.
     */
    private static void addVector(VectorSpecies<Float> species, float[] a, float[] b, float[] c) {
        int i = 0;
        int n = a.length;
        for (; i < species.loopBound(n); i += species.length()) {
            var va = FloatVector.fromArray(species, a, i);
            var vb = FloatVector.fromArray(species, b, i);
            va.add(vb).intoArray(c, i);
        }
        for (; i < n; i++) {
            c[i] = a[i] + b[i];
        }
    }

    private static void compareScalarAndVector(VectorSpecies<Float> species) {
        // A slightly larger size makes the comparison more meaningful than length=16
        int n = 1_000_000;
        float[] a = new float[n];
        float[] b = new float[n];
        float[] cScalar = new float[n];
        float[] cVector = new float[n];

        for (int i = 0; i < n; i++) {
            a[i] = i * 1.0f;
            b[i] = (n - i) * 0.5f;
        }

        // Warm-up (helps HotSpot JIT compile these methods before measuring)
        for (int w = 0; w < 5; w++) {
            addScalar(a, b, cScalar);
            addVector(species, a, b, cVector);
        }

        long t0 = System.nanoTime();
        addScalar(a, b, cScalar);
        long t1 = System.nanoTime();

        long t2 = System.nanoTime();
        addVector(species, a, b, cVector);
        long t3 = System.nanoTime();

        // Verify both outputs match (exact match is fine here since it’s only + on same inputs)
        for (int i = 0; i < n; i++) {
            if (Float.compare(cScalar[i], cVector[i]) != 0) {
                throw new IllegalStateException("Mismatch at index " + i + ": scalar=" + cScalar[i] + ", vector=" + cVector[i]);
            }
        }

        double scalarMs = (t1 - t0) / 1_000_000.0;
        double vectorMs = (t3 - t2) / 1_000_000.0;

        System.out.println();
        System.out.println("Timing (n=" + n + "):");
        System.out.printf("  Scalar: %.3f ms%n", scalarMs);
        System.out.printf("  Vector: %.3f ms%n", vectorMs);
    }
}