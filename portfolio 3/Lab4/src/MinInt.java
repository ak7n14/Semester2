public class MinInt {

    int[] arr = {24,52,74,9,34,23,64,34};

    public static void main(String[] args){
	MinInt m = new MinInt();
	System.out.println("Minimum is :" + m.findMin());
    }
    public int findMin(){
    	return findMinAux(0);
    	
    }
    
 
    private int findMinAux(int index) {
    	    int min;
    	    if (index == arr.length - 1)
    	    	return arr[index];
    	    	min = findMinAux(index + 1);
    	    if (min < arr[index])
    	    	return  min;
    	    else
    	    	return arr[index];

    	    }
}
    
    



