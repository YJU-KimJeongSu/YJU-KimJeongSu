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
 * 문제 이해도 : 3 (1 ~ 5 사이 숫자)
 * 문제 해결 : O
 * 코딩 시간 : 7분
 */