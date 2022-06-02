package Baekjoon;

import java.util.Scanner;

public class Q11047 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 동전의 종류 수
		int k = sc.nextInt(); // k원
		
		int[] a = new int[n]; // 동전 가치
		
		// 동전의 가치 오름차순으로 받기
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		// a[0]이 제일 작고, a[n-1]이 가장 큼
		
		
		// k원에서 a[n-1]부터 a[0]까지 빼기
		int count = 0; // 사용한 동전의 개수
		for (int i = n-1; true ; ) {
			if (0 <= k - a[i]) {
				k -= a[i];
				count++;
				continue;
			}
			else i--;
			if (k == 0) break;
		}
		
		System.out.println(count);
	}

}
