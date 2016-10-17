package namtran.helperutil.Model;

/**
 * Created by Nam Tran on 7/20/2015.
 */
public class News {
    public static final String ITEM = "item";
    public static final String TITLE = "title";
    public static final String LINK = "link";
    public static final String DECRIPTION ="description";
    public static final String PUBDATE = "pubDate";
    String title;
    String link;
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String decription;
    String pubdate;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    @Override
    public String toString() {
        return title+" / "+link +" / "+image+" / " +decription + "/"+ pubdate;
    }
}
