package Task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class MyWordCount {
	// public static final String fileName = "data/hamlet.txt";
	public static final String fileName = "data/fit.txt";

	private List<String> words = new ArrayList<>();

	public MyWordCount() {
		try {
			this.words.addAll(Utils.loadWords(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt)
	// In this method, we do not consider the order of tokens.
	public List<WordCount> getWordCounts() {

		Set<String> uniqueWords = new HashSet<>(); // Sử dụng Set để theo dõi các từ đã thêm vào danh sách
		List<WordCount> re = new ArrayList<>();

		for (String w : words) {
			// Nếu từ chưa được thêm vào danh sách, thêm nó và đếm số lần xuất hiện
			if (uniqueWords.add(w)) {
				WordCount wc = new WordCount(w, countWord(w));
				re.add(wc);
			}
		}
		return re;
	}

	private int countWord(String w) {
		int count = 0;
		for (String word : words) {
			if (word.equals(w)) {
				count++;
			}
		}
		return count;
	}

	// Returns the words that their appearance are 1, do not consider duplidated
	// words
	public Set<String> getUniqueWords() {
		Set<String> re = new HashSet<>();
		List<WordCount> list = getWordCounts();
		for (WordCount wc : list) {
			if (wc.getCount() == 1)
				re.add(wc.getWord());
		}
		return re;
	}

	// Returns the words in the text file, duplicated words appear once in the
	// result
	public Set<String> getDistinctWords() {
		Set<String> re = new HashSet<>();
		List<WordCount> list = getWordCounts();
		for (WordCount wc : list) {
			re.add(wc.getWord());
		}
		return re;
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according ascending order of tokens
	// in ra số lần xuất hiện của mỗi từ không trùng lặp trong tệp tin và
	// sắp xếp chúng theo thứ tự tăng dần của các token
	// Example: An - 3, Bug - 10, ...
	public Set<WordCount> printWordCounts() {
		List<WordCount> wordCounts = getWordCounts();

		// Sử dụng Map để lưu số lần xuất hiện của mỗi từ
		Map<String, Integer> wordCountMap = new HashMap<>();
		for (WordCount wordCount : wordCounts) {
			wordCountMap.put(wordCount.getWord(), wordCount.getCount());
		}

		// Tạo một TreeSet để sắp xếp WordCount objects theo thứ tự tăng dần

		Set<WordCount> re = new TreeSet<>(new Comparator<>() {
			public int compare(WordCount w1, WordCount w2) {
				return -(w2.getCount() - w1.getCount());
			}
		});
		// Thêm tất cả các WordCount objects vào TreeSet
		re.addAll(getWordCounts());
		return re;
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according descending order of occurences
	// Example: Bug - 10, An - 3, Nam - 2.
	public Set<WordCount> exportWordCountsByOccurence() {
		List<WordCount> wordCounts = getWordCounts();

		// Sử dụng Map để lưu số lần xuất hiện của mỗi từ
		Map<String, Integer> wordCountMap = new HashMap<>();
		for (WordCount wordCount : wordCounts) {
			wordCountMap.put(wordCount.getWord(), wordCount.getCount());
		}
		// Tạo một TreeSet để sắp xếp WordCount objects theo thứ tự tăng dần

		Set<WordCount> re = new TreeSet<>(new Comparator<>() {
			public int compare(WordCount w1, WordCount w2) {
				return -(w1.getCount() - w2.getCount());
			}
		});
		// Thêm tất cả các WordCount objects vào TreeSet
		re.addAll(getWordCounts());
		return re;
	}

	// delete words begining with the given pattern (i.e., delete words begin with
	// 'A' letter
	public Set<String> filterWords(String pattern) {
		Set<String> filteredWords = new HashSet<>();
		Iterator<String> iterator = words.iterator();

		// Duyệt qua danh sách từ và thêm vào filteredWords nếu không bắt đầu bằng
		// pattern
		while (iterator.hasNext()) {
			String word = iterator.next();
			if (!word.startsWith(pattern)) {
				filteredWords.add(word);
			}
		}

		// Cập nhật danh sách words
		words = new ArrayList<>(filteredWords);

		// Trả về tập hợp các từ đã lọc
		return filteredWords;
	}
}
