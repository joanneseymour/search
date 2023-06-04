package search;
// which class should the frontier belong to? Many search algorithms need it, so it can't just belong to ids or to TNode. Maybe I should make a class called LIFO frontier and another one called FIFO frontier? Should I put them in a new class called search>? What about the explored set?
// ids and dfs etc are just different searches. Maybe they should all be in a class called uninformed search.
// but maybe they should all be in a package. What is a package anyway?
// search > uninformed > dls etc.

import java.util.Stack;

public class IDS {

    static TNode tNodeBeingChecked;

    Boolean isGoal = TNode.isGoal(tNodeBeingChecked);
    int tNodeDepth = 0;
    static int level = 0;
    static int limit = 5;
    static BusRoutesTree busRoutesTree = new BusRoutesTree();
    static TNode root = TNode.root;
    static Stack<TNode> frontier = new Stack<TNode>();

    // MAYBE USE THIS ONE IN IDS:
    
//  public static void adjsToFrontierI(TNode tNodeBeingChecked, int levelBeingChecked, int limit) {
//      System.out.println("\nIn adjsToFrontierDLS. levelBeingChecked: " + levelBeingChecked + ", limit: " + limit);
//      System.out.println("Currently checking " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [adjsToFrontierDLS]");
//      adjNodes = TNode.getAdjNodes(tNodeBeingChecked);
//      System.out.println(tNodeBeingChecked.place + " has " + adjNodes.size() + " adjNodes" + " [adjsToFrontierDLS]");
//      for (int j = 0; j < adjNodes.size(); j++) {
//   	   System.out.println("Changing tNodeBeingChecked to " + adjNodes.get(j).place + adjNodes.get(j).id + " [adjsToFrontierDLS]");
//          tNodeBeingChecked = adjNodes.get(j);
//          if (!lifoFrontier.contains(tNodeBeingChecked)) {
//       	   System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " is not in frontier. Adding it to frontier [adjsToFrontierDLS]");
//              lifoFrontier.add(tNodeBeingChecked);
//          } else {
//              System.out.println("lifoFrontier already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id + " [adjsToFrontierDLS]");
//          }
//      }
//      TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
//      System.out.println("[adjsToFrontierDLS]\n");
//      checkAdjInFrontier(levelBeingChecked, limit);
//  }
  

public static void main(String[] args) {
    frontier.push(BusRoutesTree.root);
    TNode.dls(root, level, limit);
    for (int level = 0; level <= limit; level++){

            if (TNode.dls(tNodeBeingChecked, level, limit)){
                // 
                System.out.println("Found a goal");
            } else {
                System.out.println("\nids: Didn't find a goal at level " + level + ". Time to increase level");;
                if (level == limit) {
                    System.out.println("Nah Limit reached. Goodbye");
                }
            }
        }
    }

}