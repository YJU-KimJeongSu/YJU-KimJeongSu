package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KJS_Q1012 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int t = Integer.parseInt(st.nextToken()); // 테스트 케이스
		
		
		for (int temp = 0; temp < t; temp++) {
			st = new StringTokenizer(bf.readLine());
			int m = Integer.parseInt(st.nextToken()); // 가로
			int n = Integer.parseInt(st.nextToken()); // 세로
			int k = Integer.parseInt(st.nextToken()); // 배추 개수
			int count = 0; // 배추흰지렁이 수

			int[][] field = new int[m][n];
			boolean[][] visited = new boolean[m][n];
			// 초기화
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

			// 1. 값이 1인 곳 찾아서 상하좌우 4칸을 dfs로 0으로 바꿔주고
			// 2. count++
			// 3. 값이 1인 곳이 남아있으면 다시 1번으로
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
		int[] dx = { -1, 1, 0, 0 }; // 좌우
		int[] dy = { 0, 0, -1, 1 }; // 상하
		visited[w][h] = true;
		field[w][h] = 0;
		
//		for (int i = 0; i < field.length; i++) {
//			for (int j = 0; j < field[i].length; j++) {
//				if (field[i][j] == 1) dfs(field, visited, i, j);
//			}
//		}
		
		for (int i = 0; i < dy.length; i++) {
			// 이렇게하면 i = 1일때 좌, i = 2일때 우, i = 3일때 상, i = 4일때 하 탐색하고 dfs 종료
			int x = w + dx[i];
			int y = h + dy[i];
			
			// 인덱스 에러 방지용
			if (x >= 0 && y >= 0 && x < field.length && y < field[0].length) {
				if (field[x][y] == 1 && visited[x][y] == false) dfs(field, visited, x, y);
			}
		}
	}

}
