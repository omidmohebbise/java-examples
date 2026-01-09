# ExecutorService vs ForkJoinPool (simple comparison)

This note compares the most common Java executors and `ForkJoinPool` in practical terms.

> Goal: help you choose the right tool for the job.

---

## The main options

### `Executors.newSingleThreadExecutor()`
- **Threads:** 1
- **Best for:**
  - forcing *ordering* (tasks run one-by-one)
  - protecting a non-thread-safe resource without adding locks (e.g., one DB connection wrapper)
- **Tradeoffs:** no parallelism; slow if tasks are independent

### `Executors.newFixedThreadPool(n)`
- **Threads:** fixed size `n`
- **Queue:** typically an *unbounded* queue (tasks can pile up)
- **Best for:**
  - CPU-bound work (use `n ~= number of cores`)
  - controlled parallelism (you know max concurrency)
- **Tradeoffs / pitfalls:**
  - if producers submit faster than workers can process, the queue can grow without limit
  - blocking I/O tasks can waste threads and reduce throughput

### `Executors.newCachedThreadPool()`
- **Threads:** grows/shrinks dynamically
- **Queue:** *no queue* for waiting tasks; instead it tends to create more threads
- **Best for:**
  - many short, bursty tasks
  - mostly I/O-bound work where blocking is common
- **Tradeoffs / pitfalls:**
  - can create *a lot* of platform threads → memory/CPU overhead
  - can overwhelm external systems (DB, HTTP) because it may run too much in parallel

### `Executors.newScheduledThreadPool(n)`
- **Threads:** fixed `n`
- **Best for:**
  - scheduling: run later / run periodically
  - heartbeats, polling, cleanup tasks
- **Tradeoffs:** not a throughput-oriented pool; designed for timing/scheduling

### `Executors.newWorkStealingPool()`
- **Under the hood:** a `ForkJoinPool` (work-stealing)
- **Best for:**
  - CPU-bound tasks split into many smaller tasks
- **Tradeoffs:** similar rules as Fork/Join (see below), especially about blocking

### Virtual threads (JDK 21+)
- API: `Executors.newVirtualThreadPerTaskExecutor()`
- **Threads:** creates a *virtual thread per task* (very cheap vs platform threads)
- **Best for:**
  - *blocking I/O* (HTTP calls, DB calls, file I/O) with simple thread-per-request style
  - high concurrency with a straightforward programming model
- **Tradeoffs / pitfalls:**
  - CPU-bound work doesn’t magically get faster; still limited by cores
  - still need back-pressure: you can launch too many concurrent tasks and overload dependencies

---

## `ForkJoinPool` (and why it’s different)

### What it is
- A pool designed for **divide-and-conquer** problems.
- Uses **work-stealing**: each worker has its own deque; idle workers steal work from others.

### Best for
- CPU-bound algorithms where you can recursively split the work:
  - array/tensor reductions (sum)
  - parallel sort
  - recursive tree processing

### Not great for
- **Blocking I/O** inside Fork/Join tasks.
  - If tasks block, worker threads stop doing CPU work, and you lose the benefit.
  - If you *must* block, consider using `ForkJoinPool.ManagedBlocker` or use virtual threads / a dedicated I/O pool.

### Typical API shape
- Extend `RecursiveTask<T>` (returns a value) or `RecursiveAction` (returns nothing)
- In `compute()`:
  - if small enough → do work directly
  - else split into 2+ subtasks, `fork()` and `join()` and combine results

---

## Quick decision guide

- Want strict ordering? → **SingleThreadExecutor**
- Mostly CPU work, fixed concurrency? → **FixedThreadPool** or **ForkJoinPool**
- Divide-and-conquer CPU algorithm (many small tasks)? → **ForkJoinPool / RecursiveTask**
- Lots of blocking I/O and you want simple code? → **Virtual threads**
- Need scheduling (run later/periodic)? → **ScheduledThreadPool**
- Bursty short tasks, mostly I/O (and you know what you’re doing)? → **CachedThreadPool**

---

## How to compare them in code (same workload)

In this module, the file below compares these executors on the *same problem*:
- create a 3D tensor
- compute the sum sequentially
- compute the sum using different executors + `ForkJoinPool`

See:
- `java-concurrency/00-threads/src/main/java/com/omidmhebbise/thread/executerservice/forkjoin/DivideAndConquerExample.java`

---

## Important note about “benchmarks”

If you want trustworthy numbers, use **JMH**. Simple `System.nanoTime()` timing is good for learning trends, but results vary due to:
- JIT compilation / warmup
- GC
- CPU frequency scaling
- background processes

