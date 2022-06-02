package CodeUp;

import java.util.*;

public class Q1150_JeongSu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int a, b, c;
		
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		
		if(a <= b && a <= c) System.out.println(a);
		else if (b <= a && b <= c) System.out.println(b);
		else if (c <= a && c <= b) System.out.println(c);
	}

}

/* 문제 이해도 : 3 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 7분  */