package Baekjoon.Bronze;

import java.util.Arrays;
import java.util.Scanner;

public class KJS_Q1037 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // �־��� ����� ����
		int[] num = new int[n]; // �־��� ���, �̰ɷ� ���� �� ã��
		
		for (int i = 0; i < num.length; i++) {
			num[i] = sc.nextInt();
		}
		
		Arrays.sort(num);
		
		System.out.println(num[0] * num[n-1]);
	}

}
