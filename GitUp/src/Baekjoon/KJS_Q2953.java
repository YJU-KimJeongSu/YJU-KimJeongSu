package Baekjoon;

import java.util.Scanner;

public class KJS_Q2953 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int[][] score = new int[5][4];
		int maxSum = 0;
		int winner = 0;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				score[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 0; i < 5; i++) {
			int sum = 0;
			for (int j = 0; j < 4; j++) {
				sum += score[i][j];
			}
			if (sum > maxSum)  {
				maxSum = sum;
				if (winner != i+1) winner = i+1;
			}
		}
		
		System.out.println(winner + " " + maxSum);
	}

}
/* 20220514
 * 문제 이해도 : 4 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 8분
 */