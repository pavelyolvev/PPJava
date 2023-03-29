package ThreadsLab5;

import Supporting.CollectionMethods;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;

public class SyncShell implements CollectionMethods {

    CollectionMethods col;

    public SyncShell(CollectionMethods col){
        this.col = col;
    }

    @Override
    public synchronized int[] getArray() {
        return col.getArray();
    }

    @Override
    public synchronized int getElement(int ind) {

        int val;
        while ((val = col.getElement(ind))==0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return val;

    }

    @Override
    public synchronized void setElement(int ind, int val) {
        col.setElement(ind, val);

    }

    @Override
    public synchronized int getSize() {
        return col.getSize();

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public int getCreditsDuration() {
        return 0;
    }

    @Override
    public int getShowDuration(int index) {
        return 0;
    }

    @Override
    public ArrayList<Integer> getDuration() {
        return null;
    }

    @Override
    public int avgShowLength() {
        return 0;
    }

    @Override
    public void output(OutputStream out) throws IOException {

    }

    @Override
    public void write(Writer out) throws IOException {

    }
}
