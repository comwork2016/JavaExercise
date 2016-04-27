import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static Scanner in;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		int count = in.nextInt();
		in.nextLine();
		int sumLen = 0;
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < count; i++) {
			int op = in.nextInt();
			int len = in.nextInt();
			if (op == 1) {
				list.add(len);
				sumLen += len;
			} else {
				list.remove(new Integer(len));
				sumLen -= len;
			}
			int maxLen = findMaxLen(list);
			if (maxLen < sumLen / 2.0) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}
	}

	private static int findMaxLen(List<Integer> list) {
		int maxLen = 0;
		for (int i = 0; i < list.size(); i++) {
			if (maxLen < list.get(i)) {
				maxLen = list.get(i);
			}
		}
		return maxLen;
	}
}
