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
	static int returnInt = 0; // static ���ϴϱ� ��忡 returnInt�� �־ ����Ҷ����� 0���� �ʱ�ȭ��
	
	Node20364(int data) {
		this.data = data;
	}
	
	void addParent(Node20364 node) {
		this.parent = node;
	}
	
	int finding(Node20364 node) {
//		if (node.atHome) return node.data; // ���� ���ڰ� �ߺ����� ���� ���� ����
//		if (!node.parent.atHome) return finding(node.parent);
//		else if (node.parent.atHome) return node.parent.data;
//		return 0;
		// ������ �Ʒ��� �����ϴµ�, �Ʒ����� ���ΰ��� ���� �ٽ�
		
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
		int n = Integer.parseInt(st.nextToken()); // �� ��
		int q = Integer.parseInt(st.nextToken()); // ���� ��
		
		ArrayList<Node20364> nodes = new ArrayList<Node20364>();
		nodes.add(new Node20364(0)); // 1�� data�� 1�� �ε����� �ǰ� �ڸ� ���߱�� ���� ���
		nodes.add(new Node20364(1));
		
		nodes.get(1).addParent(nodes.get(0)); // ���߿� �θ� ã���� ���� ���� 0�� ��带 �θ�� ���
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
		// Ʈ�� ����� ���� ����
		for (int i = 0; i < q; i++) {
			int newDuck = Integer.parseInt(br.readLine());
			Node20364.returnInt = 0;
			
//			nodes.get(0).finding(nodes.get(newDuck))
//			�ؿ������� ���� �ö󰡸鼭 ���� ó�� ���� atHome�� true�� ����� data ��ȯ
			// ���������� ������ ������
			
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
