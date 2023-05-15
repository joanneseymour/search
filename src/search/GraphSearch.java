package search;
import java.util.ArrayList;

// This does a graphSearch, as in it gets from the beginning point (home) to the end (work).
// Then it backs up and chooses the first parent of each GNode according to the order they're written in in BusRoutes.
// That's all a basic graphSearch should do. No weights or heuristics here.

public class GraphSearch {
	static ArrayList<GNode> frontier = new ArrayList<GNode>();
	static ArrayList<GNode> explored = new ArrayList<GNode>();
	static ArrayList<GNode> solution = new ArrayList<GNode>();
	static BusRoutesGraph busRoutesGraph = new BusRoutesGraph();
	static GNode GNodeBeingChecked;
	static GNode currentState;
	static String setName;

	public static void reachgoal() {
		frontier.add(busRoutesGraph.home);
		while (frontier.size() > 0) {
			GNodeBeingChecked = frontier.remove(0);
			System.out.println("Currently checking GNode " + GNodeBeingChecked.place);
			if (!GSearch.isGoal(GNodeBeingChecked)) {
				System.out.println("...Not goal. Adding " + GNodeBeingChecked.place + " to explored set");
				explored.add(GNodeBeingChecked);
				expandToFrontier(GNodeBeingChecked, (GNodeBeingChecked.children.size()));
			} else { // else the GNodeBeingChecked is the goal
				System.out.println("Goal found!");
				GSearch.calculateSolution(GNodeBeingChecked, explored);
			}
		} // while frontier > 0
	} // reachgoal

	public static void displayGNodeList(ArrayList<GNode> set) {
		if (set == frontier) {
			setName = "Frontier";
		} else if (set == explored) {
			setName = "Explored";
		} else if (set == solution) {
			setName = "Solution";
		}
		System.out.print(setName + ": ");
		if (set.size() > 0) {
			for (int i = 0; i < set.size(); i++) {
				System.out.print(i + ". " + set.get(i).place + " ");
			}
		} else {
			System.out.print("empty");
		}
		System.out.println("");
		setName = "";
	}

	
	public static void expandToFrontier(GNode GNodeBeingChecked, int numberOfChildren) {
		// expand chosen GNode, add resulting children to frontier only if not in
		// frontier or explored set
		for (int i = 0; i < numberOfChildren; i++) {
			if (!frontier.contains(GNodeBeingChecked.children.get(i))
					&& !explored.contains(GNodeBeingChecked.children.get(i))) {
				System.out.println("Adding " + GNodeBeingChecked.place + "'s child "
						+ GNodeBeingChecked.children.get(i).place + " to the frontier");
				frontier.add(GNodeBeingChecked.children.get(i));
			}
		}
		displayGNodeList(frontier);
	}

	public static void main(String[] args) {
		reachgoal();
	}

}
