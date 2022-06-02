package Baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

public class KJS_Q2605 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList stud = new ArrayList();
		
		for (int i = 0; i < n; i++) {
			stud.add(sc.nextInt(), i+1);
		}
		
		for (int i = n-1; i >= 0; i--) {
			System.out.print(stud.get(i) + " ");
		}
	}

}
/* 20220524
 * 문제 이해도 : 3 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 6분
 */