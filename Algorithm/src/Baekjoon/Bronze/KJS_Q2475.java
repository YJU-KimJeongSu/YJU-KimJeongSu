package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q2475 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sum = 0;
		
		for (int i = 0; i < 5; i++) {
			int input = sc.nextInt();
			sum += input*input;
		}
		
		System.out.println(sum%10);
	}

}
