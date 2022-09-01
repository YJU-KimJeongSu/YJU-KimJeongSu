package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q17618 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int count = 0;
		
		for (int i = 1; i <= n; i++) {
			// ���ڸ��� ���ϱ�
			String temp = Integer.toString(i);
			char[] temp2 = new char[temp.length()];
			for (int j = 0; j < temp2.length; j++) {
				temp2[j] = temp.charAt(j);
			}
			
			int sum = 0;
			for (int j = 0; j < temp2.length; j++) {
				sum += (int)(temp2[j]-'0'); // �ƽ�Ű �ڵ�� ����ؼ� ���� �̿�
			}
			
			// �ű��� ������ ã�ƺ���
			if (i % sum == 0) count++;
		}
		
		System.out.println(count);
	}

}
/* 20220606
 * ���� ���ص� : 3 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 16��
 */