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



public static void main(String[] args) {
    frontier.push(busRoutesTree.root);
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