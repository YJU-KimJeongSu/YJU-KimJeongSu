package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KJS_Q1260_ArrayList {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 정점 개수
		int m = Integer.parseInt(st.nextToken()); // 간선 개수
		int v = Integer.parseInt(st.nextToken()); // 시작점
		
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		
		// 바깥 ArrayList = 노드 / 안쪽 ArrayList = 노드의 연결 정보
		// 1번 인덱스에 1번 노드를 넣기 위해 1개 더 만들기. 0번은 안씀
		for (int i = 0; i < n+1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		boolean[] visited = new boolean[n+1];
		
		// 양방향 그래프이므로 받으면 반대로도 연결해줄 것
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int temp = Integer.parseInt(st.nextToken());
			int temp2 = Integer.parseInt(st.nextToken());
			adj.get(temp).add(temp2);
			adj.get(temp2).add(temp);
		}
		// 그래프 모양 완성. 이까지 문제 없음
		
		// 방문할 수 있는 정점이 여러개면 번호가 작은 것부터 방문하므로 정렬
		for (int i = 0; i < adj.size(); i++) {
			Collections.sort(adj.get(i));
		}
		
		dfs(adj, visited, v);
		System.out.println();
		
		// 아래 dfs에서 visited 건드렸으니 초기화
		for (int i = 0; i < visited.length; i++) { visited[i] = false; }
		bfs(adj, visited, v);
	}
	
	static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int now) {
		visited[now] = true;
		System.out.print(now + " ");
		
		// 바깥 ArrayList(노드)안의 원소(연결 정보)가 필요함
		Iterator<Integer> iter = adj.get(now).iterator();
		
		for (int i = 0; i < adj.get(now).size(); i++) {
			int temp = iter.next();
			
			// 이번 노드에 방문하지 않았을 경우(visited로 확인) 해당 노드에서 dfs 메소드 다시 실행 
			if (visited[temp] == false) dfs(adj, visited, temp);
		}
	}
	
	static void bfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int now) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(now);
		visited[now] = true;
		
//		Iterator<ArrayList<Integer>> iter = adj.iterator();
//		while (iter.hasNext()) {
//			Iterator<Integer> iter2 = iter.next().iterator();
//			while(iter2.hasNext()) {
//				int temp = iter2.next();
//				if (visited[temp] == false) q.add(temp);
//				visited[temp] = true;
//			}
//		}
		
		while (!q.isEmpty()) {
			int t = q.poll();
			System.out.print(t + " ");
			Iterator<Integer> iter = adj.get(t).iterator();
			while (iter.hasNext()) {
				int temp = iter.next();
				if (visited[temp] == false) q.add(temp);
				visited[temp] = true;
			}

		}
	}
}
