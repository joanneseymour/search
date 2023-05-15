package search;
// import java.util.ArrayList;
// import java.util.Stack;

// public class DLS {
//     static busRoutesTree busRoutesTree = new busRoutesTree();
//     static Stack<TNode> frontier = new Stack<TNode>();
//     static Stack<TNode> adjNodes;
//     static TNode adjNode;
//     static ArrayList<TNode> explored = new ArrayList<TNode>();
//     static TNode root = busRoutesTree.root;
//     static int limit = 3;
//     static int depth;
//     static TNode tNodeBeingChecked;

//     // public static Boolean isGoal(TNode tNodeBeingChecked) {
//     //     if ((tNodeBeingChecked.place.length() > 3) && (tNodeBeingChecked.place.substring(0, 4) == "work")) {
//     //         return true;
//     //     } else {
//     //         return false;
//     //     }
//     // }

//     // public static void displayExploredFrontier(ArrayList<TNode> explored, Stack<TNode> frontier) {
//     //     if (frontier.size() > 0) {
//     //         System.out.print("Frontier: ");
//     //         for (int f = 0; f < frontier.size(); f++) {
//     //             System.out.print(frontier.get(f).place + "(" + frontier.get(f).id + "). ");
//     //         }
//     //         System.out.println("");
//     //     } else {
//     //         System.out.println("Frontier is empty");
//     //     }

//     //     if (explored.size() > 0) {
//     //         System.out.print("Explored: ");
//     //         for (int e = 0; e < explored.size(); e++) {
//     //             System.out.print(explored.get(e).place + "(" + explored.get(e).id + "). ");
//     //         }
//     //         System.out.println("\n");
//     //     } else {
//     //         System.out.println("Explored is empty");
//     //     }
//     // }

//     // public static void showSolution(TNode tNodeBeingChecked, ArrayList<TNode> explored) {
//     //     System.out.println("Goal found!");
//     //     explored.add(tNodeBeingChecked);
//     //     System.out.print("Solution: ");
//     //     for (int e = 0; e < explored.size(); e++) {
//     //         System.out.print(explored.get(e).place + "(" + explored.get(e).id + "). ");
//     //     }
//     //     System.out.println("\n");
//     // }

//     // public static void expandAdjToFrontier(TNode node) {
//     //     System.out.println("\nexpandAdjToFrontier:");
//     //     System.out.println("Currently checking " + node.place + node.id);
//     //     adjNodes = TNode.getAdjNodes(node);
//     //     System.out.println(node.place + " has " + adjNodes.size() + " adjNodes");
//     //     for (int j = 0; j < adjNodes.size(); j++) {
//     //         adjNode = adjNodes.get(j);
//     //         if (!frontier.contains(adjNode)) {
//     //             frontier.add(adjNode);
//     //         } else {
//     //             System.out.println("Frontier already contains " + adjNode.place + adjNode.id);
//     //         }
//     //     }
//     //     displayExploredFrontier(explored, frontier);
//     //     checkAdjInFrontier();
//     // }

//     // // check if they are a goal. return solution. If not, add each one to explored
//     // // and add its childen to frontier.
//     // public static void checkAdjInFrontier() {
//     //     System.out.println("checkAdjInFrontier:");
//     //     System.out.println("Checking " + adjNode.place + adjNode.id + ", depth " + adjNode.depth);
//     //     if (adjNode.depth <= limit) {
//     //         if (!explored.contains(adjNode)) {
//     //             if (isGoal(adjNode)) {
//     //                 showSolution(adjNode, explored);
//     //             } else {
//     //                 explored.add(adjNode);
//     //                 System.out.println(
//     //                         "adjNode " + adjNode.place + adjNode.id + " is not the goal. Going to expandAdjToFrontier");
//     //                 expandAdjToFrontier(adjNode);
//     //             } // if isGoal
//     //         } else {
//     //             System.out.println("Explored already contains " + adjNode.place + adjNode.id);
//     //         } // if !explored.contains
//     //     } else {
//     //         System.out.println(adjNode.place + adjNode.id + "is beyond the searchable limit");
//     //     }
//     // }


//     // public static void main(String[] args) {
//     //     frontier.push(busRoutesTree.root);
//     //     displayExploredFrontier(explored, frontier);
//     //     tNodeBeingChecked = frontier.pop();

//     //     if (!isGoal(tNodeBeingChecked)) {
//     //         if (!explored.contains(tNodeBeingChecked)) {
//     //             explored.add(tNodeBeingChecked);
//     //             System.out.println("TNodeBeingChecked is " + tNodeBeingChecked.place + tNodeBeingChecked.id
//     //                     + ". Checking its adjNodes");
//     //             expandAdjToFrontier(tNodeBeingChecked);
//     //         } else {
//     //             System.out.println("Explored already contains " + tNodeBeingChecked.place + tNodeBeingChecked.id);
//     //         }
//     //     } else {
//     //         showSolution(tNodeBeingChecked, explored);
//     //     }

//     // } // main

// }
