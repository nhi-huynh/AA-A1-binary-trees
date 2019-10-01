

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class TimeAnalysis
{	
	String dataFile = "C:\\Users\\uyenn\\eclipse-workspace-oxygen\\AA-A1\\src\\BSP_combined.txt";
	
	protected SequentialRepresentation<String> seqTree100 = new SequentialRepresentation<String>(); 
	protected SequentialRepresentation<String> seqTree1000 = new SequentialRepresentation<String>(); 
	protected SequentialRepresentation<String> seqTree2000 = new SequentialRepresentation<String>(); 
	
	protected LinkedRepresentation<String> linkedTree100 = new LinkedRepresentation<String>();  
	protected LinkedRepresentation<String> linkedTree1000 = new LinkedRepresentation<String>();  
	protected LinkedRepresentation<String> linkedTree2000 = new LinkedRepresentation<String>();  
	
	//Constructor taking fileName as parameter
	public TimeAnalysis(String fileName) {
		this.dataFile = fileName;
	} 
	
	//Empty constructor
	public TimeAnalysis() {
		
	} 
	
	//Measure time taken to split nodes n times in a BSP Tree 
	public void timeSplitNodes(BSPTree<String> tree, int times) {
		BufferedReader csvReader;
		String row = null;
		int count = 0;
		long startTime;
		long endTime;
		double estimatedTime;
		
		try {
			//Read BSP_combined.txt
			csvReader = new BufferedReader(new FileReader(this.dataFile));
			row = csvReader.readLine();
			
			//Start counting time
			startTime = System.nanoTime();
			
			//Set the first string as root node
			tree.setRootNode(row);
			
			//Continue to split nodes n times
			while (count <= times) {
				row = csvReader.readLine();
				
				if (row != null) {
					//System.out.println("Spliting " + String.valueOf(count) + "th node :" + row);
				    
					String[] data = row.split(" ");
					String srcLabel = data[0];
					String leftChild = data[1];
					String rightChild = data[2];
					
					tree.splitNode(srcLabel, leftChild, rightChild); 
				}
				count += 1;
			}
			
			//Record endtime and compute estimated time
			endTime = System.nanoTime();
			estimatedTime = ((double) (endTime - startTime))/Math.pow(10, 9);
			
			//Print result and close the file reader
			System.out.println("\nTime taken to split " + (String.valueOf(times) + " times = " + estimatedTime + " sec " ));
			csvReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	} 
	
	public void timeFindNode(BSPTree<String> tree, String nodeLabel) {
		long startTime;
		long endTime;
		double estimatedTime;
		
		startTime = System.nanoTime();
		
		tree.findNode(nodeLabel);
		
		endTime = System.nanoTime();
		
		estimatedTime = ((double) (endTime - startTime))/Math.pow(10, 9);
		
		System.out.println("Time taken to search for node " + nodeLabel + " is = " + estimatedTime + " sec " );
	}
	
	public void timeFindChildrenNode(BSPTree<String> tree, String nodeLabel) {
		long startTime;
		long endTime;
		double estimatedTime;
		
		startTime = System.nanoTime();
		
		tree.findChildren(nodeLabel);
		
		endTime = System.nanoTime();
		
		estimatedTime = ((double) (endTime - startTime))/Math.pow(10, 9);
		
		System.out.println("Time taken to search for children node of node " + nodeLabel + " is = " + estimatedTime + " sec " );
	}
	
	public void timeFindParentNode(BSPTree<String> tree, String nodeLabel) {
		long startTime;
		long endTime;
		double estimatedTime;
		
		startTime = System.nanoTime();
		
		tree.findParent(nodeLabel);
		
		endTime = System.nanoTime();
		
		estimatedTime = ((double) (endTime - startTime))/Math.pow(10, 9);
		
		System.out.println("Time taken to search for parent node of node " + nodeLabel + " is = " + estimatedTime + " sec " );
	}
	
	public void timePreorder(BSPTree<String> tree) {
		long startTime;
		long endTime;
		double estimatedTime;
		
		//PrintWriter writer = new PrintWriter(System.out);
		
		startTime = System.nanoTime();
		
		tree.printInPreorder(null);
		
		endTime = System.nanoTime();
		
		estimatedTime = ((double) (endTime - startTime))/Math.pow(10, 9);
		
		System.out.println("Time taken to traverse preorder = " + estimatedTime + " sec " );
	}
	
	public void timeInorder(BSPTree<String> tree) {
		long startTime;
		long endTime;
		double estimatedTime;
		
		PrintWriter writer = new PrintWriter(System.out);
		
		startTime = System.nanoTime();
		
		tree.printInInorder(null);
		
		endTime = System.nanoTime();
		
		estimatedTime = ((double) (endTime - startTime))/Math.pow(10, 9);
		
		System.out.println("Time taken to traverse inorder = " + estimatedTime + " sec " );
	}
	
	public void timePostorder(BSPTree<String> tree) {
		long startTime;
		long endTime;
		double estimatedTime;
		
		PrintWriter writer = new PrintWriter(System.out);
		
		startTime = System.nanoTime();
		
		tree.printInPostorder(null);
		
		endTime = System.nanoTime();
		
		estimatedTime = ((double) (endTime - startTime))/Math.pow(10, 9);
		
		System.out.println("Time taken to traverse postorder = " + estimatedTime + " sec " );
	}
	

	public void reportSplitNodes() {
		System.out.println("\nSpliting nodes for sequential trees");
		timeSplitNodes(seqTree100, 100);
		timeSplitNodes(seqTree1000, 1000);
		timeSplitNodes(seqTree2000, 2000);
		
		System.out.println("\nSpliting nodes for linked trees");
		timeSplitNodes(linkedTree100, 100);
		timeSplitNodes(linkedTree1000, 1000);
		timeSplitNodes(linkedTree2000, 2000);
	}
	
	public void reportFindNode() {
		System.out.println("\nFinding node, children, parent (node 1020) for Sequential trees 100");
		timeFindNode(seqTree100, "1020");
		timeFindChildrenNode(seqTree100, "1020");
		timeFindParentNode(seqTree100, "1020");
		
		System.out.println("\nFinding node, children, parent (node 1020) for linked trees 100");
		timeFindNode(linkedTree100, "1020");
		timeFindChildrenNode(linkedTree100, "1020");
		timeFindParentNode(linkedTree100, "1020");
		
		System.out.println("\nFinding node, children, parent (node 3110) for Sequential trees 1000");
		timeFindNode(seqTree1000, "3110");
		timeFindChildrenNode(seqTree1000, "3110");
		timeFindParentNode(seqTree1000, "3110");
		
		System.out.println("\nFinding node, children, parent (node 3110) for Linked trees 1000");
		timeFindNode(linkedTree1000, "3110");
		timeFindChildrenNode(linkedTree1000, "3110");
		timeFindParentNode(linkedTree1000, "3110");

		System.out.println("\nFinding node, children, parent (node 3960) for Sequential trees 2000");
		timeFindNode(seqTree2000, "3960");
		timeFindChildrenNode(seqTree2000, "3960");
		timeFindParentNode(seqTree2000, "3960");
		
		System.out.println("\nFinding node, children, parent (node 3960) for Linked trees 2000");
		timeFindNode(linkedTree2000, "3960");
		timeFindChildrenNode(linkedTree2000, "3960");
		timeFindParentNode(linkedTree2000, "3960");
	
	}
	
	
	public void reportTraverseNodes() {
		System.out.println("\nInorder traversal for sequential trees 100, 1000, 2000");
		timeInorder(seqTree100);
		timeInorder(seqTree1000);
		timeInorder(seqTree2000);
		
		System.out.println("\nInorder traversal for linked trees 100, 1000, 2000");
		timeInorder(linkedTree100);
		timeInorder(linkedTree1000);
		timeInorder(linkedTree2000);
		
		System.out.println("\nPostorder traversal for sequential trees 100, 1000, 2000");
		timePostorder(seqTree100);
		timePostorder(seqTree1000);
		timePostorder(seqTree2000);
		
		System.out.println("\nPostorder traversal for linked trees 100, 1000, 2000");
		timePostorder(linkedTree100);
		timePostorder(linkedTree1000);
		timePostorder(linkedTree2000);
		
		System.out.println("\nPreorder traversal for sequential trees 100, 1000, 2000");
		timePreorder(seqTree100);
		timePreorder(seqTree1000);
		timePreorder(seqTree2000);
		
		System.out.println("\nPreorder traversal for linked trees 100, 1000, 2000");
		timePreorder(linkedTree100);
		timePreorder(linkedTree1000);
		timePreorder(linkedTree2000);
	}
	
	public static void main(String[] args) {
	       
        //Construct a TimeAnalysis object with BSP_combined.txt as the default fileName
        TimeAnalysis analyser = new TimeAnalysis();
        
        //Measure time taken to split nodes 
        analyser.reportSplitNodes();
         
        //Measure time taken to find nodes 
        analyser.reportFindNode();
        
        //Measure time taken to traverse nodes 
        analyser.reportTraverseNodes();

    } 
} 
