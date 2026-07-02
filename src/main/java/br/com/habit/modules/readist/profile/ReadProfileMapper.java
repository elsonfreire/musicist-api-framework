package br.com.habit.modules.readist.profile;

import br.com.habit.modules.framework.user.model.DomainProfile;
import br.com.habit.modules.framework.user.service.DomainProfileMapper;
import org.springframework.stereotype.Component;

@Component
public class ReadProfileMapper implements DomainProfileMapper {

    @Override
    public Object toData(DomainProfile domainProfile) {
        ReadProfile profile = (ReadProfile) domainProfile;

        return new ReadProfileData(
                profile.getPreferredFormat(),
                profile.getLevel(),
                profile.getFavoriteGenre(),
                profile.getInterests()
        );
    }
}