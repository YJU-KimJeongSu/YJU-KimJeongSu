package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q11866 {

	public static void main(String[] args) {


		// ����
		
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // ��� ��
		int k = sc.nextInt(); // k ��° ��� ����
		int[] list = new int[n]; // ���� ���
		int[] rm = new int[n]; // ���ŵ� ���
		int cnt = 1; // k=3�� ��� 1 2 3 1 2 3, list���� 0 ������ �ȿ���, ���� ������ ������ 3 �Ǹ� rm�� list �� �ֱ�
		int now = 1;
		for (int i = 0; i < list.length; i++) { list[i] = i+1; }
		
		// for j�� ���ѷ��� ���� ����ٰ�, rm�� ���� ������ break�� Ż��
		// for j�� �ѹ� �������� rm�� ��ĭ�� ä������ list.length��ŭ �ݺ�
		for (int i = 0; i < list.length; i++) {
			for (int j = now%n; true; j++) { // ���� ���� �����ٰ�, j�� n�� �Ǹ� 1��
				if (j == n) j = 1;
				
				if (cnt % k != 0) { // cnt�� 1, 2�� ���� list�� ���� 0 �� �ƴϸ� ++
					if (list[j] != 0) cnt++;
				}
				else if (cnt == k) { 
					cnt = 1;
					rm[i] = list[j];
					list[j] = 0;
					now = j;
					break;
				}
				
			}
		}
		
		for (int i = 0; i < rm.length; i++) {
			System.out.println(rm[i]);
		}
	}

}
