package CodeUp;

import java.util.Arrays;
import java.util.Scanner;

public class KJS_Q1411 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* 1번 풀이
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] card = new int[n-1];
		int[] card2 = new int[n]; // 원본 카드
		
		for (int i = 0; i < n-1; i++) { card[i] = sc.nextInt(); }
		for (int i = 0; i < n; i++) { card2[i] = i+1; }
		
		Arrays.sort(card);
		
		for (int i = 0; i <= n-1; i++) {
			if (i == n-1) { System.out.println(card2[i]); }
			else if (card[i] != card2[i]) {
				System.out.println(card2[i]);
				break;
			}
		}
		*/
		
		
		// 2번 풀이
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int sum = 0; // 현재 들고 있는 카드의 수 총합
		int card = 0; // 없어진 카드
		
		for (int i = 0; i < n-1; i++) { sum += sc.nextInt(); }
		for (int i = 1; i <= n; i++) { card += i; }
		card = card - sum;
		
		System.out.println(card);
	}
}
/* 20220510
 * 1번 풀이
 * 문제 이해도 : 4 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 15분
 *
 * 2번 풀이
 * 문제 이해도 : 4 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 3분
 */