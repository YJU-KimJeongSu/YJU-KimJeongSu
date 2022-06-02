package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Q2822 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[] score = new int[8];
		int[] score2 = new int[8];
		int sum = 0;
		int[] index = new int[5];
		
		for (int i = 0; i < score.length; i++) {
			score[i] = sc.nextInt();
			score2[i] = score[i];
		}
		Arrays.sort(score2);
		
		// 상위 5개 점수 총합
		for (int i = score2.length-1; i > 2; i--) {
			sum += score2[i];
		}
		System.out.println(sum);
		
		// 상위 5개 점수의 인덱스를 index에 저장
		for (int i = 4; i >= 0; i--) {
			for (int j = 0; j < score.length; j++) {
				if (score2[i+3] == score[j]) index[i] = j; 
			}
		}
		
		// 인덱스 출력
		Arrays.sort(index);
		for (int i = 0; i < index.length; i++) {
			System.out.print((index[i]+1) + " ");
		}
	}

}
