package Baekjoon.Silver;

public class KJS_Q4673 {

	public static void main(String[] args) {
		int[] arr = new int[10000];
		
		for (int i = 0; i < 10000; i++) {
			arr[i] = i;
		}
		
		// 현재 수 + (각 자리수를 더한 값) 을 삭제
		for (int i = 1; i < arr.length; i++) {
			int temp = i;
			int sum = 0;
			while (temp != 0) {
				sum += temp % 10;
				temp /= 10;
			}
			
			if (i+sum < 10000) { arr[i+sum] = 0; }
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) { System.out.println(arr[i]); }
		}
	}

}
