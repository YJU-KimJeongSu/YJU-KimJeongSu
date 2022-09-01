package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node1 {
	// ���� Ʈ����� ���� ����
	ArrayList<Node1> child = new ArrayList<Node1>();
	int data;
	static int leafCount = 0;
	
	Node1 (int data) { this.data = data; }
	void addChild(Node1 t) { child.add(t); }
	
	// ���� ��ȸ�ϸ鼭 child.size()�� 0�̸� (=��������) leafCount++;
	public void preOrder() {
		if (child.size() == 0) leafCount++;
		for (Node1 i : child) { i.preOrder(); }
	}
}

public class KJS_Q1068 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int root = -1;
		
		Node1[] node = new Node1[n];
		for (int i = 0; i < node.length; i++) { node[i] = new Node1(i); }
		
		st = new StringTokenizer(br.readLine());
		int[] parent = new int[n];
		for (int i = 0; i < parent.length; i++) { parent[i] = Integer.parseInt(st.nextToken()); }
		
		// node[i]�� �θ� parent[i]
		// �ݴ���ϸ� node[parant[i]]�� �ڽ��� node[i]�� ��
		for (int i = 0; i < n; i++) {
			// �Է°��� -1�̸� ��Ʈ. 0���� ��Ʈ��� ������ ����
			if (parent[i] == -1) { root = i; }
			else { node[parent[i]].addChild(node[i]); }
		}
		
		st = new StringTokenizer(br.readLine());
		int del = Integer.parseInt(st.nextToken());
		
		// ��� ��带 ���鼭 child�� node[del]�� ������ ����
		for (int i = 0; i < node.length; i++) { node[i].child.remove(node[del]); }
		
		// ��Ʈ ��尡 �����Ǹ� ����� �۵� �ȵǹǷ� ���� ����ó��
		if (del == root) System.out.println(0);
		else {
			node[root].preOrder();
			System.out.println(Node1.leafCount);
		}	
	}

}
