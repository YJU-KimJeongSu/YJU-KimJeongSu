package CodeUp;

import java.util.Scanner;

public class KJS_Q1082 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String dan = sc.next();
		int num = Integer.parseInt(dan, 16); // (16����)���� -> 10����
		
		for (int i = 1; i <= 0xF; i++) { // �̷��� �׳� i�� 10������ ��
			String a = Integer.toHexString(num*i); // 10���� -> (16����)����
			String i2 = Integer.toHexString(i);
			System.out.println(dan + "*" + i2.toUpperCase() + "=" + a.toUpperCase());
		}
		
	}

}
/* ���� ���ص� : 2 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 20��
 */