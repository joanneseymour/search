package search;
import java.util.ArrayList;
import java.util.Stack;

// treeNode is already a thing in Java, so I'm calling this a tNode

public class TNode {
    String place;
    int id;
    int weight;
    static int level;
    TNode parent;
    TNode child;
    TNode rSib;
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
