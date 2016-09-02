package java8test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by lixuejiao on 16/8/29.
 */
public class LoopTest {

    private static class Article {

        private final String title;
        private final String author;
        private final List<String> tags;

        public Article(String title, String author, List<String> tags) {
            this.title = title;
            this.author = author;
            this.tags = tags;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public List<String> getTags() {
            return tags;
        }
    }

    public Article getFirstJavaArticle(List<Article> articles) {
        for (Article article : articles) {
            if (article.getTags().contains("B")) {
                return article;
            }
        }
        return null;
    }

    public Optional<Article> getFirstJavaArticle1(List<Article> articles) {
        return articles.stream()
                .filter(article -> article.getTags().contains("B"))
                .findFirst();
    }

    public static void main(String [] args) {
        LoopTest t = new LoopTest();
        List<Article> articles = new ArrayList<>();
        List<String> tags = new ArrayList<String>() {{
            add("A");
            add("B");
            add("C");
        }};
        articles.add(new Article("a","b",tags));
        articles.add(new Article("c","d",tags));
        Optional<Article> a = t.getFirstJavaArticle1(articles);
//        Article a = t.getFirstJavaArticle(articles);
        System.out.print(a.get().getAuthor());
    }
}

