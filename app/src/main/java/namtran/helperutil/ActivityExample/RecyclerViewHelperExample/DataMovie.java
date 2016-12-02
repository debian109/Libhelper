package namtran.helperutil.ActivityExample.RecyclerViewHelperExample;

import java.util.ArrayList;
import java.util.List;

import namtran.helperutil.Model.Movie;

/**
 * Created by pratap.kesaboyina on 30-11-2015.
 */
public class DataMovie {



    private String headerTitle;
    private List<Movie> allItemsInSection;


    public DataMovie() {

    }
    public DataMovie(String headerTitle, List<Movie> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
    }



    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public List<Movie> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(List<Movie> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }


}
