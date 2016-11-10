package namtran.helperutil.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {

    String name;
    String type;
    String image;
    String description;
    String releaseDay;

    public Movie(String name, String type, String image,String description, String releaseDay) {
        this.name = name;
        this.type = type;
        this.image = image;
        this.description = description;
        this.releaseDay = releaseDay;
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
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/5/58/Blackhat_poster.jpg/220px-Blackhat_poster.jpg"
                ,"Blackhat is a 2015 American action thriller mystery film co-written, co-produced and directed by Michael Mann. The film stars Chris Hemsworth, Tang Wei, Viola Davis, Holt McCallany, and Wang Leehom. The film premiered at the TCL Chinese Theatre in Los Angeles on January 8, 2015, and was released in theaters on January 16.[4] Blackhat was a box office bomb, earning only $19.7 million at the box office against a budget of $70 million. While the movie received generally mixed-to-negative reviews, with criticisms focused on casting and a point pace, some critics found brilliance in the film, enough to place the film in some critics' year-end lists."
                ,"January 8, 2015"));
        movies.add(new Movie("Fifty Shades of Grey","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/4/4b/Fifty-Gray-poster.jpg/220px-Fifty-Gray-poster.jpg"
                ,"Fifty Shades of Grey is a 2015 American erotic romantic drama film directed by Sam Taylor-Johnson with a screenplay by Kelly Marcel. The film is based on the 2011 novel of the same name by British author E. L. James. It stars Dakota Johnson as Anastasia Steele, a college graduate who begins a sadomasochistic relationship with young business magnate Christian Grey, played by Jamie Dornan."
                ,"February 9, 2015"));
        movies.add(new Movie("Chappie","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/7/71/Chappie_poster.jpg/220px-Chappie_poster.jpg"
                ,"Chappie (stylized as CHAPPiE) is a 2015 American science fiction film directed by Neill Blomkamp and written by Blomkamp and Terri Tatchell. It stars Sharlto Copley, Dev Patel, Jose Pablo Cantillo, Sigourney Weaver, Hugh Jackman, and Watkin Tudor Jones (Ninja) and Yolandi Visser of the South African zef rap-rave group Die Antwoord. The film, set and shot in Johannesburg, is about an artificially intelligent law enforcement robot captured and taught by gangsters, who nickname it Chappie."
                ,"March 4, 2015"));
        movies.add(new Movie("Cinderella","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/c/c2/Cinderella_2015_official_poster.jpg/220px-Cinderella_2015_official_poster.jpg"
                ,"Cinderella is a 2015 American romantic fantasy film, directed by Kenneth Branagh, with a screenplay written by Chris Weitz. The film is based on the eponymous folk tale and inspired by Walt Disney's 1950 animated film.[4] The film stars Lily James as the titular character, with Cate Blanchett, Richard Madden, Stellan Skarsgård, Holliday Grainger, Derek Jacobi, and Helena Bonham Carter. It is produced by David Barron, Simon Kinberg and Allison Shearmur for Walt Disney Pictures."
                ,"February 13, 2015"));
        movies.add(new Movie("Avengers: Age of Ultron","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/1/1b/Avengers_Age_of_Ultron.jpg/220px-Avengers_Age_of_Ultron.jpg"
                ,"Avengers: Age of Ultron is a 2015 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures. It is the sequel to 2012's The Avengers and the eleventh film in the Marvel Cinematic Universe (MCU). The film was written and directed by Joss Whedon and features an ensemble cast that includes Robert Downey Jr., Chris Hemsworth, Mark Ruffalo, Chris Evans, Scarlett Johansson, Jeremy Renner, Don Cheadle, Aaron Taylor-Johnson, Elizabeth Olsen, Paul Bettany, Cobie Smulders, Anthony Mackie, Hayley Atwell, Idris Elba, Stellan Skarsgård, James Spader, and Samuel L. Jackson. In Avengers: Age of Ultron, the Avengers fight Ultron, an artificial intelligence obsessed with causing human extinction."
                ,"April 13, 2015"));
        movies.add(new Movie("Mad Max: Fury Road","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Mad_Max_Fury_Road.jpg/220px-Mad_Max_Fury_Road.jpg"
                ,"Mad Max: Fury Road is a 2015 action film directed and produced by George Miller, and written by Miller, Brendan McCarthy and Nico Lathouris. The fourth instalment in the Mad Max franchise, it is an Australian and American[6] venture produced by Kennedy Miller Mitchell, RatPac-Dune Entertainment and Village Roadshow Pictures. The film is set in a future desert wasteland where gasoline and water are scarce commodities. It follows Max Rockatansky (Tom Hardy), who joins forces with Imperator Furiosa (Charlize Theron) to flee from cult leader Immortan Joe (Hugh Keays-Byrne) and his army in an armoured tanker truck, which leads to a lengthy road battle. The film also features Nicholas Hoult, Rosie Huntington-Whiteley, Riley Keough, Zoë Kravitz, Abbey Lee, and Courtney Eaton."
                ,"May 7, 2015"));
        movies.add(new Movie("Tomorrowland","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/8/80/Tomorrowland_poster.jpg/220px-Tomorrowland_poster.jpg"
                ,"Tomorrowland (subtitled A World Beyond in some regions) is a 2015 American science-fiction mystery adventure film[5] directed and co-written by Brad Bird. Bird co-wrote the film's screenplay with Damon Lindelof, from an original story treatment by Bird, Lindelof and Jeff Jensen.[6][7] The film stars George Clooney, Hugh Laurie, Britt Robertson, Raffey Cassidy, Tim McGraw, Kathryn Hahn and Keegan-Michael Key.[5] In the film, a disillusioned genius inventor and a teenage science enthusiast embark to an ambiguous alternate dimension known as \"Tomorrowland\" where their actions directly affect the world and themselves."
                ,"May 8, 2015"));
        movies.add(new Movie("Jurassic World","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Jurassic_World_poster.jpg/220px-Jurassic_World_poster.jpg"
                ,"Jurassic World is a 2015 American science-fiction adventure film and is the fourth installment of the Jurassic Park series. The film was directed and co-written by Colin Trevorrow, produced by Frank Marshall and Patrick Crowley, and stars Chris Pratt and Bryce Dallas Howard. The production companies were Steven Spielberg's Amblin Entertainment, also responsible for the rest of the Jurassic Park franchise, and Thomas Tull's Legendary Pictures. Set 22 years after the events of Jurassic Park, Jurassic World takes place on the same fictional island of Isla Nublar, off the Pacific coast of Central America, where a theme park populated with cloned dinosaurs has operated for ten years. The park plunges into chaos when a genetically created dinosaur breaks loose and goes on a rampage across the island."
                ,"May 29, 2015"));
        movies.add(new Movie("Inside Out","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/0/0a/Inside_Out_%282015_film%29_poster.jpg/220px-Inside_Out_%282015_film%29_poster.jpg"
                ,"Inside Out is a 2015 American 3D computer-animated comedy-drama adventure film produced by Pixar Animation Studios and released by Walt Disney Pictures. The film was directed by Pete Docter and co-directed by Ronnie del Carmen, with a screenplay by Docter, Meg LeFauve and Josh Cooley from a story by Docter and del Carmen. The film is set in the mind of a young girl named Riley Andersen (Kaitlyn Dias), where five personified emotions—Joy (Amy Poehler), Sadness (Phyllis Smith), Anger (Lewis Black), Fear (Bill Hader), and Disgust (Mindy Kaling)—try to lead her through life as her parents (Diane Lane and Kyle MacLachlan) move from Minnesota to San Francisco and she has to adjust to her new surroundings."
                ,"May 18, 2015"));
        movies.add(new Movie("Terminator: Genisys","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/b/bc/Terminator_Genisys.JPG/220px-Terminator_Genisys.JPG"
                ,"Terminator Genisys is a 2015 American science fiction film directed by Alan Taylor and written by Laeta Kalogridis and Patrick Lussier. The fifth installment in the Terminator franchise, following 2009's Terminator Salvation, the film stars Arnold Schwarzenegger (reprising his role as the eponymous character), Jason Clarke, Emilia Clarke and Jai Courtney. The plot follows soldier Kyle Reese in the war against Skynet; Kyle is sent from the year 2029 to 1984 by John Connor, leader of the Human Resistance, to protect Connor's mother Sarah. When Kyle arrives in the past, he discovers that the timeline has been altered and Sarah has been raised by a reprogrammed Terminator."
                ,"June 22, 2015"));
        movies.add(new Movie("Blackhat","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/5/58/Blackhat_poster.jpg/220px-Blackhat_poster.jpg"
                ,"Blackhat is a 2015 American action thriller mystery film co-written, co-produced and directed by Michael Mann. The film stars Chris Hemsworth, Tang Wei, Viola Davis, Holt McCallany, and Wang Leehom. The film premiered at the TCL Chinese Theatre in Los Angeles on January 8, 2015, and was released in theaters on January 16.[4] Blackhat was a box office bomb, earning only $19.7 million at the box office against a budget of $70 million. While the movie received generally mixed-to-negative reviews, with criticisms focused on casting and a point pace, some critics found brilliance in the film, enough to place the film in some critics' year-end lists."
                ,"January 8, 2015"));
        movies.add(new Movie("Fifty Shades of Grey","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/4/4b/Fifty-Gray-poster.jpg/220px-Fifty-Gray-poster.jpg"
                ,"Fifty Shades of Grey is a 2015 American erotic romantic drama film directed by Sam Taylor-Johnson with a screenplay by Kelly Marcel. The film is based on the 2011 novel of the same name by British author E. L. James. It stars Dakota Johnson as Anastasia Steele, a college graduate who begins a sadomasochistic relationship with young business magnate Christian Grey, played by Jamie Dornan."
                ,"February 9, 2015"));
        movies.add(new Movie("Chappie","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/7/71/Chappie_poster.jpg/220px-Chappie_poster.jpg"
                ,"Chappie (stylized as CHAPPiE) is a 2015 American science fiction film directed by Neill Blomkamp and written by Blomkamp and Terri Tatchell. It stars Sharlto Copley, Dev Patel, Jose Pablo Cantillo, Sigourney Weaver, Hugh Jackman, and Watkin Tudor Jones (Ninja) and Yolandi Visser of the South African zef rap-rave group Die Antwoord. The film, set and shot in Johannesburg, is about an artificially intelligent law enforcement robot captured and taught by gangsters, who nickname it Chappie."
                ,"March 4, 2015"));
        movies.add(new Movie("Cinderella","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/c/c2/Cinderella_2015_official_poster.jpg/220px-Cinderella_2015_official_poster.jpg"
                ,"Cinderella is a 2015 American romantic fantasy film, directed by Kenneth Branagh, with a screenplay written by Chris Weitz. The film is based on the eponymous folk tale and inspired by Walt Disney's 1950 animated film.[4] The film stars Lily James as the titular character, with Cate Blanchett, Richard Madden, Stellan Skarsgård, Holliday Grainger, Derek Jacobi, and Helena Bonham Carter. It is produced by David Barron, Simon Kinberg and Allison Shearmur for Walt Disney Pictures."
                ,"February 13, 2015"));
        movies.add(new Movie("Avengers: Age of Ultron","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/1/1b/Avengers_Age_of_Ultron.jpg/220px-Avengers_Age_of_Ultron.jpg"
                ,"Avengers: Age of Ultron is a 2015 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures. It is the sequel to 2012's The Avengers and the eleventh film in the Marvel Cinematic Universe (MCU). The film was written and directed by Joss Whedon and features an ensemble cast that includes Robert Downey Jr., Chris Hemsworth, Mark Ruffalo, Chris Evans, Scarlett Johansson, Jeremy Renner, Don Cheadle, Aaron Taylor-Johnson, Elizabeth Olsen, Paul Bettany, Cobie Smulders, Anthony Mackie, Hayley Atwell, Idris Elba, Stellan Skarsgård, James Spader, and Samuel L. Jackson. In Avengers: Age of Ultron, the Avengers fight Ultron, an artificial intelligence obsessed with causing human extinction."
                ,"April 13, 2015"));
        movies.add(new Movie("Mad Max: Fury Road","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Mad_Max_Fury_Road.jpg/220px-Mad_Max_Fury_Road.jpg"
                ,"Mad Max: Fury Road is a 2015 action film directed and produced by George Miller, and written by Miller, Brendan McCarthy and Nico Lathouris. The fourth instalment in the Mad Max franchise, it is an Australian and American[6] venture produced by Kennedy Miller Mitchell, RatPac-Dune Entertainment and Village Roadshow Pictures. The film is set in a future desert wasteland where gasoline and water are scarce commodities. It follows Max Rockatansky (Tom Hardy), who joins forces with Imperator Furiosa (Charlize Theron) to flee from cult leader Immortan Joe (Hugh Keays-Byrne) and his army in an armoured tanker truck, which leads to a lengthy road battle. The film also features Nicholas Hoult, Rosie Huntington-Whiteley, Riley Keough, Zoë Kravitz, Abbey Lee, and Courtney Eaton."
                ,"May 7, 2015"));
        movies.add(new Movie("Tomorrowland","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/8/80/Tomorrowland_poster.jpg/220px-Tomorrowland_poster.jpg"
                ,"Tomorrowland (subtitled A World Beyond in some regions) is a 2015 American science-fiction mystery adventure film[5] directed and co-written by Brad Bird. Bird co-wrote the film's screenplay with Damon Lindelof, from an original story treatment by Bird, Lindelof and Jeff Jensen.[6][7] The film stars George Clooney, Hugh Laurie, Britt Robertson, Raffey Cassidy, Tim McGraw, Kathryn Hahn and Keegan-Michael Key.[5] In the film, a disillusioned genius inventor and a teenage science enthusiast embark to an ambiguous alternate dimension known as \"Tomorrowland\" where their actions directly affect the world and themselves."
                ,"May 8, 2015"));
        movies.add(new Movie("Jurassic World","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Jurassic_World_poster.jpg/220px-Jurassic_World_poster.jpg"
                ,"Jurassic World is a 2015 American science-fiction adventure film and is the fourth installment of the Jurassic Park series. The film was directed and co-written by Colin Trevorrow, produced by Frank Marshall and Patrick Crowley, and stars Chris Pratt and Bryce Dallas Howard. The production companies were Steven Spielberg's Amblin Entertainment, also responsible for the rest of the Jurassic Park franchise, and Thomas Tull's Legendary Pictures. Set 22 years after the events of Jurassic Park, Jurassic World takes place on the same fictional island of Isla Nublar, off the Pacific coast of Central America, where a theme park populated with cloned dinosaurs has operated for ten years. The park plunges into chaos when a genetically created dinosaur breaks loose and goes on a rampage across the island."
                ,"May 29, 2015"));
        movies.add(new Movie("Inside Out","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/0/0a/Inside_Out_%282015_film%29_poster.jpg/220px-Inside_Out_%282015_film%29_poster.jpg"
                ,"Inside Out is a 2015 American 3D computer-animated comedy-drama adventure film produced by Pixar Animation Studios and released by Walt Disney Pictures. The film was directed by Pete Docter and co-directed by Ronnie del Carmen, with a screenplay by Docter, Meg LeFauve and Josh Cooley from a story by Docter and del Carmen. The film is set in the mind of a young girl named Riley Andersen (Kaitlyn Dias), where five personified emotions—Joy (Amy Poehler), Sadness (Phyllis Smith), Anger (Lewis Black), Fear (Bill Hader), and Disgust (Mindy Kaling)—try to lead her through life as her parents (Diane Lane and Kyle MacLachlan) move from Minnesota to San Francisco and she has to adjust to her new surroundings."
                ,"May 18, 2015"));
        movies.add(new Movie("Terminator: Genisys","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/b/bc/Terminator_Genisys.JPG/220px-Terminator_Genisys.JPG"
                ,"Terminator Genisys is a 2015 American science fiction film directed by Alan Taylor and written by Laeta Kalogridis and Patrick Lussier. The fifth installment in the Terminator franchise, following 2009's Terminator Salvation, the film stars Arnold Schwarzenegger (reprising his role as the eponymous character), Jason Clarke, Emilia Clarke and Jai Courtney. The plot follows soldier Kyle Reese in the war against Skynet; Kyle is sent from the year 2029 to 1984 by John Connor, leader of the Human Resistance, to protect Connor's mother Sarah. When Kyle arrives in the past, he discovers that the timeline has been altered and Sarah has been raised by a reprogrammed Terminator."
                ,"June 22, 2015"));
        movies.add(new Movie("Blackhat","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/5/58/Blackhat_poster.jpg/220px-Blackhat_poster.jpg"
                ,"Blackhat is a 2015 American action thriller mystery film co-written, co-produced and directed by Michael Mann. The film stars Chris Hemsworth, Tang Wei, Viola Davis, Holt McCallany, and Wang Leehom. The film premiered at the TCL Chinese Theatre in Los Angeles on January 8, 2015, and was released in theaters on January 16.[4] Blackhat was a box office bomb, earning only $19.7 million at the box office against a budget of $70 million. While the movie received generally mixed-to-negative reviews, with criticisms focused on casting and a point pace, some critics found brilliance in the film, enough to place the film in some critics' year-end lists."
                ,"January 8, 2015"));
        movies.add(new Movie("Fifty Shades of Grey","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/4/4b/Fifty-Gray-poster.jpg/220px-Fifty-Gray-poster.jpg"
                ,"Fifty Shades of Grey is a 2015 American erotic romantic drama film directed by Sam Taylor-Johnson with a screenplay by Kelly Marcel. The film is based on the 2011 novel of the same name by British author E. L. James. It stars Dakota Johnson as Anastasia Steele, a college graduate who begins a sadomasochistic relationship with young business magnate Christian Grey, played by Jamie Dornan."
                ,"February 9, 2015"));
        movies.add(new Movie("Chappie","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/7/71/Chappie_poster.jpg/220px-Chappie_poster.jpg"
                ,"Chappie (stylized as CHAPPiE) is a 2015 American science fiction film directed by Neill Blomkamp and written by Blomkamp and Terri Tatchell. It stars Sharlto Copley, Dev Patel, Jose Pablo Cantillo, Sigourney Weaver, Hugh Jackman, and Watkin Tudor Jones (Ninja) and Yolandi Visser of the South African zef rap-rave group Die Antwoord. The film, set and shot in Johannesburg, is about an artificially intelligent law enforcement robot captured and taught by gangsters, who nickname it Chappie."
                ,"March 4, 2015"));
        movies.add(new Movie("Cinderella","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/c/c2/Cinderella_2015_official_poster.jpg/220px-Cinderella_2015_official_poster.jpg"
                ,"Cinderella is a 2015 American romantic fantasy film, directed by Kenneth Branagh, with a screenplay written by Chris Weitz. The film is based on the eponymous folk tale and inspired by Walt Disney's 1950 animated film.[4] The film stars Lily James as the titular character, with Cate Blanchett, Richard Madden, Stellan Skarsgård, Holliday Grainger, Derek Jacobi, and Helena Bonham Carter. It is produced by David Barron, Simon Kinberg and Allison Shearmur for Walt Disney Pictures."
                ,"February 13, 2015"));
        movies.add(new Movie("Avengers: Age of Ultron","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/1/1b/Avengers_Age_of_Ultron.jpg/220px-Avengers_Age_of_Ultron.jpg"
                ,"Avengers: Age of Ultron is a 2015 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures. It is the sequel to 2012's The Avengers and the eleventh film in the Marvel Cinematic Universe (MCU). The film was written and directed by Joss Whedon and features an ensemble cast that includes Robert Downey Jr., Chris Hemsworth, Mark Ruffalo, Chris Evans, Scarlett Johansson, Jeremy Renner, Don Cheadle, Aaron Taylor-Johnson, Elizabeth Olsen, Paul Bettany, Cobie Smulders, Anthony Mackie, Hayley Atwell, Idris Elba, Stellan Skarsgård, James Spader, and Samuel L. Jackson. In Avengers: Age of Ultron, the Avengers fight Ultron, an artificial intelligence obsessed with causing human extinction."
                ,"April 13, 2015"));
        movies.add(new Movie("Mad Max: Fury Road","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Mad_Max_Fury_Road.jpg/220px-Mad_Max_Fury_Road.jpg"
                ,"Mad Max: Fury Road is a 2015 action film directed and produced by George Miller, and written by Miller, Brendan McCarthy and Nico Lathouris. The fourth instalment in the Mad Max franchise, it is an Australian and American[6] venture produced by Kennedy Miller Mitchell, RatPac-Dune Entertainment and Village Roadshow Pictures. The film is set in a future desert wasteland where gasoline and water are scarce commodities. It follows Max Rockatansky (Tom Hardy), who joins forces with Imperator Furiosa (Charlize Theron) to flee from cult leader Immortan Joe (Hugh Keays-Byrne) and his army in an armoured tanker truck, which leads to a lengthy road battle. The film also features Nicholas Hoult, Rosie Huntington-Whiteley, Riley Keough, Zoë Kravitz, Abbey Lee, and Courtney Eaton."
                ,"May 7, 2015"));
        movies.add(new Movie("Tomorrowland","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/8/80/Tomorrowland_poster.jpg/220px-Tomorrowland_poster.jpg"
                ,"Tomorrowland (subtitled A World Beyond in some regions) is a 2015 American science-fiction mystery adventure film[5] directed and co-written by Brad Bird. Bird co-wrote the film's screenplay with Damon Lindelof, from an original story treatment by Bird, Lindelof and Jeff Jensen.[6][7] The film stars George Clooney, Hugh Laurie, Britt Robertson, Raffey Cassidy, Tim McGraw, Kathryn Hahn and Keegan-Michael Key.[5] In the film, a disillusioned genius inventor and a teenage science enthusiast embark to an ambiguous alternate dimension known as \"Tomorrowland\" where their actions directly affect the world and themselves."
                ,"May 8, 2015"));
        movies.add(new Movie("Jurassic World","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Jurassic_World_poster.jpg/220px-Jurassic_World_poster.jpg"
                ,"Jurassic World is a 2015 American science-fiction adventure film and is the fourth installment of the Jurassic Park series. The film was directed and co-written by Colin Trevorrow, produced by Frank Marshall and Patrick Crowley, and stars Chris Pratt and Bryce Dallas Howard. The production companies were Steven Spielberg's Amblin Entertainment, also responsible for the rest of the Jurassic Park franchise, and Thomas Tull's Legendary Pictures. Set 22 years after the events of Jurassic Park, Jurassic World takes place on the same fictional island of Isla Nublar, off the Pacific coast of Central America, where a theme park populated with cloned dinosaurs has operated for ten years. The park plunges into chaos when a genetically created dinosaur breaks loose and goes on a rampage across the island."
                ,"May 29, 2015"));
        movies.add(new Movie("Inside Out","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/0/0a/Inside_Out_%282015_film%29_poster.jpg/220px-Inside_Out_%282015_film%29_poster.jpg"
                ,"Inside Out is a 2015 American 3D computer-animated comedy-drama adventure film produced by Pixar Animation Studios and released by Walt Disney Pictures. The film was directed by Pete Docter and co-directed by Ronnie del Carmen, with a screenplay by Docter, Meg LeFauve and Josh Cooley from a story by Docter and del Carmen. The film is set in the mind of a young girl named Riley Andersen (Kaitlyn Dias), where five personified emotions—Joy (Amy Poehler), Sadness (Phyllis Smith), Anger (Lewis Black), Fear (Bill Hader), and Disgust (Mindy Kaling)—try to lead her through life as her parents (Diane Lane and Kyle MacLachlan) move from Minnesota to San Francisco and she has to adjust to her new surroundings."
                ,"May 18, 2015"));
        movies.add(new Movie("Terminator: Genisys","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/b/bc/Terminator_Genisys.JPG/220px-Terminator_Genisys.JPG"
                ,"Terminator Genisys is a 2015 American science fiction film directed by Alan Taylor and written by Laeta Kalogridis and Patrick Lussier. The fifth installment in the Terminator franchise, following 2009's Terminator Salvation, the film stars Arnold Schwarzenegger (reprising his role as the eponymous character), Jason Clarke, Emilia Clarke and Jai Courtney. The plot follows soldier Kyle Reese in the war against Skynet; Kyle is sent from the year 2029 to 1984 by John Connor, leader of the Human Resistance, to protect Connor's mother Sarah. When Kyle arrives in the past, he discovers that the timeline has been altered and Sarah has been raised by a reprogrammed Terminator."
                ,"June 22, 2015"));
        movies.add(new Movie("Blackhat","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/5/58/Blackhat_poster.jpg/220px-Blackhat_poster.jpg"
                ,"Blackhat is a 2015 American action thriller mystery film co-written, co-produced and directed by Michael Mann. The film stars Chris Hemsworth, Tang Wei, Viola Davis, Holt McCallany, and Wang Leehom. The film premiered at the TCL Chinese Theatre in Los Angeles on January 8, 2015, and was released in theaters on January 16.[4] Blackhat was a box office bomb, earning only $19.7 million at the box office against a budget of $70 million. While the movie received generally mixed-to-negative reviews, with criticisms focused on casting and a point pace, some critics found brilliance in the film, enough to place the film in some critics' year-end lists."
                ,"January 8, 2015"));
        movies.add(new Movie("Fifty Shades of Grey","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/4/4b/Fifty-Gray-poster.jpg/220px-Fifty-Gray-poster.jpg"
                ,"Fifty Shades of Grey is a 2015 American erotic romantic drama film directed by Sam Taylor-Johnson with a screenplay by Kelly Marcel. The film is based on the 2011 novel of the same name by British author E. L. James. It stars Dakota Johnson as Anastasia Steele, a college graduate who begins a sadomasochistic relationship with young business magnate Christian Grey, played by Jamie Dornan."
                ,"February 9, 2015"));
        movies.add(new Movie("Chappie","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/7/71/Chappie_poster.jpg/220px-Chappie_poster.jpg"
                ,"Chappie (stylized as CHAPPiE) is a 2015 American science fiction film directed by Neill Blomkamp and written by Blomkamp and Terri Tatchell. It stars Sharlto Copley, Dev Patel, Jose Pablo Cantillo, Sigourney Weaver, Hugh Jackman, and Watkin Tudor Jones (Ninja) and Yolandi Visser of the South African zef rap-rave group Die Antwoord. The film, set and shot in Johannesburg, is about an artificially intelligent law enforcement robot captured and taught by gangsters, who nickname it Chappie."
                ,"March 4, 2015"));
        movies.add(new Movie("Cinderella","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/c/c2/Cinderella_2015_official_poster.jpg/220px-Cinderella_2015_official_poster.jpg"
                ,"Cinderella is a 2015 American romantic fantasy film, directed by Kenneth Branagh, with a screenplay written by Chris Weitz. The film is based on the eponymous folk tale and inspired by Walt Disney's 1950 animated film.[4] The film stars Lily James as the titular character, with Cate Blanchett, Richard Madden, Stellan Skarsgård, Holliday Grainger, Derek Jacobi, and Helena Bonham Carter. It is produced by David Barron, Simon Kinberg and Allison Shearmur for Walt Disney Pictures."
                ,"February 13, 2015"));
        movies.add(new Movie("Avengers: Age of Ultron","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/1/1b/Avengers_Age_of_Ultron.jpg/220px-Avengers_Age_of_Ultron.jpg"
                ,"Avengers: Age of Ultron is a 2015 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures. It is the sequel to 2012's The Avengers and the eleventh film in the Marvel Cinematic Universe (MCU). The film was written and directed by Joss Whedon and features an ensemble cast that includes Robert Downey Jr., Chris Hemsworth, Mark Ruffalo, Chris Evans, Scarlett Johansson, Jeremy Renner, Don Cheadle, Aaron Taylor-Johnson, Elizabeth Olsen, Paul Bettany, Cobie Smulders, Anthony Mackie, Hayley Atwell, Idris Elba, Stellan Skarsgård, James Spader, and Samuel L. Jackson. In Avengers: Age of Ultron, the Avengers fight Ultron, an artificial intelligence obsessed with causing human extinction."
                ,"April 13, 2015"));
        movies.add(new Movie("Mad Max: Fury Road","Action"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Mad_Max_Fury_Road.jpg/220px-Mad_Max_Fury_Road.jpg"
                ,"Mad Max: Fury Road is a 2015 action film directed and produced by George Miller, and written by Miller, Brendan McCarthy and Nico Lathouris. The fourth instalment in the Mad Max franchise, it is an Australian and American[6] venture produced by Kennedy Miller Mitchell, RatPac-Dune Entertainment and Village Roadshow Pictures. The film is set in a future desert wasteland where gasoline and water are scarce commodities. It follows Max Rockatansky (Tom Hardy), who joins forces with Imperator Furiosa (Charlize Theron) to flee from cult leader Immortan Joe (Hugh Keays-Byrne) and his army in an armoured tanker truck, which leads to a lengthy road battle. The film also features Nicholas Hoult, Rosie Huntington-Whiteley, Riley Keough, Zoë Kravitz, Abbey Lee, and Courtney Eaton."
                ,"May 7, 2015"));
        movies.add(new Movie("Tomorrowland","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/8/80/Tomorrowland_poster.jpg/220px-Tomorrowland_poster.jpg"
                ,"Tomorrowland (subtitled A World Beyond in some regions) is a 2015 American science-fiction mystery adventure film[5] directed and co-written by Brad Bird. Bird co-wrote the film's screenplay with Damon Lindelof, from an original story treatment by Bird, Lindelof and Jeff Jensen.[6][7] The film stars George Clooney, Hugh Laurie, Britt Robertson, Raffey Cassidy, Tim McGraw, Kathryn Hahn and Keegan-Michael Key.[5] In the film, a disillusioned genius inventor and a teenage science enthusiast embark to an ambiguous alternate dimension known as \"Tomorrowland\" where their actions directly affect the world and themselves."
                ,"May 8, 2015"));
        movies.add(new Movie("Jurassic World","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Jurassic_World_poster.jpg/220px-Jurassic_World_poster.jpg"
                ,"Jurassic World is a 2015 American science-fiction adventure film and is the fourth installment of the Jurassic Park series. The film was directed and co-written by Colin Trevorrow, produced by Frank Marshall and Patrick Crowley, and stars Chris Pratt and Bryce Dallas Howard. The production companies were Steven Spielberg's Amblin Entertainment, also responsible for the rest of the Jurassic Park franchise, and Thomas Tull's Legendary Pictures. Set 22 years after the events of Jurassic Park, Jurassic World takes place on the same fictional island of Isla Nublar, off the Pacific coast of Central America, where a theme park populated with cloned dinosaurs has operated for ten years. The park plunges into chaos when a genetically created dinosaur breaks loose and goes on a rampage across the island."
                ,"May 29, 2015"));
        movies.add(new Movie("Inside Out","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/0/0a/Inside_Out_%282015_film%29_poster.jpg/220px-Inside_Out_%282015_film%29_poster.jpg"
                ,"Inside Out is a 2015 American 3D computer-animated comedy-drama adventure film produced by Pixar Animation Studios and released by Walt Disney Pictures. The film was directed by Pete Docter and co-directed by Ronnie del Carmen, with a screenplay by Docter, Meg LeFauve and Josh Cooley from a story by Docter and del Carmen. The film is set in the mind of a young girl named Riley Andersen (Kaitlyn Dias), where five personified emotions—Joy (Amy Poehler), Sadness (Phyllis Smith), Anger (Lewis Black), Fear (Bill Hader), and Disgust (Mindy Kaling)—try to lead her through life as her parents (Diane Lane and Kyle MacLachlan) move from Minnesota to San Francisco and she has to adjust to her new surroundings."
                ,"May 18, 2015"));
        movies.add(new Movie("Terminator: Genisys","Cartoon"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/b/bc/Terminator_Genisys.JPG/220px-Terminator_Genisys.JPG"
                ,"Terminator Genisys is a 2015 American science fiction film directed by Alan Taylor and written by Laeta Kalogridis and Patrick Lussier. The fifth installment in the Terminator franchise, following 2009's Terminator Salvation, the film stars Arnold Schwarzenegger (reprising his role as the eponymous character), Jason Clarke, Emilia Clarke and Jai Courtney. The plot follows soldier Kyle Reese in the war against Skynet; Kyle is sent from the year 2029 to 1984 by John Connor, leader of the Human Resistance, to protect Connor's mother Sarah. When Kyle arrives in the past, he discovers that the timeline has been altered and Sarah has been raised by a reprogrammed Terminator."
                ,"June 22, 2015"));
        movies.add(new Movie("Blackhat","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/5/58/Blackhat_poster.jpg/220px-Blackhat_poster.jpg"
                ,"Blackhat is a 2015 American action thriller mystery film co-written, co-produced and directed by Michael Mann. The film stars Chris Hemsworth, Tang Wei, Viola Davis, Holt McCallany, and Wang Leehom. The film premiered at the TCL Chinese Theatre in Los Angeles on January 8, 2015, and was released in theaters on January 16.[4] Blackhat was a box office bomb, earning only $19.7 million at the box office against a budget of $70 million. While the movie received generally mixed-to-negative reviews, with criticisms focused on casting and a point pace, some critics found brilliance in the film, enough to place the film in some critics' year-end lists."
                ,"January 8, 2015"));
        movies.add(new Movie("Fifty Shades of Grey","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/4/4b/Fifty-Gray-poster.jpg/220px-Fifty-Gray-poster.jpg"
                ,"Fifty Shades of Grey is a 2015 American erotic romantic drama film directed by Sam Taylor-Johnson with a screenplay by Kelly Marcel. The film is based on the 2011 novel of the same name by British author E. L. James. It stars Dakota Johnson as Anastasia Steele, a college graduate who begins a sadomasochistic relationship with young business magnate Christian Grey, played by Jamie Dornan."
                ,"February 9, 2015"));
        movies.add(new Movie("Chappie","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/7/71/Chappie_poster.jpg/220px-Chappie_poster.jpg"
                ,"Chappie (stylized as CHAPPiE) is a 2015 American science fiction film directed by Neill Blomkamp and written by Blomkamp and Terri Tatchell. It stars Sharlto Copley, Dev Patel, Jose Pablo Cantillo, Sigourney Weaver, Hugh Jackman, and Watkin Tudor Jones (Ninja) and Yolandi Visser of the South African zef rap-rave group Die Antwoord. The film, set and shot in Johannesburg, is about an artificially intelligent law enforcement robot captured and taught by gangsters, who nickname it Chappie."
                ,"March 4, 2015"));
        movies.add(new Movie("Cinderella","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/c/c2/Cinderella_2015_official_poster.jpg/220px-Cinderella_2015_official_poster.jpg"
                ,"Cinderella is a 2015 American romantic fantasy film, directed by Kenneth Branagh, with a screenplay written by Chris Weitz. The film is based on the eponymous folk tale and inspired by Walt Disney's 1950 animated film.[4] The film stars Lily James as the titular character, with Cate Blanchett, Richard Madden, Stellan Skarsgård, Holliday Grainger, Derek Jacobi, and Helena Bonham Carter. It is produced by David Barron, Simon Kinberg and Allison Shearmur for Walt Disney Pictures."
                ,"February 13, 2015"));
        movies.add(new Movie("Avengers: Age of Ultron","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/1/1b/Avengers_Age_of_Ultron.jpg/220px-Avengers_Age_of_Ultron.jpg"
                ,"Avengers: Age of Ultron is a 2015 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures. It is the sequel to 2012's The Avengers and the eleventh film in the Marvel Cinematic Universe (MCU). The film was written and directed by Joss Whedon and features an ensemble cast that includes Robert Downey Jr., Chris Hemsworth, Mark Ruffalo, Chris Evans, Scarlett Johansson, Jeremy Renner, Don Cheadle, Aaron Taylor-Johnson, Elizabeth Olsen, Paul Bettany, Cobie Smulders, Anthony Mackie, Hayley Atwell, Idris Elba, Stellan Skarsgård, James Spader, and Samuel L. Jackson. In Avengers: Age of Ultron, the Avengers fight Ultron, an artificial intelligence obsessed with causing human extinction."
                ,"April 13, 2015"));
        movies.add(new Movie("Mad Max: Fury Road","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Mad_Max_Fury_Road.jpg/220px-Mad_Max_Fury_Road.jpg"
                ,"Mad Max: Fury Road is a 2015 action film directed and produced by George Miller, and written by Miller, Brendan McCarthy and Nico Lathouris. The fourth instalment in the Mad Max franchise, it is an Australian and American[6] venture produced by Kennedy Miller Mitchell, RatPac-Dune Entertainment and Village Roadshow Pictures. The film is set in a future desert wasteland where gasoline and water are scarce commodities. It follows Max Rockatansky (Tom Hardy), who joins forces with Imperator Furiosa (Charlize Theron) to flee from cult leader Immortan Joe (Hugh Keays-Byrne) and his army in an armoured tanker truck, which leads to a lengthy road battle. The film also features Nicholas Hoult, Rosie Huntington-Whiteley, Riley Keough, Zoë Kravitz, Abbey Lee, and Courtney Eaton."
                ,"May 7, 2015"));
        movies.add(new Movie("Tomorrowland","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/8/80/Tomorrowland_poster.jpg/220px-Tomorrowland_poster.jpg"
                ,"Tomorrowland (subtitled A World Beyond in some regions) is a 2015 American science-fiction mystery adventure film[5] directed and co-written by Brad Bird. Bird co-wrote the film's screenplay with Damon Lindelof, from an original story treatment by Bird, Lindelof and Jeff Jensen.[6][7] The film stars George Clooney, Hugh Laurie, Britt Robertson, Raffey Cassidy, Tim McGraw, Kathryn Hahn and Keegan-Michael Key.[5] In the film, a disillusioned genius inventor and a teenage science enthusiast embark to an ambiguous alternate dimension known as \"Tomorrowland\" where their actions directly affect the world and themselves."
                ,"May 8, 2015"));
        movies.add(new Movie("Jurassic World","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Jurassic_World_poster.jpg/220px-Jurassic_World_poster.jpg"
                ,"Jurassic World is a 2015 American science-fiction adventure film and is the fourth installment of the Jurassic Park series. The film was directed and co-written by Colin Trevorrow, produced by Frank Marshall and Patrick Crowley, and stars Chris Pratt and Bryce Dallas Howard. The production companies were Steven Spielberg's Amblin Entertainment, also responsible for the rest of the Jurassic Park franchise, and Thomas Tull's Legendary Pictures. Set 22 years after the events of Jurassic Park, Jurassic World takes place on the same fictional island of Isla Nublar, off the Pacific coast of Central America, where a theme park populated with cloned dinosaurs has operated for ten years. The park plunges into chaos when a genetically created dinosaur breaks loose and goes on a rampage across the island."
                ,"May 29, 2015"));
        movies.add(new Movie("Inside Out","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/0/0a/Inside_Out_%282015_film%29_poster.jpg/220px-Inside_Out_%282015_film%29_poster.jpg"
                ,"Inside Out is a 2015 American 3D computer-animated comedy-drama adventure film produced by Pixar Animation Studios and released by Walt Disney Pictures. The film was directed by Pete Docter and co-directed by Ronnie del Carmen, with a screenplay by Docter, Meg LeFauve and Josh Cooley from a story by Docter and del Carmen. The film is set in the mind of a young girl named Riley Andersen (Kaitlyn Dias), where five personified emotions—Joy (Amy Poehler), Sadness (Phyllis Smith), Anger (Lewis Black), Fear (Bill Hader), and Disgust (Mindy Kaling)—try to lead her through life as her parents (Diane Lane and Kyle MacLachlan) move from Minnesota to San Francisco and she has to adjust to her new surroundings."
                ,"May 18, 2015"));
        movies.add(new Movie("Terminator: Genisys","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/b/bc/Terminator_Genisys.JPG/220px-Terminator_Genisys.JPG"
                ,"Terminator Genisys is a 2015 American science fiction film directed by Alan Taylor and written by Laeta Kalogridis and Patrick Lussier. The fifth installment in the Terminator franchise, following 2009's Terminator Salvation, the film stars Arnold Schwarzenegger (reprising his role as the eponymous character), Jason Clarke, Emilia Clarke and Jai Courtney. The plot follows soldier Kyle Reese in the war against Skynet; Kyle is sent from the year 2029 to 1984 by John Connor, leader of the Human Resistance, to protect Connor's mother Sarah. When Kyle arrives in the past, he discovers that the timeline has been altered and Sarah has been raised by a reprogrammed Terminator."
                ,"June 22, 2015"));
        movies.add(new Movie("Blackhat","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/5/58/Blackhat_poster.jpg/220px-Blackhat_poster.jpg"
                ,"Blackhat is a 2015 American action thriller mystery film co-written, co-produced and directed by Michael Mann. The film stars Chris Hemsworth, Tang Wei, Viola Davis, Holt McCallany, and Wang Leehom. The film premiered at the TCL Chinese Theatre in Los Angeles on January 8, 2015, and was released in theaters on January 16.[4] Blackhat was a box office bomb, earning only $19.7 million at the box office against a budget of $70 million. While the movie received generally mixed-to-negative reviews, with criticisms focused on casting and a point pace, some critics found brilliance in the film, enough to place the film in some critics' year-end lists."
                ,"January 8, 2015"));
        movies.add(new Movie("Fifty Shades of Grey","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/4/4b/Fifty-Gray-poster.jpg/220px-Fifty-Gray-poster.jpg"
                ,"Fifty Shades of Grey is a 2015 American erotic romantic drama film directed by Sam Taylor-Johnson with a screenplay by Kelly Marcel. The film is based on the 2011 novel of the same name by British author E. L. James. It stars Dakota Johnson as Anastasia Steele, a college graduate who begins a sadomasochistic relationship with young business magnate Christian Grey, played by Jamie Dornan."
                ,"February 9, 2015"));
        movies.add(new Movie("Chappie","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/7/71/Chappie_poster.jpg/220px-Chappie_poster.jpg"
                ,"Chappie (stylized as CHAPPiE) is a 2015 American science fiction film directed by Neill Blomkamp and written by Blomkamp and Terri Tatchell. It stars Sharlto Copley, Dev Patel, Jose Pablo Cantillo, Sigourney Weaver, Hugh Jackman, and Watkin Tudor Jones (Ninja) and Yolandi Visser of the South African zef rap-rave group Die Antwoord. The film, set and shot in Johannesburg, is about an artificially intelligent law enforcement robot captured and taught by gangsters, who nickname it Chappie."
                ,"March 4, 2015"));
        movies.add(new Movie("Cinderella","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/c/c2/Cinderella_2015_official_poster.jpg/220px-Cinderella_2015_official_poster.jpg"
                ,"Cinderella is a 2015 American romantic fantasy film, directed by Kenneth Branagh, with a screenplay written by Chris Weitz. The film is based on the eponymous folk tale and inspired by Walt Disney's 1950 animated film.[4] The film stars Lily James as the titular character, with Cate Blanchett, Richard Madden, Stellan Skarsgård, Holliday Grainger, Derek Jacobi, and Helena Bonham Carter. It is produced by David Barron, Simon Kinberg and Allison Shearmur for Walt Disney Pictures."
                ,"February 13, 2015"));
        movies.add(new Movie("Avengers: Age of Ultron","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/1/1b/Avengers_Age_of_Ultron.jpg/220px-Avengers_Age_of_Ultron.jpg"
                ,"Avengers: Age of Ultron is a 2015 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures. It is the sequel to 2012's The Avengers and the eleventh film in the Marvel Cinematic Universe (MCU). The film was written and directed by Joss Whedon and features an ensemble cast that includes Robert Downey Jr., Chris Hemsworth, Mark Ruffalo, Chris Evans, Scarlett Johansson, Jeremy Renner, Don Cheadle, Aaron Taylor-Johnson, Elizabeth Olsen, Paul Bettany, Cobie Smulders, Anthony Mackie, Hayley Atwell, Idris Elba, Stellan Skarsgård, James Spader, and Samuel L. Jackson. In Avengers: Age of Ultron, the Avengers fight Ultron, an artificial intelligence obsessed with causing human extinction."
                ,"April 13, 2015"));
        movies.add(new Movie("Mad Max: Fury Road","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Mad_Max_Fury_Road.jpg/220px-Mad_Max_Fury_Road.jpg"
                ,"Mad Max: Fury Road is a 2015 action film directed and produced by George Miller, and written by Miller, Brendan McCarthy and Nico Lathouris. The fourth instalment in the Mad Max franchise, it is an Australian and American[6] venture produced by Kennedy Miller Mitchell, RatPac-Dune Entertainment and Village Roadshow Pictures. The film is set in a future desert wasteland where gasoline and water are scarce commodities. It follows Max Rockatansky (Tom Hardy), who joins forces with Imperator Furiosa (Charlize Theron) to flee from cult leader Immortan Joe (Hugh Keays-Byrne) and his army in an armoured tanker truck, which leads to a lengthy road battle. The film also features Nicholas Hoult, Rosie Huntington-Whiteley, Riley Keough, Zoë Kravitz, Abbey Lee, and Courtney Eaton."
                ,"May 7, 2015"));
        movies.add(new Movie("Tomorrowland","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/8/80/Tomorrowland_poster.jpg/220px-Tomorrowland_poster.jpg"
                ,"Tomorrowland (subtitled A World Beyond in some regions) is a 2015 American science-fiction mystery adventure film[5] directed and co-written by Brad Bird. Bird co-wrote the film's screenplay with Damon Lindelof, from an original story treatment by Bird, Lindelof and Jeff Jensen.[6][7] The film stars George Clooney, Hugh Laurie, Britt Robertson, Raffey Cassidy, Tim McGraw, Kathryn Hahn and Keegan-Michael Key.[5] In the film, a disillusioned genius inventor and a teenage science enthusiast embark to an ambiguous alternate dimension known as \"Tomorrowland\" where their actions directly affect the world and themselves."
                ,"May 8, 2015"));
        movies.add(new Movie("Jurassic World","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/Jurassic_World_poster.jpg/220px-Jurassic_World_poster.jpg"
                ,"Jurassic World is a 2015 American science-fiction adventure film and is the fourth installment of the Jurassic Park series. The film was directed and co-written by Colin Trevorrow, produced by Frank Marshall and Patrick Crowley, and stars Chris Pratt and Bryce Dallas Howard. The production companies were Steven Spielberg's Amblin Entertainment, also responsible for the rest of the Jurassic Park franchise, and Thomas Tull's Legendary Pictures. Set 22 years after the events of Jurassic Park, Jurassic World takes place on the same fictional island of Isla Nublar, off the Pacific coast of Central America, where a theme park populated with cloned dinosaurs has operated for ten years. The park plunges into chaos when a genetically created dinosaur breaks loose and goes on a rampage across the island."
                ,"May 29, 2015"));
        movies.add(new Movie("Inside Out","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/0/0a/Inside_Out_%282015_film%29_poster.jpg/220px-Inside_Out_%282015_film%29_poster.jpg"
                ,"Inside Out is a 2015 American 3D computer-animated comedy-drama adventure film produced by Pixar Animation Studios and released by Walt Disney Pictures. The film was directed by Pete Docter and co-directed by Ronnie del Carmen, with a screenplay by Docter, Meg LeFauve and Josh Cooley from a story by Docter and del Carmen. The film is set in the mind of a young girl named Riley Andersen (Kaitlyn Dias), where five personified emotions—Joy (Amy Poehler), Sadness (Phyllis Smith), Anger (Lewis Black), Fear (Bill Hader), and Disgust (Mindy Kaling)—try to lead her through life as her parents (Diane Lane and Kyle MacLachlan) move from Minnesota to San Francisco and she has to adjust to her new surroundings."
                ,"May 18, 2015"));
        movies.add(new Movie("Terminator: Genisys","Horries"
                ,"https://upload.wikimedia.org/wikipedia/en/thumb/b/bc/Terminator_Genisys.JPG/220px-Terminator_Genisys.JPG"
                ,"Terminator Genisys is a 2015 American science fiction film directed by Alan Taylor and written by Laeta Kalogridis and Patrick Lussier. The fifth installment in the Terminator franchise, following 2009's Terminator Salvation, the film stars Arnold Schwarzenegger (reprising his role as the eponymous character), Jason Clarke, Emilia Clarke and Jai Courtney. The plot follows soldier Kyle Reese in the war against Skynet; Kyle is sent from the year 2029 to 1984 by John Connor, leader of the Human Resistance, to protect Connor's mother Sarah. When Kyle arrives in the past, he discovers that the timeline has been altered and Sarah has been raised by a reprogrammed Terminator."
                ,"June 22, 2015"));
        return movies;
    }
}
