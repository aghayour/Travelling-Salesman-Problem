import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicProgramming {

	private int GRAPH_LENGTH;
	private double[][] graph;
	private String[] cityNames;
	private List<String> path;
	private double[][] table;
	private String[] tableColumnsName;
	private String[][] tableRowsName;
	private double pathLength;
	private boolean getPath;

	// **************************************************************** Constructor
	public DynamicProgramming(double[][] graph, String[] cityNames) {

		this.GRAPH_LENGTH = graph.length;

		if (GRAPH_LENGTH != cityNames.length)
			throw new IllegalStateException("cityNames must be equals with graph matrix length");
		
		if (GRAPH_LENGTH != graph[0].length)
			throw new IllegalStateException("graph matrix must be square (n x n)");

		this.graph = graph;
		this.cityNames = cityNames;

		path = new ArrayList<>();

		tableColumnsName = Arrays.copyOfRange(cityNames, 1, cityNames.length);

		tableRowsName = ArraysPlus.getSubset(Arrays.copyOfRange(cityNames, 1, cityNames.length));

		getPath = false;
	}

	// **************************************************************** Run
	public void run() {
		int column = GRAPH_LENGTH - 1; // -1 is because we don't enter start node in table

		int row = 1 << column; // the rows of table should be 2^column. E.g. 8x3

		table = new double[row][column];

		for (int i = 0; i < row; i++)
			for (int j = 0; j < column; j++) {
				if (Arrays.asList(tableRowsName[i]).contains(tableColumnsName[j]))
					continue;
				table[i][j] = D(i, j, false, false);
			}

		path.add(cityNames[0]);
		getPath = true;
		pathLength = D(row - 1, 0, true, false);
		path.add(cityNames[0]);
	}

	// **************************************************************** D method
	private double D(int row, int column, boolean firstNode, boolean findPath) {
		if (findPath && row == 0)
			return 0;
		if (firstNode)
			column--;
		else if (!findPath) {

			if (table[row][column] != 0)
				return table[row][column];
			if (row == 0)
				return graph[column + 1][0];
		}
		double min = Double.POSITIVE_INFINITY;
		int jj = 0, kk = 0;
		for (int i = 0; i < tableRowsName[row].length; i++) {
			int j = 0, k = 0;
			double num = 0;
			for (; tableRowsName[row][i] != cityNames[j + 1]; j++)
				;
			if (i == 0)
				for (; !Arrays.equals(tableRowsName[k],
						Arrays.copyOfRange(tableRowsName[row], i + 1, tableRowsName[row].length)); k++)
					;
			else if (i == tableRowsName[row].length - 1)
				for (; !Arrays.equals(tableRowsName[k],
						Arrays.copyOfRange(tableRowsName[row], 0, tableRowsName[row].length - 1)); k++)
					;
			else
				for (; !Arrays.equals(tableRowsName[k],
						ArraysPlus.mergeArrays(Arrays.copyOfRange(tableRowsName[row], 0, i),
								Arrays.copyOfRange(tableRowsName[row], i + 1, tableRowsName[row].length))); k++)
					;
			num = graph[column + 1][j + 1] + D(k, j, false, false);
			if (num < min) {
				min = num;
				jj = j;
				kk = k;
			}
		}

		if (getPath) {
			path.add(cityNames[jj + 1]);
			D(kk, jj, false, true);
		}

		return min;
	}

	// **************************************************************** Getters
	public double[][] getTable() {
		return table;
	}

	public String[] getTableColumnsName() {
		return tableColumnsName;
	}

	public String[][] getTableRowsName() {
		return tableRowsName;
	}

	public double getPathLength() {
		return pathLength;
	}

	public List<String> getPath() {
		return path;
	}

}
