package Baekjoon;

import java.util.Scanner;

public class Q25205 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		char[] name = new char[n];
		String temp = sc.next();
		
		for (int i = 0; i < name.length; i++) {
			name[i] = temp.charAt(i);
		}
		
		if (name[n-1] == 'q' || name[n-1] == 'w' || name[n-1] == 'e' || name[n-1] == 'r'
			|| name[n-1] == 't' || name[n-1] == 'a' || name[n-1] == 's' || name[n-1] == 'd' 
			|| name[n-1] == 'd' || name[n-1] == 'f' || name[n-1] == 'g' || name[n-1] == 'z' 
			|| name[n-1] == 'x' || name[n-1] == 'c' || name[n-1] == 'v') System.out.println(1);
		else System.out.println(0);
	}

}
