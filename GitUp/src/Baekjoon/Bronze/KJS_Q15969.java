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
 * ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 2��
 */