import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 通过字符的映射，求最大的字符串的值的和
 * @author huangyedi
 *
 */

public class Main {

	private static Scanner in;

	public static void main(String[] args) {
		int count = 0;
		in = new Scanner(System.in);
		count = in.nextInt();
		in.nextLine();
		String[] nums = new String[count];
		Map<Character, Long> weightMap = new TreeMap<Character, Long>();
		// init weightMap
		for (char c = 'A'; c <= 'J'; c++) {
			weightMap.put(c, 0l);
		}
		List<Character> headList = new ArrayList<Character>();
		for (int i = 0; i < count; i++) {
			nums[i] = in.nextLine();
			headList.add(nums[i].charAt(0));
			int len = nums[i].length();
			for (int k = 0; k < len; k++) {
				char c = nums[i].charAt(k);
				long weight = (long) Math.pow(10, len - 1 - k);
				weightMap.put(c, weightMap.get(c) + weight);
			}
		}
		// map.entrySet()转换成list 然后通过比较器来实现排序
		List<Map.Entry<Character, Long>> list = new ArrayList<Entry<Character, Long>>(weightMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Character, Long>>() {
			@Override
			public int compare(Entry<Character, Long> arg0, Entry<Character, Long> arg1) {
				// TODO Auto-generated method stub
				return arg1.getValue().compareTo(arg0.getValue());
			}
		});
		Map<Integer, Character> numWordMap = new HashMap<Integer, Character>();
		// 映射数字
		int num = 9;
		for (Entry<Character, Long> mapping : list) {
			char k = mapping.getKey();
			numWordMap.put(num, k);
			num--;
		}
		

		System.out.println(numWordMap);
		
		// 对0的处理
		char czero = numWordMap.get(0);
		num = 1;
		while (headList.contains(czero)) {
			char ci = numWordMap.get(num); // 从小到大的数字映射;
			// 与0交换映射
			numWordMap.put(0, ci);
			numWordMap.put(num, czero);
			num++;
			czero = ci;
		}

		System.out.println(numWordMap);
		
		// 交换映射的键值
		Map<Character, Integer> wordNumMap = new HashMap<Character, Integer>();
		for (Entry<Integer, Character> entry : numWordMap.entrySet()) {
			wordNumMap.put(entry.getValue(), entry.getKey());
		}
		// 计算和
		long sum = 0;
		for (int i = 0; i < count; i++) {
			int len = nums[i].length();
			for (int k = 0; k < len; k++) {
				char c = nums[i].charAt(k);
				int v = wordNumMap.get(c);
				long weight = (long) Math.pow(10, len - 1 - k);
				sum += v * weight;
			}
		}
		System.out.print(sum);
	}
}
