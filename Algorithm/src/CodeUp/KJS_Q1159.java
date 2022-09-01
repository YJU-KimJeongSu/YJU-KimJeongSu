package CodeUp;

import java.util.Scanner;

public class KJS_Q1159 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		
		if ((input >= 50 && input <= 70) || input % 6 == 0)
			System.out.println("win");
		else System.out.println("lose");
	}

}
/* 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 1분
 */

