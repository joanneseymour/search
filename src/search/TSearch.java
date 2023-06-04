package search;

import java.util.ArrayList;
import java.util.Stack;

public class TSearch {
    static Stack<TNode> lifoFrontier = new Stack<TNode>();
    static ArrayList<TNode> explored = new ArrayList<TNode>();
    static ArrayList<TNode> solution = new ArrayList<TNode>();
    static ArrayList<TNode> children = new ArrayList<TNode>();
    static TNode child;
    static BusRoutesTree BusRoutesTree = new BusRoutesTree();
    static TNode initialState = search.BusRoutesTree.home;
    static Stack<TNode> adjNodes = new Stack<TNode>();
    static TNode tNodeBeingChecked = initialState;
    static TNode adjNode;

    public static Boolean isGoal(TNode tNodeBeingChecked) {
        if ((tNodeBeingChecked.place.length() > 3) && (tNodeBeingChecked.place.substring(0, 4) == "work")) {
            return true;
        } else {
            return false;
        }
    }

    public static void displayFrontierExplored(TNode tNodeBeingChecked, Stack<TNode> lifoFrontier,
            ArrayList<TNode> explored) {
        System.out.println("");
        if (tNodeBeingChecked != null) {
            System.out.println("tNodeBeingChecked: " + tNodeBeingChecked.place + tNodeBeingChecked.id);
        }
        if (lifoFrontier.size() > 0) {
            System.out.print("lifoFrontier: ");
            for (int f = 0; f < lifoFrontier.size(); f++) {
                System.out.print(lifoFrontier.get(f).place + lifoFrontier.get(f).id + ". ");
            }
            System.out.println("");
        } else {
            System.out.println("lifoFrontier is empty");
        }

        if (explored.size() > 0) {
            System.out.print("Explored: ");
            for (int e = 0; e < explored.size(); e++) {
                System.out.print(explored.get(e).place + explored.get(e).id + ". ");
            }
        } else {
            System.out.print("Explored is empty");
        }
    }

    public static void showSolution() {
        System.out.print("Solution: ");
        for (int e = 0; e < explored.size(); e++) {
            System.out.print(explored.get(e).place + "(" + explored.get(e).id + "). ");
        }
        System.out.println("\n");
    }

    public static void adjsToFrontier(TNode tNodeBeingChecked) {
        System.out.println("Now in adjsToFrontier");
        adjNodes = TNode.getAdjNodes(tNodeBeingChecked);
        for (int i = 0; i < adjNodes.size(); i++) {
            adjNode = adjNodes.get(i);
            if (!explored.contains(adjNode) && !lifoFrontier.contains(adjNode)) {
                System.out.println(
                        adjNode.place + adjNode.id + " is not in explored or lifoFrontier, adding to lifoFrontier");
                lifoFrontier.push(adjNode);
                displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
            }
        }
    }
      
}
