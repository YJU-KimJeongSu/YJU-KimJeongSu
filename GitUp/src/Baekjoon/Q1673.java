package Baekjoon;

import java.util.Scanner;

public class Q1673 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			int n = sc.nextInt(); // ���� �ִ� ���� n��
			int k = sc.nextInt(); // ���� k���� ���� 1��
			
			if (n == 0 && k == 0) break;
			
			int chicken = n;
			
			while (n >= k) {
				chicken += n/k;
				n = n/k;
			}
			
			System.out.println(chicken);
		}
	}

}
