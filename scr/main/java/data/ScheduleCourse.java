package java.data;

import java.io.File;

class ScheduleCourse {
	private final String institute;
	private final String course;
	protected final String url;
	protected String path;

	public final String GetInstitute() { return institute; }
	public final String GetCourse() { return course; }
	public final String GetUrl() { return url; }
	public final String GetPath() { return path; }
	public void SetPath(String path) { this.path = path; }
	public final boolean PathExist() { return path != null; }
	public void ClearSchedule() {
		if (this.PathExist()) {
			File file = new File(this.path);
			boolean fl = file.delete();
			if (!fl) {
				System.out.println("File: " + this.path + " not delete!");
			}
			this.path = null;
		}
	}

	public ScheduleCourse(String institute, String course, String url)
	{ this.institute = institute; this.course = course; this.url = url; this.path = null; }
}
