package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node0 {
	char data;
	Node0 left;
	Node0 right;
	
	public Node0(char data) { this.data = data; }
	public void addLeft(Node0 left) { this.left = left; }
	public void addRight(Node0 right) { this.right = right; }
	
	// 전위 순회
	public void preOrder() {
		System.out.print(data);
		if (left != null) { left.preOrder(); }
		if (right != null) { right.preOrder(); }
	}
	
	// 중위 순회
	public void inOrder() {
		if (left != null) { left.inOrder(); }
		System.out.print(data);
		if (right != null) { right.inOrder(); }
	}
	
	// 후위 순회
	public void postOrder() {
		if (left != null) { left.postOrder(); }
		if (right != null) { right.postOrder(); }
		System.out.print(data);
	}
}

public class KJS_Q1991 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		Node0[] Node0 = new Node0[n];
		
		// 먼저 노드 다 만들기. Node0[0]은 A, Node0[1]은 B, ...
		char temp = 'A';
		for (int i = 0; i < Node0.length; i++) { Node0[i] = new Node0(temp++);	}
		
		// 그 다음에 연결하기
		for (int i = 0; i < Node0.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			// 노드가 알파벳 순으로 정렬되어 있으므로 알파벳을 인덱스 숫자로 변환해서 사용
			int t = st.nextToken().charAt(0) - 'A';
			int left = st.nextToken().charAt(0) - 'A';
			int right = st.nextToken().charAt(0) - 'A';
			
			// '.' - 'A' = -19
			if (left != -19) Node0[t].addLeft(Node0[left]);
			if (right != -19) Node0[t].addRight(Node0[right]);
		}
		
		Node0[0].preOrder();
		System.out.println();
		Node0[0].inOrder();
		System.out.println();
		Node0[0].postOrder();
	}

}
