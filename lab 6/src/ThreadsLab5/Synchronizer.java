package ThreadsLab5;

import Supporting.CollectionMethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Synchronizer {

    final Lock lock = new ReentrantLock();
    volatile int curInd = 0;
    CollectionMethods col;
    boolean isWrited=false;
    Synchronizer(CollectionMethods col){
        this.col=col;
    }
    public void read() throws InterruptedException {
        synchronized (lock){
            while (!isWrited)
                lock.wait();
            System.out.println("Readed:\t" + col.getElement(curInd) + "\tfrom position  \t" + curInd);
            if(curInd < col.getSize()-1)
                curInd++;
            isWrited=false;
            lock.notify();
        }
    }
    public void write(int val) throws InterruptedException {
        synchronized (lock){
            while (isWrited)
                lock.wait();
            col.setElement(curInd, val);
            isWrited=true;
            lock.notify();
        }

    }
    public int getCurInd(){
        return curInd;
    }
    public int getColSize(){
        return col.getSize();
    }

}
