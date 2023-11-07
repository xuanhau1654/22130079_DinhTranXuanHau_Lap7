package Task1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

public class MyPredicates {

	public static <T> void remove(Collection<T> coll, Predicate<T> p) {
		Iterator<T> iter = coll.iterator();
		while (iter.hasNext()) {
			T next = iter.next();
			if (p.test(next))
				iter.remove();
		}
	}

	public static <T> void retain(Collection<T> coll, Predicate<T> p) {
		Iterator<T> iterator = coll.iterator();
		while (iterator.hasNext()) {
			T obj = iterator.next();
			if (!p.test(obj)) {
				iterator.remove();
			}
		}

	}

	public static <T> Set<T> collect(Collection<T> coll, Predicate<T> p) {
		Set<T> result = new HashSet<>();
		for (T obj : coll) {
			if (p.test(obj)) {
				result.add(obj);
			}
		}
		return result;
	}

	public static <T> int find(Collection<T> coll, Predicate<T> p) {
		int index = 0;
		for (T obj : coll) {
			if (p.test(obj)) {
				return index;
			}
			index++;
		}
		return -1; // Không tìm thấy phần tử thỏa mãn điều kiện
	}
}
