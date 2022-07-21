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

	// 후위 표기식으로 변경
	static char[] convToRPNExp(char[] exp) {
		char[] convExp = new char[exp.length];
		Stack<Character> stack = new Stack<Character>(); // 연산자 담아둘 자리
		int index = 0; // 이렇게 하고 convExp[index++]
		
		// 한글자씩 잘라서 convExp에 넣기
		for (int i = 0; i < exp.length; i++) {
			// 피연산자면 그냥 convExp에 넣기
			if (isDigit(exp[i])) { convExp[index++] = exp[i]; }
			else {
				// 연산자면 우선순위 판별해서 stack에 넣거나 convExp에 넣기
				// ()는 처리 방법이 다르니 따로 작성하고
				// +-*/는 처리 방법이 같으니 묶어서 처리
				switch (exp[i]) {
				case '(' :
					stack.push(exp[i]);
					break;
				case ')' :
					// ( 만날떄까지 스택에 있는 모든 요소 convExp에 넣기
					// ()는 convExp에 넣기 않게 주의
					while (true) {
						char temp = (char)stack.pop();
						if (temp == '(') break;
						else convExp[index++] = temp;
					}
					break;
				case '+' :	case '-' :	case '*' :	case '/' :
					// stack.peek()가 exp[i]보다 우선순위가 같거나(앞에꺼 먼저 계산하니 높음) 높으면, 전부 빼서 convExp에 넣기
					while (!stack.isEmpty() && WhoPrecOp(stack.peek(), exp[i]) >= 0) {
						convExp[index++] = stack.pop();
					}
					// exp[i]가 stack.peek()보다 우선순위가  낮으면, 그냥 스택에 넣기 / 비어있을떄도
					stack.push(exp[i]);
					break;
				} // end of switch
			} // end of if-else
		} // end of for i
		
		// 스택에 남은거 전부 내보내기
		while (!stack.isEmpty()) convExp[index++] = stack.pop();
		
		return convExp;
	}
	
	// 연산자 우선순위 비교
	static int WhoPrecOp(char c1, char c2) {
		if (GetOpPrec(c1) > GetOpPrec(c2)) return 1;
		else if (GetOpPrec(c1) < GetOpPrec(c2)) return -1;
		else return 0;
	}
	
	// 연산자 우선순위 판별
	static int GetOpPrec(char c) {
		switch (c) {
		case '*' :	case '/' : return 3;
		case '+' :	case '-' : return 2;
		case '(' : return 1;
		}
		return -1;
	}
	
	// 피연산자 (A-Z) 구분
	static boolean isDigit(char c) {
		boolean digit = false;
		if (c >= 'A' && c <= 'Z') { digit = true; }
		return digit;
	}
}
