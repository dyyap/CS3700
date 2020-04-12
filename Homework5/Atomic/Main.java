package Atomic;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        AtomicCounter leetteel = new AtomicCounter();

        Thread ap = new Thread(new Producer(leetteel));
        Thread bp = new Thread(new Producer(leetteel));
        Thread cp = new Thread(new Producer(leetteel));
        Thread dp = new Thread(new Producer(leetteel));
        Thread ep = new Thread(new Producer(leetteel));

        Thread ac = new Thread(new Consumer(leetteel));
        Thread bc = new Thread(new Consumer(leetteel));
        Thread cc = new Thread(new Consumer(leetteel));
        Thread dc= new Thread(new Consumer(leetteel));
        Thread ec = new Thread(new Consumer(leetteel));

        long Start = System.currentTimeMillis();
        ap.start();
        bp.start();
        cp.start();
        dp.start();
        ep.start();
        ac.start();
        bc.start();
        bc.join();

        System.out.println("Time: " + (System.currentTimeMillis() - Start));
    }
}
