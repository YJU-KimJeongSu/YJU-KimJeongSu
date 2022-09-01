package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q4344 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		
		for (int i = 0; i < testCase; i++) {
			int n = sc.nextInt();
			int[] score = new int[n];
			int ave = 0; // �׽�Ʈ ���̽����� �ʱ�ȭ
			int cnt = 0; // �굵
			
			for (int j = 0; j < score.length; j++) {
				score[j] = sc.nextInt();
				ave += score[j];
			}
			ave = ave/n;
			
			for (int j = 0; j < score.length; j++) {
				if (ave < score[j]) { cnt++; }
			}
			
			double result = Math.round(cnt*100000.0/n) / 1000.0;
			System.out.printf("%.3f", result);
			System.out.println("%");
		}
	}

}
/* 20220521
 * ���� ���ص� : 4 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 18��
 */