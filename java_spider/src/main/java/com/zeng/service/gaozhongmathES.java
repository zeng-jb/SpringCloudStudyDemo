package com.zeng.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class gaozhongmathES {

    public static void main(String[] args) throws IOException {

        String url = "https://mp.weixin.qq.com/s?__biz=MzU3NTg3MTYyMQ==&mid=100006195&idx=5&sn=e72a6d7c748b69916de80c9689e07c6b&scene=19";

        Document document = Jsoup.parse(new URL(url), 10000);
        Element element = document.getElementById("js_content");
//        System.out.println(element.html());
        Elements elements = element.getElementsByTag("p");

        int id = 0;
        for (Element element1 : elements) {
            String img = element1.getElementsByTag("img").attr("data-src");
            System.out.println("=========================");
            System.out.println(img);
            if(img != null && img.length() > 0){
                URL target = new URL(img);
                URLConnection openConnection = target.openConnection();
                InputStream inputStream = openConnection.getInputStream();
                OutputStream outputStream = new FileOutputStream("D:\\zeng\\math\\gzbx2\\topic9\\" + id + ".jpeg");

                int temp = 0;
                while ((temp = inputStream.read()) != -1){
                    outputStream.write(temp);
                }
                System.out.println(id++ + ".jpeg 下载完毕" );
                inputStream.close();
                outputStream.close();
            }

        }
    }
}
