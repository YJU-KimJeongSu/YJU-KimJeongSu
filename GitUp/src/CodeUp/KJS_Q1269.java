package CodeUp;

import java.util.Scanner;

public class KJS_Q1269 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt(); // ���� ��
		int b = sc.nextInt(); // ���� ��
		int c = sc.nextInt(); // ���� ��
		int n = sc.nextInt(); // �� ��° ��
		int result = a;
		
		for (int i = 1; i < n; i++) {
			result = result * b + c;
		}
		
		System.out.println(result);
	}

}
/* ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 4��
 */