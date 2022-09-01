package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q10952 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = 1, b = 1;
		while (true) {
			a = sc.nextInt();
			b = sc.nextInt();
			if (a == 0 && b == 0) break;
			System.out.println(a+b);
		}
	}

}
/* 20220521
 * 문제 이해도 : 4 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 3분
 */