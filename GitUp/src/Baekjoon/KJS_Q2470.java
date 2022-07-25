package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KJS_Q2470 {
	static int sol1 = 0;
	static int sol2 = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int[] sol = new int[n];
		
		for (int i = 0; i < sol.length; i++) {
			sol[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(sol);
		twoPointer(sol);
		System.out.println(sol1 + " " + sol2);
	}
	
	static void twoPointer(int[] sol) {
		int start = 0;
		int end = sol.length-1; // 양 끝에서부터 하나씩
		int sum = sol[start] + sol[end];
		int temp = sol[start] + sol[end]; // 두 용액의 특성값 합
		sol1 = sol[start];
		sol2 = sol[end];
		
		while (true) {
			if (start == end) break;
			// 용액이 +면 +값이 조금 더 낮은(end에서 한칸 왼쪽) 용액으로 다시
			if (sum > 0) {
				if (start > sol.length-1) break;
				sum -= sol[end--];
				sum += sol[end];
				// 현재 용액의 특성값 합의 절댓값이 temp보다 작으면 sol1, sol2를 현재껄로
				if (Math.abs(sum) < Math.abs(temp) && start != end) {
					sol1 = sol[start];
					sol2 = sol[end];
					temp = sum;
				}
			}
			// 용액이 -면 -값이 조금 더 낮은(start에서 한칸 오른쪽) 용액으로 다시
			else if (sum < 0) {
				if (end > sol.length-1) break;
				sum -= sol[start++];
				sum += sol[start];
				if (Math.abs(sum) < Math.abs(temp) && start != end) {
					sol1 = sol[start];
					sol2 = sol[end];
					temp = sum;
				}
			}
			// 0이면 끝
			else {
				sol1 = sol[start];
				sol2 = sol[end];
				break;
			}
		}
	}

}
