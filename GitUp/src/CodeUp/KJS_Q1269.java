package CodeUp;

import java.util.Scanner;

public class KJS_Q1269 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt(); // 시작 값
		int b = sc.nextInt(); // 곱할 값
		int c = sc.nextInt(); // 더할 값
		int n = sc.nextInt(); // 몇 번째 항
		int result = a;
		
		for (int i = 1; i < n; i++) {
			result = result * b + c;
		}
		
		System.out.println(result);
	}

}
/* 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 4분
 */