import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static Room ROOT;
	static Cave CAVE;
	static int N;
	
	// 사실상 노드 클래스 
	static class Room {
		// food = data
		String food;
		// 하위 방을 저장하는 lwr
		// HashSet 쓰면 더 편함 ㅋ 
		ArrayList<Room> lwr;
		
		public Room() {
			this.lwr = new ArrayList<> ();
		}
		
		public Room(String food) {
			this.food = food;
			this.lwr = new ArrayList<> ();
		}
	} 
	
	// 사실 전체 트리 클래스.
	// 위에서 작성한 노드(Room) 으로 구성.
	static class Cave {  
		public Cave() {}
		
		// 노드 추가 메서드
		// 재귀로 구성.
		// while 문으로 짜도 되고 ... 
		public void addRoom(Room curr, String[] foods, int stt, int end, int deep) {
			if (foods.length == deep) return ;
			
			int mid = (stt + end) / 2;
			
			// 내가 찾는거.
			if (stt > end) {
				curr.lwr.add(stt, new Room(foods[deep]));
				Room next = curr.lwr.get(stt);
				addRoom(next, foods, 0, next.lwr.size() -1, deep+1);
			}
			else if (cpr(curr.lwr.get(mid).food, foods[deep]) == 0) {
				Room next = curr.lwr.get(mid);
				addRoom(next, foods, 0, next.lwr.size() -1, deep+1);
			}
			else if (cpr(curr.lwr.get(mid).food, foods[deep]) > 0) {
				addRoom(curr, foods, mid + 1, end, deep);
			}
			else {
				addRoom(curr, foods, stt, mid - 1, deep);
			}
		}
		
		public int cpr(String s1, String s2) {
			return (s2.compareTo(s1));
		}
	}
	
	static StringBuilder ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ans = new StringBuilder();
		 
		N = Integer.parseInt(br.readLine());
		ROOT = new Room();
		CAVE = new Cave();
		
		for (int i = 0; i < N; i++) {
			CAVE.addRoom(ROOT, br.readLine().split(" "), 0, ROOT.lwr.size()-1, 1);
		}
		explore(ROOT, -1);
		
		System.out.println(ans);
	}
	
	static int cpr(String s1, String s2) {
		return (s1.compareTo(s2));
	}
	
	static void explore(Room room, int deep) {
		if (room.food != null) {
			for (int i = 0; i < deep; i++)
				ans.append("--");
			ans.append(room.food);
			ans.append('\n');
		}
		for (Room down : room.lwr) {
			explore(down, deep+1);
		}
	}
}