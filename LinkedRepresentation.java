import java.io.PrintWriter;


/**
 * Linked Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016. 
 * @author Yongli Ren, 2019.
 */

class Node<T> 
{ 
    private T label; 
    private Node<T> left = null; 
    private Node<T> right = null; 
    private Node<T> parent = null; 
  
    public Node(T item) 
    { 
        this.label = item; 
    } 
    
    public T getLabel() 
    { 
        return this.label;
    } 
    
    public Node<T> getLeft() {
		return this.left;
	}

	public Node<T> getRight() {
		return this.right;
	}

	public Node<T> getParent() {
		return this.parent;
	}

	public void setLeft(Node<T> leftNode) 
    { 
        this.left = leftNode; 
        leftNode.setParent(this);
    } 
    

    public void setRight(Node<T> rightNode) 
    { 
        this.right = rightNode; 
        rightNode.setParent(this);
    } 
    
    public void setParent(Node<T> parentNode) 
    { 
        this.parent = parentNode; 
    } 
    
    public void detachFromTree()
    {
    	this.left = null;
    	this.right = null;
    	this.parent = null;
    }
} 



public class LinkedRepresentation<T> implements BSPTree<T> {
	private Node<T> root = null;
	
	//Facilitate traversal
	private String preorderToPrint = "";
	private String inorderToPrint = "";
	private String postorderToPrint = "";
	
	private final int maxSize = 1000001;
	
	//Keeping track of how many nodes are in the tree
	private int countNodes = 0;
	private Node[] allNodes = new Node[maxSize];
	
    /**
     * Constructs empty tree.
     */
    public LinkedRepresentation() {    
    } 
    
    @Override
    public void setRootNode(T nodeLabel) {
        this.root = new Node<T>(nodeLabel);
    } 

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        Node<T> source = searchNode(srcLabel);
    	
        if (source == null) {
        	System.err.println("Source label does not exist in the tree");
        	return;
        }
        
    	if (leftChild == null || rightChild == null) {
    		System.err.println("Leftchild and right child cannot be null");
    		return;
    	}
    	
    	//Make leftNode and rightNode
    	Node<T> leftNode = new Node<T>(leftChild);
    	Node<T> rightNode = new Node<T>(rightChild);
    	
    	//Assign the leftNode and rightNode to source node
    	source.setLeft(leftNode);
    	source.setRight(rightNode);
    	
    } 
    
    public Node<T> searchNode(T nodeLabel) {
        //Do pre-order traversal search
    	//If any node has matches nodeLabel, return that node
    	//Else, return null
    	//Reset preorderToPrint string to an empty string
    	
    	//Check if the current BST tree is empty
    	if (this.root == null) {
    		System.err.println("Searching node failed: tree root is null. Cannot pre-order traversal output");
    		return null;
    	}
    	
    	//Clear the allNodes array and its count
    	this.allNodes = new Node[maxSize];
    	this.countNodes = 0;
    	this.preorderToPrint = "";
    	
    	//Call preorder method to get the updated array of nodes
    	preorder(this.root);
    	
    	//Once allNodes are updated, we search through each item and compare it with nodeLabel
    	for (Node<T> node : this.allNodes) {
    		String existingLabel = (String) node.getLabel();
    		if (existingLabel.equals((String) nodeLabel)) {
    			return node;
    		}
    	}
    	
    	System.err.println("Node label does not exist in the tree.");
    	return null;
    } 
    
    @Override
    public boolean findNode(T nodeLabel) {
    	//Do pre-order traversal search
    	//If any node has matches nodeLabel, return true
    	//Else, return false
    	
    	//Check if the current BST tree is empty
    	if (this.root == null) {
    		System.err.println("Searching node failed: tree root is null. Cannot pre-order traversal output");
    		return false;
    	}
    	
    	//Clear the allNodes array and its count
    	this.allNodes = new Node[maxSize];
    	this.countNodes = 0;
    	this.preorderToPrint = "";
    	
    	//Call preorder method to get the updated array of nodes
    	preorder(this.root);
    	
    	//Once allNodes are updated, we search through each item and compare it with nodeLabel
    	for (Node<T> node : this.allNodes) {
    		String existingLabel = (String) node.getLabel();
    		if (existingLabel.equals((String) nodeLabel)) {
    			return true;
    		}
    	}
    	
    	System.err.println("Node label does not exist in the tree.");
        return false;
    } 

    @Override
    public String findParent(T nodeLabel) {
    	if (nodeLabel == null) {
    		System.err.println("Node label cannot be null");
    		return null;
    	}
    	
    	Node<T> source = searchNode(nodeLabel);
    	
        if (source == null) {
        	System.err.println("Node label does not exist in the tree");
        	return null;
        }
        
        Node<T> parent = source.getParent();
        
        String result = (String) source.getLabel();
		result += " ";
		result += (String) parent.getLabel();
		
//		if (parent != null) {
//			result += (String) parent.getLabel();
//		}
		
		return result;
    }

    @Override
    public String findChildren(T nodeLabel) {
    	if (nodeLabel == null) {
    		System.err.println("Node label cannot be null");
    		return null;
    	}
    	
    	Node<T> source = searchNode(nodeLabel);
    	
        if (source == null) {
        	System.err.println("Node label does not exist in the tree");
        	return null;
        }
        
        Node<T> leftChild = source.getLeft();
    	Node<T> rightChild = source.getRight();
    	
        String result = (String) source.getLabel();
		
    	//If left child exists
		if (leftChild != null) {
			result += " ";
			result += (String) leftChild.getLabel();
		}
		
		//If right child exists
		if (rightChild != null) {
			result += " ";
			result += (String) rightChild.getLabel();
		}
		
		if (leftChild == null && rightChild == null){
			result += " ";
		}
		
		return result;
    } 
    
    public void preorder(Node<T> startNode) {	//Parent, left, right
    	
    	//Break case recursion
    	if (startNode == null) {
    		return;
    	}
    	
        //Add parent node
    	this.preorderToPrint += (String) startNode.getLabel();
    	this.preorderToPrint += " "; 
    	
    	this.allNodes[this.countNodes] = startNode;
    	this.countNodes += 1;
    	
        //Process the left subtree
        preorder(startNode.getLeft()); 
  
        //Process the right subtree
        preorder(startNode.getRight()); 
    }
    
    public void inorder(Node<T> startNode) {	//Left, parent, right
    	
    	//Break case recursion
    	if (startNode == null) {
    		return;
    	}
    	
    	//Process the left subtree
        inorder(startNode.getLeft()); 
    	
        //Add parent node
    	this.inorderToPrint += (String) startNode.getLabel();
    	this.inorderToPrint += " "; 
  
        //Process the right subtree
        inorder(startNode.getRight()); 
    }
    
    
    public void postorder(Node<T> startNode) {	//Left, right, parent
    	
    	//Break case recursion
    	if (startNode == null) {
    		return;
    	}
    	
    	//Process the left subtree
        postorder(startNode.getLeft()); 
    	
        //Process the right subtree
        postorder(startNode.getRight()); 
        
        //Add parent node
    	this.postorderToPrint += (String) startNode.getLabel();
    	this.postorderToPrint += " "; 
  
        
    }
    
    
    @Override
    public void printInPreorder(PrintWriter writer) {
    	//Reset preorderToPrint string to an empty string
    	this.preorderToPrint = "";
    	
    	//Check if the current BST tree is empty
    	if (this.root == null) {
    		System.err.println("BPS tree is empty. No pre-order traversal output");
    		return;
    	}
    	
    	//Call preorder method, starting from the root node
    	preorder(this.root);
    	this.preorderToPrint += '\n';
    	
    	//Print the preorderToPrint string
    	writer.append(this.preorderToPrint);
    	writer.flush();
//    	writer.close();
    } 

    @Override
    public void printInInorder(PrintWriter writer) {
    	//Reset the inorderToPrint string to an empty string
    	this.inorderToPrint = "";
    	
    	//Check if the current BST tree is empty
    	if (this.root == null) {
    		System.err.println("BPS tree is empty. No in-order traversal output");
    		return;
    	}
    	
    	//Call inorder method, starting from the root node
    	inorder(this.root);
    	
    	this.inorderToPrint += '\n';
    	
    	//Print the inorderToPrint string
    	writer.append(this.inorderToPrint);
    	writer.flush();
//    	writer.close();
    } 

    @Override
    public void printInPostorder(PrintWriter writer) {
    	//Reset the postorderToPrint string to an empty string
    	this.postorderToPrint = "";
    	
    	//Check if the current BST tree is empty
    	if (this.root == null) {
    		System.err.println("BPS tree is empty. No post-order traversal output");
    		return;
    	}
    	
    	//Call postorder method, starting from the root node
    	postorder(this.root);
    	
    	this.postorderToPrint += '\n';
    	//Print the postorderToPrint string
    	writer.append(this.postorderToPrint);
    	writer.flush();
    	writer.close();
    } 

} // end of class LinkedRepresentation
