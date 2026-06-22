package br.com.musicist.modules.goals.service;

import br.com.musicist.modules.goals.exceptions.GeminiParseException;
import br.com.musicist.modules.goals.exceptions.GeminiUnavailableException;
import br.com.musicist.modules.user.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class GoalSuggestionTemplate {
  @Value("${gemini.api-key}")
  private String geminiApiKey;

  public final List<String> suggestGoals(User user) {
    try {
      Client client = new Client.Builder().apiKey(geminiApiKey).build();

      String prompt = buildPrompt(user);

      GenerateContentResponse response =
          client.models.generateContent("gemini-3-flash-preview", prompt, null);

      return parseGoals(response.text());
    } catch (GeminiParseException e) {
      throw e;
    } catch (Exception e) {
      throw new GeminiUnavailableException();
    }
  }

  protected abstract String buildPrompt(User user);

  private List<String> parseGoals(String json) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(json, new TypeReference<List<String>>() {});
    } catch (Exception e) {
      throw new GeminiParseException();
    }
  }
}
