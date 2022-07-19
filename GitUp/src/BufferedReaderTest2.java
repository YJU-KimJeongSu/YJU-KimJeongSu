import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderTest2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine()); // �Է� ���� �ܾ� ��
		String[] s = new String[n];
		
		// �����ٷ� �ԷµǴ� StringTokenizer ����
		for (int i = 0; i < s.length; i++) {
			s[i] = bf.readLine();
		}
		
		for (int i = 0; i < s.length; i++) {
			char[] temp = new char[s[i].length()];
			
			// s[i] �ѱ��ھ� �߶� char[]�� �ֱ� - �������
			for (int j = 0; j < temp.length; j++) {
				temp[j] = s[i].charAt(j);
			}
			
			// ������ ������� �־����� �������� ���
			for (int j = temp.length-1; j >= 0; j--) {
				System.out.print(temp[j]);
			}
			System.out.println();
		}
	}

}
