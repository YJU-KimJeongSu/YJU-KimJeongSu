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
		
		int n = Integer.parseInt(st.nextToken()); // ���� ����
		int m = Integer.parseInt(st.nextToken()); // ���� ����
		int v = Integer.parseInt(st.nextToken()); // ������
		
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		
		// �ٱ� ArrayList = ��� / ���� ArrayList = ����� ���� ����
		// 1�� �ε����� 1�� ��带 �ֱ� ���� 1�� �� �����. 0���� �Ⱦ�
		for (int i = 0; i < n+1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		boolean[] visited = new boolean[n+1];
		
		// ����� �׷����̹Ƿ� ������ �ݴ�ε� �������� ��
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int temp = Integer.parseInt(st.nextToken());
			int temp2 = Integer.parseInt(st.nextToken());
			adj.get(temp).add(temp2);
			adj.get(temp2).add(temp);
		}
		// �׷��� ��� �ϼ�. �̱��� ���� ����
		
		// �湮�� �� �ִ� ������ �������� ��ȣ�� ���� �ͺ��� �湮�ϹǷ� ����
		for (int i = 0; i < adj.size(); i++) {
			Collections.sort(adj.get(i));
		}
		
		dfs(adj, visited, v);
		System.out.println();
		
		// �Ʒ� dfs���� visited �ǵ������ �ʱ�ȭ
		for (int i = 0; i < visited.length; i++) { visited[i] = false; }
		bfs(adj, visited, v);
	}
	
	static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int now) {
		visited[now] = true;
		System.out.print(now + " ");
		
		// �ٱ� ArrayList(���)���� ����(���� ����)�� �ʿ���
		Iterator<Integer> iter = adj.get(now).iterator();
		
		for (int i = 0; i < adj.get(now).size(); i++) {
			int temp = iter.next();
			
			// �̹� ��忡 �湮���� �ʾ��� ���(visited�� Ȯ��) �ش� ��忡�� dfs �޼ҵ� �ٽ� ���� 
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
