package CodeUp;

import java.util.Scanner;

public class KJS_Q1095 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // ��ȣ ����
		int k = 0; // �Է� �޾Ƽ� �ӽ÷� ������ ��
		int low = 0;
		
		for (int i = 0; i < n; i++) {
			k = sc.nextInt();
			if (i == 0) low = k;
			if (low >= k) low = k;
		}
		
		System.out.println(low);
	}

}
/* 20220510
 * ���� ���ص� : 4 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 5��
 */