package com.dogether.dogether.datacaching.models;

/**
 * Created by dogether on 9/2/16.
 */
public class MovieDetails {
    private String Released;

    private String Type;

    private String imdbVotes;

    private String Runtime;

    private String Response;

    private String Poster;

    private String imdbID;

    private String Country;

    private String Title;

    private String imdbRating;

    private String Year;

    private String Rated;

    private String Actors;

    private String Plot;

    private String Metascore;

    private String Writer;

    private String Genre;

    private String Language;

    private String Awards;

    private String Director;

    public String getReleased ()
    {
        return Released;
    }

    public void setReleased (String Released)
    {
        this.Released = Released;
    }

    public String getType ()
    {
        return Type;
    }

    public void setType (String Type)
    {
        this.Type = Type;
    }

    public String getImdbVotes ()
    {
        return imdbVotes;
    }

    public void setImdbVotes (String imdbVotes)
    {
        this.imdbVotes = imdbVotes;
    }

    public String getRuntime ()
    {
        return Runtime;
    }

    public void setRuntime (String Runtime)
    {
        this.Runtime = Runtime;
    }

    public String getResponse ()
    {
        return Response;
    }

    public void setResponse (String Response)
    {
        this.Response = Response;
    }

    public String getPoster ()
    {
        return Poster;
    }

    public void setPoster (String Poster)
    {
        this.Poster = Poster;
    }

    public String getImdbID ()
    {
        return imdbID;
    }

    public void setImdbID (String imdbID)
    {
        this.imdbID = imdbID;
    }

    public String getCountry ()
    {
        return Country;
    }

    public void setCountry (String Country)
    {
        this.Country = Country;
    }

    public String getTitle ()
    {
        return Title;
    }

    public void setTitle (String Title)
    {
        this.Title = Title;
    }

    public String getImdbRating ()
    {
        return imdbRating;
    }

    public void setImdbRating (String imdbRating)
    {
        this.imdbRating = imdbRating;
    }

    public String getYear ()
    {
        return Year;
    }

    public void setYear (String Year)
    {
        this.Year = Year;
    }

    public String getRated ()
    {
        return Rated;
    }

    public void setRated (String Rated)
    {
        this.Rated = Rated;
    }

    public String getActors ()
    {
        return Actors;
    }

    public void setActors (String Actors)
    {
        this.Actors = Actors;
    }

    public String getPlot ()
    {
        return Plot;
    }

    public void setPlot (String Plot)
    {
        this.Plot = Plot;
    }

    public String getMetascore ()
    {
        return Metascore;
    }

    public void setMetascore (String Metascore)
    {
        this.Metascore = Metascore;
    }

    public String getWriter ()
    {
        return Writer;
    }

    public void setWriter (String Writer)
    {
        this.Writer = Writer;
    }

    public String getGenre ()
    {
        return Genre;
    }

    public void setGenre (String Genre)
    {
        this.Genre = Genre;
    }

    public String getLanguage ()
    {
        return Language;
    }

    public void setLanguage (String Language)
    {
        this.Language = Language;
    }

    public String getAwards ()
    {
        return Awards;
    }

    public void setAwards (String Awards)
    {
        this.Awards = Awards;
    }

    public String getDirector ()
    {
        return Director;
    }

    public void setDirector (String Director)
    {
        this.Director = Director;
    }

    @Override
    public String toString()
    {
        return "Movie Details: [Released = "+Released+", Type = "+Type+", imdbVotes = "+imdbVotes+", Runtime = "+Runtime+", Response = "+Response+", Poster = "+Poster+", imdbID = "+imdbID+", Country = "+Country+", Title = "+Title+", imdbRating = "+imdbRating+", Year = "+Year+", Rated = "+Rated+", Actors = "+Actors+", Plot = "+Plot+", Metascore = "+Metascore+", Writer = "+Writer+", Genre = "+Genre+", Language = "+Language+", Awards = "+Awards+", Director = "+Director+"]";
    }
}
