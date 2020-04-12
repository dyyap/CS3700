package com.example;

import akka.actor.typed.ActorSystem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class HelloWorldApp {

    public static void main(String[] args) throws InterruptedException {
        boolean done = false;
        Queue<Integer> MainQueue = new LinkedList<>();
        long startTime = System.currentTimeMillis();
        ActorSystem<Producer.Command> ProducerSystem = ActorSystem.create(Producer.create(), "ProducerSystem");
        ActorSystem<Consumer.Command> ConsumerSystem = ActorSystem.create(Consumer.create(), "ConsumerSystem");

        ProducerSystem.tell(new Producer.Load(MainQueue)); // Loads in Queue
        ProducerSystem.tell(Producer.Run.INSTANCE);
        ProducerSystem.tell(Producer.Run.INSTANCE);
        //ProducerSystem.tell(Producer.Run.INSTANCE);
        //ProducerSystem.tell(Producer.Run.INSTANCE);
        //ProducerSystem.tell(Producer.Run.INSTANCE);
        TimeUnit.SECONDS.sleep(2);
        ConsumerSystem.tell(new Consumer.Load(MainQueue));
        ConsumerSystem.tell(Consumer.Run.INSTANCE);
        ConsumerSystem.tell(Consumer.Run.INSTANCE);
        ConsumerSystem.tell(Consumer.Run.INSTANCE);
        ConsumerSystem.tell(Consumer.Run.INSTANCE);
        ConsumerSystem.tell(Consumer.Run.INSTANCE);


            while(done == false) {
            System.out.println(MainQueue.size());
                if (MainQueue.size() == 0) {
                        System.out.println("Consumers Done in: " + (System.currentTimeMillis()-startTime));
                        ProducerSystem.tell(Producer.GracefulShutdown.INSTANCE);
                        ConsumerSystem.tell(Consumer.GracefulShutdown.INSTANCE);
                        done = true;
        }



    }








}
}
