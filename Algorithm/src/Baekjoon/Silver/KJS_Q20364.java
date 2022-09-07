package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node20364 {
	int data;
	Node20364 parent;
	boolean atHome = false;
	static int returnInt = 0; // static 안하니까 노드에 returnInt가 있어서 재귀할때마다 0으로 초기화됨
	
	Node20364(int data) {
		this.data = data;
	}
	
	void addParent(Node20364 node) {
		this.parent = node;
	}
	
	int finding(Node20364 node) {
//		if (node.atHome) return node.data; // 같은 숫자가 중복으로 들어올 수도 있음
//		if (!node.parent.atHome) return finding(node.parent);
//		else if (node.parent.atHome) return node.parent.data;
//		return 0;
		// 위에서 아래로 가야하는데, 아래에서 위로가게 만들어서 다시
		
		if (node.parent == null) return returnInt;
		
		if (node.atHome) {
			returnInt = node.data;
			finding(node.parent);
		}
		else if (node.parent.atHome) {
			returnInt = node.parent.data;
			finding(node.parent);
		}
		else if (!node.parent.atHome) {
			finding(node.parent);
		}
		return returnInt;
	}
}

public class KJS_Q20364 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 땅 수
		int q = Integer.parseInt(st.nextToken()); // 오리 수
		
		ArrayList<Node20364> nodes = new ArrayList<Node20364>();
		nodes.add(new Node20364(0)); // 1번 data가 1번 인덱스가 되게 자리 맞추기용 더미 노드
		nodes.add(new Node20364(1));
		
		nodes.get(1).addParent(nodes.get(0)); // 나중에 부모 찾을때 쓰기 위해 0번 노드를 부모로 등록
//		nodes.get(0).atHome = true;
		
		for (int i = 2; i <= n; i++) {
			Node20364 temp = new Node20364(i);
			if (i % 2 == 0) {
				nodes.add(temp);
				temp.addParent(nodes.get(i/2));
			}
			else if (i % 2 == 1) {
				nodes.add(temp);
				temp.addParent(nodes.get((i-1)/2));
			}
		}
		// 트리 만들기 문제 없음
		for (int i = 0; i < q; i++) {
			int newDuck = Integer.parseInt(br.readLine());
			Node20364.returnInt = 0;
			
//			nodes.get(0).finding(nodes.get(newDuck))
//			밑에서부터 위로 올라가면서 가장 처음 만난 atHome이 true인 노드의 data 반환
			// 위에서부터 밑으로 가야함
			
			if (nodes.get(0).finding(nodes.get(newDuck)) == 0) {
//				System.out.println(0);
				bw.write("0\n");
				nodes.get(newDuck).atHome = true;
			}
			else {
//				System.out.println(nodes.get(0).finding(nodes.get(newDuck)));
				bw.write(nodes.get(0).finding(nodes.get(newDuck)) + "\n");
			}
		}
		
		bw.flush();
	}

}
