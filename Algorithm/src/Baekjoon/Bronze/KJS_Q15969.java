package Baekjoon.Bronze;

import java.util.Arrays;
import java.util.Scanner;

public class KJS_Q15969 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] score = new int[n];
		
		for (int i = 0; i < score.length; i++) {
			score[i] = sc.nextInt();
		}
		
		Arrays.sort(score);
		
		System.out.println(score[n-1] - score[0]);
	}

}
/* 20220517
 * 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 2분
 */