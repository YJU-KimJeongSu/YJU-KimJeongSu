package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KJS_Q1260_Array {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // ���� ����
		int m = Integer.parseInt(st.nextToken()); // ���� ����
		int v = Integer.parseInt(st.nextToken()); // ������
		
		int[][] adj = new int[n+1][n+1]; // 0�� �ε����� �Ⱦ�
		boolean[] visited = new boolean[n+1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int temp = Integer.parseInt(st.nextToken());
			int temp2 = Integer.parseInt(st.nextToken());
			
			// ������ ������̹Ƿ�
			adj[temp][temp2] = 1;
			adj[temp2][temp] = 1;
		}
		
		dfs(adj, v, visited);
		
		// �ѹ� �������� �ʱ�ȭ
		for (int i = 0; i < visited.length; i++) { visited[i] = false; }
		System.out.println();
		bfs(adj, v, visited);
	}
	
	static void dfs(int[][] adj, int now, boolean[] visited) {
		visited[now] = true;
		System.out.print(now + " ");
		
		for (int i = 1; i < adj.length; i++) {
			if (adj[now][i] == 1 && visited[i] == false) {
				dfs(adj, i, visited);
			}
		}
	}
	
	static void bfs(int[][] adj, int now, boolean[] visited) {
		visited[now] = true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(now);
		
		while (!q.isEmpty()) {
			int temp = q.poll();
			System.out.print(temp + " ");
			
			for (int i = 1; i < adj.length; i++) {
				if (adj[temp][i] == 1 && visited[i] == false) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
	}

}
