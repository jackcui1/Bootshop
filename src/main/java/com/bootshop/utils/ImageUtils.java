package com.bootshop.utils;

import com.bootshop.controller.ImagesController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 * @author Guowei Cui
 * @date 10/5/2018 1:27 PM
 */
public class ImageUtils {
    public static String imageNameToAbsolutePath(String imageName) {
        return MvcUriComponentsBuilder
                .fromMethodName(ImagesController.class,
                        "getFile", imageName).build().toString();
    }
}
