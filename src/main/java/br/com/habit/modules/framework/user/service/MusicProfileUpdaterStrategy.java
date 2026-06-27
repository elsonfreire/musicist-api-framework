package br.com.habit.modules.framework.user.service;

import org.springframework.stereotype.Component;

import br.com.habit.modules.framework.user.dto.UserUpdateRequest;
import br.com.habit.modules.framework.user.model.DomainProfile;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.musicist.profile.MusicProfile;

@Component
public class MusicProfileUpdaterStrategy {
    // TODO: Create interface for strategy

    public void update(DomainProfile domainProfile, UserUpdateRequest userUpdated) {

        if (userUpdated.level() != null) domainProfile.setLevel(userUpdated.level());
        if (userUpdated.instrument() != null) domainProfile.setInstrument(userUpdated.instrument());
        if (userUpdated.favoriteGenre() != null) domainProfile.setFavoriteGenre(userUpdated.favoriteGenre());
        if (userUpdated.interests() != null) {
        domainProfile.getInterests().clear();
        domainProfile.getInterests().addAll(userUpdated.interests());
        }
    }
}
