package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KJS_Q10828_2 {
	static class Stack {
		private int pointer;
		private int[] arr;
		
		public Stack() {
			pointer = 0;
			arr = new int[10000];
		}
		
		public void push(int e) {
			arr[pointer++] = e;
		}
		
		public int pop() {
			return (pointer > 0) ? arr[--pointer] : -1;
		}
		
		public int size() {
			return pointer;
		}
		
		public int empty() {
			return (pointer > 0) ? 0 : 1;
		}
		
		public int top() {
			return (pointer > 0) ? arr[pointer-1] : -1;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count = Integer.parseInt(br.readLine());
		Stack stack = new Stack();
		
		for (int i = 0; i < count; i++) {
			String input = br.readLine();
			switch (input) {
			case "pop": 
				System.out.println(stack.pop());
				break;
				
			case "size":
				System.out.println(stack.size());
				break;
				
			case "empty":
				System.out.println(stack.empty());
				break;
				
			case "top":
				System.out.println(stack.top());
				break;
				
			default:
				StringTokenizer st = new StringTokenizer(input, " ");
				st.nextToken();
				stack.push(Integer.parseInt(st.nextToken()));
			}
		}
	}

}
