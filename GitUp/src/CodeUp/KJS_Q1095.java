package CodeUp;

import java.util.Scanner;

public class KJS_Q1095 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 번호 개수
		int k = 0; // 입력 받아서 임시로 저장할 곳
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
 * 문제 이해도 : 4 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 5분
 */