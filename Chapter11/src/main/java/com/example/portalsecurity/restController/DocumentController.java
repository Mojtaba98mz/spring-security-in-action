package com.example.portalsecurity.restController;

import com.example.portalsecurity.model.Document;
import com.example.portalsecurity.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    @GetMapping("/document")
    public Document getDocument(@RequestParam Long id){
        Document document = new Document(id, "john", "This is a sample document");
        return documentService.getDocument(document);
    }


}
