package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class KJS_Q2309 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ki = new int[9];
		int sum = 0;
		boolean checker = false;
		
		for (int i = 0; i < ki.length; i++) {
			ki[i] = sc.nextInt();
			sum += ki[i];
		}
		
		sum -= 100; // ã�ƾ� �� �� Ű ��ģ��
	
		for (int i = 0; i < ki.length; i++) {
			if (checker == false) {
				for (int j = i+1; j < ki.length; j++) {
				if (ki[i] + ki[j] == sum) {
					ki[i] = 0;
					ki[j] = 0;
					checker = true;
				}
			}
			}
			
		}
		Arrays.sort(ki);
		
		for (int i = 2; i < ki.length; i++) {
			System.out.println(ki[i]);
		}
	}

}
/* 20220524
 * ���� ���ص� : 3 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 13��
 */
