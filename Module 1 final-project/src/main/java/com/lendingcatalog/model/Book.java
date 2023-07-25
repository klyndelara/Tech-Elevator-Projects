package com.lendingcatalog.model;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.UUID;
import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

public class Book implements CatalogItem {
    private String id;
    private String title;
    private String author;
    private LocalDate publishDate;



    public Book(String title, String author, LocalDate publishDate) {

        this.title = title;
        this.author = author;
        this.publishDate = publishDate;


    }
@Override
    public String toString() {
        return "* " + title + System.lineSeparator()
                + " - Written by: " + author + System.lineSeparator()
                + " - Published: " + publishDate + System.lineSeparator()
                + " - Id: " + id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }


    @Override
    public boolean matchesName(String searchStr) {
        return title.toLowerCase().contains(searchStr.toLowerCase()); }

    @Override
    public boolean matchesCreator(String searchStr) {
        return author.toLowerCase().contains(searchStr.toLowerCase());}


    @Override
    public boolean matchesYear(int searchYear) {

            return publishDate.getYear() == searchYear;
        }

    @Override
    public void registerItem() throws FileNotFoundException, FileStorageException {
        id = UUID.randomUUID().toString();
        String logMessage = "Book is registered" + "at" + LocalDate.now() + LocalTime.now() + this.toString(); //writes message to file.
        FileStorageService.writeContentsToFile(logMessage, "src/main/resources/logs" + LocalDate.now() + ".log", true);
    }


}