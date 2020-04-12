package com.example;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import akka.actor.typed.javadsl.ActorContext;

import java.util.LinkedList;
import java.util.Queue;


public class Producer extends AbstractBehavior<Producer.Command> {

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(Increment.INSTANCE, this::onIncrement)
                .onMessageEquals(Run.INSTANCE, this::onRun)
                .onMessage(Return.class, this::OnReturn)
                .onMessage(Load.class, this::onLoad)
                .onMessage(ChangeMessage.class, this::onChangeMessage)
                .onMessageEquals(GracefulShutdown.INSTANCE, this::onGracefulShutdown)

                .build();

    }

    private Behavior<Command> onGracefulShutdown() {
        getContext().getSystem().log().info("Initiating graceful shutdown...");

        // perform graceful stop, executing cleanup before final system termination
        // behavior executing cleanup is passed as a parameter to Actor.stopped
        return Behaviors.stopped(() -> getContext().getSystem().log().info("Cleanup!"));
    }
    private Behavior<Command> onChangeMessage(ChangeMessage command) {
        message = command.newMessage;
        return this;
    }

    private Behavior<Command> OnReturn(Return command) {
        return this;
    }

    private Behavior<Producer.Command> onRun() {
        ProducerObject a = new ProducerObject();
        Keeper keeper = new Keeper();
        while (a.getHasProduced() < 100) {
            if (keeper.size() < 10) {
                keeper.add(a.Increment());
                IntQueue.add(keeper.poll());
            }

        }
        return this;
    }

    private Behavior<Command> onLoad(Load command) {
        IntQueue = command.intQueue;
        return this;
    }

    public enum Increment implements Command{
        INSTANCE
    }

    public enum Run implements Command{
        INSTANCE
    }

    public enum GracefulShutdown implements Command {
        INSTANCE
    }

    public static Behavior<Command> create(){ // creates the command behaviour
        return Behaviors.setup(context -> new Producer(context));
    }

    private Behavior<Command> onIncrement(){ // the function returning Behaviours Doesn't need to initialize anything because it is not getting passed anything
        System.out.println("I Have Incremented Testing Complete");
        return this; //returns which behaviours
    }

    interface Command {}




    public static class ChangeMessage implements Command {
        public final String newMessage;
        //not possible to change after being constructed

        public ChangeMessage(String newMessage) {
            this.newMessage = newMessage;
        }
    }



    public static class Load implements Command {
        public Queue<Integer> intQueue = new LinkedList<>();

        public Load(Queue<Integer> intQueue) {
            this.intQueue = intQueue;

        }
    }

    public static class Return implements Command{
        private Queue<Integer> Queue = new LinkedList<>();
        public Return(Queue<Integer> mainQueue) {
            mainQueue = IntQueue;
            this.Queue = mainQueue;
        }

        public Queue<Integer> Return(Queue<Integer> MainQueue) {
            MainQueue = IntQueue;
            return this.Queue;
        }

    }

    private String message = "Hello World!!!";
    static Queue<Integer> IntQueue;

    private Producer(ActorContext<Command> context) {
        super(context);
    }

}