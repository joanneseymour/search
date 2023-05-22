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
    static TNode initialState = search.BusRoutesTree.home;
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
        System.out.println("\n");
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
    
    // the following code is moved over from dls.java, then TNode.java:


    public static void expandAdjToFrontier(TNode node, int level, int limit) {
        System.out.println("\nIn expandAdjToFrontier. level: " + level + ", limit: " + limit);
        System.out.println("Currently checking " + node.place + node.id);
        adjNodes = TNode.getAdjNodes(node);
        System.out.println(node.place + " has " + adjNodes.size() + " adjNodes");
        for (int j = 0; j < adjNodes.size(); j++) {
            adjNode = adjNodes.get(j);
            if (!frontier.contains(adjNode)) {
                frontier.add(adjNode);
            } else {
                System.out.println("Frontier already contains " + adjNode.place + adjNode.id);
            }
        }
        displayExploredFrontier(explored, frontier);
        checkAdjInFrontier(level, limit);
    }

    // check if they are a goal. return solution. If not, add each one to explored
    // and add its childen to frontier.
    public static void checkAdjInFrontier(int level, int limit) {
        System.out.println("checkAdjInFrontier. Level: " + level + ", limit: " + limit);
        System.out.println("Checking " + adjNode.place + adjNode.id + ", depth " + adjNode.depth);
        if (adjNode.depth <= level) {
            System.out.println(adjNode.place + adjNode.id + " depth: " + adjNode.depth + " is <= level " + level);
            if (!explored.contains(adjNode)) {
                if (isGoal(adjNode)) {
                    showSolution(adjNode, explored);
                } else {
                    explored.add(adjNode);
                    System.out.println(
                            "adjNode " + adjNode.place + adjNode.id + " is not the goal. Going to expandAdjToFrontier");
                    expandAdjToFrontier(adjNode, level, limit);
                } // if isGoal
            } else {
                System.out.println("Explored already contains " + adjNode.place + adjNode.id);
            } // if !explored.contains
        } else {
            //System.out.println("Beyond level");
            System.out.println(adjNode.place + adjNode.id + "'s depth, " + adjNode.depth + ", is beyond the current search level of " + level);
        }
    }
    
}
