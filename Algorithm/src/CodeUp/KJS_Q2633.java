package CodeUp;

import java.util.Scanner;

public class KJS_Q2633 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // n���� ����
		int k = sc.nextInt(); // ã���� �ϴ� ��, k�̻�
		int[] array = new int[n];
		int result = n+1; // k �̻��� ���� ��ġ �ε���
		
		for (int i = 0; i < array.length; i++) {
			array[i] = sc.nextInt();
		}
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] >= k)  {
				result = i+1;
				break;
			}
		}
		
		System.out.println(result);
	}

}
/* 20220510
 * ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 7��
 */