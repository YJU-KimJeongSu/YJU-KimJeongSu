package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Q10816 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] card = new int[n]; // 상근이가 가진 카드들
		for (int i = 0; i < card.length; i++) {
			card[i] = sc.nextInt();
		}
		Arrays.sort(card);
		
		int m = sc.nextInt(); 
		int target; // 찾아야할 수
		int cnt; // 찾은 개수
		
		int mid = n/2;
		int low = 0;
		int high = n;
		int temp = 0;
		
		for (int i = 0; i < m; i++) {
			target = sc.nextInt();
			cnt = 0;
			
			// 받자마자 비교. 이진탐색
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
