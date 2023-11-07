package Task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;

public class TestReadFile {
	public static void main(String[] args) throws FileNotFoundException {
		// Scanner input = new Scanner(new File("data/hamlet.txt"));
		Scanner input = new Scanner(new File("data/fit.txt"));

		while (input.hasNext()) {
			String word = input.next();
			System.out.println(word);
		}
		// count
		MyWordCount wordCounter = new MyWordCount();
		List<WordCount> wordCounts = wordCounter.getWordCounts();

		// Print word counts
		System.out.println("so lan lap lai: ");
		for (WordCount wordCount : wordCounts) {
			System.out.println(wordCount.getWord() + ": " + wordCount.getCount());
		}
		// unique

		MyWordCount wordCounter1 = new MyWordCount();

		// Lấy danh sách các từ xuất hiện một lần
		Set<String> uniqueWords = wordCounter1.getUniqueWords();

		// In ra các từ xuất hiện một lần
		System.out.println("Cac tu xuat hien mot lan:");
		for (String word1 : uniqueWords) {
			System.out.println(word1);
		}
		// khong bi trung lap
		MyWordCount wordCounter2 = new MyWordCount();

		Set<String> distinctWords = wordCounter2.getDistinctWords();

		System.out.println("danh sach khong bi lap:");
		for (String word1 : distinctWords) {
			System.out.println(word1);
		}
		// in ra số lần xuất hiện của mỗi từ không trùng lặp trong tệp tin và
		// sắp xếp chúng theo thứ tự tăng dần của các token
		MyWordCount wordCounter3 = new MyWordCount();
		Set<WordCount> sortedWordCounts = wordCounter3.printWordCounts();
		System.out.println("So lan xuat hien cua tung tu khong trung lap va sap xep theo thu tu tang dan:");
		for (WordCount wordCount : sortedWordCounts) {
			System.out.println(wordCount.getWord() + " - " + wordCount.getCount());
		}
		System.out.println("So lan xuat hien cua tung tu khong trung lap va sap xep theo thu tu giam dan:");
		MyWordCount wC = new MyWordCount();
		Set<WordCount> ex = wC.exportWordCountsByOccurence();
		for (WordCount count : ex) {
			System.out.println(count.getWord() + "-" + count.getCount());
		}

		MyWordCount wordCounter0 = new MyWordCount();

		// Lọc các từ bắt đầu bằng chữ 'A'
		String pattern = "A";
		Set<String> filteredWords = wordCounter0.filterWords(pattern);
		// Hiển thị các từ đã lọc
		System.out.println("\nCac tu đa loc (khong bat đau bang '" + pattern + "'): ");
		System.out.println(filteredWords);
		//
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);

		// Sử dụng phương thức remove để loại bỏ các số lẻ
		MyPredicates.remove(numbers, n -> n % 2 != 0);

		// In ra danh sách sau khi loại bỏ các số lẻ
		System.out.println("danh sach ko co so le: " + numbers);
		// retain
		Collection<String> words = new ArrayList<>();
		words.add("apple");
		words.add("orange");
		words.add("banana");
		words.add("grape");
		words.add("kiwi");
		Predicate<String> startsWithA = new Predicate<String>() {
			@Override
			public boolean test(String word) {
				return word.startsWith("a");
			}
		};

		// Gọi phương thức retain để giữ lại các từ bắt đầu bằng "a"
		MyPredicates.retain(words, startsWithA);

		// In ra danh sách sau khi giữ lại các từ
		System.out.println("Cac tu bat dau bang 'a': " + words);
	}

}
