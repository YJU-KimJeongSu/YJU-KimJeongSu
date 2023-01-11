package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class KJS_Q10773 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < count; i++) {
			int t = Integer.parseInt(br.readLine());
			
			if (t > 0) {
				stack.push(t);
			} else {
				stack.pop();
			}
		}
		
		long sum = 0;
		int size = stack.size();
		for (int i = 0; i < size; i++) {
			sum += stack.pop();
		}
		
		System.out.println(sum);
	}

}
