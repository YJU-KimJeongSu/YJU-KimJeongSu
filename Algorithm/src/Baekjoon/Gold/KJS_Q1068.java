package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node1 {
	// 이진 트리라는 조건 없음
	ArrayList<Node1> child = new ArrayList<Node1>();
	int data;
	static int leafCount = 0;
	
	Node1 (int data) { this.data = data; }
	void addChild(Node1 t) { child.add(t); }
	
	// 전위 순회하면서 child.size()가 0이면 (=리프노드면) leafCount++;
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
		
		// node[i]의 부모가 parent[i]
		// 반대로하면 node[parant[i]]의 자식이 node[i]가 됨
		for (int i = 0; i < n; i++) {
			// 입력값이 -1이면 루트. 0번이 루트라는 조건은 없음
			if (parent[i] == -1) { root = i; }
			else { node[parent[i]].addChild(node[i]); }
		}
		
		st = new StringTokenizer(br.readLine());
		int del = Integer.parseInt(st.nextToken());
		
		// 모든 노드를 돌면서 child에 node[del]이 있으면 삭제
		for (int i = 0; i < node.length; i++) { node[i].child.remove(node[del]); }
		
		// 루트 노드가 삭제되면 제대로 작동 안되므로 따로 예외처리
		if (del == root) System.out.println(0);
		else {
			node[root].preOrder();
			System.out.println(Node1.leafCount);
		}	
	}

}
