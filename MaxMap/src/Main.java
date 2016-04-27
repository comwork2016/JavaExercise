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
 * ͨ���ַ���ӳ�䣬�������ַ�����ֵ�ĺ�
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
		// map.entrySet()ת����list Ȼ��ͨ���Ƚ�����ʵ������
		List<Map.Entry<Character, Long>> list = new ArrayList<Entry<Character, Long>>(weightMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Character, Long>>() {
			@Override
			public int compare(Entry<Character, Long> arg0, Entry<Character, Long> arg1) {
				// TODO Auto-generated method stub
				return arg1.getValue().compareTo(arg0.getValue());
			}
		});
		Map<Integer, Character> numWordMap = new HashMap<Integer, Character>();
		// ӳ������
		int num = 9;
		for (Entry<Character, Long> mapping : list) {
			char k = mapping.getKey();
			numWordMap.put(num, k);
			num--;
		}
		

		System.out.println(numWordMap);
		
		// ��0�Ĵ���
		char czero = numWordMap.get(0);
		num = 1;
		while (headList.contains(czero)) {
			char ci = numWordMap.get(num); // ��С���������ӳ��;
			// ��0����ӳ��
			numWordMap.put(0, ci);
			numWordMap.put(num, czero);
			num++;
			czero = ci;
		}

		System.out.println(numWordMap);
		
		// ����ӳ��ļ�ֵ
		Map<Character, Integer> wordNumMap = new HashMap<Character, Integer>();
		for (Entry<Integer, Character> entry : numWordMap.entrySet()) {
			wordNumMap.put(entry.getValue(), entry.getKey());
		}
		// �����
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
