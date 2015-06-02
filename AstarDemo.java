/*
Programmer: Xinghe Lu
Date: 03/16/2014
Purpose: this program allows the user to specify the start node and goal node
then the program will run the A* to find a path from start node and goal node 
and print the path out (if one is found) and the map.
*/
import java.util.Scanner;

public class AstarDemo
{
  public static void main(String [] args)
  {
	World w = new World(10);//World takes an integer as parameter which is the percentage of the blocks
	// of the environment the default percentage of the blocks is 10%. You can change it if you want. 
	// 40% will be the best in my opinion. 
	Scanner k = new Scanner(System.in);
	int x,y;
	w.printMap();
	System.out.println("");
	System.out.println("' --- unvisited node");
	System.out.println("* --- a block\n");
	
	while(w.getStart()==null)//set start
	{
		System.out.print("Set x of start: ");
		x = k.nextInt();
		System.out.print("Set y of start: ");
		y = k.nextInt();
		w.setStart(x,y);
		System.out.print("\n");
	}

	while(w.getGoal()==null)//set goal
	{
		System.out.print("Set x of goal: ");
		x = k.nextInt();
		System.out.print("Set y of goal: ");
		y = k.nextInt();
		w.setGoal(x,y);
		System.out.println("\n");
	}
	
	w.printMap();//print the map to show the positions of start node and goal node
	System.out.println("");
	System.out.println("S --- start node");
	System.out.println("G --- goal node\n");
	System.out.println("Start Node:("+w.getStart().getX()+","+w.getStart().getY()+")");//print the coordinate of start node
	System.out.println("Goal Node:("+w.getGoal().getX()+","+w.getGoal().getY()+")");//print the coordinate of goal node
	System.out.println("");
	
	w.aStar();//run A* algorithm to find a path from start node to goal node
	
	System.out.println("\nX --- the node is on the closed set");
	System.out.println("O --- the node is on the open set");
	System.out.println("@ --- the path");
  }
}