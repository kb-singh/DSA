import java.util.*;

public class BFS_AdjacencyList {

	private static LinkedList<Integer>[] graph;
	private static int INFINITY = Integer.MAX_VALUE;

	public static void BFS(int source, int numNodes) {
		Queue<Integer> queue = new LinkedList<>();
		int[] dist = new int[numNodes]; // used to store element at particular layer
		Arrays.fill(dist, INFINITY);

		dist[source] = 0; // source element is at layer 0
		queue.add(source);

		System.out.println("BFS Graph Traversal Order :->");
		while (!queue.isEmpty()) {
			int at = queue.poll();
			System.out.print(at + " ");
			LinkedList<Integer> neighbours = graph[at];
			for (Integer neighbour : neighbours) {
				if (dist[neighbour] == INFINITY) {
					queue.add(neighbour);
					dist[neighbour] = dist[at] + 1;
				}
			}
		}

		System.out.println();
		for (int i = 0; i < dist.length; i++) {
			System.out.println(i + " at " + dist[i] + " nth");
		}
	}

	public static void addEdge(int from, int to) {
		graph[from].add(to);
	}

	public static void main(String[] args) {
		int n = 4;
		graph = new LinkedList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new LinkedList<>();
		}
		addEdge(0, 1);
		addEdge(0, 2);
		addEdge(1, 2);
		addEdge(2, 0);
		addEdge(2, 3);
		addEdge(3, 3);

		BFS(2, n);
	}
}
