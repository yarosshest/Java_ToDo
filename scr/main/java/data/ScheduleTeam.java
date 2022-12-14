package data;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ScheduleTeam extends ScheduleCourse {
	private static final int COUNT_WEEKDAY = 6;
	private static final int NAME_TEAM_ROW_INDEX = 1;
	private static final int NAME_WEEKDAY_COLUMN_INDEX = 0;
	private static final int MIN_ROW_INDEX = 3, STEP_ROW_INDEX = 14;

	private final String group;
	private final List<DayParser> list_days_schedule;

	private String DownloadWorkbook() {
		/*
		Path thisDir = FileSystems.getDefault().getPath("Java_ToDo/scr/main/java/data/resources/", this.GetInstitute()+"_"+this.GetCourse()+".xlsx");
		String path_new_file = thisDir.toString();
		System.out.println(path_new_file);
		*/
		String path_new_file = "scr/main/resources/Schedules/"+this.GetInstitute()+"_"+this.GetCourse()+".xlsx";
		File file = new File(path_new_file);
		file.delete();
		try {
			URL url = new URL(this.url);
			Path exel_schedule = Path.of(path_new_file);
			InputStream input_stream = url.openStream();
			Files.copy(input_stream, exel_schedule);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return path_new_file;
	}

	public void Reload() {
		XSSFWorkbook wb;
		try {
			FileInputStream file = new FileInputStream(this.path);
			wb = new XSSFWorkbook(file);
			/*
			fs = new POIFSFileSystem(new FileInputStream(this.path));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			 */
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		boolean find = false;
		for (int sheet_index = 0; !find && sheet_index < wb.getNumberOfSheets(); sheet_index++) {
			XSSFSheet sheet = wb.getSheetAt(sheet_index);
			int position = 5;
			XSSFRow row_title = sheet.getRow(NAME_TEAM_ROW_INDEX);
			for (int i = 0; !find & i < row_title.getLastCellNum(); i++) {
				if (row_title.getCell(position).getStringCellValue().equals(this.group)) {
					for (int index = 0; index < COUNT_WEEKDAY; index++) {
						int row_index = MIN_ROW_INDEX + index * STEP_ROW_INDEX;
						String name = sheet.getRow(row_index).getCell(NAME_WEEKDAY_COLUMN_INDEX).getStringCellValue();
						this.list_days_schedule.add(new DayParser(name, sheet, row_index, position));
					}
					find = true;
				}
				position += (i % 2 == 0) ? 5 : 10;
				// 5 10 20 25 35 40 50 55
			}
		}
	}

	public ScheduleTeam(ScheduleCourse obj_course, String team) {
		super(obj_course.GetInstitute(), obj_course.GetCourse(), obj_course.GetUrl());  // here you can obj_course.institute ...
		String path;
		if (obj_course.PathExist()) {
			path = obj_course.GetPath();
		} else {
			path = DownloadWorkbook();
			obj_course.SetPath(path);
		}
		this.path = path;
		this.group = team;
		this.list_days_schedule = new ArrayList<>();
		this.Reload();
	}

	public final DayParser GetDay(int weekday) { return list_days_schedule.get(weekday); }

	public final List<DayParser> GetListDay() { return list_days_schedule; }

	public static void main(String[] args) {
		ScheduleTeam obj = new ScheduleTeam(
				new ScheduleCourse("IIT", "2", "https://webservices.mirea.ru/upload/iblock/5b1/ga54kas7srca03glyejl0qocavazep16/IIT_2-kurs_22_23_osen_07.10.2022.xlsx")
				, "????????-06-21"
		);
		obj.ClearSchedule();
	}
}
