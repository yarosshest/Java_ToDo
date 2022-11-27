package data;

import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PairParser {
	private static final int CELL_NUMBER_PAIR = 1;
	private static final int CELL_BEGIN = 2, CELL_END = 3;
	private static final int SHIFT_CELL_TYPE_OCCUPATION = 1;
	private static final int SHIFT_CELL_TEACHER = 2;
	private static final int COUNT_WEEK = 2;
	private static final int ODD_WEEK = 1;

	private final String number;
	private final String begin;
	private final String end;

	private final List<String> discipline;
	private final List<String> type_occupation;
	private final List<String> teacher;

	private List<String> information;

	public PairParser(XSSFRow[] row, int index_discipline) {
		this.number = Double.toString(row[ODD_WEEK].getCell(CELL_NUMBER_PAIR).getNumericCellValue());
		this.begin = row[ODD_WEEK].getCell(CELL_BEGIN).getStringCellValue();
		this.end = row[ODD_WEEK].getCell(CELL_END).getStringCellValue();
		discipline = new ArrayList<>();
		information = new ArrayList<>();
		type_occupation = new ArrayList<>();
		teacher = new ArrayList<>();
		for (int index = 0; index < COUNT_WEEK; index++) {
			String discipline = row[index].getCell(index_discipline).getStringCellValue().trim();
			Pattern pattern = Pattern.compile("^[а-я0-9]+.*? [А-Я]");
			Pattern nw_line = Pattern.compile("\n");

			Matcher matcher = pattern.matcher(discipline);
			Matcher matcher_nw_line = nw_line.matcher(discipline);
			if (matcher_nw_line.find()) {
				discipline = discipline.substring(0, matcher_nw_line.start());
			}
			if (matcher.find()) {
				this.discipline.add(discipline.substring(matcher.end()-1));
				this.information.add(discipline.substring(0, matcher.end()-1));
			} else {
				this.discipline.add(discipline);
				this.information.add("");
			}
			this.type_occupation.add(row[index].getCell(index_discipline + SHIFT_CELL_TYPE_OCCUPATION).getStringCellValue());
			this.teacher.add(row[index].getCell(index_discipline + SHIFT_CELL_TEACHER).getStringCellValue());
		}
	}

	public final String GetNumber() { return number; }
	public final String GetBegin() { return begin; }
	public final String GetEnd() { return end; }

	public final String GetDiscipline(int parity) { return discipline.get(parity); }
	public final String GetTypeOccupation(int parity) { return type_occupation.get(parity); }
	public final String GetTeacher(int parity) { return teacher.get(parity); }
}
