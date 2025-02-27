package concurrency.syncandlocks;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private ReentrantLock lock = new ReentrantLock();
    private int count = 0;


    public void rawInc() {
        count++;
    }

    public synchronized void syncInc() {
        count++;
    }

    public void incrementWithLock() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }

}
