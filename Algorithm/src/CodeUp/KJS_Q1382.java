package CodeUp;

public class KJS_Q1382 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 9; i++) {
			for (int j = 2; j <= 5; j++) {
				if (j*i >= 10) {
					if (j != 5) System.out.print(j + " x " + i + " = " + j*i + "\t");
					else System.out.print(j + " x " + i + " = " + j*i);
				}
				else if (j*i < 10) {
					if (j != 5) System.out.print(j + " x " + i + " =  " + j*i + "\t");
					else System.out.print(j + " x " + i + " =  " + j*i);
				}
			}
			System.out.println();
		}
	}

}
/* 20220510
 * ���� ���ص� : 3 (1 ~ 5 ���� ����)
 * ���� �ذ� : O
 * �ڵ� �ð� : 7��
 */