package br.com.habit.modules.musicist.profile;

import br.com.habit.modules.framework.user.model.DomainProfile;
import br.com.habit.modules.framework.user.service.DomainProfileMapper;
import org.springframework.stereotype.Component;

@Component
public class MusicProfileMapper implements DomainProfileMapper {

    @Override
    public Object toData(DomainProfile domainProfile) {
        MusicProfile profile = (MusicProfile) domainProfile;

        return new MusicProfileData(
                profile.getInstrument(),
                profile.getLevel(),
                profile.getFavoriteGenre(),
                profile.getInterests()
        );
    }
}
