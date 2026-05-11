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
public class GeminiService {
  @Value("${gemini.api-key}")
  private String geminiApiKey;

  public List<String> getSuggestGoals(User user) {
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

  private String buildPrompt(User user) {
    StringBuilder context = new StringBuilder("Generate exactly 3 weekly musical goals");

    if (user.getInstrument() != null)
      context.append(" for a ").append(user.getInstrument()).append(" player");
    if (user.getLevel() != null) context.append(", level ").append(user.getLevel());
    if (user.getFavoriteGenre() != null)
      context.append(", favorite genre ").append(user.getFavoriteGenre());

    context.append(
        """
            .
            Each goal must be specific and achievable within 7 days.
            Reply ONLY with a JSON array of strings. Example: ["Goal 1", "Goal 2", "Goal 3"]
            No explanations, no markdown, just the JSON. Language: PT-BR.
        """);

    return context.toString();
  }

  private List<String> parseGoals(String json) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(json, new TypeReference<List<String>>() {});
    } catch (Exception e) {
      throw new GeminiParseException();
    }
  }
}
