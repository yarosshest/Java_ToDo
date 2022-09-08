package data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Schedules {
    private static class Item {
        public String institute;
        public String course;
        public String url;
        private Item(String institute, String course, String url)
        { this.institute = institute; this.course = course; this.url = url; }
    }
    private ArrayList<Item> list_schedules;

    public void reload_url() {
        Document doc;
        try {
            doc = Jsoup.connect("https://www.mirea.ru/schedule/").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements institutes = doc.getElementsByClass("uk-text-bold");

        for (Element institute: institutes) {
            String name_institute = institute.text();
            Elements courses = institute.parent().parent().getElementsByClass("uk-link-toggle");
            for (Element course: courses) {
                String url = course.attr("href");
                String year = course.getElementsByClass("uk-link-heading uk-margin-small-top").text();
                list_schedules.add(new Item(name_institute, year, url));
            }
        }
    }

    Schedules() {
        this.list_schedules = new ArrayList<Item>();
        this.reload_url();
    }

    public static void main(String[] args) {
        Schedules obj = new Schedules();
        for (Item it: obj.list_schedules) {
            System.out.println("+==+");
            System.out.println(it.institute);
            System.out.println(it.course);
            System.out.println(it.url);
        }
    }
}