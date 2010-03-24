package rescuecore2.standard.kernel;

import kernel.AbstractCommandFilter;
import kernel.KernelState;

import rescuecore2.messages.Command;
import rescuecore2.worldmodel.Entity;
import rescuecore2.worldmodel.EntityID;
import rescuecore2.log.Logger;
import rescuecore2.standard.entities.Human;
import rescuecore2.standard.entities.StandardEntityConstants;

/**
   A CommandFilter that discards commands from buried agents.
 */
public class BuriedAgentsCommandFilter extends AbstractCommandFilter {
    @Override
    protected boolean allowed(Command c, KernelState state) {
        EntityID id = c.getAgentID();
        Entity e = state.getWorldModel().getEntity(id);
        if (e instanceof Human) {
            Human h = (Human)e;
            if (h.isBuriednessDefined() && h.getBuriedness() > 0) {
                Logger.info("Ignoring command from buried agent " + h);
                return false;
            }
        }
        return true;
    }
}
