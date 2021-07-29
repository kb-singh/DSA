import java.util.*;

public class Tarjan_SCC_AdjacencyList {

	private static LinkedList<Integer>[] graph;
	private static int id, sccCount;
	private static int[] low, node_id;
	private static boolean[] onStack;
	private static Stack<Integer> stack;

	public static int[] Tarjac_Solver(int n) {
		id = 0;
		sccCount = 0;
		low = new int[n];
		node_id = new int[n];
		onStack = new boolean[n];
		stack = new Stack<>();
		Arrays.fill(node_id, -1); // -1 means not yet visited

		for (int i = 0; i < n; i++) {
			if (node_id[i] == -1) {
				Explore_via_dfs(i);
			}
		}

		System.out.println("Total " + sccCount + " present in this graph");
		return low;
	}

	public static void Explore_via_dfs(int at) {
		node_id[at] = low[at] = id; // assign id and low-value to node
		id++;

		stack.push(at);
		onStack[at] = true;

		LinkedList<Integer> neighbours = graph[at];
		for (Integer to : neighbours) {
			if (node_id[to] == -1) {
				Explore_via_dfs(to);
			}

			// On Backtracking
			if (onStack[to]) {
				low[at] = Math.min(low[at], low[to]); // Propagation of low link Value
			}
		}

		// Once we found the Source of SCC i.e. if low-link value is equal to node_id
		if (low[at] == node_id[at]) {
			for (int node = stack.pop();; node = stack.pop()) {
				onStack[node] = false; // mark node not onstack
				low[node] = node_id[at];
				if (node == at)
					break;
			}

			// Since, This is the Source Node of some SCC in graph, hence increase scc count
			sccCount++;
		}
	}

	public static void addEdge(int from, int to) {
		graph[from].add(to);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		int n = 8;
		graph = new LinkedList[n];

		for (int i = 0; i < n; i++) {
			graph[i] = new LinkedList<>();
		}

		addEdge(6, 0);
		addEdge(6, 2);
		addEdge(3, 4);
		addEdge(6, 4);
		addEdge(2, 0);
		addEdge(0, 1);
		addEdge(4, 5);
		addEdge(5, 6);
		addEdge(3, 7);
		addEdge(7, 5);
		addEdge(1, 2);
		addEdge(7, 3);
		addEdge(5, 0);

		int[] sccs = Tarjac_Solver(n);
		Map<Integer, List<Integer>> multimap = new HashMap<>();
		for (int i = 0; i < n; i++) {
			if (!multimap.containsKey(sccs[i]))
				multimap.put(sccs[i], new ArrayList<>());
			multimap.get(sccs[i]).add(i);
		}

		for (List<Integer> scc : multimap.values()) {
			System.out.println("Nodes: " + scc + " form a Strongly Connected Component.");
		}
	}
}
