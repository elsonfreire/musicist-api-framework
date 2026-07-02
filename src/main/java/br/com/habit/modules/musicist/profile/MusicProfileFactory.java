package br.com.habit.modules.musicist.profile;

import org.springframework.stereotype.Component;

import br.com.habit.modules.framework.user.model.DomainProfile;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user.service.DomainProfileFactory;

@Component
public class MusicProfileFactory implements DomainProfileFactory {

    @Override
    public DomainProfile create(User user) {
        MusicProfile profile = new MusicProfile();
        profile.setUser(user);
        return profile;
    }
    
}
