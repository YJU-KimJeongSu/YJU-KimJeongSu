package CodeUp;

import java.util.Scanner;

public class KJS_Q1204 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = a;
		
		// 11~13�� ���
		if (a >= 11 && a <= 13) { System.out.println(a + "th"); }
		
		// �ƴ� ��� 10�� ���� ���ڸ� ���� ���� ����
		else {
			while (b > 10) b -= 10;
			
			if ( b == 1 ) { System.out.println(a + "st"); }
			else if ( b == 2 ) { System.out.println(a + "nd"); }
			else if ( b == 3 ) { System.out.println(a + "rd"); }
			else { System.out.println(a + "th"); }
		}
	}

}

/* ���� ���ص� : 4 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 10��
 */