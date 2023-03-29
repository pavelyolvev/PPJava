package ThreadsLab5;

import Supporting.CollectionMethods;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;

public class ArrayContainer implements CollectionMethods {
    private final int[] array;
    ArrayContainer(int size){
        array = new int[size];
    }



    @Override
    public int[] getArray() {
        return array;
    }

    @Override
    public int getElement(int ind) {
        return array[ind];
    }

    @Override
    public void setElement(int ind, int val) {
        System.out.println("Write:\t" + val + "\tto position  \t" + ind);
        array[ind] = val;
    }

    @Override
    public int getSize() {
        return array.length;
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
