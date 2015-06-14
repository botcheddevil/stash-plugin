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

	    while(itr.hasNext()) {
	         RefChange rc = (RefChange)itr.next();
	         System.out.print(rc.getToHash() + " " + "commit");
	    }

        hookResponse.err().println("Just cant commit");
        return true;
    }
}
