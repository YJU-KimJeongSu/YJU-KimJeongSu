package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KJS_Q2805 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int min = 0; 
		int max = 0;
		int mid = 0; // 톱 설치할 높이
		int sum = 0; // 캔 나무 
		int[] tree = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			if (tree[i] > max) max = tree[i];
		}
		
		
		while (true) {
			int t = 0;
			mid = (min + max) / 2;
			
			for (int i = 0; i < tree.length; i++) {
				if (tree[i] - mid > 0) sum += tree[i] - mid; 
			}
			
			if (sum == m) break;
			else if (sum > m) min = mid;
			else if (sum < m) max = mid;
			t = mid; // 항상 딱 맞게 잘리는건 아니니 값 저장
		}
	}

}
