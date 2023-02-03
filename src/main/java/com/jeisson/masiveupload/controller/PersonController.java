package com.jeisson.masiveupload.controller;

import com.jeisson.masiveupload.model.Person;
import com.jeisson.masiveupload.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/readed-file")
    public ResponseEntity<List<Person>> getReadedFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(
                personService.readCsv(file)
        );
    }
    @PostMapping
    public ResponseEntity<String> savePersonInBatch(@RequestParam("file") MultipartFile file) {
        personService.savePerson(file);
        return ResponseEntity.ok(
                "File uploaded successfully!"
        );
    }

    @PostMapping("/without-batch")
    public ResponseEntity<String> saveWithoutBatch(@RequestParam("file") MultipartFile file) {
        personService.saveWithoutBatch(file);
        return ResponseEntity.ok(
                "File uploaded successfully!"
        );
    }
}
