package foo.hook;

import com.atlassian.stash.hook.*;
import com.atlassian.stash.hook.repository.*;
import com.atlassian.stash.repository.*;
import com.atlassian.stash.content.Changeset;
import com.atlassian.stash.content.ChangesetsBetweenRequest;
import com.atlassian.stash.util.Page;
import com.atlassian.stash.util.PageUtils;
import com.atlassian.stash.commit.CommitService;
import java.util.*;

public class MyPreReceiveRepositoryHook implements PreReceiveRepositoryHook
{

	 private CommitService commitService;
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

			final ChangesetsBetweenRequest request = new ChangesetsBetweenRequest.Builder(context.getRepository())
			        .exclude(rc.getFromHash())
			        .include(rc.getToHash())
			        .build();
			final Page<Changeset> cs = commitService.getChangesetsBetween(request, PageUtils.newRequest(0, 9999));
			for(Changeset changeset: cs.getValues())
			{
			    hookResponse.err().println(changeset.getMessage() + " " + " to commit");
			}

	        hookResponse.err().println(rc.getToHash() + " " + " to commit");
	        hookResponse.err().println(rc.getRefId() + " " + " Ref ID");
	        hookResponse.err().println(rc.getFromHash() + " " + " from commit");
	    }

        hookResponse.err().println("Just cant commit");
        return true;
    }
}
