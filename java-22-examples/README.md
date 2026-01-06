# Java 21

## JEP 442: Foreign Function & Memory API (Third Preview)

The Foreign Function & Memory API (FFM API) is designed to provide a powerful mechanism for Java programs to
interoperate with native (non-Java) code and access off-heap memory safely and efficiently. It is part of Project
Panama, which aims to simplify and optimize native interactions without the need for JNI (Java Native Interface), which
is often cumbersome and error-prone.

#### Key Features:

Foreign Function API: Allows Java code to call functions written in native languages like C.
Memory Access API: Provides a mechanism to allocate and manage off-heap memory, which can be shared between Java and
native code.
Memory Safety: Ensures safe access to foreign memory using memory segments, reducing risks like buffer overflows.
Efficient Interoperability: Enables efficient and high-performance interoperation with native code.

## JEP 443: Unnamed Patterns and Variables (Preview)

The main goal of JEP 443 is to simplify the code by allowing developers to ignore parts of patterns and variables that
are not needed in a particular context. This is done by using underscore (_) as a discard pattern or variable, meaning
that you don’t care about the value at that place in a pattern. This can be useful when destructuring objects, tuples,
or records.

#### Key Features:

- Unnamed Patterns: You can use _ to represent any part of a pattern that you don't care about. It explicitly indicates
  that the matched value is ignored.
- Unnamed Variables: The underscore (_) can be used as an unnamed variable when you don’t care about capturing a value
  at a specific location.

#### Benefits:

- Clarity
- Reduction of Boilerplate
- Error Prevention

## JEP 444:    Virtual Threads

"Virtual Threads", is a significant feature in Java aimed at improving concurrency and simplifying thread management.
This enhancement, first previewed in JEP 425 and finalized in JEP 444, introduces virtual threads as lightweight,
efficient threads that are managed by the Java runtime.

#### Key Concepts of JEP 444: Virtual Threads:

- Virtual Threads:
    - Virtual threads are lightweight threads, which are much more scalable compared to traditional platform threads (
      also known as carrier threads).
    - Each virtual thread is not tied to a specific operating system thread. Instead, multiple virtual threads can share
      a single OS thread, allowing for high concurrency without the overhead of OS-level thread management.
- Problem It Solves:
    - In traditional Java applications, platform threads are expensive to create and manage. They use significant
      memory, and the operating system can only handle a limited number of them efficiently.
    - This limits scalability for highly concurrent applications, like web servers, which may need to handle thousands
      or even millions of concurrent requests.
    - Virtual threads solve this by enabling the creation of a large number of threads (millions) without a proportional
      increase in resource usage.
- How Virtual Threads Work:
    - Virtual threads are managed by the Java runtime, which maps them onto a pool of platform threads (carrier threads)
      when they are actually executing.
    - When a virtual thread performs a blocking operation (e.g., I/O), it is unmounted from its platform thread, freeing
      that platform thread to perform other tasks. When the blocking operation completes, the virtual thread is
      remounted onto any available platform thread to continue execution.
    - This allows blocking operations, which would normally reduce the throughput of traditional platform threads, to be
      handled much more efficiently.

#### Benefits of Virtual Threads:

- **Scalability**: You can have millions of virtual threads, allowing applications to handle a massive number of
  concurrent
  tasks.

- **Simplified Concurrency**: With virtual threads, Java developers can write simple blocking code (e.g., using
  synchronized
  blocks or I/O operations) without worrying about resource contention or blocking performance. There's no need for
  complex non-blocking code (e.g., reactive programming) to achieve high concurrency.

- **Improved Resource Usage**: Virtual threads use significantly less memory compared to platform threads, and the CPU
  cost
  of context switching between virtual threads is much lower since they are not tightly bound to OS threads.

- **Compatibility**: Existing Java code that uses Thread APIs can run with virtual threads with minimal or no
  modification,
  making it easier to adopt.

## JEP 445: Unnamed Classes and Instances Main Methods(Preview)

- **Unnamed Classes**:
  You don’t need to declare a class explicitly. Instead, the Java program is treated as an unnamed class with an implicit class name.
  This reduces boilerplate for small programs.
- **Instance Main Methods**:
  Allows the use of an instance main method, rather than the static main method typically required for Java programs.
- **Designed for Simplicity**:
  Targeted at simplifying beginner Java programming and rapid prototyping.

**How It Works**
- The source file can contain executable code directly without a named class or static main method.
- The JVM treats the file as an unnamed class and runs the instance main method or the top-level code.

## JEP 446: Scoped Values (Preview)
Scoped Values are a new mechanism introduced in Java 21 (as a preview feature) to share immutable data within a well-defined 
scope, particularly across threads. They are an alternative to ThreadLocal, designed to address its limitations,
especially in concurrent and structured programming.