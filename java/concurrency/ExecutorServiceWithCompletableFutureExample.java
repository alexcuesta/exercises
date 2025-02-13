package concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceWithCompletableFutureExample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Example of Future and Callable.");
        System.out.println("Future blocks the main thread forcing it to poll if the result is available.");

        ExecutorService executor = Executors.newScheduledThreadPool(2);

        CompletableFuture.supplyAsync(() -> {
            return task(6000, "ALPHA");
        }, executor).thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> {
            return task(3000, "BETA");
        }, executor).thenAccept(System.out::println);

        doSomethingElse();

        System.out.println("Main thread is not blocked by the async task.");
        executor.shutdown();

        System.out.println("Bye bye");
    }

    private static String task(long time, String name) {
        try {
            System.out.println("Doing something async. Task: " + name);
            System.out.println("Thread: " + Thread.currentThread().getName());
            Thread.sleep(time);
            System.out.println("Task: " + name + " is done. Thread: " + Thread.currentThread().getName());
            return "Hello from task: " + name;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void doSomethingElse() throws InterruptedException {
        System.out.println("Doing something else...");
        Thread.sleep(1000);
    }
}