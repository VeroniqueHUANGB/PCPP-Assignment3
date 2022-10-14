package exercises05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class LongCounter {
  private long count = 0;
  Lock lock = new ReentrantLock();

  public synchronized void increment() {
    count = count + 1;
  }
  public synchronized long get() { 
    return count; 
  }

  public synchronized void add(long c) {
    // to be filled in
    lock.lock();
    try{
      count = count + c;
    }
    finally{lock.unlock(); }
  }

  public synchronized void reset() {
    // to be filled in
    lock.lock();
    try{
      count = 0;
    }
    finally{lock.unlock();}
  }
}