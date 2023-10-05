import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int start, end, weight;

        Node(int start, int end, int weight) {
            super();
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }

    static int V, E, answer, edgeCount;
    static int[] parents;
    static ArrayList<Node> nodeList;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    public void solution() throws Exception {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        nodeList = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int departure = Integer.parseInt(st.nextToken());
            int arrival = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodeList.add(new Node(departure, arrival, weight));
        }

        makeParentsNodeArray();

        edgeCount = 0;
        answer = 0;

        Collections.sort(nodeList);
        /////////////////////// 구현부

        for (Node node : nodeList) {
            if (!isHavingSameParent(node.start, node.end)) {
                union(node.start, node.end);
                answer += node.weight;
                edgeCount += 1;

                if (edgeCount == E - 1) break;
            }
        }

        /////////////////////// 출력부
        System.out.println(answer);
    }

    public static void makeParentsNodeArray() {
        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) parents[i] = i;
    }

    public static int findSet(int startNode) {
        if (parents[startNode] == startNode) return startNode;
        return parents[startNode] = findSet(parents[startNode]);
    }

    public static boolean isHavingSameParent(int firstNode, int secondNode) {
        firstNode = findSet(firstNode);
        secondNode = findSet(secondNode);

        return firstNode == secondNode;
    }

    public static void union(int firstNode, int secondNode) {
        firstNode = findSet(firstNode);
        secondNode = findSet(secondNode);

        if (firstNode != secondNode) parents[secondNode] = firstNode;
    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}