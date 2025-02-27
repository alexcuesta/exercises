package concurrency.syncandlocks;

class SynAndLocksExample {

    public static void main(String[] args) {
        int countTarget = 100000;
        System.out.println("We are counting to " + countTarget + " with 3 threads. Total should be 3 times " + countTarget + ": " + countTarget * 3);
        System.out.println("------------------------------------------------------------------");
        System.out.println("Increment without any synchronization. Race conditions will occur.");
        raceConditionExample(countTarget);

        System.out.println("------------------------------------------------------------------");
        System.out.println("Increment with synchronization. Race conditions should not occur.");
        syncCountExample(countTarget);

        System.out.println("------------------------------------------------------------------");
        System.out.println("Increment with ReentrantLock");
        reentrantLockCountExample(countTarget);

    }

    private static void raceConditionExample(int countTarget) {
        Counter counter = new Counter();
        countFast(counter::rawInc, countTarget);
        System.out.println("Final count: " + counter.getCount());
    }

    private static void syncCountExample(int countTarget) {
        Counter counter = new Counter();
        countFast(counter::syncInc, countTarget);
        System.out.println("Final count: " + counter.getCount());
    }

    private static void reentrantLockCountExample(int countTarget) {
        Counter counter = new Counter();
        countFast(counter::incrementWithLock, countTarget);
        System.out.println("Final count: " + counter.getCount());
    }

    private static void countFast(Runnable counterFunction, int countTarget) {
        Runnable task = () -> {
            for (int i = 0; i < countTarget; i++) {
                counterFunction.run();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
