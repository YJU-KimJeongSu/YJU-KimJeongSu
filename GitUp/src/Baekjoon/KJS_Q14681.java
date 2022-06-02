package Baekjoon;

import java.util.Scanner;

public class KJS_Q14681 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		
		if (x > 0 && y > 0) System.out.println(1);
		else if (x < 0 && y > 0) System.out.println(2);
		else if (x < 0 && y < 0) System.out.println(3);
		else System.out.println(4);
	}

}
/* 20220521
 * 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 2분
 */