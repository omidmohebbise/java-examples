package com.omidmohebbise.java21examples;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

public class Jep442ForeignFunctionAndMemoryAPI3rdReview {

    public static void main(String[] args) throws Throwable {
        // Load the native library
        System.loadLibrary("add");

        // Create a symbol lookup to find the 'add' function in the native library
        Linker linker = Linker.nativeLinker();
        SymbolLookup lookup = SymbolLookup.loaderLookup();

        // Lookup the 'add' function in the loaded native library
        FunctionDescriptor descriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT);
        MethodHandle addHandle = linker.downcallHandle(lookup.find("add").orElseThrow(), descriptor);

        // Call the native 'add' function
        int result = (int) addHandle.invoke(10, 20);

        System.out.println("Result from native add function: " + result);
    }
}
