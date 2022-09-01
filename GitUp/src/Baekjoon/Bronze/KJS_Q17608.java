package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q17608 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] h = new int[n];
		
		for (int i = 0; i < h.length; i++) {
			h[i] = sc.nextInt();
		}
		
		int max = h[n-1];
		int canLook = 1;
		
		for (int i = n-2; i >= 0; i--) {
			if (max < h[i])  {
				canLook++;
				max = h[i];
			}
		}
		
		System.out.println(canLook);
	}

}
/* 20220531
 * 문제 이해도 : 4 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 7분
 */