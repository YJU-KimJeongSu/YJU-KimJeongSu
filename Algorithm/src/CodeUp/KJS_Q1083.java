package CodeUp;

import java.util.Scanner;

public class KJS_Q1083 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int count = sc.nextInt();
		
		for (int i = 1; i <= count; i++) {
			if (i % 3 == 0) { System.out.println("X"); }
			else { System.out.println(i); }
		}
	}

}

/* ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 2��
 */