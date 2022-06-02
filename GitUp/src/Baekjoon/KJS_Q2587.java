package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class KJS_Q2587 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[5];
		int ave = 0;
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
			ave += arr[i];
		}
		ave /= 5;
		
		Arrays.sort(arr);
		System.out.println(ave);
		System.out.println(arr[2]);
	}

}
/* 20220524
 * 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 2분
 */