package Baekjoon.Silver;

import java.util.Scanner;

public class KJS_Q1065 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		int cnt = 0;
		
		for (int i = 1; i <= input; i++) {
			// i�� �� �ڸ����� ������������ Ȯ��
			// 1. i�� �� �ڸ����� �и��Ͽ� a b c d�� �ְ�  2. a b c d�� ������������ Ȯ��
			int a, b, c, d, temp = i;
			// ���� input = 1234���
			a = temp % 10; // 4
			temp /= 10; // 123
			b = temp % 10; // 3
			temp /= 10; // 12
			c = temp % 10; // 2
			temp /= 10; // 1
			d = temp; // 1
			// 1234 -> dcba
			
			if (a-b == b-c && b-c == c-d) { cnt++; }
			else if (a-b == b-c && d == 0) { cnt++; }
			else if (c == 0 && d == 0) {cnt ++;}
		}
		
		System.out.println(cnt);
	}

}
