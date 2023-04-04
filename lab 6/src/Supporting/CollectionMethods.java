package Supporting;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public interface CollectionMethods extends Serializable, Iterable<Integer> {

    String getTitle();
    int getCreditsDuration();
    int getShowDuration(int index);
    ArrayList<Integer> getDuration();
    int avgShowLength();
    void output(OutputStream out) throws IOException;
    void write(Writer out) throws IOException;

    int[] getArray();
    int getElement(int ind);
    void setElement(int ind, int val);
    int getSize();

    @Override
    Iterator<Integer> iterator();

}
