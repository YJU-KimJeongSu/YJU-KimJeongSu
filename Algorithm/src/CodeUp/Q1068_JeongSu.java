package CodeUp;
import java.util.*;

public class Q1068_JeongSu {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int score = sc.nextInt();
		
		if (score >= 90) System.out.println("A");
		else if (score >= 70) System.out.println("B");
		else if (score >= 40) System.out.println("C");
		else System.out.println("D");
	}

}
/* ���� ���ص� : 5 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 4��
 */