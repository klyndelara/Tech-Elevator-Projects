package com.lendingcatalog.util;

import com.lendingcatalog.util.exception.FileStorageException;

<<<<<<< HEAD
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
=======
import java.io.*;
>>>>>>> 61ee5a133fc213b9007434c585a5413996a73386
import java.util.List;
import java.util.Scanner;

public class FileStorageService {

    // Requirement: File I/O
<<<<<<< HEAD
    public static void writeContentsToFile(String contents, String filename, boolean appendFile) throws FileStorageException, FileNotFoundException {
        //  PrintWriter writer = null;
        // try {
        //     FileOutputStream fileOutputStream = new FileOutputStream(filename, appendFile);
        //      writer = new PrintWriter(fileOutputStream);
        //     writer.close();
        //  } catch (FileNotFoundException e) {
        //     throw new FileStorageException("File" + filename + "was not found.");
        // } catch (Exception e) {
        //     throw new FileStorageException("Failed to write contents due to unknown error: " + e.getMessage());

        //  } finally {
        //  if (writer != null) {
        //       writer.close();}}

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename, appendFile))) {
            writer.print(contents);
        } catch (FileNotFoundException e) {
            throw new FileStorageException("File not found.", e);
        }

=======
    public static void writeContentsToFile(String contents, String filename, boolean appendFile) throws FileStorageException {
        try (
             FileOutputStream fileOutputStream2 = new FileOutputStream(filename, appendFile);
             PrintWriter writer2 = new PrintWriter(fileOutputStream2);
        ) {
            writer2.println(contents);
        } catch (FileNotFoundException e) {
            throw new FileStorageException("File " + filename + " was not found.");
        } catch (IOException e) {
            throw new FileStorageException("Unable to close file output stream");
        }
>>>>>>> 61ee5a133fc213b9007434c585a5413996a73386
    }

        public static List<String> readContentsOfFile (String filename) throws FileStorageException {
            List<String> results = new ArrayList<>();
            try (Scanner scanner = new Scanner(new File(filename))) {
                while (scanner.hasNextLine()) {
                    results.add(scanner.nextLine());
                }


            } catch (FileNotFoundException e) {
                throw new FileStorageException("File" + filename + "was not found.");
            }
            return results;
        }
    }


