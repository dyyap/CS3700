package Atomic;

public class Consumer implements Runnable {

    AtomicCounter leet;

    public Consumer(AtomicCounter leet) {
        this.leet = leet;
    }

    public void run(){
        try{
            while(leet.atomicLength() != 0) {
                int next = leet.take();
                System.out.println(next);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
