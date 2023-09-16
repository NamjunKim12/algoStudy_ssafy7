import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // Node 정렬을 위한 Comparable 사용
    // 사실 Node 구현하지 않고 ArrayList의 배열로 작성하는게 더 좋은 풀이 같습니다.
    // Collection sort로 바로 정렬 가능하니까
    // 이 부분에서 걸려서 처음에 시간초과 많이 났었는데
    // 처음에 정렬하는 과정에서 O(n^2) 걸리는 정렬 방식을 사용해서 였습니다. 
    // Comparable 사용해서 Collection sort의 O(n log n) 으로 정렬하니 PASS 했습니다
    static class Node implements Comparable<Node>{
        // 노드 구성은 이후 문제에 따라 조금씩 바뀌는데 
        // 해당 문제의 경우 탐색 순서를 기록해야하므로, 탐색 순서를 저장하는 th 변수와
        // 노드의 순서를 저장하는 no 변수를 사용하였습니다. 
        // 이후 깊이를 저장하는 문제에서는 depth 변수를 추가해서 사용합니다. 
        int no;
        int th; 
        
        // 인접한 노드를 저장하는 리스트입니다. 
        List<Node> nears;
        
        // 생성자. 숫자를 받아서 기록하고, 인접 노드 저장 리스트를 초기화하고, 순서를 0으로 (기본값) 초기화해줍니다. 
        Node(int no) {
            this.no = no;
            nears = new ArrayList<> ();
            this.th = 0;
        }
        
        // 인접 노드 리스트에 노드를 추가하는 메서드
        public void addNear(Node node) {
            if (nears.size() == 0) {
                nears.add(node);
                return;
            }
            // 사실 위 if문은 필요 없습니다. 
            // 전에 짜던 코드가 남아있어요. 
            nears.add(node);
        }
        
        // implements 해온 Comparable의 compareTo override. 
        @Override
        public int compareTo(Node o) {
            // 만약 이번 노드가 다른 비교 노드보다 순서가 뒤면 +를 리턴.
            // 반대의 경우 - 리턴.
            // 이렇게 작성하면 노드 간 > < 등 비교 연산자 사용이 가능해지고, 
            // Collection sort를 이용할 수 있습니다. 
            // 만약 노드를 구성하지 않고, ArrayList의 배열을 사용했다면
            // 굳이 작성하지 않아도 되는 부분입니다... 
        	if (this.no > o.no) return 1;
        	return -1;
        }
        
        // 인접 노드 출력 메서드. 노드 입력 잘 되었는지 확인용.
        public void printNears() {
            System.out.print("Node" + no + " nears : ");
            for (int i = 0; i < nears.size(); i++) {
                System.out.print(nears.get(i).no + " ");
            }
            System.out.println();
        }
    }
    
    // TH => 전역으로 설정. 노드에 th를 입력할때마다 하나씩 키워서 사용 예정.
    static int N, M, R, TH;

    // 노드를 저장할 배열 
    static Node[] NODES;
    // 방문 기록
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
        
        // 노드 생성. 0번째 칸은 비워놓습니다. (null)
        for (int i = 1; i <= N; i++) {
            NODES[i] = new Node(i);
        }
        
        // 간선 정보를 받아 인접 노드 기록
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            NODES[nodeB].addNear(NODES[nodeA]);
            NODES[nodeA].addNear(NODES[nodeB]);
        }
        
        // 각 노드의 인접 노드 리스트 정렬. 
        for (int i = 1; i <= N; i++) {
            Collections.sort(NODES[i].nears);;
        }
        
        // 시작 노드 ㄱㄱ
        dfs(NODES[R]);
        
        // 1번째 노드부터 N번째 노드까지 방문 순서를 기록합니다. 
        for (int i = 1; i <= N; i++) {
            sb.append(NODES[i].th);
            sb.append('\n');
        }
        
        System.out.println(sb.toString());
        br.close();
    }
    
    public static void dfs(Node curr) {
        // 해당 노드에 방문했었다면 종료.
        if (VISITED[curr.no]) return ;
        
        // 방문 안했으면 방문 순서 기록하고 TH++
        curr.th = TH++;
        // 방문 처리.
        VISITED[curr.no] = true;
        
        // 다음 deep으로 재귀 호출. 
        for (int i = 0; i < curr.nears.size(); i++) {
            dfs(curr.nears.get(i));
        }
    }

    // depth를 기록해야하는 문제 같은 경우, 
    // 인자에 int depth를 추가해서
    // 다음 depth로 넘어가는 재귀를 호출할 때 dfs(curr.nears.get(i), depth + 1);
    // 해서 기록하는 방식으로 작성했습니다. 
}
