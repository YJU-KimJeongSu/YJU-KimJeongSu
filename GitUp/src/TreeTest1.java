class Node {
	public String data;
	public Node left;
	public Node right;
	
	public Node(String data) { this.data = data; }
	
	public void addLeft(Node left) { this.left = left; }
	public void addRight(Node right) { this.right = right; }
	
	// 전위 순회
	public void preOrder() {
		System.out.println(data);
		if (left != null) left.preOrder();
		if (right != null) right.preOrder();
	}
	
	// 중위 순회
	public void inOder() {
		if (left != null) left.inOder();
		System.out.println(data);
		if (right != null) right.inOder();
	}
	
	// 후위 순회
	public void postOrder() {
		if (left != null) left.postOrder();
		if (right != null) right.postOrder();
		System.out.println(data);
	}
}

public class TreeTest1 {

	public static void main(String[] args) {
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");
		
		a.addLeft(b);
		a.addRight(c);
		c.addLeft(d);
		c.addRight(e);
		
		a.preOrder();
		System.out.println("----------------");
		a.inOder();
		System.out.println("----------------");
		a.postOrder();
	}

}
