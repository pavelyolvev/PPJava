package Collections;

import java.util.ArrayList;
import java.util.Iterator;

public class iteratorCL implements Iterator<Integer> {

    int index = 0;

    ArrayList<Integer> elements;

    public iteratorCL( ArrayList<Integer> elements){
        this.elements = elements;
    }

    @Override
    public boolean hasNext() {
        return index<elements.size();
    }

    @Override
    public Integer next() {
        return elements.get(index++);
    }
}
