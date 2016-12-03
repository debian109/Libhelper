package namtran.helperutil.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by pratap.kesaboyina on 30-11-2015.
 */
public class DataMovie implements Parcelable {

    private String headerTitle;
    private List<Movie> allItemsInSection;

    public DataMovie(String headerTitle, List<Movie> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
    }


    protected DataMovie(Parcel in) {
        headerTitle = in.readString();
        allItemsInSection = in.readArrayList(Movie.class.getClassLoader());
    }

    public static final Creator<DataMovie> CREATOR = new Creator<DataMovie>() {
        @Override
        public DataMovie createFromParcel(Parcel in) {
            return new DataMovie(in);
        }

        @Override
        public DataMovie[] newArray(int size) {
            return new DataMovie[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(headerTitle);
        dest.writeList(allItemsInSection);
    }
}
