package br.com.musicist.modules.goals.service;

import org.springframework.stereotype.Component;

import br.com.musicist.modules.user.model.User;

@Component
public class MusicGoalSuggestionTemplate extends GoalSuggestionTemplate {

    @Override
    public String buildPrompt(User user) {
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
    
}
