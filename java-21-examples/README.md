# Java 21

### JEP 442: Foreign Function & Memory API (Third Preview)

Overview: The Foreign Function & Memory API (FFM API) is designed to provide a powerful mechanism for Java programs to interoperate with native (non-Java) code and access off-heap memory safely and efficiently. It is part of Project Panama, which aims to simplify and optimize native interactions without the need for JNI (Java Native Interface), which is often cumbersome and error-prone.

#### Key Features:

Foreign Function API: Allows Java code to call functions written in native languages like C.
Memory Access API: Provides a mechanism to allocate and manage off-heap memory, which can be shared between Java and native code.
Memory Safety: Ensures safe access to foreign memory using memory segments, reducing risks like buffer overflows.
Efficient Interoperability: Enables efficient and high-performance interoperation with native code.