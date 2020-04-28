package RockPaperScissors;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;import akka.actor.UntypedAbstractActor;

import java.util.LinkedList;
import java.util.Queue;

public class RockPaperScissors extends UntypedAbstractActor {
        Queue<rps2> checker = new LinkedList<rps2>();
        public RPS RPSToken;
        int gamesplayed = 0;
        private ActorRef actorRefB;
        RPS[] a = new RPS[2];
        int check = 0;
        int check2 = 0;
        RPS ref1;
        RPS ref2;
        ActorRef actor1;
        ActorRef actor2;
        @Override
        public void onReceive(Object message) throws Exception {
            if(message instanceof rps2) {
                checker.add((rps2) message);
                chooseWinner();
            }
            if(message instanceof ActorRef){
                if(check == 0 ){
                    actor1 = (ActorRef) message;
                    check++;
                }
                if(check == 1){
                    actor2 = (ActorRef) message;
                }
            }
            if(message.toString().equals("Play")){
                RPS RPSToken = new RPS();
                this.RPSToken = RPSToken;
                System.out.println(self().path().name() + " Has Played");
                play();

            }

        }

        public void pick1() {
            actor1.tell(RPSToken.pick(), self());
        }
         public void pick2() {
            actor2.tell(RPSToken.pick(), self());
         }
        public RPS GetChoice() {
            return RPSToken;
        }

        public void chooseWinner() {
            if(checker.size()>2) {
                RPSToken.WIN(checker.poll(), checker.poll());
                PrintWin();
            }
        }

        public void PrintWin() {
            System.out.println(RPSToken.GetWin());
        }


        public void play() throws InterruptedException {
            for(int i = 0; i < 10; i++) {
                pick1();
                pick2();
            }



        }



}


