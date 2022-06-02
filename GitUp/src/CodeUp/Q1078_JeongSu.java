package CodeUp;

import java.util.*;

public class Q1078_JeongSu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		int sum = 0;
		
		for(int i = 1; i <= input; i++) {
			if (i%2 == 0) sum += i;
		}
		
		System.out.println(sum);
		
	}

}
/* 문제 이해도 : 4 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 4분
 */