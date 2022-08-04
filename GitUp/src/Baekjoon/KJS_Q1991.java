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
	
	// ���� ��ȸ
	public void preOrder() {
		System.out.print(data);
		if (left != null) { left.preOrder(); }
		if (right != null) { right.preOrder(); }
	}
	
	// ���� ��ȸ
	public void inOrder() {
		if (left != null) { left.inOrder(); }
		System.out.print(data);
		if (right != null) { right.inOrder(); }
	}
	
	// ���� ��ȸ
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
		
		// ���� ��� �� �����. Node0[0]�� A, Node0[1]�� B, ...
		char temp = 'A';
		for (int i = 0; i < Node0.length; i++) { Node0[i] = new Node0(temp++);	}
		
		// �� ������ �����ϱ�
		for (int i = 0; i < Node0.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			// ��尡 ���ĺ� ������ ���ĵǾ� �����Ƿ� ���ĺ��� �ε��� ���ڷ� ��ȯ�ؼ� ���
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
