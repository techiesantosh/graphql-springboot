package com.boot.graphql.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Author {

    private String id;
    private String firstName;
    private String lastName;

    private static List<Author> authors = new ArrayList<>();

    public Author(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;

    }

     static {
         authors.addAll(
                 Arrays.asList(
                         new Author("author-1", "Joanne", "Rowling"),
                         new Author("author-2", "Herman", "Melville"),
                         new Author("author-3", "Anne", "Rice")));
     }

    public static Author getById(String id) {
        return authors.stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }

    public static Author addAuthor(String firstName, String lastName) {
        Author author = new Author("author-3", firstName, lastName);
         authors.add(author);
         return  author;
    }

    public String getId() {
        return id;
    }
}