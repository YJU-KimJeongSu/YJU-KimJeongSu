package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KJS_Q18352 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken()); // ���� ����
		int m = Integer.parseInt(st.nextToken()); // ���� ����
		int k = Integer.parseInt(st.nextToken()); // ã�ƾ� �ϴ� �Ÿ�
		int x = Integer.parseInt(st.nextToken()); // ��� ���� ��ȣ
		boolean[] visited = new boolean[n+1]; // 0�� �ε����� �Ÿ��� k�� ���ð� �ֳ� ���� üũ
		int[] distance = new int[n+1];
		
		ArrayList<ArrayList<Integer>> city = new ArrayList<ArrayList<Integer>>();
		// 0�� �ε��� �Ⱦ�
		for (int i = 0; i < n+1; i++) {
			city.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int t = Integer.parseInt(st.nextToken());
			int t2 = Integer.parseInt(st.nextToken());
			city.get(t).add(t2);
		}
		
		bfs(city, visited, x, distance);
		
		for (int i = 1; i < distance.length; i++) {
			if (distance[i] == k) {
				System.out.println(i);
				visited[0] = true;
			}
		}
		if (visited[0] == false) System.out.println("-1");
	}
	
	static void bfs(ArrayList<ArrayList<Integer>> city, boolean[] visited, int now, int[] distance) {
		visited[now] = true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(now);
		
		while (!q.isEmpty()) {
			int t = q.poll();
			
			// �ش� ArrayList �ȿ� �ִ� ���ڵ� Ž��
			Iterator<Integer> iter = city.get(t).iterator();
			while (iter.hasNext()) {
				int next = iter.next();
				if (visited[next] == false) {
					q.add(next);
					visited[next] = true;
					distance[next] = distance[t] + 1;
				}
			}
		}
	}

}
