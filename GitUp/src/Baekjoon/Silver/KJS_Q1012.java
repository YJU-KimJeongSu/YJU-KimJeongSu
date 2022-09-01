package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KJS_Q1012 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int t = Integer.parseInt(st.nextToken()); // �׽�Ʈ ���̽�
		
		
		for (int temp = 0; temp < t; temp++) {
			st = new StringTokenizer(bf.readLine());
			int m = Integer.parseInt(st.nextToken()); // ����
			int n = Integer.parseInt(st.nextToken()); // ����
			int k = Integer.parseInt(st.nextToken()); // ���� ����
			int count = 0; // ������������ ��

			int[][] field = new int[m][n];
			boolean[][] visited = new boolean[m][n];
			// �ʱ�ȭ
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field[i].length; j++) {
					field[i][j] = 0;
				}
			}

			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				field[a][b] = 1;
			}

			// 1. ���� 1�� �� ã�Ƽ� �����¿� 4ĭ�� dfs�� 0���� �ٲ��ְ�
			// 2. count++
			// 3. ���� 1�� ���� ���������� �ٽ� 1������
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field[i].length; j++) {
					if (field[i][j] == 1) {
						dfs(field, visited, i, j);
						count++;
					}				
				}
			}

			System.out.println(count);

			//		for (int i = 0; i < field.length; i++) {
			//			for (int j = 0; j < field[i].length; j++) {
			//				System.out.print(field[i][j]);
			//			}
			//			System.out.println();
			//		}
		}
		
		
	}
	
	static void dfs(int[][] field, boolean[][] visited, int w, int h) {
		int[] dx = { -1, 1, 0, 0 }; // �¿�
		int[] dy = { 0, 0, -1, 1 }; // ����
		visited[w][h] = true;
		field[w][h] = 0;
		
//		for (int i = 0; i < field.length; i++) {
//			for (int j = 0; j < field[i].length; j++) {
//				if (field[i][j] == 1) dfs(field, visited, i, j);
//			}
//		}
		
		for (int i = 0; i < dy.length; i++) {
			// �̷����ϸ� i = 1�϶� ��, i = 2�϶� ��, i = 3�϶� ��, i = 4�϶� �� Ž���ϰ� dfs ����
			int x = w + dx[i];
			int y = h + dy[i];
			
			// �ε��� ���� ������
			if (x >= 0 && y >= 0 && x < field.length && y < field[0].length) {
				if (field[x][y] == 1 && visited[x][y] == false) dfs(field, visited, x, y);
			}
		}
	}

}
