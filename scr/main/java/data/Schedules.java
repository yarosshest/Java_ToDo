package main.java.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Schedules {
    private final ArrayList<ScheduleCourse> list_urls_schedules;

    private void ClearSchedules() {
        for (ScheduleCourse cr: list_urls_schedules) {
            if (cr.PathExist()) {
                File file = new File(cr.GetPath());
                boolean fl = file.delete();
                if (!fl) {
                    System.out.println("File: " + cr.GetPath() + " not delete!");
                }
            }
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
    Schedules() {
        this.list_urls_schedules = new ArrayList<ScheduleCourse>();
        this.ReloadUrl();
    }

    public ScheduleTeam GetScheduleTeam(String institution, String course, String team){
        for (ScheduleCourse it: list_urls_schedules) {
            if (it.GetInstitute().equals(institution) && it.GetCourse().equals(course)) {
                return new ScheduleTeam(it, team);
            }
        }
        return null;
    }
}