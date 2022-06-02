package Baekjoon;

import java.util.Scanner;

public class KJS_Q14726 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		boolean dap = false;
		
		for (int i = 0; i < testCase; i++) {
			String cardNum = sc.next();
			int[] num = new int[16];
			
			// �ѱ��ھ� �ɰ���
			for (int j = 0; j < num.length; j++) {
				num[j] = Integer.parseInt(cardNum.substring(j, j+1));
			}
			
			// 1~2�� ����
			for (int j = 0; j < 16; j += 2) {
				num[j] = num[j] * 2;
				if (num[j] >= 10) {
					String temp = Integer.toString(num[j]);
					num[j] = Integer.parseInt(temp.substring(0, 1)) + Integer.parseInt(temp.substring(1, 2));
				}
			}
			
			int sum = 0;
			
			// 3�� ����
			for (int j = 0; j < num.length; j++) {
				sum += num[j];
			}
			
			if (sum % 10 == 0) dap = true;
			else dap = false;
			
			if (dap == true) System.out.println("T");
			else System.out.println("F");
		}
	}

}
/* 20220531
 * ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 20��
 */