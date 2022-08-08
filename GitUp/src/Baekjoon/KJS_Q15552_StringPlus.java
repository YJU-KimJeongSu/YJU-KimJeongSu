package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KJS_Q15552_StringPlus {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 시간초과!
		// StringBuilder와 기본 원리는 비슷해 보이지만
		// StringBuilder를 사용하지 않을 경우 문자 추가시마다 객체를 새로 생성하므로 느림
		// https://docs.microsoft.com/ko-kr/dotnet/api/system.text.stringbuilder?view=net-6.0
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int t = Integer.parseInt(br.readLine());
		String s = "";
		
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			s += (Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())) + "\n";
		}
		
		System.out.println(s);
	}

}
