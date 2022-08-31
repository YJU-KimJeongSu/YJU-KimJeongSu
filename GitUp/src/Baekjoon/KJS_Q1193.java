package Baekjoon;

import java.util.Scanner;

public class KJS_Q1193 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt(); // x번째 항 분수 찾기
		
		int temp = 1;
		boolean isForward = false; // 지그재그이므로 정방향/역방향 계산 필요
		
		while (x - temp > 0) {
//			System.out.println(x + "-" + temp);
			x -= temp;
			temp++;
			isForward = !isForward;
		}
		
		// 1/temp에서 시작
		// 한번 진행할 때 마다 temp는 1씩 감소하고, 분자가 1씩 증가
		// x번째 항 찾기
		int c = 1;
		while (x > 1) {
			temp--;
			c++;
			x--;
		}
		
		if (isForward) System.out.println(c + "/" + temp);
		else System.out.println(temp + "/" + c);
	}

}
