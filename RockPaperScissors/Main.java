package RockPaperScissors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

        public static void main(String[] args) {

            // Create the ‘actorTest’ actor system.
            final ActorSystem system = ActorSystem.create("actorTest");

            // Create the actors.
            ActorRef ActorRefA = system.actorOf(Props.create(RockPaperScissors.class), "actorA");
            ActorRef ActorRefB = system.actorOf(Props.create(RockPaperScissors.class), "actorB");
            ActorRef ActorRefC = system.actorOf(Props.create(RockPaperScissors.class), "actorC");

            // Send a message to ActorA.
            ActorRefA.tell(ActorRefB, ActorRef.noSender());
            ActorRefA.tell(ActorRefC, ActorRef.noSender());
            ActorRefB.tell(ActorRefA, ActorRef.noSender());
            ActorRefB.tell(ActorRefC, ActorRef.noSender());
            ActorRefC.tell(ActorRefA, ActorRef.noSender());
            ActorRefC.tell(ActorRefB, ActorRef.noSender());

            ActorRefA.tell("Play", ActorRef.noSender());
            ActorRefB.tell("Play", ActorRef.noSender());
            ActorRefC.tell("Play", ActorRef.noSender());
        }

    }

