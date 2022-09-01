package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q1362 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean isAlive = true;
		char action = 'a'; // E : 운동 , F : 먹이
		double n = 1.0; // action을 얼마나 하냐
		int num = 1; // 몇번째 펫이냐
		
		while (true) {
			double o = sc.nextInt(); // 적정체중
			double w = sc.nextInt(); // 실제체중
			action = 'a'; // 초기화
			n = 1; // 초기화
			isAlive = true;
			
			if (o == 0 && w == 0) break;
			
			// 활동하기
			while (!(action == '#' && n == 0)) {
				action = sc.next().charAt(0);
				n = sc.nextInt();
				if (action == 'E') {
					w -= n;
					if (w <= 0) isAlive = false;
				}
				else if (action == 'F' ) {
					w += n;
				}
			}
			
			// 결과 출력
			if (isAlive == true && w > o/2 && w < o*2) {
				System.out.println(num + " :-)");
				num++;
			}
			else if (isAlive == true) {
				System.out.println(num + " :-(");
				num++;
			}
			else if (isAlive == false)  {
				System.out.println(num + " RIP");
				num++;
			}
		}
		
	}

}
/* 20220531
 * 문제 이해도 : 4 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 31분
 */