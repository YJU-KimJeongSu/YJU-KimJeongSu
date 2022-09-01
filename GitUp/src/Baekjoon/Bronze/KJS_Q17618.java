package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q17618 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int count = 0;
		
		for (int i = 1; i <= n; i++) {
			// 한자리씩 더하기
			String temp = Integer.toString(i);
			char[] temp2 = new char[temp.length()];
			for (int j = 0; j < temp2.length; j++) {
				temp2[j] = temp.charAt(j);
			}
			
			int sum = 0;
			for (int j = 0; j < temp2.length; j++) {
				sum += (int)(temp2[j]-'0'); // 아스키 코드로 사용해서 차이 이용
			}
			
			// 신기한 수인지 찾아보기
			if (i % sum == 0) count++;
		}
		
		System.out.println(count);
	}

}
/* 20220606
 * 문제 이해도 : 3 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 16분
 */