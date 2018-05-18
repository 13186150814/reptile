package com.example;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

/**
 * @author 常培兵
 * @Description: 工具
 * @date 2018/5/12 15:11
 */
public class ReptileUtil {
    //
    private static String path="E:/image/";

    private static final String[] user_Agent={
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1",
            "Mozilla/5.0 (X11; CrOS i686 2268.111.0) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.57 Safari/536.11",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1090.0 Safari/536.6",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/19.77.34.5 Safari/537.1",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.9 Safari/536.5",
            "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.36 Safari/536.5",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.0 Safari/536.3",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1",
            "Mozilla/5.0 (X11; CrOS i686 2268.111.0) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.57 Safari/536.11",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1090.0 Safari/536.6",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/19.77.34.5 Safari/537.1",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.9 Safari/536.5",
            "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.36 Safari/536.5",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.0 Safari/536.3",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24"
    };

    public static String getHtml(String url){
        Random random = new Random();
        int pick = random.nextInt(36);
        String result=null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent",  user_Agent[pick]);
        httpGet.setHeader("Accept-Charset","UTF-8");
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity,"UTF-8");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //解析主页面
    public static void parsingHomepage(String homeUrl){
        System.out.println("解析主页面地址："+homeUrl);
        String html = getHtml(homeUrl);
        Document doc = Jsoup.parse(html);
        Elements elements=doc.select("div[class='gallary_item_album album'] table a[class='photosUrl']");
        for (Element elem:elements) {
            String href=elem.attr("href");
            parsingSetOfDrawings(href);
        }
    }

    //解析套图
    public static void parsingSetOfDrawings(String url){
        System.out.println("解析套图地址："+url);
        String html = getHtml(url);
        Document doc = Jsoup.parse(html);
        //获取套图名称
        String fileName=doc.select("div[class='inline']").text();
        //使用套图名称创建文件夹
        String newPath=path+fileName;
        File file=new File(newPath);
        if (!file.exists()){
            file.mkdirs();
        }

        //先解析当前页面图片，因为第一页地址不同
        parsing(url,newPath+File.separator);
        //获取最大页码
        String pages = doc.select("div[class='paginator'] span[class='next']").prev().text();
        System.out.println("获取套图的最大页码:"+pages);
        //先移除后缀准备拼接页码
        url = url.replace(".html","");
        int max=0;
        try{
            max = Integer.valueOf(pages);
        }catch (Exception ex){
            ex.printStackTrace();
            max = 0;
        }
        for (int i=2;i<=max;i++){
            parsing(url+"-"+i+".html",newPath+File.separator);
        }
    }
    //解析图片
    public static void parsing(String url,String filePath){
        System.out.println("解析图片地址："+url);
        String html = getHtml(url);
        Document doc = Jsoup.parse(html);
        Elements elements=doc.select("div[class='gallary_item'] img[class='photosImg']");
        for (Element elem:elements) {
            String src=elem.attr("src");
            System.out.println("最终图片地址:"+src);
            String fileName=src.substring(src.lastIndexOf('/')+1);
            downloadHttpUrl(src,filePath,fileName);
        }
    }

    /**
     * 下载文件---返回下载后的文件存储路径
     *
     * @param url 文件地址
     * @param dir 存储目录
     * @param fileName 存储文件名
     * @return
     */
    public static void downloadHttpUrl(String url, String dir, String fileName) {
        try {
            URL httpUrl = new URL(url);
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            FileUtils.copyURLToFile(httpUrl, new File(dir+fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //http://www.xiumeim.com/albums/page-2.html
        String startUrl = "http://www.xiumeim.com/";
        String html = getHtml(startUrl);
        Document doc = Jsoup.parse(html);
        //获取最大页码
        String pages = doc.select("div[class='paginator'] span[class='next']").prev().text();
        System.out.println("最大页码："+pages);
        int maxPages=0;
        try{
            maxPages = Integer.valueOf(pages);
        }catch (Exception ex) {
            maxPages = 0;
        }
        /*for (int i=2;i<=maxPages;i++){
            String url = "http://www.xiumeim.com/albums/page-"+i+".html";
            System.out.println(url);
        }*/
        parsingHomepage(startUrl);
        for (int i=2;i<=2;i++){
            String url = "http://www.xiumeim.com/albums/page-"+i+".html";
            parsingHomepage(url);

        }
    }

   /* public static void main(String[] args) {
        parsingSetOfDrawings("http://www.xiumeim.com/photos/LUGirls-191086.html");
    }*/

}
