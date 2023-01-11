package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class KJS_Q10828 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < count; i++) {
			String input = br.readLine();
			
			switch (input) {
			case "pop":
				if (stack.size() > 0) {
					System.out.println(stack.pop());
				} else {
					System.out.println(-1);
				}
				break;
				
			case "size":
				System.out.println(stack.size());
				break;
				
			case "empty":
				if (stack.size() > 0) {
					System.out.println(0);
				} else {
					System.out.println(1);
				}
				break;
				
			case "top":
				if (stack.size() > 0) {
					System.out.println(stack.peek());
				} else {
					System.out.println(-1);
				}
				break;
				
			default : // push x
				StringTokenizer st = new StringTokenizer(input, " ");
				st.nextToken();
				stack.push(Integer.parseInt(st.nextToken()));
			}
		}
	}

}
