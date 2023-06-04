package search;

import java.util.Stack;

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

    // MAYBE USE THIS ONE IN IDS:
    
  public static void adjsToFrontierIDS(TNode tNodeBeingChecked, int levelBeingChecked, int limit) {
      System.out.println("\nIn adjsToFrontierIDS. levelBeingChecked: " + levelBeingChecked + ", limit: " + limit);
      System.out.println("Currently checking " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [adjsToFrontierIDS]");
      adjNodes = TNode.getAdjNodes(tNodeBeingChecked);
      System.out.println(tNodeBeingChecked.place + " has " + adjNodes.size() + " adjNodes" + " [adjsToFrontierDLS]");
      for (int j = 0; j < adjNodes.size(); j++) {
   	   System.out.println("Changing tNodeBeingChecked to " + adjNodes.get(j).place + adjNodes.get(j).id + " [adjsToFrontierIDS]");
          tNodeBeingChecked = adjNodes.get(j);
          if (!lifoFrontier.contains(tNodeBeingChecked)) {
       	   System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " is not in frontier. Adding it to frontier [adjsToFrontierIDS]");
              lifoFrontier.add(tNodeBeingChecked);
          } else {
              System.out.println("lifoFrontier already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [adjsToFrontierIDS]");
          }
      }
      TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
      System.out.println("[adjsToFrontierDLS]\n");
      checkAdjInFrontier(levelBeingChecked, limit);
  }
  

public static void main(String[] args) {
    while (levelBeingChecked < (limit + 1) && (!TSearch.isGoal(tNodeBeingChecked))) {
        System.out.println("\nIn ids while loop. TNodeBeingChecked: " + tNodeBeingChecked.place
                + tNodeBeingChecked.id + "(depth " + tNodeBeingChecked.depth + ") , limit: " + limit + " [ids main]");
        TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
        System.out.println("[IDS main]\n");
        if (!TSearch.isGoal(tNodeBeingChecked)) {
            if (!explored.contains(tNodeBeingChecked)) {
                explored.add(tNodeBeingChecked);
                System.out.println("TNodeBeingChecked is " + tNodeBeingChecked.place + tNodeBeingChecked.id
                        + ". Checking its adjNodes" + " [IDS main]");
                adjsToFrontierDLS(tNodeBeingChecked, limit);
            } else {
                System.out.println(
                        "Explored already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [IDS main]");
                adjsToFrontierDLS(tNodeBeingChecked, limit);
                break;
            }
        } else {
            TSearch.showSolution();
            break;
        }
        tNodeBeingChecked = lifoFrontier.pop();
    } // while


}