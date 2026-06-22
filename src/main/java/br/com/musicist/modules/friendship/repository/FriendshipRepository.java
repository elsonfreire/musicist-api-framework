package br.com.musicist.modules.friendship.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.musicist.modules.friendship.enums.FriendshipStatusType;
import br.com.musicist.modules.friendship.model.Friendship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {
  @Query(
      """
        SELECT f FROM Friendship f
        WHERE (f.requester.id = :a AND f.receiver.id = :b)
           OR (f.requester.id = :b AND f.receiver.id = :a)
    """)
  Optional<Friendship> findBetween(@Param("a") UUID a, @Param("b") UUID b);

  @Query(
      """
        SELECT f FROM Friendship f
        WHERE (f.requester.id = :userId OR f.receiver.id = :userId)
          AND f.status = 'ACCEPTED'
    """)
  List<Friendship> findAcceptedFriendships(@Param("userId") UUID userId);

  List<Friendship> findByReceiverIdAndStatus(UUID receiverId, FriendshipStatusType status);

  @Query(
      """
        SELECT f.receiver.id FROM Friendship f WHERE f.requester.id = :userId
        UNION
        SELECT f.requester.id FROM Friendship f WHERE f.receiver.id = :userId
    """)
  List<UUID> findConnectedUserIds(@Param("userId") UUID userId);
}
