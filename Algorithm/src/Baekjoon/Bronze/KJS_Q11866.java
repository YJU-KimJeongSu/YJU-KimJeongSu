package Baekjoon.Bronze;

import java.util.Scanner;

public class KJS_Q11866 {

	public static void main(String[] args) {


		// 포기
		
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 사람 수
		int k = sc.nextInt(); // k 번째 사람 제거
		int[] list = new int[n]; // 원래 사람
		int[] rm = new int[n]; // 제거된 사람
		int cnt = 1; // k=3일 경우 1 2 3 1 2 3, list에서 0 만나면 안오름, 숫자 만나면 오르고 3 되면 rm에 list 값 넣기
		int now = 1;
		for (int i = 0; i < list.length; i++) { list[i] = i+1; }
		
		// for j는 무한루프 돌게 만들다가, rm에 값을 넣으면 break로 탈출
		// for j가 한번 돌때마다 rm이 한칸씩 채워지니 list.length만큼 반복
		for (int i = 0; i < list.length; i++) {
			for (int j = now%n; true; j++) { // 무한 루프 돌리다가, j가 n이 되면 1로
				if (j == n) j = 1;
				
				if (cnt % k != 0) { // cnt가 1, 2일 때는 list의 값이 0 이 아니면 ++
					if (list[j] != 0) cnt++;
				}
				else if (cnt == k) { 
					cnt = 1;
					rm[i] = list[j];
					list[j] = 0;
					now = j;
					break;
				}
				
			}
		}
		
		for (int i = 0; i < rm.length; i++) {
			System.out.println(rm[i]);
		}
	}

}
