package com.goganesh.bookshop.service.impl;

import com.goganesh.bookshop.domain.BookFile;
import com.goganesh.bookshop.repository.BookFileRepository;
import com.goganesh.bookshop.service.ResourceStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ResourceStorageServiceImpl implements ResourceStorageService {

    /*@Value("${upload.path}")
    String uploadPath;*/

    private final String downloadPath;
    private final BookFileRepository bookFileRepository;

    public ResourceStorageServiceImpl(
            @Value("${download.path}")String downloadPath,
            BookFileRepository bookFileRepository) {
        this.downloadPath = downloadPath;
        this.bookFileRepository = bookFileRepository;
    }

    /*public String saveNewBookImage(MultipartFile file, String slug) throws IOException {

        String resourceURI = null;
        if (!file.isEmpty()) {
            if (!new File(uploadPath).exists()) {
                Files.createDirectories(Paths.get(uploadPath));
                Logger.getLogger(this.getClass().getSimpleName()).info("created image folder in " + uploadPath);
            }

            String fileName = slug + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            Path path = Paths.get(uploadPath, fileName);
            resourceURI = "/book-covers/" + fileName;
            file.transferTo(path); //uploading user file here
            Logger.getLogger(this.getClass().getSimpleName()).info(fileName + "uploaded OK!");
        }

        return resourceURI;
    }*/

    @Override
    public Path getBookFilePath(String hash) {
        BookFile bookFile = bookFileRepository.findByHash(hash);
        return Paths.get(bookFile.getPath());
    }

    @Override
    public MediaType getBookFileMime(String hash) {
        BookFile bookFile = bookFileRepository.findByHash(hash);
        String mimeType =
                URLConnection.guessContentTypeFromName(Paths.get(bookFile.getPath()).getFileName().toString());

        if (mimeType != null) {
            return MediaType.parseMediaType(mimeType);
        }else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    @Override
    public byte[] getBookFileByteArray(String hash) throws IOException {
        BookFile bookFile = bookFileRepository.findByHash(hash);
        Path path = Paths.get(downloadPath, bookFile.getPath());
        return Files.readAllBytes(path);

    }
}

