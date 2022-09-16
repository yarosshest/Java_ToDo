package java.data;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

class Day {
	private static final int COUNT_WEEK = 2, COUNT_WEEKDAY = 12;

	private final String name;
	private final Pair[] list_pairs_day;

	public final String GetName() { return name; }
	public final Pair GetPair(int number) { return list_pairs_day[number]; }

	Day(String name, XSSFSheet sheet, int row_index, int index_discipline) {
		this.name = name;
		list_pairs_day = new Pair[COUNT_WEEKDAY];
		XSSFRow[] row = new XSSFRow[COUNT_WEEK];
		for (int index = 0; index < COUNT_WEEKDAY; index += COUNT_WEEK) {
			row[0] = sheet.getRow(row_index + index);  // odd week
			row[1] = sheet.getRow(row_index + index + 1);  // even week
			list_pairs_day[index] = new Pair(row, index_discipline);
		}
	}
}
