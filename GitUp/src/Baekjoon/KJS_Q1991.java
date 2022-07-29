package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	char data;
	Node left;
	Node right;
	
	public Node(char data) { this.data = data; }
	public void addLeft(Node left) { this.left = left; }
	public void addRight(Node right) { this.right = right; }
	
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
		Node[] node = new Node[n];
		
		// 먼저 노드 다 만들기. node[0]은 A, node[1]은 B, ...
		char temp = 'A';
		for (int i = 0; i < node.length; i++) { node[i] = new Node(temp++);	}
		
		// 그 다음에 연결하기
		for (int i = 0; i < node.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			// 노드가 알파벳 순으로 정렬되어 있으므로 알파벳을 인덱스 숫자로 변환해서 사용
			int t = st.nextToken().charAt(0) - 'A';
			int left = st.nextToken().charAt(0) - 'A';
			int right = st.nextToken().charAt(0) - 'A';
			
			// '.' - 'A' = -19
			if (left != -19) node[t].addLeft(node[left]);
			if (right != -19) node[t].addRight(node[right]);
		}
		
		node[0].preOrder();
		System.out.println();
		node[0].inOrder();
		System.out.println();
		node[0].postOrder();
	}

}
