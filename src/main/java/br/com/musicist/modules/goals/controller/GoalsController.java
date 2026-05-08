package br.com.musicist.modules.goals.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/goals")
public class GoalsController {
    @Value("${gemini.api-key}")
    private String geminiApiKey;
    
    @GetMapping
    public String getGoals() {
        Client client = new Client.Builder().apiKey(geminiApiKey).build();

        String responseLanguage = "PT-BR";
        String prompt = "I want to learn a musical instrument."
                + "Suggest exactly 3 different goals for me that I can complete in one week or less."
                + "The response should be a string array in JSON format." 
                + "The content should be in " + responseLanguage; 

        GenerateContentResponse response = 
            client.models.generateContent(
                "gemini-3-flash-preview", 
                prompt,
                null
            );

        return response.text();
    }
    
}
