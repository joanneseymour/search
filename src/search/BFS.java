package search;
import java.util.ArrayList;

// CAN'T PERFORM THE LAST STAGES OF CALCULATE SOLUTION.

public class BFS {
    static GNode goal = GSearch.goal;
    static ArrayList<GNode> fifoFrontier = GSearch.fifoFrontier;
    static ArrayList<GNode> explored = GSearch.explored;
    static ArrayList<GNode> solution = new ArrayList<GNode>();
    static GNode child;
    static ArrayList<GNode> children = GSearch.children;
    static int numberOfChildren;

    static GNode gNodeBeingChecked = BusRoutesGraph.home;

    public static void main(String[] args) {
    	BusRoutesGraph.buildGraph();
    	System.out.println("Children is " + children.size());
    	System.out.println("Nancun has " + BusRoutesGraph.nan.children.size() + " children");
    	System.out.println("Nancun has " + BusRoutesGraph.nan.getChildren().size() + " getChildren");
        fifoFrontier.add(gNodeBeingChecked);
        if (GSearch.isGoal(gNodeBeingChecked)) {
            System.out.println("Goal reached! Solution:");
        }
            gNodeBeingChecked = fifoFrontier.get(0);
            System.out.println("Before while loop. gNodeBeingChecked is " + gNodeBeingChecked.place);

            while ((fifoFrontier.size() > 0) && (!solution.contains(goal))) {
                System.out.println("In while loop.");
                System.out.println("Removing " + fifoFrontier.get(0).place + " from fifoFrontier and adding it to explored");
                gNodeBeingChecked = fifoFrontier.remove(0);
                System.out.println("Before making children. gNodeBeingChecked is " + gNodeBeingChecked.place + ". It has " + gNodeBeingChecked.children.size() + " children");
                children = gNodeBeingChecked.children;
                
                for (int i = 0; i < children.size(); i++) {
                	System.out.println("Child " + i + " of " + gNodeBeingChecked.place + " is " + gNodeBeingChecked.children.get(i).place);
                }
                numberOfChildren = children.size();

                explored.add(gNodeBeingChecked);
                GSearch.displayFrontierExplored(gNodeBeingChecked, fifoFrontier, explored);
                System.out.println(gNodeBeingChecked.place + " has " + numberOfChildren
                        + " children. Adding all its children to fifoFrontier.");
                fifoFrontier = GSearch.expandToFrontier(gNodeBeingChecked, children);
                System.out.println("Back in bfs");             
                GSearch.displayFrontierExplored(gNodeBeingChecked, fifoFrontier, explored);
                //gNodeBeingChecked = fifoFrontier.get(0);
            }
    }
    
}
