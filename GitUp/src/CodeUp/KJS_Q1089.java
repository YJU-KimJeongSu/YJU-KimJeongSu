package CodeUp;

import java.util.Scanner;

public class KJS_Q1089 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt(); // 시작값 
		int d = sc.nextInt(); // 등차의 값
		int n = sc.nextInt(); // 몇번째?
		
		for (int i = 0; i < n-1; i++) {
			a += d;
		}
		
		System.out.println(a);
	}

}

/* 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 3분
 */