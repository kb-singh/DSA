
import java.util.*;

public class ConnectedComponent {

	static LinkedList<Integer>[] adj_list;
	static Set<Integer> visited = new HashSet<>();

	public static void addEdge(int x, int y) {
		adj_list[x].add(y);
		adj_list[y].add(x);
	}

	public static void getConnectedComponent(int source, int vertex_count) {
		int count = 0;
		for (int v = 1; v <= vertex_count; v++) {
			if (!visited.contains(v)) {
				Expore_node_via_dfs(v);
				System.out.println();
				count++;
			}
		}
		System.out.println("Hence, Total " + count + " connected Component");
	}

	public static void Expore_node_via_dfs(int node) {
		Stack<Integer> stack = new Stack<>();
		stack.push(node);
		while (!stack.isEmpty()) {
			int v = stack.pop();
			if (!visited.contains(v)) {
				System.out.print(v + " ");
				visited.add(v);
			}
			Iterator<Integer> itr = adj_list[v].iterator();
			while (itr.hasNext()) {
				int n = itr.next();
				if (!visited.contains(n)) {
					stack.push(n);
				}
			}
		}
	}

	public static void main(String[] args) {
		int vertex = 4;
		int edges = 2;
		adj_list = new LinkedList[vertex + 1];

		// Initialize all All Empty LinkedList
		for (int i = 0; i <= vertex; i++) {
			adj_list[i] = new LinkedList<>();
		}

		addEdge(1, 2);
		addEdge(2, 1);

		addEdge(3, 2);
		addEdge(2, 3);

		getConnectedComponent(1, vertex);
	}
}
