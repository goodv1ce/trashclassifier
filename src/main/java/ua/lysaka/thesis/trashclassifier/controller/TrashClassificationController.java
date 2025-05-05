package ua.lysaka.thesis.trashclassifier.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.lysaka.thesis.trashclassifier.dto.TrashClassificationResponse;
import ua.lysaka.thesis.trashclassifier.service.TrashClassificationService;

@RestController
@RequestMapping("api/v1/classify")
@AllArgsConstructor
public class TrashClassificationController {
    private final TrashClassificationService classificationService;

    @PostMapping
    public ResponseEntity<TrashClassificationResponse> classifyImage(@RequestParam("image") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        TrashClassificationResponse result = classificationService.classifyTrash(imageFile);
        return ResponseEntity.ok(result);
    }
}
