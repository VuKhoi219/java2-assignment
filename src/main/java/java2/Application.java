package java2;

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
            System.out.println("3. Display");
            System.out.println("4. Exit the program");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // Crawl thông tin từ vnexpress
                    articleController.linkExtractor(vnexpressArticleService, "https://vnexpress.net/the-thao");
                    break;
                case 2:
                    //Crawl thông tin từ nguồn của tôi
                    articleController.linkExtractor(myArticleService, "https://tienphong.vn/the-thao/");
                    break;
                case 3:
                    boolean flag = true;
                    while (flag) {
                        System.out.println("Do you want to display everything or display by URL?");
                        System.out.println("1. Display all.");
                        System.out.println("2. Display by URL.");
                        System.out.println("3. Exit");
                        int choice2 = sc.nextInt();
                        if (choice2 == 1) {
                            articleController.finAll();
                        } else if (choice2 == 2) {
                            articleController.finByUrl();
                        } else if (choice2 == 3) {
                            break;
                        } else {
                            System.out.println("Invalid choice");
                        }
                    }
                    break;
                case 4:
                    loop = false;
                    System.out.println("Bye");
                    break;
            }
        }
    }
}
