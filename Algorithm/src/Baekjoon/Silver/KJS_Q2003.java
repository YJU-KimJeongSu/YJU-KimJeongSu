package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KJS_Q2003 {
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] num = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < num.length; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		twoPointer(num, m);
		System.out.println(result);
	}
	
	static void twoPointer(int[] num, int m) {
		int start = 0;
		int end = 1;
		int sum = num[0];
		
		while (true) {
			if (sum < m) {
				if (end >= num.length) break;
				sum += num[end++];
			}
			else if (sum == m) {
				result++;
				if (end >= num.length) break;
				sum += num[end++];
			}
			else if (sum > m) {
				sum -= num[start++];
			}
		}
	}

}
