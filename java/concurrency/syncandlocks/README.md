# Sync and locks

In this package, there are examples of how to create a race condition, use synchronized and reentrant locks to avoid it.
Mainly, 3 threads are created to mutate a common resource, a counter. 

* **raceConditionExample** creates an intentional race condition so you can see the counter does not reach the expected total.
* **syncCountExample** fixes the issue with the `synchronized` keyword.
* **reentrantLockExample** fixes the issue with a `ReentrantLock`. Very basic example. This is a more flexible way to lock and unlock resources.

## Key differences between `synchronized` and `ReentrantLock`

| Feature               | synchronized                                   | ReentrantLock                                       |
|-----------------------|----------------------------------------------|-----------------------------------------------------|
| Lock Acquisition      | Implicit (automatically acquired and released) | Explicit (must acquire and release manually)       |
| Try-Lock Mechanism   | Not available                                | Can attempt to acquire the lock without blocking  |
| Fairness Policy      | Unfair (threads may wait indefinitely)       | Can be fair (grants access in FIFO order)         |
| Interruptible Locking | Not possible                                | Allows a thread to interrupt another waiting thread |
| Condition Variables  | Uses wait(), notify(), notifyAll()           | Uses Condition objects with await(), signal(), signalAll() |
| Readability          | Simpler, easier to use                       | More verbose but flexible                         |
| Performance         | Slightly faster for simple scenarios          | More efficient for complex locking needs         |mance	Slightly faster for simple scenarios	More efficient for complex locking needs |

## Disadvantages of `ReentrantLock`

| Disadvantage       | Explanation |
|--------------------|-------------|
| More Verbose      | Requires explicit `lock()` and `unlock()`, increasing complexity. |
| Prone to Deadlocks | If `unlock()` is forgotten (e.g., due to an exception), it never releases the lock, causing a deadlock. |
| Overhead          | Requires extra memory and execution time compared to `synchronized`. |
| Not Always Faster | For simple scenarios, `synchronized` is often faster due to JVM optimizations. |

## When to Use What?

| Use `synchronized` when...           | Use `ReentrantLock` when... |
|--------------------------------------|-----------------------------|
| You need simple thread safety       | You need advanced locking mechanisms |
| Blocking behavior is acceptable     | You want to use `tryLock()` to avoid indefinite waiting |
| Only one condition variable is needed | Multiple conditions (`await()`, `signal()`) are required |
| You want automatic unlocking        | You need explicit `lock()`/`unlock()` control |
| JVM optimizations matter            | You need interruptible locking |
