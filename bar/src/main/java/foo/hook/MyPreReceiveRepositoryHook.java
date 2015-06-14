package foo.hook;

import com.atlassian.stash.hook.*;
import com.atlassian.stash.hook.repository.*;
import com.atlassian.stash.repository.*;
import java.util.Collection;

public class MyPreReceiveRepositoryHook implements PreReceiveRepositoryHook
{
    /**
     * Disables deletion of branches
     */
    @Override
    public boolean onReceive(RepositoryHookContext context, Collection<RefChange> refChanges, HookResponse hookResponse)
    {
        hookResponse.err().println("Just cant commit");
        return false;
    }
}
