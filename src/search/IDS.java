package search;

import java.util.ArrayList;
import java.util.Stack;


// tidy up checkAdjInFrontier

public class IDS {

    static TNode tNodeBeingChecked = TSearch.tNodeBeingChecked;
    static Stack<TNode> adjNodes = TSearch.adjNodes;
    static TNode adjNode = TSearch.adjNode;
    Boolean isGoal = TSearch.isGoal(tNodeBeingChecked);
    int tNodeDepth = 0;
    static int levelBeingChecked = 0;
    static int limit = 2;
    static Stack<TNode> lifoFrontier = TSearch.lifoFrontier;
    static ArrayList<TNode> explored = TSearch.explored;

 // JUST EXPAND THEM, DON'T CHECK THEM IN THIS METHOD:
    
public static void adjsToFrontierExploredIDS(TNode tNodeBeingChecked, int levelBeingChecked, int limit) {
 	System.out.println("*************************adjsToFrontier*******************************************");
    TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
    System.out.println("[adjsToFrontierExploredIDS]\n");
 	
		System.out.println("Expanding " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [adjsToFrontierExploredIDS]");
		adjNodes = TNode.getAdjNodes(tNodeBeingChecked);
		System.out.println(tNodeBeingChecked.place + " has " + adjNodes.size() + " adjNodes" + " [adjsToFrontierDLS]\n");
		for (int j = 0; j < adjNodes.size(); j++) {
			adjNode = adjNodes.get(j);
				System.out.println("Adding adjNode " + adjNode.place + adjNode.id + "(depth: " + adjNode.depth + ") [adjsToFrontierExploredIDS]");
				lifoFrontier.add(adjNode);
				adjNode = null; // clear value of adjNode so adjNode can be used again later
				tNodeBeingChecked = lifoFrontier.pop();	
					System.out.println("tNodeBeingChecked = " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [adjsToFrontierExploredIDS]");

					//adjsToFrontierExploredIDS(tNodeBeingChecked, levelBeingChecked, limit);
				//System.out.println("Moving on to checkAdjInFrontier [adjsToFrontierExploredIDS]");
				//checkAdjInFrontier(tNodeBeingChecked, levelBeingChecked, limit);
			} // for all adjNodes
		//tNodeBeingChecked = TSearch.tNodeBeingChecked; // reset tNode to root
		checkAdjInFrontier(tNodeBeingChecked, levelBeingChecked, limit);

        //levelBeingChecked = levelBeingChecked + 1;
		//ids(tNodeBeingChecked, levelBeingChecked, limit);

	
}


 
  // check if they are a goal. return solution. If not, add each one to explored and add its children to lifoFrontier.
  public static void checkAdjInFrontier(TNode tNodeBeingChecked, int levelBeingChecked, int limit) {
	  System.out.println("**********************checkAdjInFrontier*******************************************");
      System.out.println("checkAdjInFrontier. limit: " + limit + ", levelBeingChecked: " + levelBeingChecked);

      TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
      System.out.println("[checkAdjInFrontier]\n");
      while ((lifoFrontier.size() > 0) && (levelBeingChecked <= limit) ){       
          tNodeBeingChecked = lifoFrontier.pop();
          System.out.println("Checking " + tNodeBeingChecked.place + tNodeBeingChecked.id + ", depth "
                  + tNodeBeingChecked.depth + " [checkAdjInFrontier]");
          
          if ((TSearch.isRightDepth(tNodeBeingChecked, levelBeingChecked)) && (TSearch.canAddToExplored(tNodeBeingChecked, lifoFrontier, explored)) && (!TSearch.isGoal(tNodeBeingChecked))) {        
                  explored.add(tNodeBeingChecked);
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
    while (levelBeingChecked <= limit && (!TSearch.isGoal(tNodeBeingChecked))) {
    	System.out.println("*************************ids while loop**********************************");
        System.out.println("\nIn ids while loop. TNodeBeingChecked: " + tNodeBeingChecked.place
                + tNodeBeingChecked.id + "(depth " + tNodeBeingChecked.depth + ") , levelBeingChecked: " + levelBeingChecked + ") , limit: " + limit +" [ids]");
        TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
        System.out.println("[IDS]\n");
        if (!TSearch.isGoal(tNodeBeingChecked)) {
            if ((TSearch.canAddToExplored(tNodeBeingChecked, lifoFrontier, explored)) && (TSearch.canAddToFrontier(tNodeBeingChecked, lifoFrontier, explored))) {
                System.out.println("TNodeBeingChecked is " + tNodeBeingChecked.place + tNodeBeingChecked.id
                        + ". Expanding its adjNodes to frontier" + " [IDS]");
                //levelBeingChecked = levelBeingChecked + 1;
                adjsToFrontierExploredIDS(tNodeBeingChecked, levelBeingChecked, limit);
                //break;
            }
        } else {
        	System.out.println("GOAL [IDS]");
            TSearch.showSolution();
            break;
        }
        
  	  //levelBeingChecked = levelBeingChecked + 1;
    } // while
    
    System.out.println("Limit reached. End");

}

public static void main(String[] args) {
	ids(tNodeBeingChecked, levelBeingChecked, limit);
}
}