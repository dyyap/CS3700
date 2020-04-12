package Sieve;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Checker extends AbstractBehavior<Checker.Command> {


    public Checker(ActorContext<Checker.Command> context) {
        super(context);
    }

    @Override
    public Receive<Checker.Command> createReceive() {
        return newReceiveBuilder()

                .onMessageEquals(GracefulShutdown.INSTANCE, this::onGracefulShutdown)
                .onMessageEquals(Run.INSTANCE, this::onRun)
                .onMessage(LoadAL.class, this::onLoadAL)
                .onMessage(Load.class, this::onLoad)
                .build();
    }

    private Behavior<Command> onGracefulShutdown() {
        getContext().getSystem().log().info("Initiating graceful shutdown...");

        // perform graceful stop, executing cleanup before final system termination
        // behavior executing cleanup is passed as a parameter to Actor.stopped
        return Behaviors.stopped(() -> getContext().getSystem().log().info("Cleanup!"));
    }


    private Behavior<Command> onRun() {
        while(PHI.count * PHI.count < END) {
            if(BoolAL.get(PHI.count) == true)
            {
                for(int i = PHI.count * PHI.count; i < END; i += PHI.count) {
                    BoolAL.set(i, false);
                }
            }
            PHI.Increment();
        }
        return this;

    }

    public static Behavior<Command> create(){ // creates the command behaviour
        return Behaviors.setup(context -> new Checker(context));
    }



    interface Command {}



    public enum GracefulShutdown implements Command {
        INSTANCE
    }
    public enum Run implements Command{
        INSTANCE
    }

    private Behavior<Command> onLoadAL(LoadAL command) {
        BoolAL = command.boolAL;
        return this;
    }
    private Behavior<Command> onLoad(Load command) {
        PHI = command.c;
        END = command.end;
        return this;
    }

    public static class LoadAL implements Command {
        public ArrayList<Boolean> boolAL = new ArrayList<>();

        public LoadAL(ArrayList<Boolean> boolAL) {
            System.out.println("woot");
            this.boolAL = boolAL;

        }
    }

    public static class Load implements Command {
        public Counter c;
        public Integer end = 0;

        public Load(Counter phi, Integer end) {
            this.c = phi;
            this.end = end;
        }
    }
    private String message = "Hello World!!!";
    private static ArrayList<Boolean> BoolAL;
    private Integer END;
    private Counter PHI;




}
