package Baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class KJS_Q15552_PrintWriter2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			pw.println(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()));
		}

		pw.flush();
	}

}
