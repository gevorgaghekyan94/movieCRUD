package com.example.movie.entities;

import java.util.Objects;

public class Movie {

    private String name;
    private String description;
    private int duration;

    public Movie() {
    }

    public Movie(String name, String description, int duration) {
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return duration == movie.duration &&
                Objects.equals(name, movie.name) &&
                Objects.equals(description, movie.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, duration);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                '}';
    }
}
