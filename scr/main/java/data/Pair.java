package main.java.data;

import org.apache.poi.xssf.usermodel.XSSFRow;

public class Pair {
	private static final int CELL_NUMBER_PAIR = 1;
	private static final int CELL_BEGIN = 2, CELL_END = 3;
	private static final int SHIFT_CELL_TYPE_OCCUPATION = 1;
	private static final int SHIFT_CELL_TEACHER = 2;
	private static final int COUNT_WEEK = 2;
	private static final int ODD_WEEK = 0, EVEN_WEEK = 1;

	private final String number;
	private final String begin;
	private final String end;

	private final String[] discipline;
	private final String[] type_occupation;
	private final String[] teacher;

	public Pair(XSSFRow[] row, int index_discipline) {
		this.number = row[ODD_WEEK].getCell(CELL_NUMBER_PAIR).getStringCellValue();
		this.begin = row[ODD_WEEK].getCell(CELL_BEGIN).getStringCellValue();
		this.end = row[ODD_WEEK].getCell(CELL_END).getStringCellValue();
		discipline = new String[COUNT_WEEK];
		type_occupation = new String[COUNT_WEEK];
		teacher = new String[COUNT_WEEK];
		for (int index = 0; index < COUNT_WEEK; index++) {
			this.discipline[index] = row[index].getCell(index_discipline).getStringCellValue();
			this.type_occupation[index] = row[index].getCell(index_discipline + SHIFT_CELL_TYPE_OCCUPATION).getStringCellValue();
			this.teacher[index] = row[index].getCell(index_discipline + SHIFT_CELL_TEACHER).getStringCellValue();
		}
	}

	public final String GetNumber() { return number; }
	public final String GetBegin() { return begin; }
	public final String GetEnd() { return end; }
	public final String GetOddDiscipline() { return discipline[ODD_WEEK]; }
	public final String GetEvenDiscipline() { return discipline[EVEN_WEEK]; }
	public final String GetOddTypeOccupation() { return type_occupation[ODD_WEEK]; }
	public final String GetEvenTypeOccupation() { return type_occupation[EVEN_WEEK]; }
	public final String GetOddTeacher() { return teacher[ODD_WEEK]; }
	public final String GetEvenTeacher() { return teacher[EVEN_WEEK]; }
}
