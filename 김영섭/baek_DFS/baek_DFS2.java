import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static class Node implements Comparable<Node>{
        int no;
        int th; 
        
        List<Node> nears;
        
        Node(int no) {
            this.no = no;
            nears = new ArrayList<> ();
            this.th = 0;
        }
        
        public void addNear(Node node) {
            if (nears.size() == 0) {
                nears.add(node);
                return;
            }
            nears.add(node);
        }
        
        @Override
        public int compareTo(Node o) {
        	if (this.no > o.no) return -1;
        	return 1;
        }
        
        public void printNears() {
            System.out.print("Node" + no + " nears : ");
            for (int i = 0; i < nears.size(); i++) {
                System.out.print(nears.get(i).no + " ");
            }
            System.out.println();
        }
    }
    
    static int N, M, R, TH;
    static Node[] NODES;
    static boolean[] VISITED;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        TH = 1;
        
        NODES = new Node[N+1];
        VISITED = new boolean[N+1];
        
        for (int i = 1; i <= N; i++) {
            NODES[i] = new Node(i);
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            NODES[nodeB].addNear(NODES[nodeA]);
            NODES[nodeA].addNear(NODES[nodeB]);
        }
        
        for (int i = 1; i <= N; i++) {
            Collections.sort(NODES[i].nears);;
        }
        
        dfs(NODES[R]);
        
        for (int i = 1; i <= N; i++) {
            sb.append(NODES[i].th);
            sb.append('\n');
        }
        
        System.out.println(sb.toString());
        br.close();
    }
    
    public static void dfs(Node curr) {
        if (VISITED[curr.no]) return ;
        
        curr.th = TH++;
        VISITED[curr.no] = true;
        
        for (int i = 0; i < curr.nears.size(); i++) {
            dfs(curr.nears.get(i));
        }
    }
}
