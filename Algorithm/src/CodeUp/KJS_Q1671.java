package CodeUp;

import java.util.Scanner;

public class KJS_Q1671 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int khang = sc.nextInt();
		int com = sc.nextInt();
		// ���� 0, ���� 1, �� 2
		
		if (khang == 0) {
			if (com == 0) { System.out.println("tie"); }
			else if (com == 1) { System.out.println("win"); }
			else if (com == 2) { System.out.println("lose"); }
		}
		else if (khang == 1) {
			if (com == 0) { System.out.println("lose"); }
			else if (com == 1) { System.out.println("tie"); }
			else if (com == 2) { System.out.println("win"); }
		}
		else if (khang == 2) {
			if (com == 0) { System.out.println("win"); }
			else if (com == 1) { System.out.println("lose"); }
			else if (com == 2) { System.out.println("tie"); }
		}
	}

}

/* ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 4��
 */