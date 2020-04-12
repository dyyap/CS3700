package com.example;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.util.LinkedList;
import java.util.Queue;

public class Consumer extends AbstractBehavior<Consumer.Command> {

    @Override
    public Receive<Consumer.Command> createReceive() {
        return newReceiveBuilder()

                .onMessageEquals(Consumer.Increment.INSTANCE, this::onIncrement)
                .onMessageEquals(GracefulShutdown.INSTANCE, this::onGracefulShutdown)
                .onMessage(Consumer.ChangeMessage.class, this::onChangeMessage)
                .onMessageEquals(Run.INSTANCE, this::onRun)
                .onMessage(Load.class, this::onLoad)
                .build();
    }

    private Behavior<Command> onGracefulShutdown() {
        getContext().getSystem().log().info("Initiating graceful shutdown...");

        // perform graceful stop, executing cleanup before final system termination
        // behavior executing cleanup is passed as a parameter to Actor.stopped
        return Behaviors.stopped(() -> getContext().getSystem().log().info("Cleanup!"));
    }

    private Behavior<Consumer.Command> onChangeMessage(Consumer.ChangeMessage command) {
        message = command.newMessage;
        return this;
    }

    private Behavior<Consumer.Command> onRun(){
        while(IntQueue.size() > 0){
            System.out.println("woot");
            IntQueue.poll();

        }
        return this;

    }

    public static Behavior<Consumer.Command> create(){ // creates the command behaviour
        return Behaviors.setup(context -> new Consumer(context));
    }

    private Behavior<Consumer.Command> onIncrement(){ // the function returning Behaviours Doesn't need to initialize anything because it is not getting passed anything
        System.out.println(message);
        return this; //returns which behaviours
    }

    interface Command {}

    public enum Increment implements Command {
        INSTANCE
    }

    public enum GracefulShutdown implements Command {
        INSTANCE
    }
    public enum Run implements Command{
        INSTANCE
    }

    public static class ChangeMessage implements Command {
        public final String newMessage;
        //not possible to change after being constructed

        public ChangeMessage(String newMessage) {
            this.newMessage = newMessage;
        }
    }


    private Behavior<Consumer.Command> onLoad(Consumer.Load command) {
        IntQueue = command.intQueue;
        return this;
    }
    public static class Load implements Command {
        public Queue<Integer> intQueue = new LinkedList<>();

        public Load(Queue<Integer> intQueue) {
            System.out.println("woot");
            this.intQueue = intQueue;

        }
    }
    private String message = "Hello World!!!";
    private static Queue<Integer> IntQueue;

    private Consumer(ActorContext<Consumer.Command> context) {
        super(context);
    }

}
