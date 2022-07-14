package com.zeng.service;

import com.zeng.pojo.praceJD;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class searchJD {


    public List<praceJD> getpraceJD(String keyword) throws IOException {

        List<praceJD> praceJDList = new ArrayList<>();
        //https://search.jd.com/Search?keyword=java
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        Document document = Jsoup.parse(new URL(url), 30000);
        Element j_goodsList = document.getElementById("J_goodsList");
        Elements li = j_goodsList.getElementsByTag("li");

        for (Element element : li) {
            String img = element.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String title = element.getElementsByClass("p-name").eq(0).text();
            String price = element.getElementsByClass("p-price").eq(0).text();

            praceJD praceJD = new praceJD();
            praceJD.setImg(img);
            praceJD.setTitle(title);
            praceJD.setPrice(price);
            praceJDList.add(praceJD);
//            System.out.println("=================================");
//            System.out.println(img);
//            System.out.println(title);
//            System.out.println(price);
        }


        return praceJDList;
    }

    public static void main(String[] args) throws IOException {

        for (praceJD praceJD : new searchJD().getpraceJD("手机")) {
            System.out.println(praceJD);
        }
    }
}
