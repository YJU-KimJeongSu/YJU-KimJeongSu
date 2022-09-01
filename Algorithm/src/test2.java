
import java.util.*;

public class test2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
		int money = 0;

		if(a == b && b == c) {
			money = 10000+(a*1000);
		} else if(a == b || b == c) {
			money = 1000+(b*100);
		} else {
			if(a > b && a > c) {
				money = a*100;
			} else if (b > a && b > c) {
				money = b*100;
			} else if (c > a && c > b) {
				money = c*100;
			}
		}
		System.out.println(money);
	}
}

/*
 * 2022-05-14
 * 문제 이해도 : 
 * 문제 해결 : 
 * 코딩 시간 : 분
 */