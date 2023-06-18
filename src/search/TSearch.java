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
    static Stack<TNode> adjNodes = new Stack<TNode>();
    static TNode tNodeBeingChecked = search.BusRoutesTree.home;
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
        } else {
        	System.out.println("tNodeBeingChecked: None");
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
    
    public static Boolean canAddToExplored(TNode tNodeBeingChecked, Stack<TNode> lifoFrontier, ArrayList<TNode> explored) {
	    	if (!explored.contains(tNodeBeingChecked)) {
        		//System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " is not in explored [canAddToExplored]");
	        		if (!lifoFrontier.contains(tNodeBeingChecked)) {
	        			//System.out.println("Not in frontier, either, can add " + tNodeBeingChecked.place + tNodeBeingChecked.id + " to explored [canAddToExplored]");        			
	        			return true;
	        		} else {
	    	    		System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " already in frontier. Can't add to explored yet [canAddToExplored]");
	    	    		return false;
	        		}
	    	} else {
	    		System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " already in explored [canAddToExplored]");
	    		return false;
	    	}
    	}


    
    
    public static Boolean canAddToFrontier(TNode tNodeBeingChecked, Stack<TNode> lifoFrontier, ArrayList<TNode> explored) {
    	if (!explored.contains(tNodeBeingChecked)) {
        	if (!isGoal(tNodeBeingChecked)) {
        		//System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " is not goal [canAddToFrontier]");
        		if (!lifoFrontier.contains(tNodeBeingChecked)) {
        			//System.out.println("Not in frontier or explored, can add " + tNodeBeingChecked.place + tNodeBeingChecked.id + " to frontier [canAddToFrontier]");
        			return true;
        		} else {
    	    		System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " already in frontier [canAddToFrontier]");
    	    		return false;
        		}
        	} else {
        		System.out.println("GOAL!!!! Cannot add to frontier  [canAddToFrontier]");
        		return false;
        	}
    	} else {
    		System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " already in explored [canAddToFrontier]");
    		return false;
    	}
    	
    	
    }
    
    public static Boolean isRightDepth(TNode tNodeBeingChecked, int levelBeingChecked) {
    	if (tNodeBeingChecked.depth <= levelBeingChecked) {
    		return true;
    	} else {
    		System.out.println("Depth beyond levelBeingChecked [isRightDepth]");
    		return false;
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
