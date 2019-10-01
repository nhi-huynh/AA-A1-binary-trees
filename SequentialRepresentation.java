import java.io.PrintWriter;


/**
 * Sequential Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 * @author Yongli Ren, 2019.
 */


public class SequentialRepresentation<T> implements BSPTree<T> {
	//NOTE: BST has its root at index 1
	private final int maxSize = 4000; 
	private T[] tree = (T[]) new Object[maxSize];
	
    private T[] preOrder = (T[]) new Object[maxSize];
    int sizePreOrder = 0;
   
    private T[] inOrder = (T[]) new Object[maxSize];
    int sizeInOrder = 0;
    
    private T[] postOrder = (T[]) new Object[maxSize];
    int sizePostOrder = 0;
    
	/**
     * Constructs empty graph.
     */
    public SequentialRepresentation() {
    	for (int i = 0; i < maxSize; i++) {
    		this.tree[i] = null;
    	}
    } 

    @Override
    public void setRootNode(T nodeLabel) {
    	//NOTE: BST has its root at index 1
    	System.out.printf("Set root node to %s\n", (String) nodeLabel);
    	this.tree[1] = nodeLabel;
    } 

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
    	int srcIndex = -1;
    	
    	if (leftChild == null || rightChild == null) {
    		System.err.println("Leftchild and right child cannot be null");
    		return;
    	}
    	
    	//Find index of srcLabel 
    	//If srcLabel exists in the tree, assign its leftChild and rightChild
    	System.out.printf("Tree length is %d\n", this.tree.length);
    	
    	for(int i = 1; i < this.tree.length; i++){
    		if (this.tree[i] == null) {
    			continue;
    		}
			if (this.tree[i].equals(srcLabel)) { 
                srcIndex = i;
                System.out.printf("Found srcLabel at index %d", srcIndex);
                this.tree[2*srcIndex] = leftChild;
        		this.tree[2*srcIndex + 1] = rightChild;
        		break;
            } 
    		
    	}
    	
    	if (srcIndex < 0) {
    		System.err.println("Source label does not exist in the tree");
    	}
    }

  //Find the nodeLabel, return true if it exists
    @Override
    public boolean findNode(T nodeLabel) {
    	for(int i = 1; i < this.tree.length; i++){
    		if (this.tree[i] == null) {
    			continue;
    		}
			if (this.tree[i].equals(nodeLabel)) { 
				return true;
			} 
    		
    	}
    	return false;
    } 

    //Find the parent node of nodeLabel
    @Override
    public String findParent(T nodeLabel) {
    	int nodeIndex = -1;
		int parentIndex = -1;
		
		T parentNode = null;
		
		String result = (String) nodeLabel;
		result += " ";
		
		//NOTE: BST has its root at index 1
		//Searching for nodeLabel in the tree
    	for(int i = 1; i < this.tree.length; i++){
    		//if nodeLabel exists
    		if (this.tree[i] == null) {
    			continue;
    		}
    		if (this.tree[i].equals(nodeLabel)) { 
    			nodeIndex = i;
    			
    			//Find index of parent node
    			if (nodeIndex % 2 == 0) {
    				parentIndex = nodeIndex / 2;
    			}
    			else {
    				parentIndex = (nodeIndex - 1) / 2;
    			}
    			
    			//Accessing parentIndex
    			parentNode = this.tree[parentIndex];
    			
    			//If parent node exists
    			if (parentNode != null) {
    				result += (String) parentNode;
    			}
            } 
    	}
    	
    	//If nodeLabel does not exist
    	if (nodeIndex < 0) {
    		System.err.println("nodeLabel does not exist");
    		return null;
    	}
    	
    	return result;
    }
    
    
    //Find the child node of nodeLabel
    @Override
    public String findChildren(T nodeLabel) {
    	int nodeIndex = -1;
		int leftChildIndex = -1;
		int rightChildIndex = -1;
		
		T leftChild = null;
		T rightChild = null;
				
		String result = (String) nodeLabel;
		
    	for(int i = 1; i < this.tree.length; i++){
    		//If nodeLabel exists
    		if (this.tree[i] == null) {
    			continue;
    		}
    		if (this.tree[i].equals(nodeLabel)) { 
    			nodeIndex = i;
    			
    			//Find index of left and right child
    			leftChildIndex = 2*nodeIndex;
    			rightChildIndex = 2*nodeIndex + 1; 
    			
    			//Accessing left and right child
    			leftChild = this.tree[leftChildIndex];
    			rightChild = this.tree[rightChildIndex];
    			
    			//If left child exists
    			if (leftChild != null) {
    				result += " ";
    				result += (String) leftChild;
    			}
    			
    			//If right child exists
    			if (rightChild != null) {
    				result += " ";
    				result += (String) rightChild;
    			}
            } 
    	}
    	
    	//If nodeLabel does not exist
    	if (nodeIndex < 0) {
    		System.err.println("nodeLabel does not exist");
    		return null;
    	}
    	return result;    	
    } 
    
//    //Find the left and right children node of nodeLabel
//    public T[] findLeftRightChildren(T nodeLabel) {
//    	int nodeIndex = -1;
//		int leftChildIndex = -1;
//		int rightChildIndex = -1;
//		
//		T leftChild = null;
//		T rightChild = null;
//		
//		final int maxChildren = 2;
//		T[] leftRightChildren = (T[]) new Object[maxChildren];
//		
//		String leftResult = String.format("The left child of node {} is node ", (String) nodeLabel);
//		String rightResult = String.format("The right child of node {} is node ", (String) nodeLabel);
//		
//    	for(int i = 0; i < this.tree.length; i++){
//    		//If nodeLabel exists
//    		if (this.tree[i] == nodeLabel) { 
//    			nodeIndex = i;
//    			
//    			//Find index of left and right child
//    			leftChildIndex = 2*nodeIndex;
//    			rightChildIndex = 2*nodeIndex + 1; 
//    			
//    			//Accessing left and right child
//    			leftChild = this.tree[leftChildIndex];
//    			rightChild = this.tree[rightChildIndex];
//    			
//    			//If left child exists
//    			if (leftChild != null) {
//    				leftResult += (String) leftChild;
//    				
//    			}
//    			
//    			//If right child exists
//    			if (rightChild != null) {
//    				rightResult += (String) rightChild;
//    				
//    			}
//            } 
//    	}
//    	
//    	//If nodeLabel does not exist
//    	if (nodeIndex < 0) {
//    		System.err.println("nodeLabel does not exist");
//    	}
//    	
//    	System.out.println(leftResult);
//    	System.out.println(rightResult);
//    	
//    	leftRightChildren[0] = leftChild;
//    	leftRightChildren[1] = rightChild;
//    	
//    	return leftRightChildren;    	
//    } 
    
    
    
    public void preorder(int startIndex) {	//Parent, left, right
    	T startNode = null;
    	
    	//Avoid array out of bound exception
    	if (startIndex < maxSize) {
    		startNode = this.tree[startIndex];
    	}
    	
    	//Break case recursion
    	if (startNode == null) {
    		return;
    	}
    	
    	//Compute indexes for left and right nodes 
    	int leftIndex = 2*startIndex;
    	int rightIndex = 2*startIndex + 1;
    	
    	//Add parent node
		this.preOrder[this.sizePreOrder] = startNode; 
		this.sizePreOrder += 1;

    	//Recursion on the left subtree then right subtree
    	preorder(leftIndex);
    	preorder(rightIndex);
    }
    
    public void inorder(int startIndex) {	//Left, parent, right
    	T startNode = null;
    	
    	//Avoid array out of bound exception
    	if (startIndex < maxSize) {
    		startNode = this.tree[startIndex];
    	}
    	
    	//Break case recursion
    	if (startNode == null) {
    		return;
    	}

    	//Compute indexes for left and right nodes 
    	int leftIndex = 2*startIndex;
    	int rightIndex = 2*startIndex + 1;
    	
    	//Recursion on the left subtree 
    	inorder(leftIndex);
    	
    	//Add parent node
		this.inOrder[this.sizeInOrder] = startNode; 
		this.sizeInOrder += 1;
    	
		//Recursion on the right subtree
		inorder(rightIndex);
    }
    
    public void postorder(int startIndex) {	//Left, right, parent
    	T startNode = null;
    	
    	//Avoid array out of bound exception
    	if (startIndex < maxSize) {
    		startNode = this.tree[startIndex];
    	}
    	
    	//Break case recursion
    	if (startNode == null) {
    		return;
    	}
    	
    	//Compute indexes for left and right nodes 
    	int leftIndex = 2*startIndex;
    	int rightIndex = 2*startIndex + 1;
    	
    	//Recursion on the left subtree then right subtree
    	postorder(leftIndex);
    	postorder(rightIndex);
    	
    	//Add parent node
		this.postOrder[this.sizePostOrder] = startNode; 
		this.sizePostOrder += 1;
    }
    
    
    @Override
    public void printInPreorder(PrintWriter writer) {
    	//Reset the preOrder array before traversal
    	for (int i = 0; i < this.preOrder.length; i++) {
    		this.preOrder[i] = null;
    	}
    	
        this.sizePreOrder = 0;
        
        //Check if the current BST tree is empty
    	if (this.tree[1] == null) {
    		System.err.println("BPS tree is empty. No pre-order traversal output");
    		return;
    	}
    	
    	//Call preorder method, starting from the root node
    	preorder(1);
    	
    	//Print all items in the preOrder array
    	String toPrint = "";
    	
    	for (T node : this.preOrder) {
    		if (node == null) {
    			break;
    		}
    		toPrint += (String) node;
    		toPrint += " ";
    	}
    	System.out.printf("Preorder to print %s\n", toPrint);
    	writer.append(toPrint);
    	writer.flush();
//    	writer.close();
    }

    @Override
    public void printInInorder(PrintWriter writer) {
    	//Reset the inOrder array before traversal
    	for (int i = 0; i < this.inOrder.length; i++) {
    		this.inOrder[i] = null;
    	}

        this.sizeInOrder = 0;
        
        //Check if the current BST tree is empty
    	if (this.tree[1] == null) {
    		System.err.println("BPS tree is empty. No in-order traversal output");
    		return;
    	}
    	
    	//Call inorder method, starting from the root node
    	inorder(1);
    	
    	//Print all items in the preOrder array
    	String toPrint = "";
    	
    	for (T node : inOrder) {
    		if (node == null) {
    			break;
    		}
    		toPrint += (String) node;
    		toPrint += " ";
    	}
    	
    	System.out.printf("Inorder to print %s\n", toPrint);
    	writer.append(toPrint);
    	writer.flush();
//    	writer.close();    	
    }

    @Override
    public void printInPostorder(PrintWriter writer) {
    	//Reset the postOrder array before traversal
    	for (int i = 0; i < this.postOrder.length; i++) {
    		this.postOrder[i] = null;
    	}
        this.sizePostOrder = 0;
        
        //Check if the current BST tree is empty
    	if (this.tree[1] == null) {
    		System.err.println("BPS tree is empty. No post-order traversal output");
    		return;
    	}
    	
    	//Call postorder method, starting from the root node
    	postorder(1);
    	
    	//Print all items in the preOrder array
    	String toPrint = "";
    	
    	for (T node : postOrder) {
    		if (node == null) {
    			break;
    		}
    		toPrint += (String) node;
    		toPrint += " ";
    	}
    	
    	System.out.printf("Postorder to print %s\n", toPrint);
    	writer.append(toPrint);
    	writer.flush();
    	writer.close();
    }

} // end of class SequentialRepresentation