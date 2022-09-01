package Baekjoon.Silver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class KJS_Q25178 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean canDo = true;
		int leng = sc.nextInt();
		String duru = sc.next();
		String dura = sc.next();
		char[] c1 = new char[duru.length()];
		char[] c2 = new char[dura.length()];
		
		for (int i = 0; i < c1.length; i++) {
			c1[i] = duru.charAt(i);
		}
		for (int i = 0; i < c2.length; i++) {
			c2[i] = dura.charAt(i);
		}
		
		
		// 조건 1
		char[] temp1 = c1.clone();
		char[] temp2 = c2.clone();
		Arrays.sort(temp1);
		Arrays.sort(temp2);
		for (int i = 0; i < temp1.length; i++) {
			if (temp1[i] != temp2[i]) { canDo = false; break; }
		}
		
		
		// 조건 2. 정상 작동
		if (c1[0] != c2[0]) { canDo = false; }
		if (c1[c1.length-1] != c2[c2.length-1]) { canDo = false; }
		
		
		// 조건 3
		// a e i o u 제거 대신 0으로 통일
		for (int i = 0; i < c1.length; i++) {
			if (c1[i] == 'a' || c1[i] == 'e' || c1[i] == 'i' || c1[i] == 'o' || c1[i] == 'u') {
				c1[i] = '0';
			}
		}
		for (int i = 0; i < c2.length; i++) {
			if (c2[i] == 'a' || c2[i] == 'e' || c2[i] == 'i' || c2[i] == 'o' || c2[i] == 'u') {
				c2[i] = '0';
			}
		}
		
		// ArrayList를 만들어서 0이 아니면 넣기
		ArrayList temp3 = new ArrayList();
		ArrayList temp4 = new ArrayList();
		for (int i = 0; i < c1.length; i++) {
			if (c1[i] != '0') temp3.add(c1[i]);
		}
		for (int i = 0; i < c2.length; i++) {
			if (c2[i] != '0') temp4.add(c2[i]);
		}
		
		// ArrayList에서 순서가 같나 비교
		for (int i = 0; i < temp3.size(); i++) {
			if (temp3.get(i) != temp4.get(i)) { canDo = false; break; }
		}
		
		
		
		// 출력
		if (canDo == false) { System.out.println("NO"); }
		else { System.out.println("YES"); }
	}

}
