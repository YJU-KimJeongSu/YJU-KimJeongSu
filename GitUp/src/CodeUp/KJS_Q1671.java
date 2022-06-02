package CodeUp;

import java.util.Scanner;

public class KJS_Q1671 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int khang = sc.nextInt();
		int com = sc.nextInt();
		// 바위 0, 가위 1, 보 2
		
		if (khang == 0) {
			if (com == 0) { System.out.println("tie"); }
			else if (com == 1) { System.out.println("win"); }
			else if (com == 2) { System.out.println("lose"); }
		}
		else if (khang == 1) {
			if (com == 0) { System.out.println("lose"); }
			else if (com == 1) { System.out.println("tie"); }
			else if (com == 2) { System.out.println("win"); }
		}
		else if (khang == 2) {
			if (com == 0) { System.out.println("win"); }
			else if (com == 1) { System.out.println("lose"); }
			else if (com == 2) { System.out.println("tie"); }
		}
	}

}

/* 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 4분
 */