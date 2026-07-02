package br.com.habit.modules.framework.user.service;

import br.com.habit.modules.framework.user.dto.UserResponse;
import br.com.habit.modules.framework.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserResponseFactory {

    private final DomainProfileMapper domainProfileMapper;

    public UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBio(),
                user.getCity(),
                user.getState(),
                user.getCreatedAt(),
                domainProfileMapper.toData(user.getDomainProfile())
        );
    }
}
