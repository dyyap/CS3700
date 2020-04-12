package Atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class AtomicCounter {

    private int Capacity;
    private AtomicReferenceArray<Integer> List;
    private AtomicInteger counter;

    public AtomicCounter() {
        Capacity = 9;
        List = new AtomicReferenceArray<Integer>(10);
        counter = new AtomicInteger(0);
    }

    public boolean put(int i){
        if(counter.get() < Capacity) {
            List.set(counter.getAndIncrement(), i);
            return true;
        }
        return false;
    }


    public int take() {
        while(counter.get() >= 0) {
            return List.get(counter.decrementAndGet());
        }
        return 0;
    }
    public int atomicLength(){
        return counter.get();
    }
}
