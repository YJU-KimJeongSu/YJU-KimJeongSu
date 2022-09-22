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
		long m = Long.parseLong(st.nextToken());
		long min = 0; 
		long max = 0;
		long cutter = 0; // Åé ¼³Ä¡ÇÒ ³ôÀÌ
		long sum = 0; // Äµ ³ª¹« 
		long[] tree = new long[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			tree[i] = Long.parseLong(st.nextToken());
			if (tree[i] > max) max = tree[i];
		}
		
		boolean isBigger = false;
		while (min <= max) {
			sum = 0;
			cutter = (min + max) / 2;
			
			for (int i = 0; i < tree.length; i++) {
				if (tree[i] > cutter) sum += tree[i] - cutter;
			}
			if (sum >= m) isBigger = true;
			else isBigger = false;
			
			if (sum == m) break;
			else if (sum < m) max = cutter-1;
			else min = cutter+1;
		}	
		
		if (isBigger || cutter == 0) System.out.println(cutter);
		else System.out.println(cutter-1);
	}

}
