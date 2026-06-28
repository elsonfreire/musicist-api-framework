package br.com.habit.modules.framework.user.service;

import org.springframework.stereotype.Service;

import br.com.habit.modules.framework.user.dto.MusicUserUpdateRequest;
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
        MusicProfile profile = (MusicProfile) user.getDomainProfile();
        MusicUserUpdateRequest musicUserUpdateRequest = (MusicUserUpdateRequest) userUpdateRequest;

        if (musicUserUpdateRequest.getLevel() != null) profile.setLevel(musicUserUpdateRequest.getLevel());
        if (musicUserUpdateRequest.getInstrument() != null) profile.setInstrument(musicUserUpdateRequest.getInstrument());
        if (musicUserUpdateRequest.getFavoriteGenre() != null) profile.setFavoriteGenre(musicUserUpdateRequest.getFavoriteGenre());
        if (musicUserUpdateRequest.getInterests() != null) {
        profile.getInterests().clear();
        profile.getInterests().addAll(musicUserUpdateRequest.getInterests());
        }
    }
    
}
