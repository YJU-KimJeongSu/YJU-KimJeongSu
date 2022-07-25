package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KJS_Q1806 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(bf.readLine(), " ");
		int[] num = new int[n];
		
		for (int i = 0; i < num.length; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(twoPointer(num, s));
	}
	
	static int twoPointer(int[] num, int s) {
		int start = 0;
		int end = 1;
		int sum = num[0];
		int count = 100001;
		
		while (true) {
			// s보다 작으면 우측 요소 한개 더 더하기
			if (sum < s) {
				if (end >= num.length) break;
				sum += num[end++];
			}
			// s보다 크거나 같으면 좌측 요소 한개 빼기, 카운트 세기
			else {
				if (start >= num.length) break;
				if (count > end - start) count = end - start;
				sum -= num[start++];
			}
		}
		
		if (count == 100001) return 0;
		else return count;
	}

}
