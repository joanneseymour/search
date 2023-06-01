package search;
import java.util.ArrayList;
import java.util.Stack;

 public class DLS {
   static Stack<TNode> lifoFrontier = TSearch.lifoFrontier;
   static Stack<TNode> adjNodes = TSearch.adjNodes;
   static TNode adjNode = TSearch.adjNode;
   static ArrayList<TNode> explored = TSearch.explored;
   static int limit = 5;
    static int levelBeingChecked;
   static TNode tNodeBeingChecked = TSearch.tNodeBeingChecked;
   
   // LINE 140 RUNS EVEN AFTER GOAL IS REACHED!

   
   public static void adjsToFrontierDLS(TNode tNodeBeingChecked, int limit) {
       System.out.println("\nIn adjsToFrontierDLS. limit: " + limit);
       System.out.println("Will expand the adjNodes of " + tNodeBeingChecked.place + tNodeBeingChecked.id + " to the frontier [adjsToFrontierDLS]");
       adjNodes = TNode.getAdjNodes(tNodeBeingChecked);
       System.out.println(tNodeBeingChecked.place + " has " + adjNodes.size() + " adjNodes" + " [adjsToFrontierDLS]");
       for (int j = 0; j < adjNodes.size(); j++) {
    	   System.out.println("Changing tNodeBeingChecked to " + adjNodes.get(j).place + adjNodes.get(j).id + " (depth " + adjNodes.get(j).depth + ") [adjsToFrontierDLS]");
           tNodeBeingChecked = adjNodes.get(j);
           if (tNodeBeingChecked.depth <= limit) {
               if (!lifoFrontier.contains(tNodeBeingChecked)) {
            	   System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " is not in frontier. Adding it to frontier [adjsToFrontierDLS]");
                   lifoFrontier.add(tNodeBeingChecked);
               } else {
            	   // already in frontier
                   System.out.println("lifoFrontier already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [adjsToFrontierDLS]");
               } // already in frontier    	   
           } else {
        	   // depth beyond limit
        	   System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + "'s depth " + tNodeBeingChecked.depth + " is beyond the limit of " + limit + ". Moving on to next adjNode. [adjsToFrontierDLS");
           } // depth beyond limit

       }
       TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
       System.out.println("[adjsToFrontierDLS]\n");
       checkAdjInFrontier(tNodeBeingChecked, limit);
   }
   
   
   // MAYBE USE THIS ONE IN IDS:
   
//   public static void adjsToFrontierI(TNode tNodeBeingChecked, int levelBeingChecked, int limit) {
//       System.out.println("\nIn adjsToFrontierDLS. levelBeingChecked: " + levelBeingChecked + ", limit: " + limit);
//       System.out.println("Currently checking " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [adjsToFrontierDLS]");
//       adjNodes = TNode.getAdjNodes(tNodeBeingChecked);
//       System.out.println(tNodeBeingChecked.place + " has " + adjNodes.size() + " adjNodes" + " [adjsToFrontierDLS]");
//       for (int j = 0; j < adjNodes.size(); j++) {
//    	   System.out.println("Changing tNodeBeingChecked to " + adjNodes.get(j).place + adjNodes.get(j).id + " [adjsToFrontierDLS]");
//           tNodeBeingChecked = adjNodes.get(j);
//           if (!lifoFrontier.contains(tNodeBeingChecked)) {
//        	   System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " is not in frontier. Adding it to frontier [adjsToFrontierDLS]");
//               lifoFrontier.add(tNodeBeingChecked);
//           } else {
//               System.out.println("lifoFrontier already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [adjsToFrontierDLS]");
//           }
//       }
//       TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
//       System.out.println("[adjsToFrontierDLS]\n");
//       checkAdjInFrontier(levelBeingChecked, limit);
//   }
   
   
   
   // check if they are a goal. return solution. If not, add each one to explored
   // and add its children to lifoFrontier.
   public static void checkAdjInFrontier(TNode tNodeBeingChecked, int limit) {
       System.out.println("checkAdjInFrontier. limit: " + limit);
       TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
       System.out.println("[checkAdjInFrontier]\n");
       
       while (lifoFrontier != null) {
    	   tNodeBeingChecked = lifoFrontier.pop();
    	   //tNodeBeingChecked = lifoFrontier.get((lifoFrontier.size()-1));
    	   System.out.println("Checking " + tNodeBeingChecked.place + tNodeBeingChecked.id + ", depth " + tNodeBeingChecked.depth + " [checkAdjInFrontier]");
       if (tNodeBeingChecked.depth <= limit) {
           System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " depth: " + tNodeBeingChecked.depth + " is <= limit " + limit + " [checkAdjInFrontier]");
           if (!explored.contains(tNodeBeingChecked)) {
        	   explored.add(tNodeBeingChecked);
               if (TSearch.isGoal(tNodeBeingChecked)) {
            	   System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " is GOAL!!!! [checkAdjInFrontier]");

            	   TSearch.showSolution();
            	   break;
               } else {
                   
                   //commented out because I used pop instead:
                   
            	   //lifoFrontier.remove(lifoFrontier.get(lifoFrontier.size()-1));
                   System.out.println(
                           "tNodeBeingChecked " + tNodeBeingChecked.place + tNodeBeingChecked.id + " is not the goal. Going to adjsToFrontierDLS" + "[checkAdjInFrontier]");
                   DLS.adjsToFrontierDLS(tNodeBeingChecked, limit);
                   
               } // if isGoal
           } else {
               System.out.println("Explored already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [checkAdjInFrontier]");
           } // if !explored.contains
       } else {
           //System.out.println("Beyond levelBeingChecked");
           System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + "'s depth, " + tNodeBeingChecked.depth + ", is beyond the limit of " + limit + " [checkAdjInFrontier]");
       }
       break;
    	   
       }
       
   }
   
   
 public static void main(String[] args) {

//    tNodeBeingChecked = lifoFrontier.pop();

    while (levelBeingChecked < (limit+1)) {
        System.out.println("\nIn dls. TNodeBeingChecked: "+ tNodeBeingChecked.place + tNodeBeingChecked.id  +"(depth " + tNodeBeingChecked.depth + ") , limit: " + limit + " [main]");

        TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
        System.out.println("[DLS main]\n");
        
	    if (!TSearch.isGoal(tNodeBeingChecked)) {
	        if (!explored.contains(tNodeBeingChecked)) {
	            explored.add(tNodeBeingChecked);
	            System.out.println("TNodeBeingChecked is " + tNodeBeingChecked.place + tNodeBeingChecked.id
	                    + ". Checking its adjNodes" + " [main]");
	            adjsToFrontierDLS(tNodeBeingChecked, limit);
	        } else {
	            System.out.println("Explored already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [main]");
	            adjsToFrontierDLS(tNodeBeingChecked, limit);
	            break;
	        }
	    } else {
	        TSearch.showSolution();
	        break;
	    }
	    
	    // THIS LINE PLAYS EVEN AFTER GOAL REACHED:
	    System.out.println("Not goal. " + "[main]");
	    System.out.println("Before change, levelBeingChecked is " + levelBeingChecked + " [main]");
	    levelBeingChecked = levelBeingChecked + 1;
	    System.out.println("After change, levelBeingChecked is now " + levelBeingChecked + " [main]");
	    tNodeBeingChecked = lifoFrontier.pop();
	    
	} // while

    
} // main

 }
