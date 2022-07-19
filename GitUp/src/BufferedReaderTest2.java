import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderTest2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine()); // 입력 받을 단어 수
		String[] s = new String[n];
		
		// 여러줄로 입력되니 StringTokenizer 없이
		for (int i = 0; i < s.length; i++) {
			s[i] = bf.readLine();
		}
		
		for (int i = 0; i < s.length; i++) {
			char[] temp = new char[s[i].length()];
			
			// s[i] 한글자씩 잘라서 char[]에 넣기 - 순서대로
			for (int j = 0; j < temp.length; j++) {
				temp[j] = s[i].charAt(j);
			}
			
			// 위에서 순서대로 넣었으니 역순으로 출력
			for (int j = temp.length-1; j >= 0; j--) {
				System.out.print(temp[j]);
			}
			System.out.println();
		}
	}

}
