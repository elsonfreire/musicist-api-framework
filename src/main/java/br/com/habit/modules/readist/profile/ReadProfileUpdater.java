package br.com.habit.modules.readist.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user.service.DomainProfileUpdater;

@Component
@RequiredArgsConstructor
public class ReadProfileUpdater implements DomainProfileUpdater {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void update(User user, Object domainProfileData) {
        if (!(user.getDomainProfile() instanceof ReadProfile profile)) {
            return;
        }

        if (domainProfileData == null) {
            return;
        }

        ReadProfileData data =
            objectMapper.convertValue(domainProfileData, ReadProfileData.class);

        if (data.preferredFormat() != null) profile.setPreferredFormat(data.preferredFormat());
        if (data.level() != null) profile.setLevel(data.level());
        if (data.favoriteGenre() != null) profile.setFavoriteGenre(data.favoriteGenre());
        if (data.interests() != null) {
            profile.getInterests().clear();
            profile.getInterests().addAll(data.interests());
        }
    }
}