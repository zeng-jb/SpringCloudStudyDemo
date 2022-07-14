package com.zeng.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

public class pictureSpider {


    public static void main(String[] args) throws Exception{

        String url = "https://mp.weixin.qq.com/s/Wp_jSx2A-fXjBE0TRQA35Q";

        Document document = Jsoup.parse(new URL(url), 10000);
        Element element = document.getElementById("js_content");
        Elements elements = element.getElementsByTag("section");
        for (Element element1 : elements) {
            Elements section = element1.getElementsByTag("section");
            System.out.println("=======================");
            String img = section.eq(2).attr("img");
            System.out.println(section.html());
//            System.out.println("========================");
            System.out.println(img);
        }


    }
}
