package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class KJS_Q6198 {
	static class Building {
		int canLook;
		int height;
		
		public Building(int canLook, int height) {
			this.canLook = canLook;
			this.height = height;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		
		Stack<Building> buildings = new Stack<>();
		long result = 0;
		
		for (int i = 0; i < count; i++) {
			int height = Integer.parseInt(br.readLine());
			
			while (buildings.size() > 0 && buildings.peek().height <= height) {
				buildings.pop();
			}
			buildings.push(new Building(0, height));
			
			// 시간초과로 알고리즘 변경
//			// 리스트 돌면서 이번에 입력받은 높이랑 비교
//			for (int j = 1; j < buildings.size(); j++) {
//				Building b = buildings.get(j-1);
//				if (b.height > height) { // 더 낮은 건물이 들어오면 canLook 증가
//					b.canLook++;
//				} else { // 더 높은 건물이 들어오면 앞으로 계속 못보니 리스트에서 제거
//					result += b.canLook;
//					buildings.remove(b);
//				}
//			}
			
			result += buildings.size()-1;
		}
		
		// 시간초과로 알고리즘 변경
//		for (int i = 0; i < buildings.size(); i++) {
//			Building b = buildings.get(i);
//			result += b.canLook;
//			buildings.remove(b);
//		}
		
		System.out.println(result);
	}

}
