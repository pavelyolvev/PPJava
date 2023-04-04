package ThreadsLab5;

import java.util.Random;

public class WriteRunnable implements Runnable{
    Random rand =new Random();
    Synchronizer sync;
    WriteRunnable(Synchronizer sync){
        this.sync=sync;
    }

    @Override
    public void run(){
        Random rand = new Random();
        //ColMethodsSyncer syncer = new ColMethodsSyncer(col);
        for (int i = 0; i < sync.getColSize(); i++){
            try {
                sync.write(100 - rand.nextInt(99));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //System.out.println("////////////////////"+sync.getCurInd());
        }

    }
}
