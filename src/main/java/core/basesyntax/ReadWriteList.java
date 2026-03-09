package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock wl = lock.writeLock();
    private Lock rl = lock.readLock();

    public void add(E element) {
        wl.lock();
        try {
            list.add(element);
        } finally {
            wl.unlock();
        }
    }

    public E get(int index) {
        E element = null;
        rl.lock();
        try {
            element = list.get(index);
        } finally {
            rl.unlock();
        }
        return element;
    }

    public int size() {
        int i = 0;
        rl.lock();
        try {
            return list.size();
        } finally {
            rl.unlock();
            return i;
        }
    }
}
