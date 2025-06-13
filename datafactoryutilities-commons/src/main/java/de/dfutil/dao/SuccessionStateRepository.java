package de.dfutil.dao;

import de.dfutil.entities.SuccessionStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessionStateRepository<T> extends JpaRepository<SuccessionStateEntity, Long> {

//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query("")
//    default void addPredecessor(UUID uuid, UUID predecessorUuid);
//
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query("")
//    default void removePredecessor(UUID uuid, UUID predecessorUuid);
//
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query("")
//    default void addPredecessor(T entity, T predecessor);
//
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query("")
//    default void removePredecessor(T entity, T predecessor);
//
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query("")
//    default void addSuccessor(UUID uuid, UUID predecessorUuid);
//
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query("")
//    default void removeSuccessor(UUID uuid, UUID predecessorUuid);
//
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query("")
//    default void addSuccessor(T entity, T predecessor);
//
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query("")
//    default void removeSuccessor(T entity, T predecessor);

}
