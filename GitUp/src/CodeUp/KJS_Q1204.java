package CodeUp;

import java.util.Scanner;

public class KJS_Q1204 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = a;
		
		// 11~13일 경우
		if (a >= 11 && a <= 13) { System.out.println(a + "th"); }
		
		// 아닐 경우 10씩 빼서 한자리 수로 만들어서 실행
		else {
			while (b > 10) b -= 10;
			
			if ( b == 1 ) { System.out.println(a + "st"); }
			else if ( b == 2 ) { System.out.println(a + "nd"); }
			else if ( b == 3 ) { System.out.println(a + "rd"); }
			else { System.out.println(a + "th"); }
		}
	}

}

/* 문제 이해도 : 4 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 10분
 */