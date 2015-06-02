/*
Programmer: Xinghe Lu
Date: 03/16/2014
Purpose: this program used to build the environment and contains the 
A* algorithm. You can run A* right after you build the environment and 
indicate the start node and goal node
*/

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class World
{
	private Node[][] node = new Node[15][15];//15*15 tile-wolrd
	private Node startNode,goalNode,currentNode;
	ArrayList<Node> adjacencyList,openSet,closedSet;
	private double percentage;
	private int numOfBlocks;
	
	public World(int percentage)
	{
		this.percentage = (double)(percentage/100.0);
		this.initializeWorld();//initialize the environment
	}
	
	public void initializeWorld()//used to build the environment
	{
		startNode = null;
		goalNode = null;
		int k = 0;
		numOfBlocks = (int)(225*percentage);//calculate the number of the blocks
		
		for(int i=0;i<15;i++)//build the world
		{
			for(int j=0;j<15;j++)
			{	
				node[i][j] = new Node(i,j,k);
				k++;
			}
		}
		
		Random ran = new Random();
		int row, column;
		int i=0;
		
		while(i<numOfBlocks)//set up blocks
		{
			row = ran.nextInt(15);
			column = ran.nextInt(15);
			
			if(node[row][column].getStatus().equals("'"))
			{
				node[row][column].setUnpathable();
				i++;
			}
		}
	}
	
	public void printMap()//used to print out the map
	{
		for(int i=14;i>=0;i--)
		{
			for(int j=0;j<15;j++)
			{
				System.out.print(" "+node[j][i].getStatus());
			}
			System.out.println("");
		}
	}
	
	public void setStart(int x, int y)//used to set up the start node
	{
		if(node[x][y].getStatus().equals("'"))
		{
			node[x][y].setStart();
			startNode = node[x][y];
		}
		
		else
		System.out.print("This node is a block.\n");
	}
	
	public void setGoal(int x, int y)//used to set up the goal node
	{
		if(node[x][y].getStatus().equals("'"))
		{
			node[x][y].setGoal();
			goalNode = node[x][y];
		}
		else
		System.out.print("This node is a block.\n");
	}
	
	public Node getStart()//return the object of start node
	{
		return startNode;
	}
	
	public Node getGoal()//return the object of goal node
	{
		return goalNode;
	}
	
	public void adjacency(Node aNode)//used to collect the adjacent nodes of current node except block(s)
	{
		adjacencyList = new ArrayList<Node>();
		
		int x = aNode.getX();
		int y = aNode.getY();
		
		if(x+1<=14)
		{
			if(!node[x+1][y].getStatus().equals("*"))
			adjacencyList.add(node[x+1][y]);
		}
		
		if(x-1>=0)
		{
			if(!node[x-1][y].getStatus().equals("*"))
			adjacencyList.add(node[x-1][y]);
		}
		
		if(y+1<=14)
		{
			if(x+1<=14)
			{
				if(!node[x+1][y+1].getStatus().equals("*"))
				adjacencyList.add(node[x+1][y+1]);
			}
			
			
			if(x-1>=0)
			{
				if(!node[x-1][y+1].getStatus().equals("*"))
				adjacencyList.add(node[x-1][y+1]);
			}
				
			if(!node[x][y+1].getStatus().equals("*"))
			adjacencyList.add(node[x][y+1]);
		}
		
		if(y-1>=0)
		{
			if(x+1<=14)
			{
				if(!node[x+1][y-1].getStatus().equals("*"))
				adjacencyList.add(node[x+1][y-1]);
			}
				
			if(x-1>=0)
			{
				if(!node[x-1][y-1].getStatus().equals("*"))
				adjacencyList.add(node[x-1][y-1]);
			}
			
			if(!node[x][y-1].getStatus().equals("*"))
			adjacencyList.add(node[x][y-1]);
		}
	}
	
	public void aStar()//A*, the kernel of the algorithm
	{	
		openSet = new ArrayList<Node>();
		closedSet = new ArrayList<Node>();
		Node n;
		double new_g = 0.0;
		boolean emptyOpenSet = false;
		
		currentNode = startNode;//set start node to be current node, start node will the first node to be checked
		closedSet.add(currentNode);//add it to closed set
		currentNode.setClosedList();
		
		while(currentNode.getID()!=goalNode.getID()&&(!emptyOpenSet))//the loop will stop either the path is found or not
		{
			this.adjacency(currentNode);//collect the adjacent nodes of current node
			for(int i=0;i<adjacencyList.size();i++)//evaluate each adjacent node of current node
			{
				n = adjacencyList.get(i);
				
				if(this.onSet(n,closedSet))//if it is on closed set, continue
				{ }
				
				else//if it is not on closed set then we do..
				{
					if(this.onSet(n,openSet))//if it is on open set we have to evaluate it
					{
						new_g = this.compute_g(n,currentNode);//calculate the new g(x) if current node is the parent of node n
						if(new_g<n.getG())//if it is smaller than current g(x) then
						{
							n.setParent(currentNode);//set current node to be the parent of node n
							n.setG(new_g);
							n.setF();
						}
						//else do nothing
					}
					
					else//if it is not on open set
					{
						n.setParent(currentNode);//set current node to be the parent of node n
						n.setH(goalNode.getX(),goalNode.getY());//calculate h(x) of node n
						n.setG(this.compute_g(n,currentNode));//set g(x) of node n
						n.setF();//calculate f(x) of node n
						openSet.add(n);//add node n to open set
						n.setOpenList();
					}
				}
			}
			
			if(openSet.size()==0)//if the open set is empty, stop the while loop
			emptyOpenSet = true;
			
			else//if the open set is not empty, do..
			{
				currentNode = this.nodeWithLowestF();//set the node with lowest f(x) on open set to be  current node
				this.deleteNodeFromOpenSet(currentNode);//delete current node from open set
				closedSet.add(currentNode);//add current node to closed set
				currentNode.setClosedList();
			}
			
		}
		
		if(currentNode.getID()==goalNode.getID())//if current node is the goal node then a path is found
		{
			this.printMap();
			System.out.println("\n");
			System.out.println("Path found!\n");
			System.out.print("Path:");
			
			n = goalNode;
			n.setPath();
			while(n.getParent()!=null)//trace the path from goal node to start node
			{
				n.getParent().setChild(n);//set current node to be the child of its parent
				n=n.getParent();
				n.setPath();
			}
			
			n = startNode;
			while(n!=null)//trace the path from start node to goal node
			{
				System.out.print("("+n.getX() + "," + n.getY()+")");//print out the coordinate of current node
				
				if(n.getID()!=goalNode.getID())
				System.out.print("->");
				
				n=n.getChild();
			}
			
			System.out.println("\n");
		}
		
		else//if path not found, print following message
		System.out.println("No path could be found.\n");
		
		this.printMap();//print out the map with the path
	}
	
	public double compute_g(Node n, Node m)//used to compute g(x) of a node
	{
		if((n.getX()==m.getX())||(n.getY()==m.getY()))
		return (m.getG()+1.0);
		
		else
		return (m.getG()+1.4142);
	}
	
	public Node nodeWithLowestF()//used to find a node with the lowest f(x) on open set, since it stores nodes without order
	{
		Node min = openSet.get(0);
		
		for(int i=1;i<openSet.size();i++)
		{
			if(openSet.get(i).getF() < min.getF())
			min = openSet.get(i);
		}
		
		return min;
	}
	
	public void deleteNodeFromOpenSet(Node m)//used to delete a node from open set
	{
		for(int i=0;i<openSet.size();i++)
		{
			if(m.getID()==openSet.get(i).getID())
			{
				openSet.remove(i);
				i = openSet.size();
			}
		}
	}

	public boolean onSet(Node n, ArrayList<Node> m)//used to check if a node is on open set or closed set
	{
		boolean result = false;
		
		for(int i=0;i<m.size();i++)
		{
			if(n.getID()==(m.get(i).getID()))
			{
				i = m.size();
				result = true;
			}
		}
		
		return result;
	}
}