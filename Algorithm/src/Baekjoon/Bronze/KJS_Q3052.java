package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q3052 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int[] a = new int[10];
		
		for (int i = 0; i < a.length; i++) {
			a[i] = sc.nextInt();
			a[i] = a[i] % 42;
		}
		
		int count = 10;
		
		for (int i = 0; i < a.length; i++) {
			 for (int j = i+1; j < a.length; j++) {
				if (a[i] == a[j]) {
					count--;
					break;
					}
			}
		}
		
		System.out.println(count);
	}

}
