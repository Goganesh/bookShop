package com.goganesh.bookshop.service;

import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Path;

public interface ResourceStorageService {

    Path getBookFilePath(String hash);
    MediaType getBookFileMime(String hash);
    byte[] getBookFileByteArray(String hash) throws IOException;
}
