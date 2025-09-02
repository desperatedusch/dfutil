package de.dfutil.dao;

import java.time.LocalDateTime;
import java.util.UUID;

public interface SuccessionsApplicableRepository {

    void outdate(UUID uuid, LocalDateTime date);

    void changeStatus(UUID uuid, String status);

    void apply(UUID uuid, LocalDateTime date);

    void resetAppliedState();

    void resetOutdatedState();

}
