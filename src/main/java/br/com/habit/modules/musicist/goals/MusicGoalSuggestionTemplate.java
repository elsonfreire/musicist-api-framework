package br.com.habit.modules.musicist.goals;

import org.springframework.stereotype.Component;

import br.com.habit.modules.framework.goals.service.GoalSuggestionTemplate;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.musicist.profile.MusicProfile;

@Component
public class MusicGoalSuggestionTemplate extends GoalSuggestionTemplate {

    @Override
    public String buildPrompt(User user) {
        StringBuilder context = new StringBuilder("Generate exactly 3 weekly musical goals");

        MusicProfile musicProfile = (MusicProfile) user.getDomainProfile();

        if (musicProfile.getInstrument() != null)
            context.append(" for a ").append(musicProfile.getInstrument()).append(" player");
        if (musicProfile.getLevel() != null) context.append(", level ").append(musicProfile.getLevel());
        if (musicProfile.getFavoriteGenre() != null)
            context.append(", favorite genre ").append(musicProfile.getFavoriteGenre());

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
