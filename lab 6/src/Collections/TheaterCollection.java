package Collections;

import Supporting.CollectionMethods;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TheaterCollection implements CollectionMethods {
    private static final Logger LOGGER = Logger.getLogger(TheaterCollection.class.getName());
    private static long serialVersionUID;
    String title;
    int creditsDuration; //Время титров
    ArrayList<Integer> duration;
    //int[] duration; // Время выступления

    public TheaterCollection(){
        title = "Theater collection";
        creditsDuration = 0;
        duration = new ArrayList<>();
    }
    public TheaterCollection(String title, int creditsDuration, ArrayList<Integer> duration) throws ThColException{
        if(Objects.isNull(title))
            throw  new TheaterCollection.ThColException("Title is null");

        if(Objects.isNull(duration))
            throw new TheaterCollection.ThColException("Duration is null");

        this.title = title;
        this.creditsDuration = creditsDuration;
        this.duration = duration;
    }
    @Override
    public  String getTitle(){
        return title;
    }
    @Override
    public int getCreditsDuration(){
        return creditsDuration;
    }
    @Override
    public int getShowDuration(int index){
        return duration.get(index);
    }
    @Override
    public ArrayList<Integer> getDuration(){
        return duration;
    }
    @Override
    public int avgShowLength(){ //Средняя длина фильмов без титров
        if(duration.size() == 0)
            throw new ThColRTException("Collection is empty");
        int sum=0;
        for (Integer integer : duration) sum += integer - creditsDuration;
        return sum;
    }



    @Override
    public String toString(){
        return ("Type: Theater, " +
                "Title=" + title + ", " +
                "Credits Duration=" + creditsDuration + ", " +
                "Durations=" + duration);
    }
    @Override
    public boolean equals(Object object){
        if (object == this)
            return true;
        if (object == null || object.getClass() != this.getClass())
            return false;

        TheaterCollection thCol = (TheaterCollection) object;
        if(!title.equals(thCol.title))
            return false;
        if(!(creditsDuration == thCol.creditsDuration))
            return false;
        return duration.equals(thCol.duration);
    }
    @Override
    public int hashCode(){
        return Objects.hash(title, creditsDuration, duration);
    }

    public static class ThColException extends Exception{
        @Serial
        private static final long serialVersionUID = 1L;
        public ThColException(String message){
            super(message);

            LOGGER.log(Level.SEVERE, "An exception occurred", message);
            try {
                FileHandler fileHandler = new FileHandler("myLogFile.log");
                fileHandler.setFormatter(new SimpleFormatter());
                LOGGER.addHandler(fileHandler);
                LOGGER.log(Level.SEVERE, "An exception occurred", message);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Failed to write to log file", ex);
            }
        }
    }
    public static class ThColRTException extends RuntimeException{
        @Serial
        private static final long serialVersionUID = 1L;
        public ThColRTException(String message){
            super(message);

            LOGGER.log(Level.SEVERE, "An exception occurred", message);
            try {
                FileHandler fileHandler = new FileHandler("myLogFile.log");
                fileHandler.setFormatter(new SimpleFormatter());
                LOGGER.addHandler(fileHandler);
                LOGGER.log(Level.SEVERE, "An exception occurred", message);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Failed to write to log file", ex);
            }
        }
    }

    @Override
    public void output(OutputStream out) throws IOException {
        DataOutputStream intData = new DataOutputStream(out);

        out.write("TheaCol".getBytes());
        out.write('\0');
        out.write(title.getBytes());
        out.write('\0');
        intData.writeInt(creditsDuration);

        for (Integer integer : duration) {
            out.write('\0');
            intData.writeInt(integer);
        }
        out.close();
    }

    @Override
    public void write(Writer out) throws IOException {
        out.write("TheaCol");
        out.write(' ');
        out.write(title);
        out.write(' ');
        out.write(String.valueOf(creditsDuration));
        for (Integer integer : duration) {
            out.write(' ');
            out.write(String.valueOf(integer));
        }
        out.write(' ');
        out.close();

    }

    @Override
    public int[] getArray() {
        return new int[0];
    }

    @Override
    public int getElement(int ind) {
        return 0;
    }

    @Override
    public void setElement(int ind, int val) {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new iteratorCL(duration);
    }

}
