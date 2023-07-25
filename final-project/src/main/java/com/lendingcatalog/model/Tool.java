package com.lendingcatalog.model;

import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Tool implements CatalogItem {
    private String id;
    private String type;
    private  String manufacturer;
    private int count;


    public Tool(String type, String manufacturer, int count){

        this.type = type;
        this.manufacturer = manufacturer;
        this.count = count;
    }
    @Override
    public String toString() {
        return "* " + type + System.lineSeparator()
                + " - Manufacturer: " + manufacturer + System.lineSeparator()
                + " - Amount: " + count + System.lineSeparator()
                + " - Id: " + id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean matchesName(String searchStr) {
        return type.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return manufacturer.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return false;
    }

    @Override
    public void registerItem() throws FileNotFoundException, FileStorageException {
        id = UUID.randomUUID().toString();
        String logMessage = "Tool is registered" + "at" + LocalDate.now() + LocalTime.now() + this.toString();
        FileStorageService.writeContentsToFile(logMessage, "src/main/resources/logs" + LocalDate.now() + ".log", true);
    }
}

