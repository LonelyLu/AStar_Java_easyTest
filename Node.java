/*
Programmer: Xinghe Lu
Date: 03/16/2014
Purpose: this is node class, which contains the coordinate and id of a node, and indicate its
status whether it is a block, unvisited node, start node, goal node, a node on open set or closed set
or a node of the path. It also contains the f, g and h where g(x) is the cost from start to 
current node and h is the heuristic value which indicates how far is the node to goal node
and f = g+h
*/
import static java.lang.Math.abs;

public class Node
{
	private int x,y,id;
	private String status;//used to depict the status of this node, like, unvisited, on closed set or open set, block etc.
	private Node parent,child;
	private double f,g,h;
 
	public Node(int a, int b, int c)
	{
	x = a;
	y = b;
	status = "'";
	parent = null;
	child = null;
	f=0;
	g=0;
	h=0;
	id = c;//id is unique so you don't need to check x and y every time
	}
 
	public int getID()
	{
		return id;
	}
 
	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
 
	public void setStart()
	{
		status = "S";
	}
 
	public void setGoal()
	{
		status = "G";
	}
 
	public void setUnpathable()
	{
		status = "*";
	}
 
	public void setClosedList()
	{
		status = "X";
	}
 
	public void setOpenList()
	{
		status = "O";
	}
 
	public void setPath()
	{
		status = "@";
	}
 
	public String getStatus()
	{
		return status;
	}
 
	public void setChild(Node a)
	{
		child = a;
	}
	
	public Node getChild()
	{
		return child;
	}
 
	public void setParent(Node b)
	{
		parent = b;
	}
	
	public Node getParent()
	{
		return parent;
	}
 
	public void setH(int x1, int y1)//calculate heuristic (Manhattan method)
	{
		h = Math.abs(x-x1) + Math.abs(y-y1);
	}
	
	public void setG(double g)
	{
		this.g = g;
	}
	
	public double getG()
	{
		return g;
	}
	
	public void setF()//calculate f
	{
		f = g+h;
	}
	
	public double getF()
	{
		return f;
	}
}