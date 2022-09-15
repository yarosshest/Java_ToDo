package main.java.data;

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

	public ScheduleCourse(String institute, String course, String url)
	{ this.institute = institute; this.course = course; this.url = url; this.path = null; }
}
