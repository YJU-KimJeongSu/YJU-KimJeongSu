import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BufferedReaderTest1 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String s = bf.readLine(); // 한줄씩 읽음
		String max = ""; // 가장 긴거 집어넣을 곳
		
		st = new StringTokenizer(s, " "); // 띄어쓰기마다 토큰 나누기
		
		while (st.hasMoreTokens()) {
			String temp = st.nextToken();
			if (max.length() < temp.length()) { max = temp; }
		}
		
		System.out.println(max);
	}

}
