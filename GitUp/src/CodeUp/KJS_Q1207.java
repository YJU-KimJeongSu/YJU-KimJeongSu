package CodeUp;

import java.util.Scanner;

public class KJS_Q1207 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		
		if (a+b+c+d == 0) System.out.println("모");
		else if (a+b+c+d == 1) System.out.println("도");
		else if (a+b+c+d == 2) System.out.println("개");
		else if (a+b+c+d == 3) System.out.println("걸");
		else if (a+b+c+d == 4) System.out.println("윷");
	}

}
/* 문제 이해도 : 5 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 2분
 */