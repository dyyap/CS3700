package Sieve;

import akka.actor.typed.ActorSystem;


import java.util.ArrayList;


public class MainTalker {


    public static void main(String[] args) {
        boolean done = false;
        ArrayList<Boolean> Tester = new ArrayList<>();
        Counter Count = new Counter(2);

        int n = 1000000;

        for (int i = 0; i < n; i++) {
            Tester.add(Boolean.TRUE);
        }


        ActorSystem<Checker.Command> CheckerSystem = ActorSystem.create(Checker.create(), "ProducerSystem");
        CheckerSystem.tell(new Checker.LoadAL(Tester));
        CheckerSystem.tell(new Checker.Load(Count, n));
        long startTime = System.currentTimeMillis();
        CheckerSystem.tell(Checker.Run.INSTANCE);
        CheckerSystem.tell(Checker.Run.INSTANCE);
        CheckerSystem.tell(Checker.Run.INSTANCE);
        CheckerSystem.tell(Checker.Run.INSTANCE);
        CheckerSystem.tell(Checker.Run.INSTANCE);
        CheckerSystem.tell(Checker.Run.INSTANCE);
        CheckerSystem.tell(Checker.Run.INSTANCE);
        CheckerSystem.tell(Checker.Run.INSTANCE);
        CheckerSystem.tell(Checker.Run.INSTANCE);
        CheckerSystem.tell(Checker.Run.INSTANCE);
        CheckerSystem.tell(Checker.Run.INSTANCE);
        CheckerSystem.tell(Checker.Run.INSTANCE);


        while (done == false) {
            System.out.println(Count.ShowCount());
            if (Count.ShowCount() * Count.ShowCount() > n - 1) {
                System.out.println("Following are the prime numbers ");
                System.out.println("smaller than or equal to " + n);

                for (int i = 2; i < n; i++) {
                    if (Tester.get(i) == true)
                        System.out.println(i + " ");

                    CheckerSystem.tell(Checker.GracefulShutdown.INSTANCE);
                    System.out.println("Checkers Done in: " + (System.currentTimeMillis() - startTime));

                    done = true;

                }
            }


        }
    }
}

