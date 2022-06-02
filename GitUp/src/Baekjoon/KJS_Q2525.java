package Baekjoon;

import java.util.Scanner;

public class KJS_Q2525 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int h = sc.nextInt();
		int m = sc.nextInt();
		int cookTime = sc.nextInt();
		
		m += cookTime;
		
		while (m >= 60) { m -= 60; h++; }
		while (h >= 24) { h -= 24; }
		
		System.out.println(h + " " + m);
	}

}
/* 20220514
 * 문제 이해도 : 4 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 7분
 */