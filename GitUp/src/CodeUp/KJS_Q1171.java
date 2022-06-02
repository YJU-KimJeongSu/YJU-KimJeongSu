package CodeUp;

import java.util.Scanner;

public class KJS_Q1171 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt(); // 학년 1자리
		int b = sc.nextInt(); // 반 2자리
		int c = sc.nextInt(); // 번호 3자리
		// 총합 6자리 만들기
		String str;
		
		if ( b < 10 ) { str = a + "0" + b; }
		else { str = "" + a + b; }
		
		if ( c >= 100 ) { str = str + c; }
		else if ( c >= 10) { str = str + "0" + c; }
		else { str = str + "00" + c; }
		
		System.out.println(str);
	}
}
/* 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 8분
 */