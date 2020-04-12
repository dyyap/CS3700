package com.example;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import akka.actor.typed.javadsl.ActorContext;



public class Main extends AbstractBehavior<Main.Command> {

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(SayHello.INSTANCE, this::onSayHello) //actor doing its job
                .onMessage(ChangeMessage.class, this::onChangeMessage) //input
                .build();
    }

    private Behavior<Command> onChangeMessage(ChangeMessage command) {
        message = command.newMessage;
        return this;
    }

    public static Behavior<Command> create(){ // creates the command behaviour
        return Behaviors.setup(context -> new Main(context));
    }

    private Behavior<Command> onSayHello(){ // the function
        System.out.println(message);
        return this; //returns which behaviours
    }

    interface Command {}

    public enum SayHello implements Command{
        INSTANCE
    }

    public static class ChangeMessage implements Command {
        public final String newMessage;
        //not possible to change after being constructed
        public ChangeMessage(String newMessage) {
            this.newMessage = newMessage;
        }
    }

    private String message = "Hello World!!!";

    private Main(ActorContext<Command> context) {
        super(context);
    }

}
