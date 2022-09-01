package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q2480 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		// 다 같은 경우
		if (a == b && b == c) {
			System.out.println(10000 + (a*1000));
		}
		// 2개만 같은 경우
		else if (a == b || a == c) {
			System.out.println(1000 + (a*100));
		}
		else if (b == c) {
			System.out.println(1000 + (b*100));
		}
		// 다 다른 경우
		else {
			if (a > b && a > c) System.out.println(a*100);
			else if (b > c && b > a) System.out.println(b*100);
			else if (c > a && c > b) System.out.println(c*100);
		}
	}

}
/* 20220514
 * 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 6분
 */