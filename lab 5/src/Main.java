import Supporting.CollectionMethods;

import java.io.*;
import java.util.*;

import Supporting.*;
import ThreadsLab5.Menu;
import Collections.*;

public class Main {
    static ArrayList<CollectionMethods> interAr = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String exit = "exit";


    public static void menu() {
        char input;

        while (true) {
            System.out.println();
            System.out.println("1 -- Film Collection \n2 -- Theater Collection \n3 -- Master Collection \n4 -- Input/Output Test \n5 -- Save to File \n6 -- Load from File \n7 -- Open threads menu \n0 -- Exit");
            input = scanner.nextLine().charAt(0);

            try {
                switch (input) {
                    case '1' -> openFilmCollection();
                    case '2' -> openTheaterCollection();
                    case '3' -> openMasterCollection();
                    case '4' -> streamTest();
                    case '5' -> saveToFile();
                    case '6' -> loadFromFile();
                    case '7' -> openThreads();
                    case '0' -> {
                        System.exit(0);
                        return;
                    }
                    default -> {
                        System.exit(99);
                        return;
                    }
                }
            } catch (TheaterCollection.ThColException | FilmCollection.FilmColException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    static public void main(String[] args){
        menu();

    }
    static void openFilmCollection() throws FilmCollection.FilmColException {
        String title;
        int credits;
        ArrayList<Integer> duration= new ArrayList<>();


        System.out.println("Film Collection opened. Type exit to leave");
        System.out.println();


        System.out.print("Input Title: ");
        String input = scanner.nextLine();
        if(!exit.equals(input)){
            title = input;

        }
        else return;

        System.out.print("Input Duration of credits: ");
        input = scanner.nextLine();
        if(!exit.equals(input)){
            credits = Integer.parseInt(input);
        }
        else return;

        while (true){
            System.out.print("Input duration for Film "+ duration.size() + ": ");
            input = scanner.nextLine();
            if(!exit.equals(input)) duration.add(Integer.parseInt(input));
            else break;
        }
        interAr.add(new FilmCollection(title,credits,duration));

    }
    static void openTheaterCollection() throws TheaterCollection.ThColException {
        String title;
        int credits;
        ArrayList<Integer> duration= new ArrayList<>();

        System.out.println("Theater Collection opened. Type exit to leave");
        System.out.println();


        System.out.print("Input Title: ");
        String input = scanner.nextLine();
        if(!exit.equals(input)){
            title = input;

        }
        else return;

        System.out.print("Input Duration of credits: ");
        input = scanner.nextLine();
        if(!exit.equals(input)){
            credits = Integer.parseInt(input);

        }
        else return;

        while (true){
            System.out.print("Input duration for theatre show "+duration.size()+ ": ");
            input = scanner.nextLine();
            if(!exit.equals(input)) duration.add(Integer.parseInt(input));
            else break;
        }
        interAr.add(new TheaterCollection(title,credits,duration));
    }

    private static void openMasterCollection() throws IOException {
        System.out.println("Master Collection opened:");
        for (CollectionMethods collectionMethods : interAr) {
            System.out.println(collectionMethods);
        }
        System.out.println();
        sameFunc();
        System.out.println();
        sameCollection();
        
    }
    private static void sameFunc(){

        var sameFuncMap = new HashMap<Double, ArrayList<CollectionMethods>>();
        double key;

        for (CollectionMethods collectionMethods : interAr) {
            key = collectionMethods.avgShowLength();

            if (sameFuncMap.containsKey(key)) {
                sameFuncMap.get(key).add(collectionMethods);
            } else {
                var list = new ArrayList<CollectionMethods>();
                list.add(collectionMethods);
                sameFuncMap.put(key, list);
            }

        }

        System.out.println("Same Functional methods results: " + "\n");

        for (Map.Entry entry: sameFuncMap.entrySet()) System.out.println(entry);
    }
    private static void sameCollection() throws IOException {
        ArrayList<FilmCollection> films = new ArrayList<>();
        ArrayList<TheaterCollection> theaters = new ArrayList<>();

        for (CollectionMethods collectionMethods : interAr) {
            if (collectionMethods.getClass() == FilmCollection.class) {
                films.add((FilmCollection) collectionMethods);
            }
            if (collectionMethods.getClass() == TheaterCollection.class) {
                theaters.add((TheaterCollection) collectionMethods);
            }
        }
        System.out.println("Collection of films:");
        System.out.println(films + "\n");
        System.out.println("Collection of theaters:");
        System.out.println(theaters + "\n");
    }

    private static void streamTest() throws IOException {
        CollectionMethods fromCol;
        CollectionMethods toCol;

        Random rand = new Random();
        if(interAr.size() != 0)
            fromCol = interAr.get(rand.nextInt(interAr.size()));
        else throw new RuntimeException("Collections is empty");

        //Byte stream
        ByteArrayOutputStream outputStream;
        InputStream inputStream;

        //Char stream
        CharArrayWriter writer;
        Reader reader;

        System.out.println("/////////////////INPUT AND OUTPUT/////////////////");

        //////////BYTE//////////
        System.out.println("\u001B[32m" + "Byte Stream:"+"\u001B[0m");
        System.out.println("\tSended Collection:");
        System.out.println("\t  " + fromCol + "\n");
        outputStream = new ByteArrayOutputStream();
        StreamMethods.outputCollectionMethods(fromCol, outputStream);

        inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        try {
            toCol = StreamMethods.inputCollectionMethods(inputStream);
        } catch (TheaterCollection.ThColException | FilmCollection.FilmColException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\tRecieved Collection: ");
        System.out.println("\t  " +toCol + "\n");
        System.out.print("\tRecieved == Sended? \t ");
        System.out.println("\t  " +fromCol.equals(toCol) + "\n");

        if(interAr.size() != 0)
            fromCol = interAr.get(rand.nextInt(interAr.size()));
        else throw new RuntimeException("Collections is empty");

        /////////CHAR///////////
        System.out.println("\u001B[32m" + "Char Stream: " + "\u001B[0m");
        System.out.println("\tSended Collection:");
        System.out.println("\t  " + fromCol + "\n");
        writer = new CharArrayWriter();
        StreamMethods.writeColMethods(fromCol, writer);
        reader = new CharArrayReader(writer.toCharArray());
        try {
            toCol = StreamMethods.readColMethods(reader);
        } catch (FilmCollection.FilmColException | TheaterCollection.ThColException e){
            throw new RuntimeException(e);
        }
        System.out.println("\tRecieved Collection: ");
        System.out.println("\t  " + toCol + "\n");
        System.out.print("\tRecieved == Sended? \t ");
        System.out.println("\t  " + toCol.equals(fromCol) + "\n");

        if(interAr.size() != 0)
            fromCol = interAr.get(rand.nextInt(interAr.size()));
        else throw new RuntimeException("Collections is empty");

        ////////Serialize/Deserialize///////
        System.out.println("\u001B[32m" + "Serialize and deserialize: " +  "\u001B[0m");
        System.out.println("\tSended Collection:");
        System.out.println("\t  " + fromCol + "\n");
        outputStream = new ByteArrayOutputStream();
        StreamMethods.serializeColMethods(fromCol, outputStream);
        inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        toCol = StreamMethods.deserializeColMethods(inputStream);
        System.out.println("\tRecieved Collection: ");
        System.out.println("\t  " + toCol + "\n");
        System.out.print("\tRecieved == Sended? \t ");
        System.out.println("\t  " + toCol.equals(fromCol));
    }
    private static void saveToFile() {

        try {
            FileOutputStream fileOut = new FileOutputStream("Saved_Collections.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(interAr);
            objectOut.close();
            fileOut.close();
            System.out.println("\u001B[32m" + "Succefully saved" +  "\u001B[0m");
        }catch (IOException e){
            throw new RuntimeException("Saving error");
        }

    }
    private static void loadFromFile(){
        try {
            FileInputStream fileIn = new FileInputStream("Saved_Collections.bin");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            interAr = (ArrayList<CollectionMethods>) objectIn.readObject();
            fileIn.close();
            objectIn.close();
            System.out.println("\u001B[32m" + "Succefully loaded" +  "\u001B[0m");
        }catch (IOException e){
            throw new RuntimeException("Saving error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void openThreads(){
        Menu.menu();
    }
}
