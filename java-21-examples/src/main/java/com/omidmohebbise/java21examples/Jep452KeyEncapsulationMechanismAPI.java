package com.omidmohebbise.java21examples;

import javax.crypto.KEM;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jep452KeyEncapsulationMechanismAPI {

    static void main(String[] args) throws Exception {
        // JEP 452 introduces javax.crypto.KEM (standard in Java 21) for doing:
        //   encapsulate(publicKey)  -> (sharedSecret, encapsulation)
        //   decapsulate(privateKey, encapsulation) -> sharedSecret

        String kemAlgorithm = chooseKemAlgorithm();
        if (kemAlgorithm == null) {
            System.out.println("No KEM algorithms found in the installed security providers.");
            System.out.println("Installed providers: " + Arrays.toString(Security.getProviders()));
            return;
        }

        System.out.println("Using KEM algorithm: " + kemAlgorithm);

        // 1) Recipient generates a key pair (recipient keeps private key, shares public key)
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(kemAlgorithm);
        kpg.initialize(secureKeySizeIfSupported(kemAlgorithm), SecureRandom.getInstanceStrong());
        KeyPair recipientKeyPair = kpg.generateKeyPair();

        // 2) Sender encapsulates to recipient public key
        KEM kem = KEM.getInstance(kemAlgorithm);
        KEM.Encapsulator encapsulator = kem.newEncapsulator(recipientKeyPair.getPublic());
        KEM.Encapsulated encapsulated = encapsulator.encapsulate();

        SecretKey senderSecret = encapsulated.key();
        byte[] encapsulation = encapsulated.encapsulation();

        // 3) Recipient decapsulates using their private key and the received encapsulation bytes
        KEM.Decapsulator decapsulator = kem.newDecapsulator(recipientKeyPair.getPrivate());
        SecretKey recipientSecret = decapsulator.decapsulate(encapsulation);

        // 4) Verify both sides got the same derived secret
        byte[] senderBytes = senderSecret.getEncoded();
        byte[] recipientBytes = recipientSecret.getEncoded();

        boolean match = Arrays.equals(senderBytes, recipientBytes);
        System.out.println("Derived secrets match: " + match);
        System.out.println("SecretKey algorithm (sender):    " + senderSecret.getAlgorithm());
        System.out.println("SecretKey format (sender):       " + senderSecret.getFormat());
        System.out.println("SecretKey length (bytes):        " + (senderBytes == null ? "<unextractable>" : senderBytes.length));
        System.out.println("Encapsulation length (bytes):    " + encapsulation.length);

        if (!match) {
            throw new IllegalStateException("KEM decapsulation did not reproduce the same secret key");
        }
    }

    /**
     * Picks the first available KEM algorithm from installed providers, preferring modern names first.
     *
     * Algorithm availability depends on the JDK build + configured providers.
     */
    private static String chooseKemAlgorithm() {
        List<String> preferred = List.of(
                // Prefer post-quantum KEMs if present (provider-specific names)
                "ML-KEM",
                "ML-KEM-512",
                "ML-KEM-768",
                "ML-KEM-1024",
                // Some providers may expose X25519-based KEM constructions under different names
                "X25519",
                "X25519-KEM"
        );

        List<String> available = listKemAlgorithms();
        for (String p : preferred) {
            for (String a : available) {
                if (a.equalsIgnoreCase(p)) return a;
            }
        }
        return available.isEmpty() ? null : available.get(0);
    }

    private static List<String> listKemAlgorithms() {
        List<String> algs = new ArrayList<>();
        for (Provider provider : Security.getProviders()) {
            for (Provider.Service svc : provider.getServices()) {
                if ("KEM".equalsIgnoreCase(svc.getType())) {
                    String alg = svc.getAlgorithm();
                    if (!algs.contains(alg)) {
                        algs.add(alg);
                    }
                }
            }
        }
        if (!algs.isEmpty()) {
            System.out.println("Available KEM algorithms: " + algs);
        }
        return algs;
    }

    /**
     * Some KEM KeyPairGenerators accept a key-size, others ignore it or require a specific size.
     * This helper tries to pick a safe default for demo purposes. If the algorithm rejects it,
     * the caller will still fail fast with a clear exception.
     */
    private static int secureKeySizeIfSupported(String algorithm) {
        // Conservative defaults; providers may ignore these.
        if (algorithm == null) return 0;
        String a = algorithm.toUpperCase();
        if (a.contains("1024")) return 1024;
        if (a.contains("768")) return 768;
        if (a.contains("512")) return 512;
        // For generic names, pick a mid option.
        return 768;
    }
}

