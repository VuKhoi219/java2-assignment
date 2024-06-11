package java2;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticleController extends  Thread{
    // lấy link con qua link cha
    public void linkExtractor(ArticleService articleService, String url) {
        MySqlArticleRepository mySqlArticleRepository = new MySqlArticleRepository();
        ArrayList<String> getLinks = articleService.getLinks(url);
        for (int i = 0; i < getLinks.size(); i++) {
            Article article = articleService.getArticle(getLinks.get(i));
            System.out.printf("%d - %s\n", i + 1, article.getTitle());
            mySqlArticleRepository.save(article);
        }
    }

    // Hiển thị
    public void finByUrl() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the URL you want to view.");
        String url = scanner.nextLine();
        MySqlArticleRepository mySqlArticleRepository = new MySqlArticleRepository();
        Article article = mySqlArticleRepository.findByUrl(url);
        if (article != null) {
            System.out.println("Url: " + article.getBaseUrl());
            System.out.println("Title: " + article.getTitle());
            System.out.println("Description: " + article.getDescription());
            System.out.println("Content: " + article.getContent());
            System.out.println("Thumbnail: " + article.getThumbnail());
            System.out.println("Create at: " + article.getCreatedAt());
            System.out.println("Update at: " + article.getUpdateAt());
            System.out.println("Delete at: " + article.getDeleteAt());
            System.out.println("Status: " + article.getStatus());
        } else {
            System.out.println("Article not found");
        }
    }

    public void finAll() {
        MySqlArticleRepository mySqlArticleRepository = new MySqlArticleRepository();
        ArrayList<Article> articles = mySqlArticleRepository.findAll();
        for (Article article : articles) {
            System.out.println("Title: " + article.getTitle());
        }
    }
}
