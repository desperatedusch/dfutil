package de.dfutil.core.listener;

import de.dfutil.events.CreatedFrom;
import de.dfutil.events.MergeWith;

public interface DataFactoryImportHandler {

    public void handleCreate(CreatedFrom event);
    public void handleMerge(MergeWith event);

}
