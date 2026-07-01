package br.com.habit.modules.musicist.practice;

import br.com.habit.modules.framework.practice.dto.PracticeRequest;
import br.com.habit.modules.framework.practice.model.Practice;
import br.com.habit.modules.framework.practice.service.PracticeStrategy;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.musicist.enums.InstrumentType;
import br.com.habit.modules.musicist.profile.MusicProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class MusicPracticeStrategy implements PracticeStrategy {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getDomainType() {
        return "MUSIC"; 
    }

    @Override
    public Practice createPracticeEntity(PracticeRequest request, User user) {
        
        MusicProfile musicProfile = (MusicProfile) user.getDomainProfile();
        MusicPracticeDomainData musicData = objectMapper.convertValue(
            request.domainData(), 
            MusicPracticeDomainData.class
        );

        InstrumentType instrument = musicData.instrument();
        
        if (instrument == null) {
            instrument = musicProfile.getInstrument(); 
        }

        if (instrument == null) {
            throw new IllegalArgumentException("O instrumento é obrigatório para práticas musicais.");
        }

        return new MusicPractice(
            request.durationMinutes(),
            request.notes(),
            request.date(),
            user,
            instrument
        );
    }
}