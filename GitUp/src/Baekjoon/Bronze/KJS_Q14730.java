package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q14730 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // ���� ����
		int result = 0;
		
		for (int i = 0; i < n; i++) {
			int c = sc.nextInt(); // ���
			int k = sc.nextInt(); // ����
			
			result += c * k;
		}
		
		System.out.println(result);
	}

}
