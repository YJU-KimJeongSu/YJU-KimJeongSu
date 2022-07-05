package Baekjoon;

import java.util.Scanner;

public class KJS_Q13305 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cnt = sc.nextInt();
		long[] distance = new long[cnt-1];
		long[] cost = new long[cnt];
		
		for (int i = 0; i < distance.length; i++) {
			distance[i] = sc.nextLong();
		}
		for (int i = 0; i < cost.length; i++) {
			cost[i] = sc.nextLong();
		}
		
		long sum = distance[0] * cost[0]; // 맨 처음은 무조건 넣어야함
		long low = cost[0];
		long now = cost[0];
		
		for (int i = 1; i < cost.length-1; i++) {
			now = cost[i];
			if (low > now)  {
				low = now;
				sum += distance[i] * low;
			}
			else sum += distance[i] * low;
		}
		
		System.out.println(sum);
		
		sc.close();
	}

}
