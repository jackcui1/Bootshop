package com.bootshop.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

/*
 * The Service to handle file systems.
 */
@Service
public class StorageFileService {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("upload-dir");

    public void store(MultipartFile file, String destinationFileName) {
        try {
            System.out.println(destinationFileName + "  " + file.getContentType());
            Files.copy(file.getInputStream(), this.rootLocation.resolve(destinationFileName));
            System.out.println(rootLocation.toString());
        } catch (Exception e) {
            throw new RuntimeException("Coping file fail.");
        }
    }

    public void store(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException(file.getFileName() + " does not exist.");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Loading file fail.");
        }
    }

    public void deleteFile(String filename) {

        Path file = rootLocation.resolve(filename);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {

                resource.getFile().delete();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Delete a file fail.");
        } catch (IOException e) {
            throw new RuntimeException("Delete a file fail.");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storeage!");
        }
    }
}
