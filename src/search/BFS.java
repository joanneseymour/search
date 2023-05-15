package search;
import java.util.ArrayList;


public class BFS {
    static GNode goal = GSearch.goal;
    static ArrayList<GNode> fifoFrontier = new ArrayList<GNode>();
    static ArrayList<GNode> explored = new ArrayList<GNode>();
    static ArrayList<GNode> solution = new ArrayList<GNode>();
    static GNode child;
    static ArrayList<GNode> children;
    static int numberOfChildren;
    static BusRoutesGraph busRoutesGraph = new BusRoutesGraph();
    static GNode gNodeBeingChecked = busRoutesGraph.home;



    public static void main(String[] args) {
        fifoFrontier.add(gNodeBeingChecked);
        if (GSearch.isGoal(gNodeBeingChecked)) {
            System.out.println("Goal reached! Solution:");
        }
            gNodeBeingChecked = fifoFrontier.get(0);
            children = gNodeBeingChecked.children;
            numberOfChildren = children.size();
            while ((fifoFrontier.size() > 0) && (!solution.contains(goal))) {
                System.out.println("In while loop. gNodeBeingChecked is " + gNodeBeingChecked.place);
                System.out.println("");
                System.out.println("Removing " + fifoFrontier.get(0).place + " from fifoFrontier and adding it to explored");
                gNodeBeingChecked = fifoFrontier.remove(0);
                //explored.add(gNodeBeingChecked);
                GSearch.displayFrontierExplored(fifoFrontier, explored);
                System.out.println(gNodeBeingChecked.place + " has " + children.size()
                        + " children. Adding all its children to fifoFrontier.");
                fifoFrontier = GSearch.expandToFrontier(gNodeBeingChecked, children);
                System.out.println("Back in bfs");
              
                //	EXPANDTOFRONTIER SHOULD RETURN BOTH EXPLORED AND FRONTIER. EXPLORED IS EMPTY ONCE WE'RE BACK IN BFS. MAYBE MAKE EXPLORED AND FRONTIER SETS BELONG TO SOMEWHERE ELSE?
              
                GSearch.displayFrontierExplored(fifoFrontier, explored);
                //gNodeBeingChecked = fifoFrontier.get(0);
            }
    }
    
}
