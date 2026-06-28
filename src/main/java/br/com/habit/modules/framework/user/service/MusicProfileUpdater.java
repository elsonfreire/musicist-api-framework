package br.com.habit.modules.framework.user.service;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.habit.modules.framework.user.dto.MusicProfileData;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.musicist.profile.MusicProfile;

@Component
public class MusicProfileUpdater implements DomainProfileUpdater {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void update(User user, Object domainProfileData) {
        if (!(user.getDomainProfile() instanceof MusicProfile profile)) {
            return;
        }

        MusicProfileData data =
            objectMapper.convertValue(domainProfileData, MusicProfileData.class);
        
        if (data.level() != null) profile.setLevel(data.level());
        if (data.instrument() != null) profile.setInstrument(data.instrument());
        if (data.favoriteGenre() != null) profile.setFavoriteGenre(data.favoriteGenre());
        if (data.interests() != null) {
            profile.getInterests().clear();
            profile.getInterests().addAll(data.interests());
        }
    }
}
