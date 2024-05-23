package mz.org.csaude.mentoring.common;

import mz.org.csaude.mentoring.util.SyncSatus;

public interface Syncable {

    void setSyncSatus(SyncSatus syncSatus);

    SyncSatus getSyncSatus();
}
