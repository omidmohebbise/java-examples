package com.omidmohebbise.java22examples;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * JEP 423: Region Pinning for G1
 *
 * This JEP is a JVM/GC change: it improves how the G1 garbage collector deals with
 * "pinned" regions (regions that can't be moved), which are often created when Java
 * code interacts with native memory / direct buffers.
 *
 * Key takeaway:
 * - There is no new Java language/library API for JEP 423.
 * - You observe it via GC behavior (logs / performance), especially with direct buffers
 *   and other mechanisms that can pin memory.
 *
 * This demo creates many direct (off-heap) buffers and keeps them alive for a while.
 * The program itself doesn't prove JEP 423 is active; it's a workload you can run
 * with GC logging to observe G1 behavior.
 *
 * Suggested run (Java 22, G1):
 *   -Xms256m -Xmx256m
 *   -XX:+UseG1GC
 *   -Xlog:gc*,safepoint:file=gc.log:time,level,tags
 *
 * If you compare Java 21 vs Java 22, you may see fewer/shorter GC pauses or different
 * region management when many regions become pinned.
 */
public class Jep423RegionPinningForG1 {

    public static void main(String[] args) throws Exception {
        int bufferSizeBytes = Integer.getInteger("bufferSize", 1 * 1024 * 1024); // 1 MiB
        int count = Integer.getInteger("count", 128);
        int holdSeconds = Integer.getInteger("holdSeconds", 10);

        System.out.printf("Allocating %d direct buffers of %,d bytes (%,d MiB total) ...%n",
                count,
                bufferSizeBytes,
                (long) count * bufferSizeBytes / (1024 * 1024));

        List<ByteBuffer> buffers = new ArrayList<>(count);

        // Allocate direct buffers and touch each page so the OS commits memory.
        for (int i = 0; i < count; i++) {
            ByteBuffer buf = ByteBuffer.allocateDirect(bufferSizeBytes);

            // Touch the buffer (lightly) to ensure it's actually backed by memory.
            // (Stride by 4 KiB, common page size.)
            for (int p = 0; p < bufferSizeBytes; p += 4096) {
                buf.put(p, (byte) (p ^ i));
            }

            buffers.add(buf);

            if ((i + 1) % 16 == 0) {
                System.out.printf("  allocated %d/%d%n", i + 1, count);
            }
        }

        // Create some on-heap churn too, to provoke concurrent marking / evacuation.
        System.out.println("Creating on-heap garbage to encourage GC cycles...");
        for (int round = 0; round < 50; round++) {
            byte[][] garbage = new byte[128][];
            for (int i = 0; i < garbage.length; i++) {
                garbage[i] = new byte[256 * 1024]; // 256 KiB
            }
            if (round % 10 == 0) {
                System.out.printf("  churn round %d/50%n", round);
            }
        }

        System.out.printf("Holding direct buffers for %d seconds. Check GC logs now...%n", holdSeconds);
        Thread.sleep(holdSeconds * 1000L);

        // Drop references and request GC (not guaranteed). This can help show cleanup in logs.
        buffers.clear();
        System.out.println("Released buffers, requesting GC... (not guaranteed)");
        System.gc();

        Thread.sleep(2000);
        System.out.println("Done.");
    }
}
