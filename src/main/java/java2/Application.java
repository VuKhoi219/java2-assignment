package java2;

import java.util.ArrayList;
import java.util.Scanner;

public class Application extends Thread {
    public static void main(String[] args) {
        generateMenu();
    }

    public static void generateMenu() {
        VnexpressArticleService vnexpressArticleService = new VnexpressArticleService();
        MyArticleService myArticleService = new MyArticleService();
        ArticleController articleController = new ArticleController();
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("Please enter your choice: ");
            System.out.println("1. Crawl information from VNExpress.");
            System.out.println("2. Crawl information from my source");
            System.out.println("3. Display by URL.");
            System.out.println("4. Display the list of current news");
            System.out.println("5. Exit the program");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // Crawl thông tin từ vnexpress
                    articleController.linkExtractor(vnexpressArticleService  , "https://vnexpress.net/the-thao");
                    break;
                case 2:
                    //Crawl thông tin từ nguồn của tôi
                    articleController.linkExtractor(myArticleService , "https://tienphong.vn/the-thao/");
                    break;
                case 3:
                    System.out.println("Enter the URL you want to view.");
                    String url = sc.nextLine();
                    articleController.finByUrl(url);
                case 4:
                    //Hiển thị danh sách tin hiện có
                    articleController.finAll();
                    break;
                case 5:
                    loop = false;
                    System.out.println("Bye");
                    break;
            }
        }
    }
}
