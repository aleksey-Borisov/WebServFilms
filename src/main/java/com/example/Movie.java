package com.example;

public class Movie {
    private int id;
    private String name;
    public int rating;
    private String comment;

//    public void Movie() {
//        if (!DemoApplication.movies.isEmpty()) {
//            Movie lastMovie = DemoApplication.movies.get(DemoApplication.movies.size() - 1);
//            this.setId(lastMovie.getId() + 1);
//        } else {
//            this.setId(1);
//        }
//    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}

