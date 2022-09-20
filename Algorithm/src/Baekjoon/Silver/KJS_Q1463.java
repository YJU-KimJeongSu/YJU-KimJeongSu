package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class KJS_Q1463 {
	
	static class NumCount {
		int data;
		int count;
		
		public NumCount(int num, int count) {
			this.data = num;
			this.count = count;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Queue<NumCount> que = new LinkedList<NumCount>();
		que.add(new NumCount(Integer.parseInt(br.readLine()), -1));
		int data = 0;
		int count = 0;
		
		while (true) {
			NumCount tempNumCount = que.poll();
			data = tempNumCount.data;
			count = ++tempNumCount.count;
			
			if (data == 1) break;
			
			if (data % 3 == 0) que.add(new NumCount(data/3, count));
			if (data % 2 == 0) que.add(new NumCount(data/2, count));
			que.add(new NumCount(data-1, count));
		}
		
		System.out.println(count);
		
	}

}
