package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KJS_Q16953 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long[] now = { a, 1 }; // 1¹ø ÀÎµ¦½º´Â Å½»ö È½¼ö
		boolean isFind = false; 
		
		Queue<long[]> q = new LinkedList<long[]>();
		q.add(now);
		
		while (!q.isEmpty()) {
			now = q.poll();
			if (now[0]*2 == b) {
				isFind = true;
				break;
			}
			else if (now[0]*2 < b) q.add(new long[] {now[0]*2, now[1]+1});	
			
			if (now[0]*10+1 == b ) {
				isFind = true;
				break;
			}
			else if (now[0]*10+1 < b) q.add(new long[] {now[0]*10+1, now[1]+1});
		}
		
		if (isFind) System.out.println(now[1]+1);
		else System.out.println(-1);
	}

}
