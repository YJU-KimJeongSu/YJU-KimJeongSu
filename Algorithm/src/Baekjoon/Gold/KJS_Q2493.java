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
		
		// ó���� ���� �ȵǴ� ���ÿ� �ְ� 0���
		top.add(new Top(Integer.parseInt(st.nextToken()), index++));
		System.out.print(0 + " ");
		
		// �̹� ž�� �� ž���� ������ �� ž ����
		for (int i = 1; i < n; i++) {
			int temp = Integer.parseInt(st.nextToken()); // �̹� Ÿ�� ����
			// ���̻� ž�� ���ų�, temp���� ���� ž�� ������ Ż��
			while (!top.empty()) {
				if (top.peek().height <= temp) top.pop();
				else break;
			}
			
			// �̹��� ���°� ���� ū ž�̸� 0���
			if (top.empty()) {
				System.out.print(0 + " ");
				top.push(new Top(temp, index++));
			}
			// �ƴϸ� �� ž�� �ε��� ���
			else {
				System.out.print(top.peek().index + " ");
				top.push(new Top(temp, index++));
			}
			
		}
		
	}

}
