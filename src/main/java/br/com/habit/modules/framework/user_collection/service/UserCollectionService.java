package br.com.habit.modules.framework.user_collection.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import br.com.habit.modules.framework.user.model.User;

public interface UserCollectionService<TRequest, TResponse, TStatus> {
    Map<TStatus, List<TResponse>> findAllByUser(User user);

    TResponse create(TRequest request, User user);

    TResponse updateStatus(UUID id, TStatus status, User user);

    void delete(UUID id, User user);
}
