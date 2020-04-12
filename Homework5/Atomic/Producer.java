package Atomic;

public class Producer implements Runnable {
    AtomicCounter teel;

    public Producer(AtomicCounter leet) {
        this.teel = leet;
    }

    public void run() {
        for (int i = 1; i < 100; i++) {
            teel.put(i);
        }

        teel.put(-1);
    }
}
