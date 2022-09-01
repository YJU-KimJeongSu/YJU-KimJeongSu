package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q17614 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();		
		int count = 0;
		
		for (int i = 0; i <= n; i++) {
			String temp = Integer.toString(i);
			
			for (int j = 0; j < temp.length(); j++) {
				if (temp.charAt(j) == '3' || temp.charAt(j) == '6' || temp.charAt(j) == '9') count++;
			}
		}
		
		System.out.println(count);
	}

}
/* 20220606
 * 문제 이해도 : 3 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 10분
 */
