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

    // this gets the node's children, grand children etc, or siblings if no child
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

   


    
}
