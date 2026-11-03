import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysPlus {

	// **************************************************************** Merge Arrays
	public static String[] mergeArrays(String[] first, String[] second) {
		List<String> list = new ArrayList<>(Arrays.asList(first));
		list.addAll(Arrays.asList(second));
		String[] s = new String[list.size()];
		list.toArray(s);
		return s;
	}

	// *********************************************************** Get String Subset
	public static String[][] getSubset(String[] set) {
		List<String> mySet = List.of(set);
		List<List<String>> subset = powerSet(mySet);
		String[][] array = new String[subset.size()][];
		String[] blankArray = new String[0];
		for (int i = 0; i < subset.size(); i++) {
			array[i] = subset.get(i).toArray(blankArray);
		}

		return array;
	}

	// **************************************************************** Make Subset
	public static <T> List<List<T>> powerSet(List<T> List) {
		@SuppressWarnings("unchecked")
		T[] element = (T[]) List.toArray();
		final int List_LENGTH = 1 << element.length;
		List<List<T>> powerList = new ArrayList<>();
		for (int binaryList = 0; binaryList < List_LENGTH; binaryList++) {
			List<T> subList = new ArrayList<>();
			for (int bit = 0; bit < element.length; bit++) {
				int mask = 1 << bit;
				if ((binaryList & mask) != 0) {
					subList.add(element[bit]);
				}
			}
			powerList.add(subList);
		}
		return powerList;
	}

}
