package CodeUp;

import java.util.Scanner;

public class KJS_Q1089 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt(); // ���۰� 
		int d = sc.nextInt(); // ������ ��
		int n = sc.nextInt(); // ���°?
		
		for (int i = 0; i < n-1; i++) {
			a += d;
		}
		
		System.out.println(a);
	}

}

/* ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 3��
 */