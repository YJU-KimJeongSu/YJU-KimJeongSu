package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class KJS_Q9012 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < count; i++) {
			String input = br.readLine();
			char[] t = input.toCharArray();
			stack.clear();
			
			// 앞에서부터 한글자씩 돌려보기
			for (int j = 0; j < t.length; j++) {
				if (t[j] == '(') { // 괄호 열기
					stack.add(1);
				} else { // 괄호 닫기
					if (stack.size() > 0) { // 열고나서 닫으면 정상
						stack.pop();
					} else { // 안열고(=스택이 빔) 그냥 닫으면 비정상
						stack.add(1); // 뒤에 if문에서 비정상으로 걸리게 처리
						break;
					}
				}
			}
			
			if (stack.size() > 0) { // 한줄 끝났는데 스택이 비어있지 않으면 비정상
				System.out.println("NO");
			} else { // 비어있으면 정상
				System.out.println("YES");
			}
			
		}
	}

}
