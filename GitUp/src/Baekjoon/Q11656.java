package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Q11656 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str = sc.next(); // �Է¹��� ���ڿ�
		String[] st = new String[str.length()]; // �ѱ��ھ� �ڸ���
		String[] s = new String[str.length()]; // �߶� �ٽ� ������
		
		
		// �ѱ��ھ� �߶� st�� �����ϱ�
		for (int i = 0; i < str.length(); i++) {
			st[i] = str.substring(i, i+1);
		}
		
		// �ٽ� ������ s�� ����
		for (int i = 0; i < str.length(); i++) {
			for (int j = i; j < str.length(); j++) {
				if ( j == i ) s[i] = st[j];
				else s[i] += st[j];
			}
		}
		
		Arrays.sort(s);
		
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
		}
	}

}
