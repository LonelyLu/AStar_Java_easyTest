This file is a brief overview of the programs.

There are three classes: 1)Node class 2)World class 3)AstarDemo
Node class contains essential information the node needs for A*, including coordinate, status
of the node (unvisted? on closed set? on open set? a block?). AstartDemo is used to demonstrate
the path and map with the path(if the path is found). 

World(int) class contains the A* algorithm, you can run the method astar() right after you 
build the environment. it takes an integer as parameter which is the percentage of the block
of the map, the default is 10 percent, you can find a very direct route from start node to goal
node, if 40% of the nodes are blocks, it will be better to test how good the A* is, and you are more
likely to find an isolated node. Most of work is done by this class.


UML digram of World class

-Node: Node[][]
-startNode: Node
-goalNode: Node
-currentNode: Node
adjacencyList: ArrayList<Node>
openSet: ArrayList<Node>
closedSet: ArrayList<Node>
-percentage: double
-numOfClocks: int


+World(percentage:int)
+initializeWorld():void
+printMap():void
+setStart(x:int,y:int):void
+setGoal(x:int,y:int):void
+getStart():Node
+getGoal():Node
+adjacency(aNode:Node):void
+aStar():void
+compute_g(n:Node,m:Node):double
+nodeWithLowestF():Node
+deleteNodeFromOpenSet(m:Node):void
+onSet(n:Node,m:ArrayList<Node>):boolean

Node is a 2 dimensional array holds 225 objects (nodes)
startNode is the pointer points to start node
goalNode is the pointer points to goal node
currentNode is the pointer points to current node
adjacencyList is an array list collects the adjacent nodes of current node
openSet is an array list for open set
closedSet is an array list for closed set
percentage is the variable holds the percentage of blocks of the environment
numOfBlocks is the variable holds the number of blocks of the enviroment

World(percentage:int) is the constructor

initializeWorld() is used to initialize the environment and all data

printMap() is used to print out the map of the world

setStart(x:int,y:int) is used to set the coordinate of start node

setGoal(x:int,y:int) is used to set the coordinate of goal node

getStart() is used to get the address of start node

getGoal() is used to get the address of goal node

adjacency(aNode:Node) is used to find out all the adjacent nodes of current node (not including block(s))

aStar() implements A* algorithm, it will print out the path if one's found or else a message to tell the path
is not found

compute_g(n:Node,m:Node) is used to compute g(x) from start node to node n(an adjacent node of current node), through current node

nodeWithLowestF() is used to find out the node with the lowest f(x) on open set

deleteNodeFromOpenSet(m:Node) is used to delete a node from open set

onSet(n:Node,m:ArrayList<Node>) is used to check if node n is on set(open set or closed set)



Sample 1:
 * ' ' * * ' * * ' * ' ' ' ' S
 G ' ' ' * * ' ' ' ' ' ' ' ' '
 * * * * * ' ' * * * * ' * * '
 ' ' * * * ' ' * * ' * * ' * '
 ' ' * * ' ' * * ' * ' ' * * '
 * ' ' ' * ' ' ' ' ' * * ' * '
 ' ' ' * * * ' * ' ' ' ' ' ' '
 ' ' ' ' * * ' * ' * * ' ' ' '
 * * ' * ' * * ' ' ' ' ' ' * *
 * ' * * * * ' ' ' ' ' * ' ' '
 * ' ' ' ' * ' ' ' ' ' * * * *
 ' ' ' * ' ' * ' ' ' ' * ' * *
 ' * ' * ' * ' ' ' * ' ' ' ' *
 ' ' ' * ' * ' * * ' * ' * ' '
 ' ' * ' * * * ' ' ' * ' ' * *

S --- start node
G --- goal node

Start Node:(14,14)
Goal Node:(0,13)

No path could be found.

 * ' ' * * X * * X * X X X X X
 G ' ' ' * * X X X X X X X X X
 * * * * * X X * * * * X * * X
 X X * * * X X * * X * * X * X
 X X * * X X * * X * X X * * X
 * X X X * X X X X X * * X * X
 X X X * * * X * X X X X X X X
 X X X X * * X * X * * X X X X
 * * X * X * * X X X X X X * *
 * X * * * * X X X X X * X X X
 * X X X X * X X X X X * * * *
 X X X * X X * X X X X * X * *
 X * X * X * X X X * X X X X *
 X X X * X * X * * X * X * X X
 X X * X * * * X X X * X X * *

X --- the node is on the closed set
O --- the node is on the open set
@ --- the path




Sample 2

' * ' ' ' ' ' ' * * * ' ' G *
 ' * * ' ' ' * ' * ' ' ' ' ' *
 ' ' ' ' ' * * * ' ' * ' ' ' '
 ' * * ' ' ' ' * ' ' ' ' ' * '
 ' ' ' * * ' ' * ' ' ' * * ' '
 * ' * ' * * ' * * ' ' ' ' * *
 ' * ' * ' ' * * * ' * ' * ' *
 * ' * ' ' ' * * * ' * ' * * *
 ' ' ' * ' ' * * ' ' ' * ' ' '
 * * ' * ' ' ' ' * * * * * * *
 ' ' ' * * ' ' ' * * * ' * * *
 ' ' ' * * * ' ' ' ' * ' ' ' '
 * ' ' * ' * ' * ' ' ' ' * ' '
 ' * * * ' ' ' * * ' ' ' ' ' *
 S ' ' * * ' * ' * ' * ' ' ' '

S --- start node
G --- goal node

Start Node:(0,0)
Goal Node:(13,14)

 ' * O X X X X X * * * ' O X *
 ' * * X X X * X * ' ' O O X *
 ' ' O X X * * * O ' * O X O O
 O * * X X X X * ' O O X O * '
 O X X * * X X * ' O X * * ' '
 * X * X * * X * * O X X O * *
 O * X * X X * * * X * O * ' *
 * X * X X X * * * X * ' * * *
 O X X * X X * * X O O * ' ' '
 * * X * O X X X * * * * * * *
 X X X * * O O O * * * ' * * *
 X X X * * * ' ' ' ' * ' ' ' '
 * X X * ' * ' * ' ' ' ' * ' '
 X * * * ' ' ' * * ' ' ' ' ' *
 X X X * * ' * ' * ' * ' ' ' '


Path found!

Path:(0,0)->(0,1)->(1,2)->(2,3)->(2,4)->(2,5)->(2,6)->(3,7)->(4,7)->(5,6)->(6,5)->(7,5)->(8,6)->(9,7)->(9,8)->(10,9)->(10,10)->(11,11)->(12,12)->(13,13)->(13,14)

 ' * O X X X X X * * * ' O @ *
 ' * * X X X * X * ' ' O O @ *
 ' ' O X X * * * O ' * O @ O O
 O * * X X X X * ' O O @ O * '
 O X X * * X X * ' O @ * * ' '
 * X * X * * X * * O @ X O * *
 O * X * X X * * * @ * O * ' *
 * X * @ @ X * * * @ * ' * * *
 O X @ * X @ * * @ O O * ' ' '
 * * @ * O X @ @ * * * * * * *
 X X @ * * O O O * * * ' * * *
 X X @ * * * ' ' ' ' * ' ' ' '
 * @ X * ' * ' * ' ' ' ' * ' '
 @ * * * ' ' ' * * ' ' ' ' ' *
 @ X X * * ' * ' * ' * ' ' ' '

X --- the node is on the closed set
O --- the node is on the open set
@ --- the path


Note:
The route printed on map doesn't have a direction so the path (with coordinates of each node along the path)
tells the direction of the path! 