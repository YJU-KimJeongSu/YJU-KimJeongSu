package Baekjoon;

import java.util.Scanner;

public class KJS_Q1193 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt(); // x��° �� �м� ã��
		
		int temp = 1;
		boolean isForward = false; // ��������̹Ƿ� ������/������ ��� �ʿ�
		
		while (x - temp > 0) {
//			System.out.println(x + "-" + temp);
			x -= temp;
			temp++;
			isForward = !isForward;
		}
		
		// 1/temp���� ����
		// �ѹ� ������ �� ���� temp�� 1�� �����ϰ�, ���ڰ� 1�� ����
		// x��° �� ã��
		int c = 1;
		while (x > 1) {
			temp--;
			c++;
			x--;
		}
		
		if (isForward) System.out.println(c + "/" + temp);
		else System.out.println(temp + "/" + c);
	}

}
