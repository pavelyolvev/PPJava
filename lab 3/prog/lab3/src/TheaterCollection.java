import java.util.ArrayList;
import java.util.Objects;

public class TheaterCollection implements CollectionMethods{
    String title;
    int creditsDuration; //Время титров
    ArrayList<Integer> duration; // Время выступления

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
        public ThColException(String message){
            super(message);
        }
    }
    public static class ThColRTException extends RuntimeException{
        public ThColRTException(String message){
            super(message);
        }
    }
}
