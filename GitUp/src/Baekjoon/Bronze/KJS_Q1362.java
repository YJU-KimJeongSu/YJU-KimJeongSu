package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q1362 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean isAlive = true;
		char action = 'a'; // E : � , F : ����
		double n = 1.0; // action�� �󸶳� �ϳ�
		int num = 1; // ���° ���̳�
		
		while (true) {
			double o = sc.nextInt(); // ����ü��
			double w = sc.nextInt(); // ����ü��
			action = 'a'; // �ʱ�ȭ
			n = 1; // �ʱ�ȭ
			isAlive = true;
			
			if (o == 0 && w == 0) break;
			
			// Ȱ���ϱ�
			while (!(action == '#' && n == 0)) {
				action = sc.next().charAt(0);
				n = sc.nextInt();
				if (action == 'E') {
					w -= n;
					if (w <= 0) isAlive = false;
				}
				else if (action == 'F' ) {
					w += n;
				}
			}
			
			// ��� ���
			if (isAlive == true && w > o/2 && w < o*2) {
				System.out.println(num + " :-)");
				num++;
			}
			else if (isAlive == true) {
				System.out.println(num + " :-(");
				num++;
			}
			else if (isAlive == false)  {
				System.out.println(num + " RIP");
				num++;
			}
		}
		
	}

}
/* 20220531
 * ���� ���ص� : 4 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 31��
 */