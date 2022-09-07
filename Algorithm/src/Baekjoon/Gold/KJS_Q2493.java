package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Top {
	int height;
	int index;
	
	Top(int height, int index) {
		this.height = height;
		this.index = index;
	}
}

public class KJS_Q2493 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int index = 1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Top> top = new Stack<Top>();
		
		// 처음은 수신 안되니 스택에 넣고 0출력
		top.add(new Top(Integer.parseInt(st.nextToken()), index++));
		System.out.print(0 + " ");
		
		// 이번 탑이 앞 탑보다 높으면 앞 탑 삭제
		for (int i = 1; i < n; i++) {
			int temp = Integer.parseInt(st.nextToken()); // 이번 타워 높이
			// 더이상 탑이 없거나, temp보다 높은 탑을 만나면 탈출
			while (!top.empty()) {
				if (top.peek().height <= temp) top.pop();
				else break;
			}
			
			// 이번에 들어온게 제일 큰 탑이면 0출력
			if (top.empty()) {
				System.out.print(0 + " ");
				top.push(new Top(temp, index++));
			}
			// 아니면 앞 탑의 인덱스 출력
			else {
				System.out.print(top.peek().index + " ");
				top.push(new Top(temp, index++));
			}
			
		}
		
	}

}
