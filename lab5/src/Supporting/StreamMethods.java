package Supporting;

import Collections.*;
import Supporting.*;
import ThreadsLab5.SyncShell;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class StreamMethods {
    public static void outputCollectionMethods(CollectionMethods o, OutputStream out) throws IOException {

        o.output(out);
    }
    public static CollectionMethods inputCollectionMethods(InputStream in) throws IOException, FilmCollection.FilmColException, TheaterCollection.ThColException {
        String type=null;
        String title;
        int creditsDuration;
        ArrayList<Integer> duration = new ArrayList<>();

        ByteArrayOutputStream byteArr = new ByteArrayOutputStream();
        DataInputStream dataArr = new DataInputStream(in);

        int curInt;
        while((curInt = in.read()) != -1){
            if(curInt != '\0')
                byteArr.write(curInt);
            else{
                type = byteArr.toString();
                byteArr.reset();
                while((curInt = in.read()) != '\0'){
                    byteArr.write(curInt);
                }
                break;
            }
        }
        title = byteArr.toString();
        byteArr.reset();

        creditsDuration = dataArr.readInt();

        while((curInt = in.read()) != -1){
            if (curInt =='\0'){
                duration.add(dataArr.readInt());
            }
        }
        if(Objects.equals(type, "FilmCol"))
            return new FilmCollection(title, creditsDuration, duration);
        else return new TheaterCollection(title, creditsDuration, duration);
    }
    public static void writeColMethods(CollectionMethods o, Writer out) throws IOException {
        o.write(out);
    }

    public static CollectionMethods readColMethods(Reader in) throws IOException, FilmCollection.FilmColException, TheaterCollection.ThColException {
        String type=null;
        String title=null;
        int creditsDuration=-1;
        ArrayList<Integer> duration = new ArrayList<>();

        StreamTokenizer strTknr = new StreamTokenizer(in);

        strTknr.wordChars('a', 'z');

        strTknr.nextToken();
        type = strTknr.sval;

        strTknr.nextToken();
        title = strTknr.sval;

        strTknr.parseNumbers();

        strTknr.nextToken();
        creditsDuration = (int) strTknr.nval;

        while (strTknr.nextToken() != StreamTokenizer.TT_EOF){
            if(strTknr.ttype == StreamTokenizer.TT_NUMBER){
                duration.add((int) strTknr.nval);
            }
        }
        if(Objects.equals(type, "FilmCol"))
            return new FilmCollection(title, creditsDuration, duration);
        else return new TheaterCollection(title, creditsDuration, duration);
    }

    public static void serializeColMethods(CollectionMethods o, OutputStream out){
        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(out);

            objectOut.writeObject(o);
            objectOut.close();
            out.close();
        }catch (IOException e){
            throw new RuntimeException("Serialize object error");
        }

    }
    public static CollectionMethods deserializeColMethods(InputStream in){
        CollectionMethods col=null;
        try {
            ObjectInputStream objectIn = new ObjectInputStream(in);
            col = (CollectionMethods) objectIn.readObject();
            objectIn.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return col;
    }

    public static CollectionMethods synchedColMeth(CollectionMethods col){
        return new SyncShell(col);
    }
}
