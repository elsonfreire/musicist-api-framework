package br.com.habit.modules.readist.profile;

import org.springframework.stereotype.Component;

import br.com.habit.modules.framework.user.model.DomainProfile;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user.service.DomainProfileFactory;

@Component
public class ReadProfileFactory implements DomainProfileFactory {

    @Override
    public DomainProfile create(User user) {
        ReadProfile profile = new ReadProfile();
        profile.setUser(user);
        return profile;
    }
    
}