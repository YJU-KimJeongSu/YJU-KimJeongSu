package Baekjoon;

import java.util.Scanner;

public class Q11047 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // ������ ���� ��
		int k = sc.nextInt(); // k��
		
		int[] a = new int[n]; // ���� ��ġ
		
		// ������ ��ġ ������������ �ޱ�
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		// a[0]�� ���� �۰�, a[n-1]�� ���� ŭ
		
		
		// k������ a[n-1]���� a[0]���� ����
		int count = 0; // ����� ������ ����
		for (int i = n-1; true ; ) {
			if (0 <= k - a[i]) {
				k -= a[i];
				count++;
				continue;
			}
			else i--;
			if (k == 0) break;
		}
		
		System.out.println(count);
	}

}
