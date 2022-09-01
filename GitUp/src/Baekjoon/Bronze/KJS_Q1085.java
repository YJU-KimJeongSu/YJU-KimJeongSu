package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q1085 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int w = sc.nextInt();
		int h = sc.nextInt();
		
		// x, y, w-x, h-y
		if (x <= y && x <= w-x && x <= h-y) { System.out.println(x); }
		else if (y <= x && y <= w-x && y <= h-y) { System.out.println(y); }
		else if (w-x <= x && w-x <= y && w-x <= h-y) { System.out.println(w-x); }
		else { System.out.println(h-y); }
	}

}
