package Baekjoon;

import java.util.Scanner;

public class KJS_Q2525 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int h = sc.nextInt();
		int m = sc.nextInt();
		int cookTime = sc.nextInt();
		
		m += cookTime;
		
		while (m >= 60) { m -= 60; h++; }
		while (h >= 24) { h -= 24; }
		
		System.out.println(h + " " + m);
	}

}
/* 20220514
 * ���� ���ص� : 4 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 7��
 */