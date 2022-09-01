package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q14730 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 항의 개수
		int result = 0;
		
		for (int i = 0; i < n; i++) {
			int c = sc.nextInt(); // 계수
			int k = sc.nextInt(); // 차수
			
			result += c * k;
		}
		
		System.out.println(result);
	}

}
