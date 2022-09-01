package CodeUp;

import java.util.Scanner;

public class KJS_Q2633 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // n개의 정수
		int k = sc.nextInt(); // 찾고자 하는 값, k이상
		int[] array = new int[n];
		int result = n+1; // k 이상인 수의 위치 인덱스
		
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
 * 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 7분
 */