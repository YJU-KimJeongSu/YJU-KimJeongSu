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
 * ���� ���ص� : 3 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 6��
 */