package Baekjoon.Silver;

import java.util.Arrays;
import java.util.Scanner;

public class KJS_Q1427 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n = sc.next();
		String num[] = new String[n.length()];
		
		for (int i = 0; i < num.length; i++) {
			num[i] = n.substring(i, i+1);
		}
		
		Arrays.sort(num);
		
		for (int i = num.length-1; i >= 0; i--) {
			System.out.print(num[i]);
		}
	}

}
