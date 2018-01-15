package news.service;

import java.util.*;
import news.domain.*;
import java.nio.file.*;
import java.io.IOException;
import news.domain.FormObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class ArticleConfigService {

    private List<Article> articles;

    public ArticleConfigService() {
        articles = new ArrayList<>();
        this.initialize();
    }

    public List<Article> getAllArticles() {
        return this.articles;
    }
    
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    private void initialize() {
        articles.add(driverlessBuses());
        articles.add(abduction());
    }
    
    // TODO: Break down in smaller chunks.
    public FileObject findImage(String location) {
        FileObject fo = new FileObject();
        Path path = Paths.get(location);
        try {
            byte[] content = Files.readAllBytes(path);
            fo.setContent(content);
            fo.setContentType("image/jpeg");
            fo.setContentLength((long) content.length);
            return fo;
        } catch (IOException ex) {
            System.err.println("Error reading file: " + ex.getMessage());
            return null;
        }
    }

    public LocalDateTime formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyy HH:mm");
        String text = date.format(formatter);
        return LocalDateTime.parse(text, formatter);
    }

    public Article createArticle(Article article, FileObject fo) {
        article.setFileObject(fo);
        article.setPubDate(formatDate(LocalDateTime.now())); // Is this the problem?
        return article;
    }
        
    // !!! Under development, used in form validation
    public Article transferToArticle(Article article, FormObject formObject) {
        article.setTitle(formObject.getTitle());
        article.setCaption(formObject.getCaption());
        article.setContent(formObject.getContent());
        article.setCategory(formObject.getCategory());
        return article;
    }

    private Article driverlessBuses() {
        String title = "Singapore announces driverless buses on public roads from 2022";
        String caption = "Driverless buses are to be first launched in three towns on less crowded roads "
                        + "made to be suitable for the purpose.";
        FileObject fo = findImage("src/main/resources/images/driverless_bus.jpg");
        String content = "On Wednesday, Singapore's government announced its intention to have driverless \n" +
                        "buses operating on public roads from 2022. Driverless buses are to be first \n" +
                        "launched in three towns on less crowded roads made to be suitable for the \n" +
                        "purpose. The autonomous buses are to run during off-peak times, complementing \n" +
                        "human-driven bus services.\n" +
                        "\n" +
                        "According to the joint announcement from the Land Transport Authority and \n" +
                        "Singapore's Ministry of Transport, commuters will be able to use their mobile \n" +
                        "phones to hail a driverless shuttle. Transport Minister Khaw Boon Wan remarked, \n" +
                        "\"The autonomous vehicles will greatly enhance the accessibility and connectivity \n" +
                        "of our public transport system, particularly for the elderly, families with \n" +
                        "young children and the less mobile.\"\n" +
                        "\n" +
                        "Along with the announcement, a two hectare (roughly five acre) test centre has \n" +
                        "been unveiled outside Nanyang Technological University, where self-driving \n" +
                        "vehicles are to be driven in simulated Singapore traffic. The test centre is \n" +
                        "also to be used for developers of self-driving vehicles to observe how they \n" +
                        "react to pedestrians, extreme weather, aggressive drivers, and various other \n" +
                        "road conditions. At the launch, Mr Khaw also said, \"Our land transport \n" +
                        "constraints may help us become a global player in urban mobility solutions. \n" +
                        "What works here is likely to also work in other cities\". He noted driverless \n" +
                        "technology testing for Singapore was underway by at least 10 companies.";
        String category = "technology";
        LocalDateTime pubDate = LocalDateTime.of(2017, 11, 24, 10, 30);
        return new Article(title, caption, fo, content, category, pubDate);
    }
    
    private Article abduction() {
        String title = "Abducted Canadian-US couple recovered from Pakistan's tribal areas";
        String caption = "The couple was abducted by the Haqqani network in 2012 while they were on a tour "
                        + "to Afghanistan.";
        FileObject fo = null;
        String content = "Pakistan Army announced on Thursday they had recovered an abducted Canadian-US \n" +
                        "couple and their three children from the tribal areas of Pakistan in an \n" +
                        "operation carried out from US intelligence. The couple were abducted in 2012 by \n" +
                        "the Haqqani network.\n" +
                        "\n" +
                        "According to media reports, wife Caitlan Coleman from the US, husband Joshua \n" +
                        "Boyle from Canada, and their three children, two boys and a girl, were recovered \n" +
                        "from custody through an intelligence-based operation conducted by Pakistan Army.\n" +
                        "\n" +
                        "The couple was abducted by the Haqqani network in 2012 while they were on a tour \n" +
                        "to Afghanistan. Caitlan Coleman was seven months pregnant at the time. She gave \n" +
                        "birth during her abduction to two boys and a girl.\n" +
                        "\n" +
                        "In December, a video was released of the couple in which both were sitting with \n" +
                        "their —then two— children. They asked authorities to negotiate to set them free.\n" +
                        "\n" +
                        "According to a US official and Pakistan Army's press release on Friday, the \n" +
                        "couple with their children were repatriated.";
        String category = "events";
        LocalDateTime pubDate = LocalDateTime.of(2017, 10, 15, 11, 43);
        return new Article(title, caption, fo, content, category, pubDate);
    }

}
