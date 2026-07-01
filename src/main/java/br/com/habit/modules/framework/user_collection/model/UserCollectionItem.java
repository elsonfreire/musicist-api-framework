package br.com.habit.modules.framework.user_collection.model;

import br.com.habit.modules.framework.shared.model.UserOwnedEntity;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class UserCollectionItem extends UserOwnedEntity {}
