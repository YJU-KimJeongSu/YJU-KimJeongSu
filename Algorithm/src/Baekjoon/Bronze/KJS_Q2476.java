package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q2476 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // �����ϴ� ��� ��
		int max = 0;
		int result = 0; // ���
		
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			
			// �� ���� ���
			if (a == b && b == c) {
				result = 10000 + (a*1000);
			}
			// 2���� ���� ���
			else if (a == b || a == c) {
				result = 1000 + (a*100);
			}
			else if (b == c) {
				result = 1000 + (b*100);
			}
			// �� �ٸ� ���
			else {
				if (a > b && a > c) result = a * 100;
				else if (b > c && b > a) result = b * 100;
				else if (c > a && c > b) result = c * 100;
			}
			
			if (result > max) max = result;
		}
		
		System.out.println(max);
	}

}
/* 20220514
 * ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 5��
 */