package mr.minecraft15.blockphysics.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

import mr.minecraft15.blockphysics.Main;


public class PhysicsListener implements Listener {
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerLogin(final BlockPhysicsEvent e) {
	Block b = e.getBlock();
	if(b != null) {
	    Material material = b.getType();
	    if(material != null && Main.isPhysicsDisabled(material)) {
		e.setCancelled(true);
	    }
	}
    }
}
