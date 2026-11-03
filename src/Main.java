import java.util.List;

public class Main {

	public static void main(String[] args) {

		final double b = Double.POSITIVE_INFINITY;
		
		// ****************************************** Directional graph
		// 1
		/*double[][] graph = {
				{ b, 2, b, b },
				{ 1, b, 6, 4 },
				{ b, 7, b, 8 },
				{ 6, 3, b, b }
				};*/
		
		// 2
		/*double[][] graph = {
				{ b, 2, 1, b, 4 },
				{ 1, b, 2, 1, 1 },
				{ b, 2, b, 3, b },
				{ 1, b, 1, b, 2 },
				{ 2, b, b, 1, b }
				};*/
		
		// 3
		/*double[][] graph = {
				{ b, 2, b, b, 3 },
				{ b, b, 2, 6, b },
				{ b, b, b, 5, 3 },
				{ 9, b, 4, b, 2 },
				{ b, 7, b, b, b }
				};*/
		
		// ****************************************** Normal graph
		// 1
		/*double[][] graph = {
				{ b, 10, 15, 20 },
				{ 10, b, 35, 25 },
				{ 15, 35, b, 30 },
				{ 20, 25, 30, b }
				};*/
		
		// 2
		/*double[][] graph = {
				{ b, 9, b, b, 8, 14 },
				{ 9, b, 14, b, 8, 7 },
				{ b, 14, b, 15, b, 8 },
				{ b, b, 15, b, 12, 12 },
				{ 8, 8, b, 12, b, 10 },
				{ 14, 7, 8, 12, 10, b }
				};*/
		
		// 3
		/*double[][] graph = {
				{ b, 500, 200, 185, 205 },
				{ 500, b, 305, 360, 340 },
				{ 200, 305, b, 320, 165 },
				{ 185, 360, 320, b, 302 },
				{ 205, 340, 165, 302, b }
				};*/
		
		// 4
		double[][] graph = {
				{ b, 50, 60, b, b, b, b, 70, 30, 10 },
				{ 50, b, 10, 90, 70, b, b, b, b, 70 },
				{ 60, 10, b, 20, 70, b, b, b, b, 40 },
				{ b, 90, 20, b, 20, 70, 80, b, b, b },
				{ b, 70, 70, 20, b, 60, 70, b, b, b },
				{ b, b, b, 70, 60, b, 20, 70, b, b },
				{ b, b, b, 80, 70, 20, b, 40, 50, b },
				{ 70, b, b, b, b, 70, 40, b, 30, 60 },
				{ 30, b, b, b, b, b, 50, 30, b, 20 },
				{ 10, 70, 40, b, b, b, b, 60, 20, b }
				};
		
		// ***********************************************************
		
		//String[] cityNames = { "V1", "V2", "V3", "V4" }; //4
		//String[] cityNames = { "1", "2", "3", "4" }; //4
		//String[] cityNames = { "1", "2", "3", "4", "5" }; //5
		//String[] cityNames = { "A", "B", "C", "D", "E" }; //5
		//String[] cityNames = { "A", "B", "C", "D", "E", "F" }; //6
		String[] cityNames = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }; //10

		// ***********************************************************
		DynamicProgramming t = new DynamicProgramming(graph, cityNames);
		t.run();

		String[][] rowNames = t.getTableRowsName();

		String[] columnNames = t.getTableColumnsName();

		System.out.println();

		double[][] getTable = t.getTable();
		int row = getTable.length;
		int column = getTable[0].length;
		String[][] table = new String[column][row];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++)
				if (getTable[i][j] == 0)
					table[j][i] = "-";
				else
					table[j][i] = getTable[i][j] + "";
		}

		for (int i = 0; i < rowNames.length; i++) {
			System.out.print("          " + "{ ");
			for (String item : rowNames[i])
				System.out.print(item + " ");
			System.out.print("}");
		}

		System.out.println();
		String space = "        ";

		System.out.println(space.substring(columnNames[0].length())
				+ "-----------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < column; i++) {
			System.out.print(columnNames[i] + "    | ");
			for (String item : table[i])
				System.out.print(item + space.substring(item.length()) + "    | ");
			System.out.println();
			System.out.println(space.substring(columnNames[i].length())
					+ "-----------------------------------------------------------------------------------------------------------------");
		}

		double pathLength = t.getPathLength();

		System.out.println("\n" + "Path Length: " + pathLength + "\n");

		List<String> path = t.getPath();

		System.out.println("Path: " + path);

	}

}
