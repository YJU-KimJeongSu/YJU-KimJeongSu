package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q2438 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		
		for (int i = 0; i < a; i++) {
			for (int j = a-i; j <= a; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
