package com.example;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.util.Queue;

public class QueueActor extends AbstractBehavior<QueueActor.Command> {



    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()

                .onMessageEquals(QueueActor.Increment.INSTANCE, this::onIncrement)
                .onMessage(QueueActor.GiveSystem.class, this::onGiveSystem)
                .onMessage(QueueActor.ChangeMessage.class, this::onChangeMessage)
                .onMessage(QueueActor.Run.class, this::onRun)
                .build();
    }


    private Behavior<Command> onGiveSystem(GiveSystem command2) {
        ConsumerSystem = command2.newConsumerSystem;
        return this;
    }

    private Behavior<Command> onChangeMessage(ChangeMessage command) {
        message = command.newMessage;
        return this;
    }




    private Behavior<QueueActor.Command> onRun(QueueActor.Run command){
        ProducerObject a = new ProducerObject();
        Keeper keeper = new Keeper();
        while (a.getHasProduced() < 100) {
            if (keeper.size() < 10) {
                keeper.add(a.Increment());

            }


        }
        return this;

    }

    public static Behavior<QueueActor.Command> create(){ // creates the command behaviour
        return Behaviors.setup(QueueActor :: new);
    }

    private Behavior<QueueActor.Command> onIncrement(){ // the function returning Behaviours Doesn't need to initialize anything because it is not getting passed anything
        System.out.println(message);
        return this; //returns which behaviours
    }
    public enum Increment implements QueueActor.Command {
        INSTANCE
    }

    interface Command {}




    public static class ChangeMessage implements QueueActor.Command {
        public final String newMessage;
        //not possible to change after being constructed

        public ChangeMessage(String newMessage) {
            this.newMessage = newMessage;
        }
    }

    public static class Run implements QueueActor.Command {
        public final Queue<Integer> IntQueue;

        public Run(Queue<Integer> IntQueue) {
            this.IntQueue = IntQueue;

        }
    }
    public static class GiveSystem implements QueueActor.Command, Consumer.Command {
        public ActorSystem<Consumer.Command> newConsumerSystem = ActorSystem.create(Consumer.create(), "ConsumerSystem");;

        public GiveSystem(ActorSystem<Consumer.Command> newConsumerSystem) {
            System.out.println("work");
            this.newConsumerSystem = newConsumerSystem;
        }
    }


    private String message = "Hello World!!!";
    ActorSystem<Consumer.Command> ConsumerSystem;
    private QueueActor(ActorContext<Command> context) {
        super(context);
    }

}
