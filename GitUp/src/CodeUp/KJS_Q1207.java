package CodeUp;

import java.util.Scanner;

public class KJS_Q1207 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		
		if (a+b+c+d == 0) System.out.println("��");
		else if (a+b+c+d == 1) System.out.println("��");
		else if (a+b+c+d == 2) System.out.println("��");
		else if (a+b+c+d == 3) System.out.println("��");
		else if (a+b+c+d == 4) System.out.println("��");
	}

}
/* ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 2��
 */