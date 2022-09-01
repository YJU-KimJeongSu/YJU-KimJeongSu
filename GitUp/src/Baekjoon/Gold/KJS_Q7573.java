package Baekjoon.Gold;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class KJS_Q7573 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		ArrayList<Point> throwingP = new ArrayList<Point>();
		st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()); // 모눈종이 크기
		int l = Integer.parseInt(st.nextToken()); // 그물 길이
		int m = Integer.parseInt(st.nextToken()); // 물고기 수
		int max = 0;
		int temp = 0;
		Point[] p = new Point[m];
		
		// 물고기 위치 받기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			p[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// 그물을 던져야 할 위치 정하기
		// 물고기 위치(p[]) && 물고기의 교차점
		for (int i = 0; i < p.length; i++) {
			throwingP.add(p[i]);
		}
		// 한 점을 기준으로 잡고, 다른 모든 점이랑 비교해보면서 교차점 찾기
		for (int i = 0; i < p.length-1; i++) {
			for (int j = i+1; j < p.length; j++) {
				int tempX = p[i].x;
				int tempY = p[i].y;
				if (p[j].x < p[i].x) tempX = p[j].x;
				if (p[j].y < p[i].y) tempY = p[j].y;
				throwingP.add(new Point(tempX, tempY));
			}
		}
		
		// 그물 모양 정하기. l = (i + j) * 2  // i = x길이, j = y길이
		for (int i = 1; i < l/2; i++) {
			int j = l/2 - i;
			temp = Search(p, m, i, j, throwingP);
			if (temp > max) max = temp;
		}
		
		System.out.println(max);
	}
	
	// 각 모양마다 모든 물고기 좌표에 대입하면서 물고기 찾기
	// 물고기 위치에서만 그물을 던지면 안되고, 교차점에서도 던져봐야함
	static int Search(Point p[], int m, int x, int y, ArrayList<Point> throwingP) {
		int maxFish = 0; // 해당 모양으로 찾을 수 있는 최대 물고기 수
		int fish = 0;
		
		// 각 물고기마다 대입해보는 반복문
		for (int i = 0; i < throwingP.size(); i++) {
			// 대입했을 때 물고기가 몇마리 있는지 찾는 반복문
			fish = 0;
			for (int j = 0; j < m; j++) {
				// 모든 물고기의 x좌표(p[j].x)가 현재 시작점(p[i].x)보다 크거나 같고
				// 그물 끝점(=현재 시작점+그물크기(p[i].x+x))보다 작거나 같으면 fish++
				// y도 마찬가지
//				if ((p[j].x >= p[i].x && p[j].x <= p[i].x+x) &&
//					(p[j].y >= p[i].y && p[j].y <= p[i].y+y)) fish++;
				Point tempP = (Point) throwingP.get(i);
				if ((p[j].x >= tempP.x && p[j].x <= tempP.x+x) &&
					(p[j].y >= tempP.y && p[j].y <= tempP.y+y)) fish++;
			}
			if (fish > maxFish) maxFish = fish;
		}
		return maxFish;
	}

}
