package CodeUp;

import java.util.Scanner;

public class KJS_Q1367 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		for (int i = n-1; i >=0; i--) {
			for (int j = 0; j < i; j++) { System.out.print(" "); }
			for (int j = 0; j < n; j++) { System.out.print("*"); }
			System.out.println();
		}
	}

}
/* 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 4분
 */