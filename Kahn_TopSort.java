import java.util.*;

public class Kahn_TopSort {

	// Adjacency List Graph
	static LinkedList<Integer>[] graph;

	public static int[] TopSort_via_Kahn(int n) {

		// Counting In-degree for every Node
		// Source Node has 0 In-degree i.e. not dependency
		int[] in_degree = new int[n];
		for (int i = 0; i < n; i++) {
			LinkedList<Integer> neighbours = graph[i];
			for (Integer neighbour : neighbours) {
				in_degree[neighbour]++;
			}
		}

		// Queue is part of Kahn algorithm
		Queue<Integer> q = new LinkedList<>();

		// Add all Source Node in Queue i.e. Nodes with no dependency or 0 in-degree
		for (int i = 0; i < in_degree.length; i++) {
			if (in_degree[i] == 0) {
				q.offer(i);
			}
		}

		int index = 0;
		int[] top_sort = new int[n];
		while (!q.isEmpty()) {
			int at = q.poll();
			top_sort[index++] = at;

			// Reduce in-degree of adjacent neighbours since they are no more dependent on
			// 'at' node
			LinkedList<Integer> neighbours = graph[at];
			for (Integer neighbour : neighbours) {
				in_degree[neighbour]--;

				// If any neighbour becomes the Source Node with 0 in-degree, then add to queue
				if (in_degree[neighbour] == 0) {
					q.offer(neighbour);
				}
			}
		}

		if (index != n) {
			System.out.println("Thier Exist a Cycle in graph");
			return null;
		}

		return top_sort;
	}

	// Add directed Edges
	public static void addDirectedEdge(int from, int to) {
		graph[from].add(to);
	}

	public static void main(String[] args) {
		int n = 14;
		graph = new LinkedList[n];

		for (int i = 0; i < n; i++) {
			graph[i] = new LinkedList<>();
		}

		addDirectedEdge(0, 2);
		addDirectedEdge(0, 3);
		addDirectedEdge(0, 6);
		addDirectedEdge(1, 4);
		addDirectedEdge(2, 6);
		addDirectedEdge(3, 1);
		addDirectedEdge(3, 4);
		addDirectedEdge(4, 5);
		addDirectedEdge(4, 8);
		addDirectedEdge(6, 7);
		addDirectedEdge(6, 11);
		addDirectedEdge(7, 4);
		addDirectedEdge(7, 12);
		addDirectedEdge(9, 2);
		addDirectedEdge(9, 10);
		addDirectedEdge(10, 6);
		addDirectedEdge(11, 12);
		addDirectedEdge(12, 8);

		int[] ans = TopSort_via_Kahn(n);
		for (int x : ans) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
}
