package CodeUp;

import java.util.Scanner;

public class KJS_Q1171 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt(); // �г� 1�ڸ�
		int b = sc.nextInt(); // �� 2�ڸ�
		int c = sc.nextInt(); // ��ȣ 3�ڸ�
		// ���� 6�ڸ� �����
		String str;
		
		if ( b < 10 ) { str = a + "0" + b; }
		else { str = "" + a + b; }
		
		if ( c >= 100 ) { str = str + c; }
		else if ( c >= 10) { str = str + "0" + c; }
		else { str = str + "00" + c; }
		
		System.out.println(str);
	}
}
/* ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 8��
 */