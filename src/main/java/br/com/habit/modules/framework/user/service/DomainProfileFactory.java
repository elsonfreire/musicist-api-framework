package br.com.habit.modules.framework.user.service;

import br.com.habit.modules.framework.user.model.DomainProfile;
import br.com.habit.modules.framework.user.model.User;

public interface DomainProfileFactory {
    DomainProfile create(User user);
}
