package CodeUp;

import java.util.Scanner;

public class KJS_Q1380 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt(); // �� �ֻ����� ��
		
		for (int i = 1; i < k; i++) {
			if (i <= 6 && (k-i) <= 6)	{ System.out.println(i + " " + (k-i)); }
		}
	}

}
/* ���� ���ص� : 4 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 4��
 */