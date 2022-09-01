import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BufferedReaderTest1 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String s = bf.readLine(); // ���پ� ����
		String max = ""; // ���� ��� ������� ��
		
		st = new StringTokenizer(s, " "); // ���⸶�� ��ū ������
		
		while (st.hasMoreTokens()) {
			String temp = st.nextToken();
			if (max.length() < temp.length()) { max = temp; }
		}
		
		System.out.println(max);
	}

}
