package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q2751 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] x = new int[n];
		
		for (int i = 0; i < x.length; i++) {
			x[i] = sc.nextInt();
		}
		
		bubbleSort(x);
		
		for (int i = 0; i < x.length; i++) {
			System.out.println(x[i]);
		}
	}
	
	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length-1-i; j++) {
				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
}