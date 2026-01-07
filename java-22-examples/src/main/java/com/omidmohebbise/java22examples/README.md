# java-22-examples

Small, single-module Gradle project for experimenting with Java 22 JEP samples.

## Java 22 JEPs to cover

- 423: Region Pinning for G1
- 447: Statements before `super(...)` (Preview)
- 454: Foreign Function & Memory API
- 456: Unnamed Variables & Patterns
- 457: Class-File API (Preview)
- 458: Launch Multi-File Source-Code Programs
- 459: String Templates (Second Preview)
- 460: Vector API (Seventh Incubator)
- 461: Stream Gatherers (Preview)
- 462: Structured Concurrency (Second Preview)
- 463: Implicitly Declared Classes and Instance Main Methods (Second Preview)
- 464: Scoped Values (Second Preview)

## Implemented samples

### JEP 423: Region Pinning for G1

- Class: `com.omidmohebbise.java22examples.Jep423RegionPinningForG1`
- What it does: allocates many `ByteBuffer.allocateDirect(...)` buffers and keeps them alive briefly.
  This creates a workload where G1 may need to deal with pinned regions.
- How to observe: run with G1 + GC logging and inspect the GC log output.

### JEP 447: Statements before `super(...)` (Preview)

- Class: `com.omidmohebbise.java22examples.Jep447StatementsBeforeSuper`
- What it does: demonstrates the ability to have statements before the `super(...)` call in a constructor.
- How to observe: run the program and observe the output order of statements.

### JEP 454: Foreign Function & Memory API

- Class: `com.omidmohebbise.java22examples.Jep454ForeignFunctionMemory`
- What it does: showcases the use of the Foreign Function & Memory API to call a foreign function and manipulate memory.
- How to observe: run the program and verify the output against expected results.

### JEP 456: Unnamed Variables & Patterns

- Class: `com.omidmohebbise.java22examples.Jep456UnnamedVariablesPatterns`
- What it does: demonstrates the use of unnamed variables and patterns in Java 22.
- How to observe: run the program and observe the output.

### JEP 457: Class-File API (Preview)

- Class: `com.omidmohebbise.java22examples.Jep457ClassFileApi`
- What it does: showcases the new Class-File API introduced in Java 22 (Preview feature).
- How to observe: run the program and inspect the class file generated.

### JEP 458: Launch Multi-File Source-Code Programs

- Class: `com.omidmohebbise.java22examples.Jep458LaunchMultiFile`
- What it does: demonstrates the ability to launch multi-file source-code programs.
- How to observe: run the program with multiple source files and observe the output.

### JEP 459: String Templates (Second Preview)

- Class: `com.omidmohebbise.java22examples.Jep459StringTemplates`
- What it does: showcases the use of string templates in Java 22 (Second Preview).
- How to observe: run the program and observe the formatted output.

### JEP 460: Vector API (Seventh Incubator)

- Class: `com.omidmohebbise.java22examples.Jep460VectorApi`
- What it does: demonstrates the use of the Vector API in Java 22 (Seventh Incubator).
- How to observe: run the program and verify the output against expected results.

### JEP 461: Stream Gatherers (Preview)

- Class: `com.omidmohebbise.java22examples.Jep461StreamGatherers`
- What it does: showcases the use of stream gatherers in Java 22 (Preview feature).
- How to observe: run the program and inspect the output.

### JEP 462: Structured Concurrency (Second Preview)

- Class: `com.omidmohebbise.java22examples.Jep462StructuredConcurrency`
- What it does: demonstrates the use of structured concurrency in Java 22 (Second Preview).
- How to observe: run the program and observe the output.

### JEP 463: Implicitly Declared Classes and Instance Main Methods (Second Preview)

- Class: `com.omidmohebbise.java22examples.Jep463ImplicitlyDeclaredClasses`
- What it does: showcases the feature of implicitly declared classes and instance main methods in Java 22 (Second Preview).
- How to observe: run the program and observe the output.

### JEP 464: Scoped Values (Second Preview)

- Class: `com.omidmohebbise.java22examples.Jep464ScopedValues`
- What it does: demonstrates the use of scoped values in Java 22 (Second Preview).
- How to observe: run the program and observe the output.
