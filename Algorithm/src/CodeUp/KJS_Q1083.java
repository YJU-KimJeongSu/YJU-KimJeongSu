package CodeUp;

import java.util.Scanner;

public class KJS_Q1083 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int count = sc.nextInt();
		
		for (int i = 1; i <= count; i++) {
			if (i % 3 == 0) { System.out.println("X"); }
			else { System.out.println(i); }
		}
	}

}

/* 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 2분
 */