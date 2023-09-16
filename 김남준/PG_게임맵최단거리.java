import java.util.LinkedList;
import java.util.Queue;

public class 게임맵최단거리 {
    // 우, 좌, 상, 하
    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0 ,0};

    public int solution(int[][] maps) {
        // 맵을 나타내는 n * m 크기의 배열과 방문 여부를 저장할 배열 선언
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();

        // 시작점을 큐에 넣고 방문처리
        // 큐에 삽입할 원소는 시작점의 x,y 좌표(x, y)와 현재까지의 이동량를 순서대로 저장하는 배열
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;

        // BFS 수행, 큐가 빌 때까지 반복
        while(!queue.isEmpty()) {
            // 하나를 빼서 현재 위치로 설정
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int distance = cur[2];

            // 도착점에 도달한 경우 answer에 현재까지의 거리 저장 후 종료
            if(x == n - 1 && y == m - 1) {
                answer = distance;
                break;
            }

            // 4방향 탐색
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                // 맵 초과 or 이미 방문한 경우 or 벽인 경우 pass
                if(nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if(visited[nx][ny] || maps[nx][ny] == 0) continue;

                // 방문 처리 후 큐에 삽입
                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny, distance + 1});
            }
        }

        return answer == 0 ? -1 : answer;
    }
}