# Java 21

## JEP 442: Foreign Function & Memory API (Third Preview)

The Foreign Function & Memory API (FFM API) is designed to provide a powerful mechanism for Java programs to interoperate with native (non-Java) code and access off-heap memory safely and efficiently. It is part of Project Panama, which aims to simplify and optimize native interactions without the need for JNI (Java Native Interface), which is often cumbersome and error-prone.

#### Key Features:

Foreign Function API: Allows Java code to call functions written in native languages like C.
Memory Access API: Provides a mechanism to allocate and manage off-heap memory, which can be shared between Java and native code.
Memory Safety: Ensures safe access to foreign memory using memory segments, reducing risks like buffer overflows.
Efficient Interoperability: Enables efficient and high-performance interoperation with native code.

## JEP 443: Unnamed Patterns and Variables (Preview)
The main goal of JEP 443 is to simplify the code by allowing developers to ignore parts of patterns and variables that are not needed in a particular context. This is done by using underscore (_) as a discard pattern or variable, meaning that you don’t care about the value at that place in a pattern. This can be useful when destructuring objects, tuples, or records.

#### Key Features:
- Unnamed Patterns: You can use _ to represent any part of a pattern that you don't care about. It explicitly indicates that the matched value is ignored.
- Unnamed Variables: The underscore (_) can be used as an unnamed variable when you don’t care about capturing a value at a specific location.
#### Benefits:
- Clarity
- Reduction of Boilerplate
- Error Prevention