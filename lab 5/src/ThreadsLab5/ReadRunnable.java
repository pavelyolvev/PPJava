package ThreadsLab5;

public class ReadRunnable implements Runnable{
    Synchronizer sync;

    ReadRunnable(Synchronizer sync){
        this.sync = sync;
    }
    @Override
    public void run(){
        //ColMethodsSyncer syncer = new ColMethodsSyncer(col);

        for (int i = 0; i < sync.getColSize(); i++){
            try {
                sync.read();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
