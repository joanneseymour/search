package search;

import java.util.ArrayList;
import java.util.Stack;

// it never goes past level 1.

// line 90: when frontier is empty, obvs there's nothing to pop and so everything breaks


// OH it looks like the work is repeated with every iteration. https://www.javatpoint.com/ai-uninformed-search-algorithms 
// So the explored set is cleared each time the levelBeingChecked increases? YES


// go to the next level when the frontier is empty? YES


// later, compare IDS and DLS on codeDiff
// if the only thing that changes is that IDS has levelBeingChecked, then add the levelBeingChecked argument to both adjsToFrontier and checkAdjToFrontier. Add a String argument (values IDS or DLS). If the value is IDS, execute extra code which changes level. If not, levelBeingChecked is always equal to limit.

public class IDS {

    static TNode tNodeBeingChecked = TSearch.tNodeBeingChecked;
    static Stack<TNode> adjNodes = TSearch.adjNodes;
    static TNode adjNode = TSearch.adjNode;
    Boolean isGoal = TSearch.isGoal(tNodeBeingChecked);
    int tNodeDepth = 0;
    static int levelBeingChecked = 0;
    static int limit = 5;
    static Stack<TNode> lifoFrontier = TSearch.lifoFrontier;
    static ArrayList<TNode> explored = TSearch.explored;

    // MAYBE USE THIS ONE IN IDS:
    
  public static void adjsToFrontierIDS(TNode tNodeBeingChecked, int levelBeingChecked, int limit) {
	  System.out.println("*************************adjsToFrontier*******************************************");
	  levelBeingChecked = levelBeingChecked + 1;
      System.out.println("\nIn adjsToFrontierIDS. levelBeingChecked: " + levelBeingChecked + ", limit: " + limit);
      System.out.println("Expanding " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [adjsToFrontierIDS]");
      adjNodes = TNode.getAdjNodes(tNodeBeingChecked);
      System.out.println(tNodeBeingChecked.place + " has " + adjNodes.size() + " adjNodes" + " [adjsToFrontierDLS]\n");
      for (int j = 0; j < adjNodes.size(); j++) {
    	  adjNode = adjNodes.get(j);
   	   System.out.println("adjNode number " + j + " of " + tNodeBeingChecked.place + tNodeBeingChecked.id + " is " + adjNode.place + adjNode.id + " [adjsToFrontierIDS]");
          if (!lifoFrontier.contains(adjNode)) {
       	   System.out.println(adjNode.place + adjNode.id + " is not in frontier. Adding it to frontier [adjsToFrontierIDS]");
              lifoFrontier.add(adjNode);
          } else {
              System.out.println("lifoFrontier already contains " + adjNode.place + adjNode.id + " [adjsToFrontierIDS]");
          }
      }
      tNodeBeingChecked = null;
      
      TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
      System.out.println("[adjsToFrontierDLS]\n");
      checkAdjInFrontier(tNodeBeingChecked, levelBeingChecked, limit);
  }
 
  // check if they are a goal. return solution. If not, add each one to explored and add its children to lifoFrontier.
  public static void checkAdjInFrontier(TNode tNodeBeingChecked, int levelBeingChecked, int limit) {
	  System.out.println("**********************checkAdjInFrontier*******************************************");
      System.out.println("checkAdjInFrontier. limit: " + limit + ", levelBeingChecked: " + levelBeingChecked);

      TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
      System.out.println("[checkAdjInFrontier]\n");
      while (lifoFrontier.size() > 0) {       
          tNodeBeingChecked = lifoFrontier.pop();
          System.out.println("Checking " + tNodeBeingChecked.place + tNodeBeingChecked.id + ", depth "
                  + tNodeBeingChecked.depth + " [checkAdjInFrontier]");
          if (tNodeBeingChecked.depth <= levelBeingChecked) {
              System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " depth: " + tNodeBeingChecked.depth
                      + " is <= levelBeingChecked " + levelBeingChecked + " [checkAdjInFrontier]");
              
              if (!explored.contains(tNodeBeingChecked)) {
                  explored.add(tNodeBeingChecked);
                  if (TSearch.isGoal(tNodeBeingChecked)) {
                      System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id
                              + " is GOAL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! [checkAdjInFrontier]");
                      TSearch.showSolution();
                      break;
                  } else {
                      System.out.println(
                              "tNodeBeingChecked " + tNodeBeingChecked.place + tNodeBeingChecked.id
                                      + " is not the goal. Checking next last item in frontier" + "[checkAdjInFrontier]");
                         // tNodeBeingChecked = lifoFrontier.pop();


                  }
              } else {
                  System.out.println("Explored already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id
                          + " [checkAdjInFrontier]");
                  break;
              }
          } else {
              System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + "'s depth, "
                      + tNodeBeingChecked.depth + ", is beyond the levelBeingChecked of " + levelBeingChecked + " [checkAdjInFrontier]");
              break;
          }
      } // while
    	  System.out.println("Frontier is empty");
    	  explored.clear();
    	  tNodeBeingChecked = TSearch.tNodeBeingChecked;

    	ids(tNodeBeingChecked, levelBeingChecked, limit);

  }


public static void ids(TNode tNodeBeingChecked, int levelBeingChecked, int limit) {
    while (levelBeingChecked < (limit + 1) && (!TSearch.isGoal(tNodeBeingChecked))) {
    	System.out.println("*************************ids while loop**********************************");
        System.out.println("\nIn ids while loop. TNodeBeingChecked: " + tNodeBeingChecked.place
                + tNodeBeingChecked.id + "(depth " + tNodeBeingChecked.depth + ") , levelBeingChecked: " + levelBeingChecked + ") , limit: " + limit +" [ids]");
        TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
        System.out.println("[IDS]\n");
        if (!TSearch.isGoal(tNodeBeingChecked)) {
            if (!explored.contains(tNodeBeingChecked)) {
                explored.add(tNodeBeingChecked);
                System.out.println("TNodeBeingChecked is " + tNodeBeingChecked.place + tNodeBeingChecked.id
                        + ". Expanding its adjNodes to frontier" + " [IDS]");
                adjsToFrontierIDS(tNodeBeingChecked, levelBeingChecked, limit);
            } else {
                System.out.println(
                        "Explored already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [IDS]");
                adjsToFrontierIDS(tNodeBeingChecked, levelBeingChecked, limit);
                break;
            }
        } else {
            TSearch.showSolution();
            break;
        }
        
  	  levelBeingChecked = levelBeingChecked + 1;
    } // while
    System.out.println("End");

}

public static void main(String[] args) {
	ids(tNodeBeingChecked, levelBeingChecked, limit);
}
}