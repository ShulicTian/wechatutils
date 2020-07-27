package wechat.qiye.message.entity;

import java.util.List;

/**
 * 图文消息
 *
 * @author tianslc
 */
public class MpnewsMessage {
    private List<NewsArticle> articles;

    public List<NewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsArticle> articles) {
        this.articles = articles;
    }
}
