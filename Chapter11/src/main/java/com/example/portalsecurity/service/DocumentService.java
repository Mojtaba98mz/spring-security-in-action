package com.example.portalsecurity.service;

import com.example.portalsecurity.model.Document;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DocumentService {

    @PreAuthorize("hasPermission(#document, 'read')")
    public Document getDocument(Document document) {
        return document;
    }

    @PreAuthorize("hasPermission(#document, 'write')")
    public void updateDocument(Document document, String newContent) {
        document.setContent(newContent);
    }
}
