package news.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import news.domain.Article;
import org.springframework.stereotype.Service;

@Service
public class ArticleConfigService {

    private List<Article> articles;

    public ArticleConfigService() {
        articles = new ArrayList<>();
    }

    public void initialize() {
        articles.add(driverlessBuses());
        articles.add(abduction());
    }

    public List<Article> getAllArticles() {
        return this.articles;
    }

    private Article driverlessBuses() {
        String title = "Singapore announces driverless buses on public roads from 2022";
        String caption = "Driverless buses are to be first launched in three towns on less crowded roads "
                        + "made to be suitable for the purpose.";
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
        return new Article(title, caption, content, category, pubDate);
    }
    
    private Article abduction() {
        String title = "Abducted Canadian-US couple recovered from Pakistan's tribal areas";
        String caption = "The couple was abducted by the Haqqani network in 2012 while they were on a tour "
                        + "to Afghanistan.";
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
        LocalDateTime pubDate = LocalDateTime.of(2017, 10, 15, 11, 43);
        String category = "events";
        return new Article(title, caption, content, category, pubDate);
    }

}
