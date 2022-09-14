package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class KJS_Q1978 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 1부터 1000까지 모든 소수 다 여기 넣기
		LinkedList<Integer> sosu = new LinkedList<Integer>();
		sosu.add(2);
		for (int i = 3; i <= 1000; i++) {
			boolean isSosu = true;
			for (Integer t : sosu) {
				if (i % t == 0) isSosu = false;
			}
			if (isSosu) sosu.add(i);
		}
		
		int count = 0;
		
		for (int i = 0; i < arr.length; i++) {
			if (sosu.contains(arr[i])) {
				count++;
			}
		}
		
		System.out.println(count);
	}

}
