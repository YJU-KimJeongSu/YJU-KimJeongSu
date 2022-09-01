package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	int data;
	Node left;
	Node right;
	
	public Node(int data) {
		this.data = data;
	}
	
	public void insert(Node node) {
		// 현재 비교하는 노드의 data보다 작으면
		// 현재 노드의 left가 null이면 node.left에 넣기
		// null이 아니라면 left와 비교
		if (this.data < node.data ) {
			if (node.left == null) node.left = this;
			else this.insert(node.left);
		}
		else if (this.data > node.data) {
			if (node.right == null) node.right = this;
			else this.insert(node.right);
		}
	}
	
	public void postOrder(Node node) {
		if (node.left != null) postOrder(node.left);
		if (node.right != null) postOrder(node.right);
		System.out.println(node.data);
	}
}

public class KJS_Q5639 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		Node root = new Node(Integer.parseInt(br.readLine()));
		
		while (true) {
			int data;
			try {
				st = new StringTokenizer(br.readLine());
				data = Integer.parseInt(st.nextToken());
			} catch (Exception e) {
				break;
			}
			
//			Node node = new Node(data);
			new Node(data).insert(root);;
		}
		
		root.postOrder(root);
	}

}
