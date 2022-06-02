package CodeUp;

import java.util.Scanner;

public class KJS_Q1082 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String dan = sc.next();
		int num = Integer.parseInt(dan, 16); // (16진수)문자 -> 10진수
		
		for (int i = 1; i <= 0xF; i++) { // 이래도 그냥 i는 10진수로 됨
			String a = Integer.toHexString(num*i); // 10진수 -> (16진수)문자
			String i2 = Integer.toHexString(i);
			System.out.println(dan + "*" + i2.toUpperCase() + "=" + a.toUpperCase());
		}
		
	}

}
/* 문제 이해도 : 2 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 20분
 */