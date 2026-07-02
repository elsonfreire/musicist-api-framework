package br.com.habit.modules.musicist.practice;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.habit.modules.framework.practice.dto.PracticeRequest;
import br.com.habit.modules.framework.practice.model.Practice;
import br.com.habit.modules.framework.practice.service.PracticeStrategy;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.musicist.enums.InstrumentType;
import br.com.habit.modules.musicist.profile.MusicProfile;

@Component
public class MusicPracticeStrategy implements PracticeStrategy {

    private final ObjectMapper objectMapper = new ObjectMapper();

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
            throw new IllegalArgumentException("An instrument is required for music practices.");
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