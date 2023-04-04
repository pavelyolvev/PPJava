package ThreadsLab5;

import Supporting.CollectionMethods;

public class ReadThread extends Thread{
    CollectionMethods col;
    ReadThread(CollectionMethods col){
        this.col = col;
    }
    @Override
    public void run(){
        for (int i = 0; i < col.getSize(); i++){
            System.out.println("Readed:\t" + col.getElement(i) + "\tfrom position  \t" + i);
        }
    }
}
