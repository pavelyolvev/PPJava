package ThreadsLab5;

import Supporting.CollectionMethods;

import java.util.Random;

public class WriteThread extends Thread{
    Random rand =new Random();

    CollectionMethods col;
    WriteThread(CollectionMethods col){
        this.col=col;
    }

    @Override
    public void run(){
        for (int i = 0; i < col.getSize(); i++){
            col.setElement(i,100 - rand.nextInt(99));

        }
    }
}
