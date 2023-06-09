package search;
// Stops as soon as it reaches a goal, doesn't traverse the entire tree

import java.util.ArrayList;
import java.util.Stack;

public class DFS {
    static Stack<TNode> lifoFrontier = TSearch.lifoFrontier;
    static ArrayList<TNode> explored = TSearch.explored;
    static Stack<TNode> adjNodes = TSearch.adjNodes; // holds adjacent nodes
    static TNode tNodeBeingChecked = BusRoutesTree.home;
    static TNode adjNode = TSearch.adjNode;

    public static void main(String[] args) {
        if (TSearch.isGoal(tNodeBeingChecked)) {
            System.out.println("Start: Goal found! Solution: ");
        }
        TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
        System.out.println("Adding " + tNodeBeingChecked.place + tNodeBeingChecked.id + " to explored.");
        explored.add(tNodeBeingChecked);
        TSearch.adjsToFrontier(tNodeBeingChecked);
        System.out.println("Back in main");
        TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
        while (lifoFrontier.size() > 0) {
            tNodeBeingChecked = lifoFrontier.pop();
            System.out.println("In while loop, tNodeBeingChecked: " + tNodeBeingChecked.place + tNodeBeingChecked.id);
            if (!explored.contains(tNodeBeingChecked)) {
                System.out.println(
                        tNodeBeingChecked.place + tNodeBeingChecked.id + " is not in explored, adding to explored.");
                explored.add(tNodeBeingChecked);
                TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
                TSearch.adjsToFrontier(tNodeBeingChecked);
                if (TSearch.isGoal(tNodeBeingChecked)) {
                    System.out.print("Goal found! ");
                    TSearch.showSolution();
                    break;
                }
            }
            System.out.println("");
        }
    }
}
