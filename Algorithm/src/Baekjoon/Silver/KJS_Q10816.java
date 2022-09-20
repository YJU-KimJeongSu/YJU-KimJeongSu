package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class KJS_Q10816 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<Integer, Integer> cards = new HashMap<>(); // 카드 번호, 중복된 카드 수
		int t = 0;
		
		for (int i = 0; i < n; i++) {
			t = Integer.parseInt(st.nextToken());
			if (cards.containsKey(t)) {
				cards.put(t, (cards.get(t))+1);
			}
			else {
				cards.put(t, 1);
			}
		}
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			t = Integer.parseInt(st.nextToken());
			if (cards.containsKey(t)) {
//				System.out.print(cards.get(t) + " ");
				bw.write(cards.get(t) + " ");
			}
			else {
//				System.out.print(0 + " ");
				bw.write(0 + " ");
			}
		}
		
		bw.flush();
		
	}

}
