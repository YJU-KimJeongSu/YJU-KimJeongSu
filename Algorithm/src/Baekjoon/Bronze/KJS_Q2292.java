package Baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KJS_Q2292 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long num = Integer.parseInt(br.readLine()); // 찾아가야 하는 방 번호
		int count = 0; // 지나간 방 수
		
		long t = 0;
		num--;
		
		while (num > 0) {
			num = num - t;
			t += 6;
			count++;
		}
		
		if (count == 0) System.out.println(1);
		else System.out.println(count);
	}

}
