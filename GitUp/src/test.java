import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int row = sc.nextInt();

		for(int i=0; i<row; i++) {
			int column = sc.nextInt();
			double array[][] = new double[row][column];
			int sum = 0;
			int avg = 0;
			for(int j=0; j<array[i].length; j++) {
				array[i][j] = sc.nextInt();
				sum += array[i][j];
			}
			avg = sum / array[i].length;
			System.out.print(avg);
		}



	}
}