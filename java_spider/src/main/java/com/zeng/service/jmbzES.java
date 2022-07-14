package com.zeng.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;

public class jmbzES {

    public static void main(String[] args) throws Exception {
        String url = "https://bz.zzzmh.cn/index";

        Document document = Jsoup.parse(new URL(url), 10000);

        Elements elements = document.getElementsByClass("container");
        System.out.println(elements.html());
    }
}
