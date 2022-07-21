package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class KJS_Q1918 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp = br.readLine();
		char[] exp = new char[temp.length()];
		for (int i = 0; i < exp.length; i++) {
			exp[i] = temp.charAt(i);
		}
		
//		System.out.println(convToRPNExp(exp));
		exp = convToRPNExp(exp);
		for (int i = 0; i < exp.length; i++) {
			if (exp[i] != 0) System.out.print(exp[i]);
		}
	}

	// ���� ǥ������� ����
	static char[] convToRPNExp(char[] exp) {
		char[] convExp = new char[exp.length];
		Stack<Character> stack = new Stack<Character>(); // ������ ��Ƶ� �ڸ�
		int index = 0; // �̷��� �ϰ� convExp[index++]
		
		// �ѱ��ھ� �߶� convExp�� �ֱ�
		for (int i = 0; i < exp.length; i++) {
			// �ǿ����ڸ� �׳� convExp�� �ֱ�
			if (isDigit(exp[i])) { convExp[index++] = exp[i]; }
			else {
				// �����ڸ� �켱���� �Ǻ��ؼ� stack�� �ְų� convExp�� �ֱ�
				// ()�� ó�� ����� �ٸ��� ���� �ۼ��ϰ�
				// +-*/�� ó�� ����� ������ ��� ó��
				switch (exp[i]) {
				case '(' :
					stack.push(exp[i]);
					break;
				case ')' :
					// ( ���������� ���ÿ� �ִ� ��� ��� convExp�� �ֱ�
					// ()�� convExp�� �ֱ� �ʰ� ����
					while (true) {
						char temp = (char)stack.pop();
						if (temp == '(') break;
						else convExp[index++] = temp;
					}
					break;
				case '+' :	case '-' :	case '*' :	case '/' :
					// stack.peek()�� exp[i]���� �켱������ ���ų�(�տ��� ���� ����ϴ� ����) ������, ���� ���� convExp�� �ֱ�
					while (!stack.isEmpty() && WhoPrecOp(stack.peek(), exp[i]) >= 0) {
						convExp[index++] = stack.pop();
					}
					// exp[i]�� stack.peek()���� �켱������  ������, �׳� ���ÿ� �ֱ� / �����������
					stack.push(exp[i]);
					break;
				} // end of switch
			} // end of if-else
		} // end of for i
		
		// ���ÿ� ������ ���� ��������
		while (!stack.isEmpty()) convExp[index++] = stack.pop();
		
		return convExp;
	}
	
	// ������ �켱���� ��
	static int WhoPrecOp(char c1, char c2) {
		if (GetOpPrec(c1) > GetOpPrec(c2)) return 1;
		else if (GetOpPrec(c1) < GetOpPrec(c2)) return -1;
		else return 0;
	}
	
	// ������ �켱���� �Ǻ�
	static int GetOpPrec(char c) {
		switch (c) {
		case '*' :	case '/' : return 3;
		case '+' :	case '-' : return 2;
		case '(' : return 1;
		}
		return -1;
	}
	
	// �ǿ����� (A-Z) ����
	static boolean isDigit(char c) {
		boolean digit = false;
		if (c >= 'A' && c <= 'Z') { digit = true; }
		return digit;
	}
}
