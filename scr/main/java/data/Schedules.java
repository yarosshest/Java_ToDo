package data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Schedules {
    private final ArrayList<ScheduleCourse> list_urls_schedules;

    private void ClearSchedules() {
        for (ScheduleCourse cr: list_urls_schedules) {
            cr.ClearSchedule();
        }
        list_urls_schedules.clear();
    }

    public void ReloadUrl() {
        this.ClearSchedules();
        Document doc;
        try {
            doc = Jsoup.connect("https://www.mirea.ru/schedule/").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements institutes = doc.getElementsByClass("uk-text-bold");

        for (Element institute: institutes) {
            String name_institute = institute.text();
            assert institute.parent() != null;  // throw ERROR
            assert institute.parent().parent() != null;
            Elements courses = institute.parent().parent().getElementsByClass("uk-link-toggle");
            for (Element course: courses) {
                String url = course.attr("href");
                String year = course.getElementsByClass("uk-link-heading uk-margin-small-top").text();
                list_urls_schedules.add(new ScheduleCourse(name_institute, year, url));
            }
        }
    }
    public Schedules() {
        this.list_urls_schedules = new ArrayList<ScheduleCourse>();
        this.ReloadUrl();
    }

    public ScheduleTeam GetScheduleTeam(String institution, String course, String team){
//        Calendar now = Calendar.getInstance();
//        int year = now.get(Calendar.YEAR) % 100;
//        int course = year - Integer.parseInt(team.trim());
        course = course + " курс";
        for (ScheduleCourse it: list_urls_schedules) {
            if (it.GetInstitute().equals(institution) && it.GetCourse().equals(course)) {
                return new ScheduleTeam(it, team);
            }
        }
        return null;
    }
}