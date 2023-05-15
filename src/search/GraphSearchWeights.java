package search;
import java.util.ArrayList;
import java.util.Collections;

// This does a graphSearch, then it backs up from the goal, calculating the lowest cost route from each parent to the nodeBeingChecked. Cost is measured by time in minutes
// However I want it to choose the lowest cost path from the beginning, without backing up. In that case you'll need to add in distance from goal.
// But, although it picks the lowest cost path to each GNode from each possible parent, it doesn't actually choose the optimal solution.

// 1.
// It comes up with a solution where you take 3 short bus journeys on relatively rare buses.
// It doesn't take into account number of potential buses. e.g. B to C: only 1 bus.
// B to D: at least 2 options for buses.

// 2. 
// It doesn't track the total time for the whole journey, only the times taken between each point.

public class GraphSearchWeights {
	static ArrayList<GNode> frontier = new ArrayList<GNode>();
	static ArrayList<GNode> explored = new ArrayList<GNode>();
	static ArrayList<GNode> solution = new ArrayList<GNode>();
	static ArrayList<Edge> bestEdges = new ArrayList<Edge>();
	static ArrayList<Edge> theseEdges = new ArrayList<Edge>();
	static ArrayList<Edge> edgesToSort = new ArrayList<Edge>();
	static ArrayList<GNode> parentsToCheck = new ArrayList<GNode>();
	static ArrayList<GNode> set;
	static BusRoutesGraph busRoutesGraph = new BusRoutesGraph();
	static GNode nodeBeingChecked;
	static GNode currentState;
	static String setName;
	static GNode bestParent;
	static int weight;


	public static void getBestParent(ArrayList<GNode> parentsToCheck) {
		GNode thisParent = null;
		Edge thisEdge;
		Edge bestEdge = null;
		GNode bestParent;
		bestEdges.clear();
		edgesToSort.clear();

		// check all parentsToCheck of nodeBeingChecked
		for (int i = 0; i < parentsToCheck.size(); i++) {
			displayNodeList("parentsToCheck");
			thisParent = parentsToCheck.get(i);
			theseEdges = thisParent.getEdges();
			edgesToSort.clear();

			// shows edges protruding from thisParent
			for (int j = 0; j < theseEdges.size(); j++) {
				thisEdge = theseEdges.get(j);
				//weight = thisParent.getWeight(thisEdge);
				//System.out.print(" " + thisEdge.edgeName + ", weight: " + weight + ",");
			}
			System.out.println("");

			// skip over (not remove, too mafan) any edges that don't go directly to or from
			// nodeBeingChecked:
			for (int k = 0; k < theseEdges.size(); k++) {
				thisEdge = thisParent.getEdge(theseEdges, k);
				if (canAddEdgeToList(thisEdge, nodeBeingChecked)) {
					edgesToSort.add(thisEdge);
				}
				if (edgesToSort.size() > 0) {
					Collections.sort(edgesToSort);
					displayEdgeList(edgesToSort);
				}
			}
			bestEdge = edgesToSort.get(0);
			bestEdges.add(bestEdge);
			Collections.sort(edgesToSort);
			for (int k = 0; k < edgesToSort.size(); k++) {
				System.out.print(edgesToSort.get(k).edgeName + ": " + edgesToSort.get(k).weight + ", ");
			}
			System.out.println("");
			bestEdge = edgesToSort.get(0);
			if (!bestEdges.contains(bestEdge)) {
				bestEdges.add(bestEdge);
			}
		} // check all parentsToCheck of nodeBeingChecked
			// display all bestEdges that end up in nodeBeingChecked
		displayEdgeList(bestEdges);
		// sort edges in order by weight
		Collections.sort(bestEdges);
		bestEdge = bestEdges.get(0);
		bestParent = bestEdge.start;
		solution.add(0, bestParent);
		displayNodeList("solution");
		nodeBeingChecked = bestParent;
		GSearch.calculateSolution(nodeBeingChecked, explored);
	} // getBestParent

	public static void displayEdgeList(ArrayList<Edge> set) {
		setName = "";
		setName = getEdgeListName(set);
		System.out.print(setName + ":");
		if (set.size() > 0) {
			for (int i = 0; i < set.size(); i++) {
				System.out.print(" " + set.get(i).edgeName + ", weight: " + set.get(i).weight + ",");
			}
		} else {
			System.out.print(" empty");
		}
		System.out.println("");
	}

	public static String getEdgeListName(ArrayList<Edge> edgeList) {
		String listName = "";
		if (edgeList == bestEdges) {
			listName = "BestEdges";
		} else if (edgeList == theseEdges) {
			listName = "TheseEdges";
		} else if (edgeList == edgesToSort) {
			listName = "EdgesToSort";
		} else {
			listName = "Untitled List";
		}
		return listName;
	}

	public static boolean canAddEdgeToList(Edge edge, GNode nodeBeingChecked) {
		if ((edge.end == nodeBeingChecked) || (edge.start == nodeBeingChecked)) {
			return true;
		} else {
			// skip this one as it starts/ends in nodeBeingChecked
			System.out.println(
					"(Skipping " + edge.edgeName + " because it doesn't go to / from  " + nodeBeingChecked.place + ")");
			return false;
		}
	}

	public static void expandToFrontier(GNode nodeBeingChecked, int numberOfChildren) {
		// expand chosen GNode, add resulting children to frontier only if not in
		// frontier or explored set
		for (int i = 0; i < numberOfChildren; i++) {
			if (!frontier.contains(nodeBeingChecked.children.get(i))
					&& !explored.contains(nodeBeingChecked.children.get(i))) {
				frontier.add(nodeBeingChecked.children.get(i));
			}
		}
		// displayNodeList(frontier);
	}

	public static ArrayList<GNode> getNodeList(String listName) {
		if (listName == "frontier") {
			set = frontier;
		} else if (listName == "explored") {
			set = explored;
		} else if (listName == "solution") {
		} else {
			System.out.println("No set found");
		}
		return set;
	}

	public static void displayNodeList(String listName) {
		getNodeList(listName);
		System.out.print(listName + ": ");
		if (set.size() > 0) {
			for (int i = 0; i < set.size(); i++) {
				System.out.print(i + ". " + set.get(i).place + " ");
			}
		} else {
			System.out.print("empty");
		}
		System.out.println("");
		// setName = "";
	}

	public static void main(String[] args) {
		GSearch.reachGoal();
	}

}
