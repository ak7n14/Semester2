import java.util.ArrayList;
import java.util.Random;
 
public class Question1 {
 
    public static void main(String[] args) throws InterruptedException {
    	
    	ArrayList<Integer> rolls = new ArrayList<>();
    	
        SynchRollDie die = new SynchRollDie();
        int sumRolls = 0;
        
        //Create 5 synchrolls
        Thread[] threads = new Thread[5];
        for(int i=0; i < threads.length; i++){
        	threads[i] = new Thread(die);
        	threads[i].start();
        	threads[i].join();
        	rolls.add(die.getVal());
        }
        
        //Loop through all the rolls in the arraylist and add them to sum
        for(int i : rolls){
        	sumRolls += i;
        }
        
        System.out.println("Sum of all rolls = " + sumRolls);
    }
 
    private static class SynchRollDie extends RollDie {
    
    	//getVal method, while the method roll() is still being called
    	//Wait until it's finished then return the value of the final face
        public synchronized int getVal() {
            while (!this.ready) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    return 0;
                }
            }
            return this.face;
        }
    }
 
    private static class RollDie implements Runnable {
 
        protected boolean ready = false;
        protected int face;
 
        //Loops through a random number from 1-20 times
      	//and prints out a random number from 1-6
      	//for each loop the thread sleeps 330* the number of the loop
        public synchronized void roll(){
        	Random r = new Random();
            int face = 1;
            for (int i = 0; i < r.nextInt(20); i++) {
                face = r.nextInt(6) + 1;
                System.out.println(face);
                try {
                    Thread.sleep(i * 330);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            this.face = face;
            System.out.println("Final roll was: " + this.face);
            this.ready = true;
            this.notify();
        }
 
        public void run() {
           this.roll();
        }
    }
}