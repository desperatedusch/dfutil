package de.dfutil.importing.updates;

import jakarta.transaction.Transactional;

public interface Successions {

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    default void process() {
        handleOrObjects();
        handleObObjects();
        handleSbObjects();
    }

    void handleOrObjects();

    void handleObObjects();

    void handleSbObjects();
}
