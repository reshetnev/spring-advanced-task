package com.epam.reshetnev.spring.advanced.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.epam.reshetnev.spring.core.service.FileService;

@Controller
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView handleFormUpload(MultipartHttpServletRequest req) {

        ModelAndView model = new ModelAndView();

        Map<String, MultipartFile> fileMap = req.getFileMap();

        if (!fileMap.isEmpty()) {
            try {
                List<String> dataList = fileService.parseFiles(fileMap);
                model.addObject("dataList", dataList);
                model.setViewName("upload");
            } catch(Exception e) {
                model.addObject("message", e.getMessage());
                model.setViewName("errorParse");
            } finally {
                return model;
            }
        }

        model.setViewName("errorUpload");
        return model;
    }

}
