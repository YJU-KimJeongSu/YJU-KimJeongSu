package CodeUp;

import java.util.Scanner;

public class KJS_Q1630 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int count = sc.nextInt();
		String[] song = new String[count];
		
		for (int i = 0; i < song.length; i++) {
			song[i] = sc.next();
		}
		
		for (int i = 0; i < song.length; i++) {
			System.out.println(song[i]);
			if (i != song.length-1) System.out.println("AMOLED");
		}
	}

}

/* ���� ���ص� : 3 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 7��
 */