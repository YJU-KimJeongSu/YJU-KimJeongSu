package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class KJS_Q2751 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		
		byte[] m = new byte[1000001];
		byte[] p = new byte[1000001];
		
		int t = 0;
		
		for (int i = 0; i < n; i++) {
			t = Integer.parseInt(br.readLine());
			if (t > 0) p[t]++;
			else m[-t]++;
		}
		
		for (int i = 0; i < m.length; i++) {
			if (m[1000000-i] != 0) bw.write(-1*(1000000-i) + "\n");
		}
		
		for (int i = 0; i < p.length; i++) {
			if (p[i] != 0) bw.write(i + "\n");
		}
		
		bw.close();
	}

}
