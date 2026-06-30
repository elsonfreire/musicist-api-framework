package br.com.habit.modules.framework.user.service;

import br.com.habit.modules.framework.user.model.User;

public interface DomainProfileUpdater {
    void update(User user, Object userUpdateRequest);
}
