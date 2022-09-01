package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q1673 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			int n = sc.nextInt(); // 갖고 있는 쿠폰 n장
			int k = sc.nextInt(); // 도장 k개당 쿠폰 1장
			
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
