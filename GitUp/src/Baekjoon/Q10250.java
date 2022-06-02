package Baekjoon;

import java.util.Scanner;

public class Q10250 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt(); // 테스트 데이터
		int h; // 높이
		int w; // 가로
		int n; // 손님 수
		
		int c_h, c_w = 0;
		
		int[] hotel = new int[t];
		
		for (int i = 0; i < t; i++) {
			h = sc.nextInt();
			w = sc.nextInt();
			n = sc.nextInt();
			// 손님이 들어갈 층
			if (n % h != 0)  { c_h = n % h; }
			else { c_h = h; }
			// 손님이 들어갈 가로
			for (int j = 0; j <= w; j++) {
				if (n > h * (j-1) && n <= h*j) {
					c_w = j;
				}
			}
			// 최종적으로 들어갈 방
			if (c_h == 0) c_h = 1;
			hotel[i] = (c_h * 100) + c_w;
		}
		
		for (int i = 0; i < hotel.length; i++) {
			System.out.println(hotel[i]);
		}
	}

}
