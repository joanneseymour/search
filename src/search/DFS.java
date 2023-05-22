package search;
// Stops as soon as it reaches a goal, doesn't traverse the entire tree

import java.util.ArrayList;

// to do: make a TSearch.java and put in displayExploredFrontier. Also compare it with the GSearch version.
import java.util.Stack;

public class DFS {
    static Stack<TNode> lifoFrontier = new Stack<TNode>();
    static ArrayList<TNode> explored = new ArrayList<TNode>();
    static Stack<TNode> adjNodes = new Stack<TNode>(); // holds adjacent nodes
    static TNode tNodeBeingChecked = BusRoutesTree.home;
    static TNode adjNode;

    
    public static void main(String[] args) {
        if (TSearch.isGoal(tNodeBeingChecked)){
            System.out.println("Start: Goal found! Solution: ");
        }
        TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
        System.out.println("Adding " + tNodeBeingChecked.place + tNodeBeingChecked.id + " to explored.");
        explored.add(tNodeBeingChecked);
        
        TSearch.adjsToFrontier(tNodeBeingChecked);
        TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
        // while frontier is not empty do
            while (lifoFrontier.size() > 0){
                tNodeBeingChecked = lifoFrontier.pop();
                System.out.println("In while loop, tNodeBeingChecked: " + tNodeBeingChecked.place + tNodeBeingChecked.id);
                // if node is not in explored set then
                if (!explored.contains(tNodeBeingChecked)){
                // add node to explored
                System.out.println(tNodeBeingChecked.place + tNodeBeingChecked.id + " is not in explored, adding to explored.");
                    explored.add(tNodeBeingChecked);
                    TSearch.displayFrontierExplored(tNodeBeingChecked, lifoFrontier, explored);
                    TSearch.adjsToFrontier(tNodeBeingChecked);
                    if (TSearch.isGoal(tNodeBeingChecked)){
                        System.out.print("Goal found! ");
                        TSearch.showSolution();
                        break;
                    }
                    }
                    System.out.println("");
                }
            }
        }










                    
                
            
        
        
        

        

    
    
