import java.util.*;

public class dfs_adjacenylist {

	static LinkedList<Integer>[] adj_list;

	public static void Explore_via_dfs(int source) {
		Set<Integer> visited = new HashSet<>();
		Stack<Integer> stack = new Stack<>();
		stack.push(source);

		while (!stack.isEmpty()) {
			int vertex = stack.pop();
			if (!visited.contains(vertex)) {
				System.out.println(vertex);
				visited.add(vertex);
			}
			Iterator<Integer> itr = adj_list[vertex].iterator();
			while (itr.hasNext()) {
				int v = itr.next();
				if (!visited.contains(v)) {
					stack.push(v);
				}
			}
		}
	}

	static void addEdge(int x, int y) {
		adj_list[x].add(y);
	}

	public static void main(String[] args) {
		int vertex_count = 4;
		adj_list = new LinkedList[vertex_count + 1];

		// Initialize all Empty LinkedList
		for (int i = 0; i <= vertex_count; i++) {
			adj_list[i] = new LinkedList<>();
		}

		addEdge(1, 2);
		addEdge(2, 1);

		addEdge(3, 2);
		addEdge(2, 3);

		addEdge(4, 3);
		addEdge(3, 4);

		addEdge(1, 4);
		addEdge(4, 1);

		Explore_via_dfs(1);
	}
}
