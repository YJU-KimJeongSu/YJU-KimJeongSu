package Baekjoon.Bronze;

import java.util.Arrays;
import java.util.Scanner;

public class KJS_Q1037 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 주어질 약수의 개수
		int[] num = new int[n]; // 주어진 약수, 이걸로 원래 수 찾기
		
		for (int i = 0; i < num.length; i++) {
			num[i] = sc.nextInt();
		}
		
		Arrays.sort(num);
		
		System.out.println(num[0] * num[n-1]);
	}

}
