package CodeUp;

import java.util.Arrays;
import java.util.Scanner;

public class KJS_Q1411 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* 1�� Ǯ��
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] card = new int[n-1];
		int[] card2 = new int[n]; // ���� ī��
		
		for (int i = 0; i < n-1; i++) { card[i] = sc.nextInt(); }
		for (int i = 0; i < n; i++) { card2[i] = i+1; }
		
		Arrays.sort(card);
		
		for (int i = 0; i <= n-1; i++) {
			if (i == n-1) { System.out.println(card2[i]); }
			else if (card[i] != card2[i]) {
				System.out.println(card2[i]);
				break;
			}
		}
		*/
		
		
		// 2�� Ǯ��
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int sum = 0; // ���� ��� �ִ� ī���� �� ����
		int card = 0; // ������ ī��
		
		for (int i = 0; i < n-1; i++) { sum += sc.nextInt(); }
		for (int i = 1; i <= n; i++) { card += i; }
		card = card - sum;
		
		System.out.println(card);
	}
}
/* 20220510
 * 1�� Ǯ��
 * ���� ���ص� : 4 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 15��
 *
 * 2�� Ǯ��
 * ���� ���ص� : 4 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 3��
 */