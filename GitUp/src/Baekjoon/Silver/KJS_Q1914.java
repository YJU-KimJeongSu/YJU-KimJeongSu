package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class KJS_Q1914 {
	static void hanoi(int num, int a, int b, int c) {
		if (num == 1) {
			System.out.println(a + " " + c);
		}
		else {
			// num-1에서 b랑 c 바꿔서 호출
			hanoi(num-1, a, c, b);
			// num번쨰 원반을 a에서 c로 이동
			System.out.println(a + " " + c);
			// num-1에서 b랑 a 바꿔서 호출
			hanoi(num-1, b, a, c);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 100이 들어올 경우 long으로도 불가능
//		System.out.println((long)(Math.pow(2, n) - 1));
		
		BigInteger bi = new BigInteger("2");
		System.out.println(bi.pow(n).subtract(new BigInteger("1")));
		// 2^n - 1 인데
		// 2^n이 BigInteger니까
		// -1도 BigInteger로 해줘야함
		
		if (n <= 20) {
			hanoi(n, 1, 2, 3);
		}
	}
}