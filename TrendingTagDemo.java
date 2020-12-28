package smallcodes;



import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class TrendingTagDemo {
	
	private static final int HASHTAGS_TO_PRINT = 10;

	public static void main(String[] args) throws Exception {
		InputStream input = new FileInputStream(new File("E:/Workspace/practice1/input.txt"));
//		InputStream input = System.in;
		Scanner in = new Scanner(input);
		CounterMap counterMap = new CounterMap();

		while (in.hasNextLine()) {
			String tag = getTag(in.nextLine());
			if (tag != null) {
				counterMap.put(tag);
			}
		}

		int count = 0;
		for (Map.Entry<String, Integer> entry : counterMap.getSortedByCount()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
			count++;
			if (count == HASHTAGS_TO_PRINT) {
				break;
			}
		}

		in.close();
	}

	private static String getTag(String tweet) {
		String tag = tweet.substring(tweet.indexOf('#') + 1);
		if (tweet.length() == tag.length()) {
			return null;
		}
		return tag;
	}

	@SuppressWarnings("serial")
	public static class CounterMap extends TreeMap<String, Integer> {

		public CounterMap() {
		}

		public void put(String tag) {
			Integer count = get(tag);
			if (count == null) {
				count = 1;
			} else {
				count++;
			}
			put(tag, count);
		}

		public TreeSet<Map.Entry<String, Integer>> getSortedByCount() {
			TreeSet<Map.Entry<String, Integer>> set = new TreeSet<>(
					new Comparator<Map.Entry<String, Integer>>() {
						@Override
						public int compare(java.util.Map.Entry<String, Integer> o1,
								java.util.Map.Entry<String, Integer> o2) {
							return o2.getValue() - o1.getValue();
						}
					});
			set.addAll(entrySet());
			return set;
		}
	}
}
