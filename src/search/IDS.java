package search;

import java.util.ArrayList;
import java.util.Stack;


// continue to simplify with new methods and check console for errors

public class IDS {

    static TNode tNodeBeingChecked = TSearch.tNodeBeingChecked;
    static Stack<TNode> adjNodes = TSearch.adjNodes;
    static TNode adjNode = TSearch.adjNode;
    Boolean isGoal = TSearch.isGoal(tNodeBeingChecked);
    int tNodeDepth = 0;
    static int levelBeingChecked = 0;
    static int limit = 3;
    static Stack<TNode> lifoFrontier = TSearch.lifoFrontier;
    static ArrayList<TNode> explored = TSearch.explored;

 // why is xnhy11 already in explored???
    
public static void adjsToFrontierExploredIDS(TNode tNodeBeingChecked, int levelBeingChecked, int limit) {
 	System.out.println("*************************adjsToFrontier*******************************************");
    TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
    System.out.println("[adjsToFrontierExploredIDS]\n");
 	
	levelBeingChecked = levelBeingChecked + 1;
	System.out.println("\nIn adjsToFrontierExploredIDS. levelBeingChecked: " + levelBeingChecked + ", limit: " + limit);	  
	if (levelBeingChecked <= limit) {
		System.out.println("Expanding " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [adjsToFrontierExploredIDS]");
		adjNodes = TNode.getAdjNodes(tNodeBeingChecked);
		System.out.println(tNodeBeingChecked.place + " has " + adjNodes.size() + " adjNodes" + " [adjsToFrontierDLS]\n");
		for (int j = 0; j < adjNodes.size(); j++) {
			adjNode = adjNodes.get(j);
			if (TSearch.isRightDepth(adjNode, levelBeingChecked) && (TSearch.canAddToFrontier(adjNode, lifoFrontier, explored)))
				System.out.println("Adding adjNode " + adjNode.place + adjNode.id + "(depth: " + adjNode.depth + ") [adjsToFrontierExploredIDS]");
				lifoFrontier.add(adjNode);
				adjNode = null; // clear value of adjNode so adjNode can be used again later
//				explored.add(tNodeBeingChecked);
				tNodeBeingChecked = lifoFrontier.pop();
				if (!TSearch.isGoal(tNodeBeingChecked)) {			
					System.out.println("tNodeBeingChecked = " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [adjsToFrontierExploredIDS]");
					adjsToFrontierExploredIDS(tNodeBeingChecked, levelBeingChecked, limit);
					} else {
						System.out.println("Goal found, well done [adjsToFrontierDLS]");
					}
				System.out.println("Moving on to checkAdjInFrontier [adjsToFrontierExploredIDS]");
				checkAdjInFrontier(tNodeBeingChecked, levelBeingChecked, limit);
			} // for
	} // if level < limit
	
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
      
    	  System.out.println("Frontier is empty.");
    	  if (!explored.contains(tNodeBeingChecked)) {
        	  explored.add(tNodeBeingChecked);
    	  }
    	  if (TNode.getRSib(tNodeBeingChecked)!=null) {
    		  tNodeBeingChecked = TNode.getRSib(tNodeBeingChecked);
    		  System.out.println("tNodeBeingChecked is now " + tNodeBeingChecked.place + tNodeBeingChecked.id );
    		  adjsToFrontierExploredIDS(tNodeBeingChecked, levelBeingChecked, limit);
    	  } else {
        	  if (!explored.contains(tNodeBeingChecked)) {
    		  explored.add(tNodeBeingChecked);
        	  }
    	  }
    	  
          TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
          System.out.println("[checkAdjInFrontier]\n");
    	  //explored.clear();
    	  tNodeBeingChecked = TSearch.tNodeBeingChecked;
    	  System.out.println("Changing levelBeingChecked to 0 and tNodeBeingChecked to root");
    	  levelBeingChecked = 0;
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
                System.out.println("TNodeBeingChecked is " + tNodeBeingChecked.place + tNodeBeingChecked.id
                        + ". Expanding its adjNodes to frontier" + " [IDS]");
                adjsToFrontierExploredIDS(tNodeBeingChecked, levelBeingChecked, limit);
            } else {
                System.out.println(
                        "Explored already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [IDS]");
                //adjsToFrontierExploredIDS(tNodeBeingChecked, levelBeingChecked, limit);
                break;
            }
        } else {
            TSearch.showSolution();
            break;
        }
        
  	  levelBeingChecked = levelBeingChecked + 1;
    } // while
    
    System.out.println("Limit reached. End");

}

public static void main(String[] args) {
	ids(tNodeBeingChecked, levelBeingChecked, limit);
}
}