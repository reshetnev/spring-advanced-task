package com.epam.reshetnev.spring.core.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface FileService {

    public List<String> parseFiles(Map<String, MultipartFile> fileMap) throws IOException, JsonParseException, JsonMappingException;
}
