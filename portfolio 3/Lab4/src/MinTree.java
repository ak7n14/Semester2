public class MinTree {

    Tree tree = new Tree( 24, 
                 new Tree( 45, 
                     null , 
                     new Tree(8, null , null) ) , 
                 new Tree ( 17, 
                     new Tree (74 , null , null ) , 
                     null ) );

    public static void main(String[] args){
	MinTree mt = new MinTree();
	System.out.println("Minimum is :" + mt.findMin());
    }

    public int findMin(){
      return findMinAux(tree.getVal());
    }
    public int findMinAux(int n) {
    
    	int root, left, right;
    
    int min = -1;
    if (tree != null) {
        root = tree.getVal();
        left = minimum(tree.left());
        right = minimum(tree.right());
        if (left > -1){
            if(right > -1){
                min = Math.min(left, right);
                min = Math.min(root, min);
            }
            else{
                min = Math.min(left, root);
            }
        }
        else{
            min = root;
        }
    }
    return min;
    } 
    
    public int minimum(Tree tree){
    	if(tree.left()!=null && tree.right()!=null){
    		if (tree.left().getVal()>tree.right().getVal())
    			return tree.right().getVal();
    		else
    			return tree.left().getVal();
    	}
    	else if(tree.left()==null){
    		return tree.right().getVal();
    	}
    	else
    		return tree.left().getVal();
    	
    }
}
class Tree {

   private int val;
   private Tree left, right;

   public Tree(int val, Tree left, Tree right){
     this.val = val;
     this.left = left;
     this.right = right;
   }

   public int getVal(){
      return val;
   }

   public Tree left(){
      return left;
   }

   public Tree right(){
      return right;
   }
}