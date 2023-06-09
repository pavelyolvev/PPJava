import java.util.*;

public class Main {
    static ArrayList<CollectionMethods> interAr = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String exit = "exit";



    public static void menu() throws FilmCollection.FilmColException, TheaterCollection.ThColException {
        char input;

        while (true) {
            System.out.println();
            System.out.println("1 -- Film Collection \t 2 -- Theater Collection \t 3 -- Master Collection \t 0 -- Exit");
            input = scanner.nextLine().charAt(0);

            switch (input) {
                case '1' -> openFilmCollection();
                case '2' -> openTheaterCollection();
                case '3' -> openMasterCollection();
                case '0' -> {
                    System.exit(0);
                    return;
                }

            }
        }
    }


    static public void main(String[] args) throws FilmCollection.FilmColException, TheaterCollection.ThColException {
        menu();

    }
    static void openFilmCollection() throws FilmCollection.FilmColException {
        String title = null;
        int credits = -1;
        ArrayList<Integer> duration = new ArrayList<>();


        System.out.println("Film Collection opened. Type exit to leave");
        System.out.println();

        System.out.print("Input Title: ");
        String input = scanner.nextLine();
        if (!exit.equals(input)) {
            title = input;

        }

        System.out.print("Input Duration of credits: ");
        input = scanner.nextLine();
        if (!exit.equals(input))
            credits = Integer.parseInt(input);


        while (true) {
            System.out.print("Input duration for Film " + (duration.size() + 1) + ": ");
            input = scanner.nextLine();
            if (!exit.equals(input)) duration.add(Integer.parseInt(input));
            else break;
        }

        if (credits == -1 || duration.size() == 0 || title == null)
            throw new FilmCollection.FilmColException("Not all data is entered");
        interAr.add(new FilmCollection(title, credits, duration));

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
            System.out.print("Input duration for theatre show "+(duration.size()+1)+ ": ");
            input = scanner.nextLine();
            if(!exit.equals(input))
                duration.add(Integer.parseInt(input));
            else break;
        }
            interAr.add(new TheaterCollection(title,credits,duration));

    }

    private static void openMasterCollection() {
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

        System.out.println("Same Functional methods results: ");
        System.out.println();

        for (Map.Entry entry: sameFuncMap.entrySet()) System.out.println(entry);
    }
    private static void sameCollection(){
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
        System.out.println(films);
        System.out.println();
        System.out.println("Collection of theaters:");
        System.out.println(theaters);
        System.out.println();
    }
}
