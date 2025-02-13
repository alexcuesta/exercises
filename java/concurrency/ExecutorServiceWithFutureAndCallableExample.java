package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceWithFutureAndCallableExample {

    public static void main(String[] args) {
        System.out.println("Example of Future and Callable.");
        System.out.println("Future blocks the main thread forcing it to poll if the result is available.");
        Callable<String> task = () -> {
            Thread.sleep(5000);
            return "Hello from Callable!";
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> future = executor.submit(task);

        try {

            // we poll the future object to check if the result is available
            System.out.println("Waiting for the result...");
            while (!future.isDone()) {
                doSomethingElse();
            }

            // we can now use the result
            System.out.println("The future is here! Result: " + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private static void doSomethingElse() throws InterruptedException {
        System.out.println("Doing something else...");
        Thread.sleep(1000);
    }
}