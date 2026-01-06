package com.omidmohebbise.java21examples;

record Point(int x, int y) {
}

record Rectangle(int width, int height) {
}

public class Jep443UnnamedPatternsAndVariables_Preview {

    public static void main(String[] args) {
        // Example 1:
        Point p = new Point(3, 7);

        // Using an unnamed pattern to discard 'y' since we don't need it
        if (p instanceof Point(int x, _)) {
            System.out.println("X-coordinate is: " + x);
        }

        // Example 2:
        Rectangle rect = new Rectangle(5, 10);

        switch (rect) {
            case Rectangle(int w, _):
                System.out.println("Width is: " + w);
                break;
        }
    }
}
