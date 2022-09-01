package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q1267 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // n���� ��ȭ
		int[] callTime = new int[n];
		int[] callTime2 = new int[n];
		
		for (int i = 0; i < callTime.length; i++) {
			callTime[i] += sc.nextInt();
			callTime2[i] = callTime[i];
		}
		
		int y = 0; // ���� �����
		int m = 0; // �ν� �����
		
		// ���Ŀ���� ���
		for (int i = 0; i < callTime.length; i++) {
			while (callTime[i] >= 0) {
				y += 10;
				callTime[i] -= 30;
			}
		}
		
		// �νĿ���� ���
		for (int i = 0; i < callTime2.length; i++) {
			while (callTime2[i] >= 0) {
				m += 15;
				callTime2[i] -= 60;
			}
		}
		
		
		if ( y < m ) System.out.println("Y " + y);
		else if ( y > m ) System.out.println("M " + m);
		else System.out.println("Y M " + y);
	}

}

/* 20220514
 * ���� ���ص� : 4 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 14��
 */