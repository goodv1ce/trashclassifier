package ua.lysaka.thesis.trashclassifier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrashClassificationResponse {
    private String predictedClass;
    private double confidence;
}


