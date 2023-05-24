package search;
import java.util.ArrayList;
import java.util.Stack;

 public class DLS {
   static Stack<TNode> lifoFrontier = TSearch.lifoFrontier;
   static Stack<TNode> adjNodes = TSearch.adjNodes;
   static TNode adjNode = TSearch.adjNode;
   static ArrayList<TNode> explored = TSearch.explored;
   static int limit = 5;
   static int levelBeingChecked = 0;
   static TNode tNodeBeingChecked = TSearch.tNodeBeingChecked;
   
// levelBeingChecked NEVER CHANGES! NEEDS TO INCREASE BY 1 ON EACH ITERATION UNTIL IT HITS THE LIMIT   

   
   public static void adjsToFrontierLimited(TNode node, int levelBeingChecked, int limit) {
       System.out.println("\nIn adjsToFrontierLimited. levelBeingChecked: " + levelBeingChecked + ", limit: " + limit);
       System.out.println("Currently checking " + node.place + node.id);
       adjNodes = TNode.getAdjNodes(node);
       System.out.println(node.place + " has " + adjNodes.size() + " adjNodes");
       for (int j = 0; j < adjNodes.size(); j++) {
           adjNode = adjNodes.get(j);
           if (!lifoFrontier.contains(adjNode)) {
               lifoFrontier.add(adjNode);
           } else {
               System.out.println("lifoFrontier already contains " + adjNode.place + adjNode.id);
           }
       }
       TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
       checkAdjInFrontier(levelBeingChecked, limit);
   }
   
   // check if they are a goal. return solution. If not, add each one to explored
   // and add its children to lifoFrontier.
   public static void checkAdjInFrontier(int levelBeingChecked, int limit) {
       System.out.println("checkAdjInFrontier. levelBeingChecked: " + levelBeingChecked + ", limit: " + limit);
       System.out.println("Checking " + adjNode.place + adjNode.id + ", depth " + adjNode.depth);
       if (adjNode.depth <= levelBeingChecked) {
           System.out.println(adjNode.place + adjNode.id + " depth: " + adjNode.depth + " is <= levelBeingChecked " + levelBeingChecked);
           if (!explored.contains(adjNode)) {
               if (TSearch.isGoal(adjNode)) {
            	   TSearch.showSolution();
               } else {
                   explored.add(adjNode);
                   System.out.println(
                           "adjNode " + adjNode.place + adjNode.id + " is not the goal. Going to expandAdjToFrontier");
                   DLS.adjsToFrontierLimited(adjNode, levelBeingChecked, limit);
                   
               } // if isGoal
           } else {
               System.out.println("Explored already contains " + adjNode.place + adjNode.id);
           } // if !explored.contains
       } else {
           //System.out.println("Beyond levelBeingChecked");
           System.out.println(adjNode.place + adjNode.id + "'s depth, " + adjNode.depth + ", is beyond the current search levelBeingChecked of " + levelBeingChecked);
       }
   }
   
   
 public static void main(String[] args) {
    System.out.println("\nIn dls. levelBeingChecked: " + levelBeingChecked + ", limit: " + limit);

    TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
//    tNodeBeingChecked = lifoFrontier.pop();


    if (!TSearch.isGoal(tNodeBeingChecked)) {
        if (!explored.contains(tNodeBeingChecked)) {
            explored.add(tNodeBeingChecked);
            System.out.println("TNodeBeingChecked is " + tNodeBeingChecked.place + tNodeBeingChecked.id
                    + ". Checking its adjNodes");
            adjsToFrontierLimited(tNodeBeingChecked, levelBeingChecked, limit);
        } else {
            System.out.println("Explored already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id);
        }
    } else {
        TSearch.showSolution();
    }
    System.out.println("Not goal");
} // main

 }
