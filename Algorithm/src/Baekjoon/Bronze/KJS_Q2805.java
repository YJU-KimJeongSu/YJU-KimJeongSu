package Baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KJS_Q2805 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		
		int n = Integer.parseInt(st.nextToken()); // 한 줄에 있는 나무의 수
		int m = Integer.parseInt(st.nextToken()); // 필요한 길이
		int h = 0; // 절단기의 높이
		
		int myTree = 0; // 절단기로 잘라서 얻은 나무의 양
		
		int[] a = new int[n]; // 나무의 높이
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		// 제일 높은 값을 찾기 위해 오름차순 정렬 ,  a[0]이 제일 작고, a[n-1]이 제일 큼 
		Arrays.sort(a);
		
		
		/* 너무 느려서 이진 탐색으로 변경
		// 제일 높은 나무 높이를 기준으로 1m씩 내리기
		for (h = a[a.length-1]; h > 0; h--) {
			// 나무 n그루 하나씩 베어보기
			for (int j = 0; j < n; j++) {
				
				if (a[j] - h > 0) { myTree += a[j] - h; }
			}
			
			if (myTree >= m) break;
			myTree = 0;
		}
		*/
		
		
		
		System.out.println(h);
	}

}
