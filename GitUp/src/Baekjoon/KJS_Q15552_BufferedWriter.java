package Baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class KJS_Q15552_BufferedWriter {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 버퍼 관련 참고 : https://javacan.tistory.com/entry/43
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			bw.write(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()) + "\n");
		}
		
		bw.flush();
	}

}
