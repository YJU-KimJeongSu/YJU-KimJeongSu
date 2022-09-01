package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class KJS_Q1914 {
	static void hanoi(int num, int a, int b, int c) {
		if (num == 1) {
			System.out.println(a + " " + c);
		}
		else {
			// num-1���� b�� c �ٲ㼭 ȣ��
			hanoi(num-1, a, c, b);
			// num���� ������ a���� c�� �̵�
			System.out.println(a + " " + c);
			// num-1���� b�� a �ٲ㼭 ȣ��
			hanoi(num-1, b, a, c);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 100�� ���� ��� long���ε� �Ұ���
//		System.out.println((long)(Math.pow(2, n) - 1));
		
		BigInteger bi = new BigInteger("2");
		System.out.println(bi.pow(n).subtract(new BigInteger("1")));
		// 2^n - 1 �ε�
		// 2^n�� BigInteger�ϱ�
		// -1�� BigInteger�� �������
		
		if (n <= 20) {
			hanoi(n, 1, 2, 3);
		}
	}
}