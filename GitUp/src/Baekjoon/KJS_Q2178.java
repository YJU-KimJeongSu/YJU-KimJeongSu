package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KJS_Q2178 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()); // 가로
		int m = Integer.parseInt(st.nextToken()); // 세로
		int[][] maze = new int[n][m];
		
		for (int i = 0; i < maze.length; i++) {
			String temp = bf.readLine();
			for (int j = 0; j < temp.length(); j++) {
				maze[i][j] = Integer.parseInt(temp.substring(j, j+1));
			}
		}
		
		System.out.println(bfs(maze));
	}
	
	static int bfs(int[][] maze) {
		int[] dx = { -1, 1, 0, 0 }; // 좌우
		int[] dy = { 0, 0, -1, 1 }; // 상하
		int nowX = 0;
		int nowY = 0;
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		
		visited[nowX][nowY] = true;
		q.add(new int[] {nowX, nowY});
		
		while (!q.isEmpty()) {
			int[] t = q.poll();
			nowX = t[0];
			nowY = t[1];
			
			// 상하좌우만 탐색
			for (int i = 0; i < 4; i++) {
				int x = nowX + dx[i];
				int y = nowY + dy[i];
				
				// 인덱스 에러 방지
				if (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length) {
					// 방문한 곳이 1이면 방문
					if (maze[x][y] >= 1 && visited[x][y] == false) {
						// 아랫줄 +1을 ++로 써서 20분 넘게 헤맸으니 주의
						maze[x][y] = maze[nowX][nowY]+1;
						q.add(new int[] {x, y});
						visited[x][y] = true;
					}
				}
			}
		}
		
		return maze[maze.length-1][maze[0].length-1];
	}

}
