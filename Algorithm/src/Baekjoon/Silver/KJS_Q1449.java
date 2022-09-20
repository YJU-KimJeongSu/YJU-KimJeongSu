package Baekjoon.Silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KJS_Q1449 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] holes = new int[n];
		for (int i = 0; i < holes.length; i++) {
			holes[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(holes);
		
		int nowHole = 0;
		int tape = 0;
		
		// �������� �������� start = -1
		int start = -1;
		for (int i = 0; i < n; i++) {
			nowHole = holes[i];
			// �������� ������������ �� �������� ���ο� ������ ���
			if (start == -1) {
				start = nowHole;
				tape++;
			}
			
			// ������ �ϳ��� start+l-1���� Ŀ�� ����
			if (nowHole <= start+l-1) {
				continue;
			}
			else {
				start = nowHole;
				tape++;
			}
		}
		
		System.out.println(tape);
	}

}
