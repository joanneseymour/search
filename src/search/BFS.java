package search;
import java.util.ArrayList;

// GAOJI DOESN'T GET ADDED TO SOLUTION

// REACHGOAL IS REDUNDANT

public class BFS {
    static GNode goal = GSearch.goal;
    static ArrayList<GNode> fifoFrontier = GSearch.fifoFrontier;
    static ArrayList<GNode> explored = GSearch.explored;
    static ArrayList<GNode> solution = new ArrayList<GNode>();
    static GNode child;
    static ArrayList<GNode> children = GSearch.children;

    static GNode gNodeBeingChecked = BusRoutesGraph.home;

    public static void main(String[] args) {
    	BusRoutesGraph.buildGraph();
        fifoFrontier.add(gNodeBeingChecked);
        if (GSearch.isGoal(gNodeBeingChecked)) {
            System.out.println("Goal reached! Solution:");
        }
            gNodeBeingChecked = fifoFrontier.get(0);
            while ((fifoFrontier.size() > 0) && (!solution.contains(goal))) {
                System.out.println("In while loop.");
                System.out.println("Removing " + fifoFrontier.get(0).place + " from fifoFrontier and adding it to explored [BFS while]");
                gNodeBeingChecked = fifoFrontier.remove(0);
                children = gNodeBeingChecked.children;
                explored.add(gNodeBeingChecked);
                GSearch.displayFrontierExplored(gNodeBeingChecked, fifoFrontier, explored);
                System.out.println(gNodeBeingChecked.place + " has " + children.size()
                        + " children. Adding all its children to fifoFrontier. [BFS while]");
                fifoFrontier = GSearch.expandToFrontier(gNodeBeingChecked, children);
                if (!explored.contains(goal)) {
                    System.out.println("Back in bfs, at the end");
                    GSearch.displayFrontierExplored(gNodeBeingChecked, fifoFrontier, explored);
                } else {
                	fifoFrontier.clear();
                	System.out.println("END");
                }
                

                

                
                //gNodeBeingChecked = fifoFrontier.get(0);
            }
    }
    
}
