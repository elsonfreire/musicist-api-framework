package br.com.habit.modules.framework.user.service;

import org.springframework.stereotype.Service;

import br.com.habit.modules.framework.user.dto.UserUpdateRequest;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user.repository.UserRepository;
import br.com.habit.modules.musicist.profile.MusicProfile;

@Service
public class MusicUserService extends UserService {
    public MusicUserService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    protected void updateDomainProfile(User user, UserUpdateRequest userUpdateRequest) {
        MusicProfile musicProfile = (MusicProfile) user.getDomainProfile();
        
        if (userUpdateRequest.level() != null) musicProfile.setLevel(userUpdateRequest.level());
        if (userUpdateRequest.instrument() != null) musicProfile.setInstrument(userUpdateRequest.instrument());
        if (userUpdateRequest.favoriteGenre() != null) musicProfile.setFavoriteGenre(userUpdateRequest.favoriteGenre());
        if (userUpdateRequest.interests() != null) {
        musicProfile.getInterests().clear();
        musicProfile.getInterests().addAll(userUpdateRequest.interests());
        }
    }
    
}
