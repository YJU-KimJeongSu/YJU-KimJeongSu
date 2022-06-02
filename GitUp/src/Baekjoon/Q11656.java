package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Q11656 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str = sc.next(); // 입력받을 문자열
		String[] st = new String[str.length()]; // 한글자씩 자른거
		String[] s = new String[str.length()]; // 잘라서 다시 붙힌거
		
		
		// 한글자씩 잘라서 st에 저장하기
		for (int i = 0; i < str.length(); i++) {
			st[i] = str.substring(i, i+1);
		}
		
		// 다시 붙혀서 s로 저장
		for (int i = 0; i < str.length(); i++) {
			for (int j = i; j < str.length(); j++) {
				if ( j == i ) s[i] = st[j];
				else s[i] += st[j];
			}
		}
		
		Arrays.sort(s);
		
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
		}
	}

}
