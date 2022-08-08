package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class KJS_Q4963 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int count = 0;
			
			if (w == 0 && h == 0) break;
			
			int[][] island = new int[h][w]; // h w ����
			boolean[][] visited = new boolean[h][w];
			
			// ���ٿ� w���� h�� ��ŭ �Է� �ޱ�
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < w; j++) {
					int t = Integer.parseInt(st.nextToken());
					island[i][j] = t;
				}
			}
			
			// �� ���� ���� ����
			
			// ���� ��ġ ���� 8���� ������� dfs�ؼ� 0���� �ٲٰ�, ������ count++. ������ �� �ٸ� 1 ã�Ƽ� dfs
			for (int i = 0; i < island.length; i++) {
				for (int j = 0; j < island[i].length; j++) {
					if (island[i][j] == 1) {
						count++;
						dfs(island, visited, i, j);
					}
				}
			}
			System.out.println(count);
		}
	}
	
	public static void dfs(int[][] island, boolean[][] visited, int t1, int t2) {
		// �»� �� ��� �� ���� �� ���� ��
		int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
		int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
		visited[t1][t2] = true;
		island[t1][t2] = 0;
		
		for (int i = 0; i < 8; i++) {
			int tt1 = t1 + dx[i];
			int tt2 = t2 + dy[i];
			// �ε��� ���� ����
			if (tt1 >= 0 && tt2 >= 0 && tt1 < island.length && tt2 < island[0].length) {
				if (visited[tt1][tt2] == false && island[tt1][tt2] == 1) {
					dfs(island, visited, tt1, tt2);
				}
			}
		}
	}

}
