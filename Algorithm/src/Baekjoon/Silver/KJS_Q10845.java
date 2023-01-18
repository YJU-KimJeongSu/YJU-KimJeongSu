package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class KJS_Q10845 {
	static class Queue {
		private int pointer;
		private int[] arr;
		
		public Queue(int size) {
			arr = new int[size];
		}
		
		public void push(int x) {
			arr[pointer++] = x;
		}
		
		public int pop() {
			if (pointer > 0) {
				int t = arr[0];
				
				for (int i = 1; i < arr.length; i++) {
					arr[i-1] = arr[i];
				}
				pointer--;
				
				return t;
			} else {
				return -1;
			}
		}
		
		public int size() {
			return pointer;
		}
		
		public int empty() {
			return (pointer == 0) ? 1 : 0;
		}
		
		public int front() {
			return (pointer > 0) ? arr[0] : -1;
		}
		
		public int back() {
			return (pointer > 0) ? arr[pointer-1] : -1;

		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		Queue queue = new Queue(n);
		
		for (int i = 0; i < n; i++) {
			String t = br.readLine();
			
			switch (t) {
			case "pop" : bw.write(queue.pop() + "\n"); break;
			case "size" : bw.write(queue.size() + "\n");; break;
			case "empty" : bw.write(queue.empty() + "\n");; break;
			case "front" : bw.write(queue.front() + "\n");; break;
			case "back" : bw.write(queue.back() + "\n");; break;
			default :
				t = t.substring(5);
				queue.push(Integer.parseInt(t));
			}
		}
		
		bw.flush();
	}
}
