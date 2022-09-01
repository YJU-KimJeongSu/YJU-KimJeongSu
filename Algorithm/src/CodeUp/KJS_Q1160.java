package CodeUp;

import java.util.Scanner;

public class KJS_Q1160 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		
		if(input == 1 || input == 3 || input == 5 || input == 7) {
			System.out.println("oh my god");
		}
		else { System.out.println("enjoy"); }
		
		
		/* Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		
		switch(input) {
		case 1:
		case 3:
		case 5:
		case 7:
			System.out.println("oh my god");
			break;
			
		default : System.out.println("enjoy");
		} */
	}
}
/* 문제 이해도 : 3 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 4분(if문) // 3분(switch문-구글링) 
 */

