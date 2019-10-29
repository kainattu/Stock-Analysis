package com.kainattu.stock.api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TestApp {

    public static void main(String[] args) {
//		SpringApplication.run(StockApiApplication.class, args);


        try {

//            Document doc = Jsoup.connect("https://in.finance.yahoo.com/quote/INDIACEM.NS/financials").userAgent("Mozilla").get();
			Document doc = Jsoup.connect("https://in.finance.yahoo.com/quote/SBIN.NS/financials").userAgent("Mozilla").get();

//            System.out.println("Doc :"+doc);

            Elements elements1= doc.getElementsByClass("D(tbr) fi-row Bgc($hoverBgColor):h"); //Bgc($lv1BgColor) fi-row:h_Bgc($hoverBgColor)");

            int i=1;
            for(Element e: elements1){
//				System.out.println(" e "+i+":"+ e);
                i++;

                int j=1;
                for ( Element e1 :e.getAllElements()){
                    System.out.println(" Child "+i+","+j+":"+e1);
                    j++;
                }

            }


          /*  int k=0;
            for ( Element e2:elements.get(7).getAllElements().select("div")){
                System.out.println("e2 "+k+":"+e2.text());
                k++;
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
