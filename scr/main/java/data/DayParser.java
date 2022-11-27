package data;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.List;

public class DayParser {
	private static final int COUNT_WEEK = 2, COUNT_PAIR = 7;

	private final String name;
	private final List<PairParser> list_pairs_day;

	public final String GetName() { return name; }
	public final List<PairParser> GetListPair() { return list_pairs_day; }

	DayParser(String name, XSSFSheet sheet, int row_index, int index_discipline) {
		this.name = name;
		list_pairs_day = new ArrayList<>();
		XSSFRow[] row = new XSSFRow[COUNT_WEEK];
		for (int index = 0; index < COUNT_PAIR*2; index += COUNT_WEEK) {
			row[1] = sheet.getRow(row_index + index);  // even week
			row[0] = sheet.getRow(row_index + index + 1);  // odd week
			list_pairs_day.add(new PairParser(row, index_discipline));
		}
	}
}
