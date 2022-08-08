package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KJS_Q15552_StringBuffer {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// StrinBuilder = 동기화 X , StringBuffer = 동기화 O
		// https://ifuwanna.tistory.com/221
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()));
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
