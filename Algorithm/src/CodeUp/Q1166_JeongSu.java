package CodeUp;

import java.util.Scanner;

public class Q1166_JeongSu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int year = sc.nextInt();
		
		if (year % 4 == 0 && year % 100 != 0) { System.out.println("yes"); }
		else if (year % 400 == 0) { System.out.println("yes"); }
		else { System.out.println("no"); }
	}

}

/* ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 2��
 */