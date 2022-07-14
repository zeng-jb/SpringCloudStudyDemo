package com.zeng.service;

import com.zeng.pojo.ScoreOne;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class gaokaoSpider {


    public static List<ScoreOne> getScoreOne() throws IOException {
        List<ScoreOne> list = new ArrayList<>();

        String url  = "https://www.dxsbb.com/news/117925.html";

        Document parse = Jsoup.parse(new URL(url),30000);

        Elements tablebox = parse.getElementsByClass("tablebox");
//        System.out.println(tablebox.html());
        Element element = tablebox.get(0);
        Elements tr = element.getElementsByTag("tr");
        List<Element> tr1 = tr.subList(2, tr.size()-1);
        for (Element element1 : tr1) {

//            System.out.println();
//            System.out.println(element1.html());
            Elements tds = element1.select("td");
//            System.out.println(tds.text());
            String score = tds.eq(0).text();
            String num = tds.eq(1).text();
            String total = tds.eq(2).text();

            ScoreOne scoreOne = new ScoreOne(score, Integer.parseInt(num), Integer.parseInt(total));

            list.add(scoreOne);
        }

        return list;
    }

    public static void main(String[] args) throws IOException {
        List<ScoreOne> scoreOne = getScoreOne();

        for (ScoreOne one : scoreOne) {
            System.out.println(one);
        }
    }
}
