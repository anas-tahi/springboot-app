package com.example.carrito.controller;

import com.example.carrito.service.DatabaseExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Path;

@Controller
public class AdminController {

    @Autowired
    private DatabaseExportService exportService;

    @GetMapping("/admin/export")
    public ResponseEntity<FileSystemResource> exportDatabase() throws IOException {
        Path exportFile = exportService.exportDatabase();

        FileSystemResource resource = new FileSystemResource(exportFile.toFile());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=products.sql")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
