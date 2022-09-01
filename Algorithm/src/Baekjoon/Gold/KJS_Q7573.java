package Baekjoon.Gold;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class KJS_Q7573 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		ArrayList<Point> throwingP = new ArrayList<Point>();
		st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()); // ������ ũ��
		int l = Integer.parseInt(st.nextToken()); // �׹� ����
		int m = Integer.parseInt(st.nextToken()); // ����� ��
		int max = 0;
		int temp = 0;
		Point[] p = new Point[m];
		
		// ����� ��ġ �ޱ�
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			p[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// �׹��� ������ �� ��ġ ���ϱ�
		// ����� ��ġ(p[]) && ������� ������
		for (int i = 0; i < p.length; i++) {
			throwingP.add(p[i]);
		}
		// �� ���� �������� ���, �ٸ� ��� ���̶� ���غ��鼭 ������ ã��
		for (int i = 0; i < p.length-1; i++) {
			for (int j = i+1; j < p.length; j++) {
				int tempX = p[i].x;
				int tempY = p[i].y;
				if (p[j].x < p[i].x) tempX = p[j].x;
				if (p[j].y < p[i].y) tempY = p[j].y;
				throwingP.add(new Point(tempX, tempY));
			}
		}
		
		// �׹� ��� ���ϱ�. l = (i + j) * 2  // i = x����, j = y����
		for (int i = 1; i < l/2; i++) {
			int j = l/2 - i;
			temp = Search(p, m, i, j, throwingP);
			if (temp > max) max = temp;
		}
		
		System.out.println(max);
	}
	
	// �� ��縶�� ��� ����� ��ǥ�� �����ϸ鼭 ����� ã��
	// ����� ��ġ������ �׹��� ������ �ȵǰ�, ������������ ����������
	static int Search(Point p[], int m, int x, int y, ArrayList<Point> throwingP) {
		int maxFish = 0; // �ش� ������� ã�� �� �ִ� �ִ� ����� ��
		int fish = 0;
		
		// �� ����⸶�� �����غ��� �ݺ���
		for (int i = 0; i < throwingP.size(); i++) {
			// �������� �� ����Ⱑ ��� �ִ��� ã�� �ݺ���
			fish = 0;
			for (int j = 0; j < m; j++) {
				// ��� ������� x��ǥ(p[j].x)�� ���� ������(p[i].x)���� ũ�ų� ����
				// �׹� ����(=���� ������+�׹�ũ��(p[i].x+x))���� �۰ų� ������ fish++
				// y�� ��������
//				if ((p[j].x >= p[i].x && p[j].x <= p[i].x+x) &&
//					(p[j].y >= p[i].y && p[j].y <= p[i].y+y)) fish++;
				Point tempP = (Point) throwingP.get(i);
				if ((p[j].x >= tempP.x && p[j].x <= tempP.x+x) &&
					(p[j].y >= tempP.y && p[j].y <= tempP.y+y)) fish++;
			}
			if (fish > maxFish) maxFish = fish;
		}
		return maxFish;
	}

}
