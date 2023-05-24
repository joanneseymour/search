package search;
import java.util.ArrayList;
import java.util.Stack;

 public class DLS {
   static Stack<TNode> lifoFrontier = TSearch.lifoFrontier;
   static Stack<TNode> adjNodes = TSearch.adjNodes;
   static TNode adjNode = TSearch.adjNode;
   static ArrayList<TNode> explored = TSearch.explored;
   static int limit = 3;
   static int level = 0;
   static int depth;
   static TNode tNodeBeingChecked = TSearch.tNodeBeingChecked;


 public static void main(String[] args) {
    System.out.println("\nIn dls. Level: " + level + ", limit: " + limit);

    TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
//    tNodeBeingChecked = lifoFrontier.pop();
    System.out.println("Checking " + tNodeBeingChecked.place + tNodeBeingChecked.id);

    if (!TSearch.isGoal(tNodeBeingChecked)) {
        if (!explored.contains(tNodeBeingChecked)) {
            explored.add(tNodeBeingChecked);
            System.out.println("TNodeBeingChecked is " + tNodeBeingChecked.place + tNodeBeingChecked.id
                    + ". Checking its adjNodes");
            TSearch.adjsToFrontierLimited(tNodeBeingChecked, level, limit);
        } else {
            System.out.println("Explored already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id);
        }
    } else {
        TSearch.showSolution();
    }
    System.out.println("Not goal");
} // main

 }
