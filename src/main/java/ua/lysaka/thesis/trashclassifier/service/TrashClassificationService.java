package ua.lysaka.thesis.trashclassifier.service;

import lombok.RequiredArgsConstructor;
import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.lysaka.thesis.trashclassifier.dto.TrashClassificationResponse;
import ua.lysaka.thesis.trashclassifier.model.TrashType;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrashClassificationService {
    private final MultiLayerNetwork model;
    private final List<String> labels = Arrays.stream(TrashType.values())
            .map(Enum::name)
            .toList();

    public TrashClassificationResponse classifyTrash(MultipartFile imageFile) {
        try {
            // Loading the image
            NativeImageLoader loader = new NativeImageLoader(100, 100, 3);
            INDArray image = loader.asMatrix(imageFile.getInputStream());

            // Pixels normalizing
            ImagePreProcessingScaler scaler = new ImagePreProcessingScaler(0, 1);
            scaler.transform(image);

            // Inference
            INDArray output = model.output(image);

            double[] probabilities = output.toDoubleVector();

            // Виводимо кожен клас з ймовірністю
            for (int i = 0; i < labels.size(); i++) {
                System.out.printf("%s: %.4f%n", labels.get(i), probabilities[i]);
            }

            int classIndex = Nd4j.argMax(output, 1).getInt(0);
            double confidence = output.getDouble(0, classIndex);
            String predictedClass = labels.get(classIndex);

            return new TrashClassificationResponse(predictedClass, confidence);
        } catch (IOException e) {
            return new TrashClassificationResponse("ERROR", 0.0);
        }
    }
}
