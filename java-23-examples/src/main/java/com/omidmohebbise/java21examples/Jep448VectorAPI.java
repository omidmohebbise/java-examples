package com.omidmohebbise.java21examples;
import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

/**
 * The Vector API in Java 21 allows developers to perform vectorized computations,
 * which can significantly improve performance for data-parallel operations.
 * Below is an example of using the Vector API to perform element-wise addition of two float arrays.
 */
public class Jep448VectorAPI {
    public static void main(String[] args) {
        // Define the species for 256-bit vectors
        VectorSpecies<Float> SPECIES = FloatVector.SPECIES_256;

        // Input arrays
        float[] a = new float[16];
        float[] b = new float[16];
        float[] result = new float[16];

        // Initialize input arrays
        for (int i = 0; i < a.length; i++) {
            a[i] = i + 1.0f;
            b[i] = (i + 1) * 2.0f;
        }

        // Perform vectorized addition
        int i = 0;
        for (; i < SPECIES.loopBound(a.length); i += SPECIES.length()) {
            // Load vectors from arrays
            var va = FloatVector.fromArray(SPECIES, a, i);
            var vb = FloatVector.fromArray(SPECIES, b, i);

            // Perform addition
            var vresult = va.add(vb);

            // Store the result back into the result array
            vresult.intoArray(result, i);
        }

        // Handle remaining elements (if any)
        for (; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }

        // Print the result
        System.out.println("Result:");
        for (float value : result) {
            System.out.print(value + " ");
        }
    }
}