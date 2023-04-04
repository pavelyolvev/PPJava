package Supporting;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

public class unModCol implements CollectionMethods{

    CollectionMethods recievedCol;
    unModCol(CollectionMethods o){
        recievedCol = o;
    }
    @Override
    public String getTitle() {
        return recievedCol.getTitle();
    }

    @Override
    public int getCreditsDuration() {
        return recievedCol.getCreditsDuration();
    }

    @Override
    public int getShowDuration(int index) {
        return recievedCol.getShowDuration(index);
    }

    @Override
    public ArrayList<Integer> getDuration() {
        return recievedCol.getDuration();
    }

    @Override
    public int avgShowLength() {
        return recievedCol.avgShowLength();
    }

    @Override
    public void output(OutputStream out) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(Writer out) throws IOException {
        recievedCol.write(out);
    }

    @Override
    public int[] getArray() {
        return recievedCol.getArray();
    }

    @Override
    public int getElement(int ind) {
        return recievedCol.getElement(ind);
    }

    @Override
    public void setElement(int ind, int val) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSize() {
        return recievedCol.getSize();
    }

    @Override
    public Iterator<Integer> iterator() {
        return recievedCol.iterator();
    }
}
