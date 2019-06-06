package Project04;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/*
 * CSCI232: project 4
 * Yueh-Chen Tsou
 * 6/03/2019
 * This program implements both Breadth First Search and Depth First Search algorithm. Both of two algorithms are used 
 * for traversing and searching a node in a graph.
 * DfS algorithm is to traverse the graph in such a way that it tries to go far from the root node. Stack is used in the
 * implementation of the DFS.
 * BFS algorithm visits the nodes level by level, so it will start with level 0, and then it moves to the next levels 
 * until the last level.
 * 
 */
// Stack is used to implement DFS algorithm
public class DFSvBFSdemo {
	   public static List<Integer> dfs(int start, LinkedList<Integer> list[])
	    {
	    	Stack<Integer> stack = new Stack<Integer>();	// First in last out
	    	List<Integer> visited = new ArrayList<>();		// store the visited vertices
	        int cur = start;
	        
	        stack.push(start);								// push the root onto the stack	 
	        
	        while(!stack.isEmpty()) {
	    	 
	     	   cur=stack.pop();								// last in first out, pop the vertex in the top of stack
	     	   Iterator dfsList = list[cur].listIterator();	// constructor to find the neighbors
	     	   if (!visited.contains(cur)){					// if the vertex hasn't visited yet
	     		   visited.add(cur);						// add the visited vertex onto the ArrayList 
	     	   
	     		   while (dfsList.hasNext())				// until doesn't exist a neighbor
	     		   {
	     			   int adj = (int) dfsList.next();		// get the neighbor
	 	                stack.add(adj);						// push the neighbor onto stack
	 	            }
	 	        }
	 	    }
	    	return visited;
	    }
	    
	   // Queue in used to implement BFS algorithm
	    public static List<Integer> bfs(int start, LinkedList<Integer> list[]) 
	    {	
	    	LinkedList<Integer> queue = new LinkedList<Integer>();// first in first out
	    	List<Integer> visited = new ArrayList<>();			// store the visited vertices
	   
	    	int cur = start;
	    	queue.add(cur);									// push the root onto the queue
	    	visited.add(cur);								
	    	while(queue.size()!=0) {
	 	 
	    		cur=queue.poll();
	    		Iterator bfsList = list[cur].listIterator();	// constructor to find a neighbor
		  
		       while (bfsList.hasNext())						// until doesn't exist a neighbor
		       {
		            int adj = (int) bfsList.next(); 			// get the neighbor
		            if (!visited.contains(adj))					// if the vertex hasn't yet been visited
		            {
		                visited.add(adj);						// add the visited vertex onto the ArrayList
		                queue.add(adj);							// push the vertex onto queue 
		            }
		        }
		    }
	    	return visited;
	    }	
	    	
	 
	    public static void main(String args[])
	    {
	    
	    	int start = 6;
	    	int vertices = 10;
	    	LinkedList<Integer>[] list= new LinkedList[vertices];
	    	
	    	for (int i = 0; i < vertices; i++)
	            list[i] = new LinkedList<Integer>();
	    	
	    	int[] node = new int[100];
	    	int edges=0;
	    	
	    	// read adjacency list from the text file "input.txt
	    	try {
	    		Scanner fileInput = new Scanner(new File("input.txt"));			
				while (fileInput.hasNext()) {
					if(fileInput.hasNextInt()) {
						node[edges] = fileInput.nextInt();
						edges++;
					}			
					else {
						break;
					}
				}
				fileInput.close();
			} catch (FileNotFoundException exc) {
				System.out.println("There was a problem opening the input file");
			}
	    	
	    	int i=0;
	    	while (i<edges) {
	    		list[node[i]].add(node[i+1]);	// add the edges onto the LinkedList
	    		i = i+2;
	    	}
	    	
/*	    	
	    	list[6].add(3);
	    	list[3].add(0);
	    	list[0].add(1);
	    	list[3].add(8);
	        list[6].add(4);
	        list[4].add(8);
	        list[4].add(9);
	        list[4].add(1);
	        list[1].add(2);
	        list[9].add(2);
	        list[6].add(7);
	        list[7].add(5);
	        list[5].add(9);    		
  */     
	       List<Integer> visitedDFS = dfs(start, list);
	       List<Integer> visitedBfs = bfs(start, list);
			
	       // output to the text file "output.txt"
	       try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
				writer.write("DFS: ");
				for(Integer v1: visitedDFS)
					writer.write(v1 + " ");
				writer.newLine();
				writer.newLine();
				writer.write("BFS: ");
				for(Integer v2: visitedBfs)
					writer.write(v2 + " ");
			
				writer.close();
			} catch (IOException exc) {
				System.out.println("There was a problem opening the input file");
			}

	    
	    }

}
