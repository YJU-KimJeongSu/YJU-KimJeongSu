package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Q10816 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] card = new int[n]; // ����̰� ���� ī���
		for (int i = 0; i < card.length; i++) {
			card[i] = sc.nextInt();
		}
		Arrays.sort(card);
		
		int m = sc.nextInt(); 
		int target; // ã�ƾ��� ��
		int cnt; // ã�� ����
		
		int mid = n/2;
		int low = 0;
		int high = n;
		int temp = 0;
		
		for (int i = 0; i < m; i++) {
			target = sc.nextInt();
			cnt = 0;
			
			// ���ڸ��� ��. ����Ž��
			while (true) {
				mid = (high+low) / 2;
				if (card[mid] == target) {
					
				}
				
				else if (card[mid] > target) high = mid-1;
				else low = mid+1;
			}
			
		
		}
		
	}

}
