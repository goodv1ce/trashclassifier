package ua.lysaka.thesis.trashclassifier.config;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class ModelConfig {
    @Bean
    public MultiLayerNetwork trashClassificationModel() throws IOException {
        File modelFile = new File("resources/model/model.zip");
        MultiLayerNetwork model = ModelSerializer.restoreMultiLayerNetwork(modelFile);
        model.init();

        return model;
    }
}
