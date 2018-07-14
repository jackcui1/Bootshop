package com.bootshop.controller;

import com.bootshop.service.StoreFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.core.io.Resource;

/**
 * @author Guowei Cui
 * @date 7/13/2018 8:22 PM
 */
@Controller
public class ImageController {

    @Autowired
    private StoreFileService storageService;

    @GetMapping("/imgfiles/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = (Resource) storageService.loadFile(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

}
