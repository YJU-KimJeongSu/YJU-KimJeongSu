package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q24883 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String c = sc.next();
		
		if (c.equals("n") || c.equals("N")) System.out.println("Naver D2");
		else System.out.println("Naver Whale");
	}

}
/* 20220517
 * 문제 이해도 : 3 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 4분
 */