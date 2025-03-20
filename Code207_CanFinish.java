import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 */
public class Code207_CanFinish {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph();
        Map<Integer, GraphNode> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];
            if (!map.containsKey(from)) {
                map.put(from, new GraphNode(from));
                graph.nodes.add(map.get(from));
            }
            if (!map.containsKey(to)) {
                map.put(to, new GraphNode(to));
                graph.nodes.add(map.get(to));
            }
            GraphNode fromNode = map.get(from);
            GraphNode toNode = map.get(to);
            fromNode.nexts.add(toNode);
            toNode.in++;
        }

        Queue<GraphNode> queue = new LinkedList<>();
        for (GraphNode node:map.values()) {
            if (node.in == 0) {
                queue.add(node);
            }
        }

        Set<GraphNode> set = new HashSet<>();
        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            set.add(node);
            for (int i = 0; i < node.nexts.size(); i++) {
                GraphNode to = (GraphNode) node.nexts.get(i);
                to.in--;
                if (to.in == 0 && !set.contains(to)) {
                    queue.add(to);

                }
            }
        }


        return set.size() == graph.nodes.size();
    }

    class Graph {
        List<GraphNode> nodes;

        Graph() {
            this.nodes = new ArrayList<>();
        }
    }

    class GraphNode<Integer> {
        Integer val;
        GraphNode from;
        List<GraphNode> nexts;
        int in;

        GraphNode(Integer val) {
            this.nexts = new ArrayList<>();
            this.val = val;
            this.in = 0;
        }

        // 重写equals方法
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (obj instanceof GraphNode) {
                GraphNode node = (GraphNode) obj;
                return this.val.equals(node.val);
            }
            return false;
        }
    }
}
