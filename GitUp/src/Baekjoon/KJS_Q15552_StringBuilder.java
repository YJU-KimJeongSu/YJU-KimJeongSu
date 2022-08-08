package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KJS_Q15552_StringBuilder {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// string을 build하는 것은 빠르지만, 출력하는 것이 느리기 떄문에 출력은 한번만하므로 빠름
		// StrinBuilder = 동기화 X , StringBuffer = 동기화 O
		// https://ifuwanna.tistory.com/221
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()));
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
