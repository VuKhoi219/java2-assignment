package java2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class MyArticleService implements ArticleService {
    @Override
    public ArrayList<String> getLinks(String url) {
        HashSet<String> links = new HashSet();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements articles = doc.select("a");
            for (int i = 0; i < articles.size(); i++) {
                String href = articles.get(i).attr("href");
                if (href.contains("https://tienphong") && href.contains(".tpo")) {
                    links.add(href);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ArrayList(links);
    }

    @Override
    public Article getArticle(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            String title = document.select("h1.article__title ").text();
            String description = document.select("div.article__sapo").text();
            String content = document.select("div.article__body p").text();
            String thumb = document.select("div.article__body  img").attr("data-src");
            String create = document.select("time ").text();
            Article article = new Article();
            article.setBaseUrl(url);
            article.setTitle(title);
            article.setDescription(description);
            article.setContent(content);
            article.setThumbnail(thumb);
            article.setCreatedAt(create);
            return article;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
