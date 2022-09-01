package KakaoCodingTest;

import java.util.Scanner;

public class test1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int min;
		int max;
		
		// 최소 이동 횟수 구하기
		if (a+1 == b && b+1 == c) { min = 0; }
		else if (a+2 == b && b < c) { min = 1; }
		else if (a < b && b+2 == c) { min = 1; }
		else min = 2;
		
		// 최대 이동 횟수 구하기
		if (b-a < c-b) { max = c-b-1; }
		else if (b-a > c-b) { max = b-a-1; }
		else if (a+1 == b && b+1 == c) { max = 0;}
		else { max = b-a-1; }
		
		System.out.println(min + " " + max);
	}

}