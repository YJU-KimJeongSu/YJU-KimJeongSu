package CodeUp;

import java.util.Scanner;

public class KJS_Q1380 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt(); // 두 주사위의 합
		
		for (int i = 1; i < k; i++) {
			if (i <= 6 && (k-i) <= 6)	{ System.out.println(i + " " + (k-i)); }
		}
	}

}
/* 문제 이해도 : 4 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 4분
 */