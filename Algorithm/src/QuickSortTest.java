
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
			// �ǹ� ���ʿ� �ǹ����� ���� ���� �ְ�, �����ʿ� ū ���� �ְ�
			// left�� right�� pivot�̶� ������ ���ߴ� �װɷδ� �Ȳ���
			while (arr[left] < pivot) left++;
			while (arr[right] > pivot) right--;
			
			// ã������ ����.
			if (left <= right) {
				temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
			
		}
		
		// i == right Ȥ�� j == left�� ���� ��. �ƴϸ� ��Ƽ�� ������ ���
		if (i < right) quickSort(arr, i, right);
		if (j > left) quickSort(arr, left, j);
	}

}
