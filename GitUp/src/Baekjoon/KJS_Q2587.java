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
 * ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 2��
 */