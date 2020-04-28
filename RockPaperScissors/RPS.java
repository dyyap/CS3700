package RockPaperScissors;

import java.util.Random;



public class RPS {
    int win;
    rps2 choice;
    public RPS(){
        win = 0;
    }
    public void AP(){
        win++;
    }

    public rps2 pick() {
        Random rand = new Random();
        int check = rand.nextInt(3);
        if (check == 0) {
            choice = rps2.ROCK;
            return choice;
        }
        if (check == 1) {
            choice = rps2.PAPER;
            return choice;
        }
        else{
            choice = rps2.SCISSORS;
            return choice;
        }
    }

    public void WIN(rps2 A, rps2 B) {
        if(A == rps2.ROCK){
            if(B == rps2.SCISSORS){
                win++;
            }
        }

        if(A == rps2.PAPER){
            if(B == rps2.ROCK) {
                win++;
            }
        }
        if(A == rps2.SCISSORS){
            if(B == rps2.ROCK){
               win++;
            }

        }
    }

    public rps2 WYG() {
        return choice;
    }

    public int GetWin() {
        return win;
    }



}



