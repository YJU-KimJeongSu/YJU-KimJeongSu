package CodeUp;

import java.util.Scanner;

public class KJS_Q1173 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int hour = sc.nextInt();
		int minute = sc.nextInt();
		
		if ( minute < 30  && hour > 0) { System.out.println((hour-1) + " " + (minute+30)); }
		else if (minute < 30 && hour == 0) { System.out.println("23 " + (minute+30)); }
		else { System.out.println(hour + " " + (minute-30)); }
	}

}

/* ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 5��
 */