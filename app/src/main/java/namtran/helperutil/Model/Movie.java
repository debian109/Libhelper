package namtran.helperutil.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {

    String name;
    String type;
    String image;
    String description;
    String releaseDay;

    public Movie(String name, String type, String image) {
        this.name = name;
        this.type = type;
        this.image = image;
        this.description = description;
        this.releaseDay = releaseDay;
    }

    protected Movie(Parcel in) {
        name = in.readString();
        type = in.readString();
        image = in.readString();
        description = in.readString();
        releaseDay = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(image);
        dest.writeString(description);
        dest.writeString(releaseDay);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDay() {
        return releaseDay;
    }

    public void setReleaseDay(String releaseDay) {
        this.releaseDay = releaseDay;
    }

    public static List<Movie> getMovie(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Blackhat","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/5/58/Blackhat_poster.jpg/220px-Blackhat_poster.jpg"));
        movies.add(new Movie("Fifty Shades of Grey","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/4/4b/Fifty-Gray-poster.jpg/220px-Fifty-Gray-poster.jpg"));
        movies.add(new Movie("Chappie","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/7/71/Chappie_poster.jpg/220px-Chappie_poster.jpg"));
        movies.add(new Movie("Cinderella","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/c/c2/Cinderella_2015_official_poster.jpg/220px-Cinderella_2015_official_poster.jpg"));
        movies.add(new Movie("Avengers: Age of Ultron","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/1/1b/Avengers_Age_of_Ultron.jpg/220px-Avengers_Age_of_Ultron.jpg"));
        movies.add(new Movie("Mad Max: Fury Road","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Mad_Max_Fury_Road.jpg/220px-Mad_Max_Fury_Road.jpg"));
        movies.add(new Movie("Tomorrowland","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/8/80/Tomorrowland_poster.jpg/220px-Tomorrowland_poster.jpg"));
        movies.add(new Movie("Jurassic World","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Jurassic_World_poster.jpg/220px-Jurassic_World_poster.jpg"));
        movies.add(new Movie("Inside Out","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/0/0a/Inside_Out_%282015_film%29_poster.jpg/220px-Inside_Out_%282015_film%29_poster.jpg"));
        movies.add(new Movie("Terminator: Genisys","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/b/bc/Terminator_Genisys.JPG/220px-Terminator_Genisys.JPG"));
        movies.add(new Movie("13 Hours: The Secret Soldiers of Benghazi","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/a/a2/13_Hours_poster.png/220px-13_Hours_poster.png"));
        movies.add(new Movie("The 5th Wave","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/5/55/5th-Wave_poster.jpg"));
        movies.add(new Movie("The Finest Hours","Action"
                ,"http://img.v3.news.zdn.vn/w660/Uploaded/xbhunku/2015_11_08/f3.jpg"));
        movies.add(new Movie("Kung Fu Panda 3","Cartoon"
                ,"http://img.v3.news.zdn.vn/w660/Uploaded/xbhunku/2015_11_08/f4.jpg"));
        movies.add(new Movie("Fifty Shades of Black","Horries"
                ,"http://img.v3.news.zdn.vn/w660/Uploaded/xbhunku/2015_11_08/f5.jpg"));
        movies.add(new Movie("Hail, Caesar!","Action"
                ,"http://img.v3.news.zdn.vn/w660/Uploaded/xbhunku/2015_11_08/f6.jpg"));
        movies.add(new Movie("Pride and Prejudice and Zombies","Horries"
                ,"http://img.v3.news.zdn.vn/w660/Uploaded/xbhunku/2015_11_08/f7.jpg"));
        movies.add(new Movie("Deadpool","Horries"
                ,"http://img.v3.news.zdn.vn/w660/Uploaded/xbhunku/2015_11_08/f8.jpg"));
        movies.add(new Movie("Zoolander 2","Action"
                ,"http://img.v3.news.zdn.vn/w660/Uploaded/xbhunku/2015_11_08/f9.jpg"));
        movies.add(new Movie("Triple 9","Cartoon"
                ,"http://img.v3.news.zdn.vn/w660/Uploaded/xbhunku/2015_11_08/f10.jpg"));
        movies.add(new Movie("The Witch","Horries"
                ,"http://img.v3.news.zdn.vn/w660/Uploaded/xbhunku/2015_11_08/f11.jpg"));
        movies.add(new Movie("London Has Fallen","Action"
                ,"http://img.v3.news.zdn.vn/w660/Uploaded/xbhunku/2015_11_08/f12.jpg"));
        movies.add(new Movie("Zootopia","Cartoon"
                ,"http://img.v3.news.zdn.vn/w660/Uploaded/xbhunku/2015_11_08/f13.jpg"));
        movies.add(new Movie("Allegiant","Horries"
                ,"http://img.v3.news.zdn.vn/w660/Uploaded/xbhunku/2015_11_08/f14.jpg"));
        movies.add(new Movie("I Saw the Light","Cartoon"
                ,"http://img.v3.news.zdn.vn/w660/Uploaded/xbhunku/2015_11_08/f15.jpg"));
        movies.add(new Movie("Hush","Horries"
                ,"http://img.f17.ione.vnecdn.net/2016/08/30/1-6968-1472532758.jpg"));
        movies.add(new Movie("The Conjuring 2","Horries"
                ,"http://img.f17.ione.vnecdn.net/2016/08/30/2-8318-1472532758.jpg"));
        movies.add(new Movie("The Neon Demon","Horries"
                ,"http://img.f17.ione.vnecdn.net/2016/08/30/3-3335-1472532758.jpg"));
        movies.add(new Movie("The Invitation","Horries"
                ,"http://img.f17.ione.vnecdn.net/2016/08/30/THE-INVITATION-Poster-Final-9472-1472532758.jpg"));
        movies.add(new Movie("10 Cloverfield Lane","Horries"
                ,"http://img.f17.ione.vnecdn.net/2016/08/30/5-3676-1472532758.jpg"));
        movies.add(new Movie("Don't Breathe","Horries"
                ,"http://img.f17.ione.vnecdn.net/2016/08/30/6-8739-1472532758.jpg"));
        movies.add(new Movie("The Shallows","Horries"
                ,"http://img.f17.ione.vnecdn.net/2016/08/30/7-7854-1472532758.jpg"));
        movies.add(new Movie("The Witch","Horries"
                ,"http://img.f17.ione.vnecdn.net/2016/08/30/8-2118-1472532758.jpg"));

        return movies;
    }
}
