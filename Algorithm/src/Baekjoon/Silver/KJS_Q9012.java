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
			
			// �տ������� �ѱ��ھ� ��������
			for (int j = 0; j < t.length; j++) {
				if (t[j] == '(') { // ��ȣ ����
					stack.add(1);
				} else { // ��ȣ �ݱ�
					if (stack.size() > 0) { // ������ ������ ����
						stack.pop();
					} else { // �ȿ���(=������ ��) �׳� ������ ������
						stack.add(1); // �ڿ� if������ ���������� �ɸ��� ó��
						break;
					}
				}
			}
			
			if (stack.size() > 0) { // ���� �����µ� ������ ������� ������ ������
				System.out.println("NO");
			} else { // ��������� ����
				System.out.println("YES");
			}
			
		}
	}

}
