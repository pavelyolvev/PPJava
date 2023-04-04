package ThreadsLab5;

import Supporting.CollectionMethods;

import java.util.Scanner;

public class Menu{
    static Scanner scanner = new Scanner(System.in);

    public static void menu(){
        char inp;

        while (true){

            System.out.println("1 - task 1 \t 2 - task2 \t 3 - task3 \t 0 - exit");
            inp = scanner.nextLine().charAt(0);
            try {
                switch (inp){
                    case '1' ->task1();
                    case '2' ->task2();
                    case '3' ->task3();
                    case '0' ->{return;}
                    default -> {
                        continue;
                    }
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    static void task1(){
        System.out.println("\u001B[32m"+"Task 1: 2 Classes extends Thread \n"+
                           "Threads work without synchronization \n"+"\u001B[0m");

        CollectionMethods task1 = new ArrayContainer(10);


        Thread thread = new Thread(new WriteThread(task1));
        thread.start();

        Thread thread1 = new Thread(new ReadThread(task1));
        thread1.start();

        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    static void task2(){
        System.out.println("\u001B[32m"+"Task 2: 2 Classes implements Runnable with Synchronizer \n" +
                            "Threads synchronized to write in format write-read-write-read...\n"+"\u001B[0m");
        Synchronizer task2 = new Synchronizer(new ArrayContainer(10));

        Thread thread = new Thread(new WriteRunnable(task2));
        thread.start();

        Thread thread1 = new Thread(new ReadRunnable(task2));
        thread1.start();

        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
    static void task3(){
        System.out.println("\u001B[32m"+"Task 3: Methods from interface synchronization to safely use in multithread"+"\u001B[0m");
        ArrayContainer task3 = new ArrayContainer(10);
        CollectionMethods syncedArr = Supporting.StreamMethods.synchedColMeth(task3);

        Thread thread = new Thread(new WriteThread(syncedArr));
        Thread thread1 = new Thread(new ReadThread(syncedArr));

        thread.start();
        thread1.start();

        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}