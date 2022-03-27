package com.goganesh.bookshop.controllers.api;

import com.goganesh.bookshop.service.ResourceStorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class FileApiController {


    private final ResourceStorageService resourceStorageService;

    @GetMapping("/books/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash")String hash) throws IOException {
        Path path = resourceStorageService.getBookFilePath(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file path: " + path);

        MediaType mediaType = resourceStorageService.getBookFileMime(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file mime type: " + mediaType);

        byte[] data =resourceStorageService.getBookFileByteArray(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file data len: "+data.length);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
    }

    /*@PostMapping("/{slug}/img/save")
    public String saveNewBookImage(@RequestParam("file")MultipartFile file,@PathVariable("slug")String slug) throws IOException {
        String savePath = storage.saveNewBookImage(file,slug);
        Book bookToUpdate = bookRepository.findBookBySlug(slug);
        bookToUpdate.setImage(savePath);
        bookRepository.save(bookToUpdate); //save new path in db here

        return "redirect:/books/"+slug;
    }*/
}
