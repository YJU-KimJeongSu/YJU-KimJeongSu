package Baekjoon;

import java.util.Scanner;

public class Q10250 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt(); // �׽�Ʈ ������
		int h; // ����
		int w; // ����
		int n; // �մ� ��
		
		int c_h, c_w = 0;
		
		int[] hotel = new int[t];
		
		for (int i = 0; i < t; i++) {
			h = sc.nextInt();
			w = sc.nextInt();
			n = sc.nextInt();
			// �մ��� �� ��
			if (n % h != 0)  { c_h = n % h; }
			else { c_h = h; }
			// �մ��� �� ����
			for (int j = 0; j <= w; j++) {
				if (n > h * (j-1) && n <= h*j) {
					c_w = j;
				}
			}
			// ���������� �� ��
			if (c_h == 0) c_h = 1;
			hotel[i] = (c_h * 100) + c_w;
		}
		
		for (int i = 0; i < hotel.length; i++) {
			System.out.println(hotel[i]);
		}
	}

}
