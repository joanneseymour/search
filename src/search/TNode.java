package search;
import java.util.ArrayList;
import java.util.Stack;

// treeNode is already a thing in Java, so I'm calling this a tNode

public class TNode {
    String place;
    int id;
    int weight;
    static int level;
    //int depth;
    TNode parent;
    //static Stack<TNode> adjNodes; // adjNodes will go into frontier (a stack) so let's keep them the same data structure
    TNode child;
    TNode rSib;
    //TNode tNodeBeingChecked;

    // moved over from dls.java
    //static BusRoutesTree busRoutesTree = new BusRoutesTree();
    static Stack<TNode> frontier = new Stack<TNode>();
    
    static Stack<TNode> adjNodes;
    static TNode adjNode;
    static ArrayList<TNode> explored = new ArrayList<TNode>();
    static TNode home = BusRoutesTree.home;

    int depth;
    static TNode tNodeBeingChecked;

    public TNode(int id, String place, TNode child, TNode rSib, int depth){

        this.place = place;
        this.depth = depth;
        this.id = id;
        child = null;
        rSib = null;
        TNode.adjNodes = new Stack<TNode>();
    }

    public static TNode getChild(TNode tNodeBeingChecked){
        return tNodeBeingChecked.child;
    }

    public static TNode getRSib(TNode tNodeBeingChecked){
        return tNodeBeingChecked.rSib;
    }

    // this gets the node's children, grandchildren etc, or siblings if no child
    public static Stack<TNode> getAdjNodes(TNode tNodeBeingChecked){
        TNode adjNode;
        adjNodes.clear();
        if (tNodeBeingChecked.child != null){
            adjNode = tNodeBeingChecked.child;
        } else {
            adjNode = tNodeBeingChecked.rSib;
        }
        while (adjNode != null){
            adjNodes.push(adjNode);
            adjNode = adjNode.rSib;           
        }
        return adjNodes;
    }

    public static void showAdjNodes(TNode tNodeBeingChecked, Stack<TNode> adjNodes){
        if (adjNodes.size() > 0){
            System.out.print("Adjnodes of " + tNodeBeingChecked.place + ": ");
            for (int a = 0; a < adjNodes.size(); a++){
                System.out.print(adjNodes.get(a).place + "(" + adjNodes.get(a).id + "). ");
            }
            System.out.println("");
        } else {
            System.out.println("No adjNodes.");
        }
    }

    // the following code is moved over from dls.java:
    public static Boolean isGoal(TNode tNodeBeingChecked) {
        if ((tNodeBeingChecked.place.length() > 3) && (tNodeBeingChecked.place.substring(0, 4) == "work")) {
            return true;
        } else {
            return false;
        }
    }

    public static void displayExploredFrontier(ArrayList<TNode> explored, Stack<TNode> frontier) {
        if (frontier.size() > 0) {
            System.out.print("Frontier: ");
            for (int f = 0; f < frontier.size(); f++) {
                System.out.print(frontier.get(f).place + "(" + frontier.get(f).id + "). ");
            }
            System.out.println("");
        } else {
            System.out.println("Frontier is empty");
        }

        if (explored.size() > 0) {
            System.out.print("Explored: ");
            for (int e = 0; e < explored.size(); e++) {
                System.out.print(explored.get(e).place + "(" + explored.get(e).id + "). ");
            }
            System.out.println("\n");
        } else {
            System.out.println("Explored is empty");
        }
    }

    public static void showSolution(TNode tNodeBeingChecked, ArrayList<TNode> explored) {
        System.out.println("Goal found!");
        explored.add(tNodeBeingChecked);
        System.out.print("Solution: ");
        for (int e = 0; e < explored.size(); e++) {
            System.out.print(explored.get(e).place + "(" + explored.get(e).id + "). ");
        }
        System.out.println("\n");
    }

    public static void expandAdjToFrontier(TNode node, int level, int limit) {
        System.out.println("\nIn expandAdjToFrontier. level: " + level + ", limit: " + limit);
        System.out.println("Currently checking " + node.place + node.id);
        adjNodes = TNode.getAdjNodes(node);
        System.out.println(node.place + " has " + adjNodes.size() + " adjNodes");
        for (int j = 0; j < adjNodes.size(); j++) {
            adjNode = adjNodes.get(j);
            if (!frontier.contains(adjNode)) {
                frontier.add(adjNode);
            } else {
                System.out.println("Frontier already contains " + adjNode.place + adjNode.id);
            }
        }
        displayExploredFrontier(explored, frontier);
        checkAdjInFrontier(level, limit);
    }

    // check if they are a goal. return solution. If not, add each one to explored
    // and add its childen to frontier.
    public static void checkAdjInFrontier(int level, int limit) {
        System.out.println("checkAdjInFrontier. Level: " + level + ", limit: " + limit);
        System.out.println("Checking " + adjNode.place + adjNode.id + ", depth " + adjNode.depth);
        if (adjNode.depth <= level) {
            System.out.println(adjNode.place + adjNode.id + " depth: " + adjNode.depth + " is <= level " + level);
            if (!explored.contains(adjNode)) {
                if (isGoal(adjNode)) {
                    showSolution(adjNode, explored);
                } else {
                    explored.add(adjNode);
                    System.out.println(
                            "adjNode " + adjNode.place + adjNode.id + " is not the goal. Going to expandAdjToFrontier");
                    expandAdjToFrontier(adjNode, level, limit);
                } // if isGoal
            } else {
                System.out.println("Explored already contains " + adjNode.place + adjNode.id);
            } // if !explored.contains
        } else {
            //System.out.println("Beyond level");
            System.out.println(adjNode.place + adjNode.id + "'s depth, " + adjNode.depth + ", is beyond the current search level of " + level);
        }
    }

    // dls method used to be the main method of dls.java:
    public static Boolean dls(TNode tNode, int level, int limit) {
        System.out.println("\nIn dls. Level: " + level + ", limit: " + limit);
        // frontier.push(busRoutesTree.root);
        displayExploredFrontier(explored, frontier);
        tNodeBeingChecked = frontier.pop();
        System.out.println("Checking " + tNodeBeingChecked.place + tNodeBeingChecked.id);

        if (!isGoal(tNodeBeingChecked)) {
            if (!explored.contains(tNodeBeingChecked)) {
                explored.add(tNodeBeingChecked);
                System.out.println("TNodeBeingChecked is " + tNodeBeingChecked.place + tNodeBeingChecked.id
                        + ". Checking its adjNodes");
                expandAdjToFrontier(tNodeBeingChecked, level, limit);
            } else {
                System.out.println("Explored already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id);
            }
        } else {
            showSolution(tNodeBeingChecked, explored);
            return true;
        }
        System.out.println("dls. Not goal, exiting dls and going back to ids");
        return false;

    } // dls


    
}
