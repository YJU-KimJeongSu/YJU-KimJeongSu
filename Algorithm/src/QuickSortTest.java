
public class QuickSortTest {

	public static void main(String[] args) {
		int[] arr = { 5, 1, 6, 3, 4, 2, 7 };
		quickSort(arr, 0, arr.length-1);
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		int[] arr2 = { 2, 3, 8, 3, 6, 2 };
		quickSort(arr2, 0, arr2.length-1);
		for (int i : arr2) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		int[] arr3 = { 2, 2, 2, 2, 3, 2, 2, 2};
		quickSort(arr3, 0, arr3.length-1);
		for (int i : arr3) {
			System.out.print(i + " ");
		}
	}

	private static void quickSort(int[] arr, int i, int j) {
		int left = i;
		int right = j;
		int pivot = arr[(i+j)/2];
		int temp = 0;
		
		while (left <= right) {
			// 피벗 왼쪽엔 피벗보다 작은 수만 있게, 오른쪽엔 큰 수만 있게
			// left나 right가 pivot이랑 만나면 멈추니 그걸로는 안꼬임
			while (arr[left] < pivot) left++;
			while (arr[right] > pivot) right--;
			
			// 찾았으면 스왑.
			if (left <= right) {
				temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
			
		}
		
		// i == right 혹은 j == left면 정렬 끝. 아니면 파티션 나눠서 재귀
		if (i < right) quickSort(arr, i, right);
		if (j > left) quickSort(arr, left, j);
	}

}
