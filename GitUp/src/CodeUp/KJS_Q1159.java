package CodeUp;

import java.util.Scanner;

public class KJS_Q1159 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		
		if ((input >= 50 && input <= 70) || input % 6 == 0)
			System.out.println("win");
		else System.out.println("lose");
	}

}
/* ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 1��
 */

