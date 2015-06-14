package foo.hook;

import com.atlassian.stash.hook.*;
import com.atlassian.stash.hook.repository.*;
import com.atlassian.stash.repository.*;
import java.util.*;

public class MyPreReceiveRepositoryHook implements PreReceiveRepositoryHook
{
    /**
     * Disables deletion of branches
     */
    @Override
    public boolean onReceive(RepositoryHookContext context, Collection<RefChange> refChanges, HookResponse hookResponse)
    {
        Iterator itr =  refChanges.iterator();

        hookResponse.err().println("Size of collection " + refChanges.size());

	    while(itr.hasNext()) {
	        RefChange rc = (RefChange)itr.next();
	        hookResponse.err().println(rc.getToHash() + " " + "commit");
	    }

        hookResponse.err().println("Just cant commit");
        return true;
    }
}
