import java.util.ArrayList;
import java.util.Random;


public class Question2 {
	
	public static void main(String[] args) throws InterruptedException{
		
		//Takes the two arguments entered and turns them to ints
		int arraySize = 300;
				//Integer.parseInt(args[0]);
		int numberOfThreads = 25;
				//Integer.parseInt(args[1]);
		
		//Create IntList and sets the capacity to the first arguement
		IntList il = new IntList(arraySize);
		
		Random r = new Random();
		
		//Adds however many numbers specified to the IntList
		for(int i = 0; i < arraySize - 1; i++){
			il.add(r.nextInt(200));
		}
		
		//Creates number of threads specified and starts them all
		Thread[] threads = new Thread[numberOfThreads];
		for(int i = 0; i < threads.length; i++){
			threads[i] = new Thread(new primeThread(il));
			threads[i].start();
		}
		
	}

}

class IntList  {
	
	private ArrayList<Integer> prime;
	
	//Set size of arraylist to the argument
	public IntList(int size){
		prime = new ArrayList<Integer>(size);
	}
	
	public ArrayList<Integer> getArray(){
		return prime;
	}
	
	public synchronized ArrayList<Integer> add(Integer o){
		prime.add(o);
		return prime;
	}
	
	public synchronized int get(){
		int temp = prime.get(0);
		prime.remove(0);
		return temp;
	}
	
	public synchronized boolean isEmpty(){
		return prime.size() == 0;
	}

}

class primeThread implements Runnable {
	
	IntList il;
	int threadNo;
	
	primeThread(IntList il){
		this.il = il;
	}

	//Infinite loop that checks if the integer list
	//isn't empty, and then checks whether or not the number
	//that is retrieved in prime or not
	public synchronized void run() {
		while(true){
			if(!il.isEmpty()){
				int tempPrime = il.get();
				checkPrime(tempPrime);
			}
		}
	}
	
	//Method to check if the integer if prime or not
	public  boolean checkPrime(int n) {
		if (n <= 1) {
			System.out.println("Integer is not prime: "+ n);
			return false;
		}
		if (n == 2) {
			System.out.println("Integer is prime: "+ n);
			return true;
		}
		if (n % 2 == 0) {
			System.out.println("Integer is not prime: "+ n);
			return false;
		}
		for (int i = 3; i <= Math.sqrt(n) + 1; i = i + 2) {
			if (n % i == 0) {
				System.out.println("Integer is not prime: "+ n);
				return false;
			}
		}
		System.out.println("Integer is prime: "+ n);
		return true;
	}
}