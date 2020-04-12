package com.example;

import java.util.Queue;

public class ConsumerObject {


    int BellySum;

    public ConsumerObject() {
        BellySum = 0;
    }

    public synchronized void SynchronizedConsume(Queue<Integer> a) {
        if (!a.isEmpty()) {
            BellySum =+ a.poll();
        }
    }

    public void Consume(Queue<Integer> a) {
        if (!a.isEmpty()) {
            BellySum =+ a.poll();
        }
    }

    public int getBellySum() {
        return BellySum;
    }
}
