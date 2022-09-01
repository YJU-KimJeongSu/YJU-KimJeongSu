package CodeUp;

import java.util.Scanner;

public class KJS_Q1230 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		if (a > 170 && b > 170 && c > 170) { System.out.println("PASS"); }
		else if ( a <= 170) { System.out.println("CRASH " + a); }
		else if ( b <= 170) { System.out.println("CRASH " + b); }
		else if ( c <= 170) { System.out.println("CRASH " + c); }
	}

}
/* 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 3분
 */