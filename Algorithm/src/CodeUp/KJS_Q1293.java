package CodeUp;

import java.util.Scanner;

public class KJS_Q1293 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int stud = sc.nextInt();
		int[] score = new int[stud];
		int max = 0;
		int min = 100;
		
		for (int i = 0; i < stud; i++) {
			score[i] = sc.nextInt();
		}
		
		for (int i = 0; i < score.length; i++) {
			if (score[i] > max) max = score[i];
			if (score[i] < min) min = score[i];
		}
		
		System.out.println(max + " " + min);
	}

}


/* 문제 이해도 : 4 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 5분
 */