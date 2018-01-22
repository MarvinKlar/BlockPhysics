package mr.minecraft15.blockphysics.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import mr.minecraft15.blockphysics.Main;

public class PhysicsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender s, final Command c, final String l, final String[] a) {
	if (s.hasPermission("BlockPhysics.command")) {
	    if(s instanceof Player) {
		Player p = (Player) s;
		
		ItemStack is = p.getItemInHand();
		if(is == null || is.getType() == Material.AIR) {
		    s.sendMessage(Main.getMessageManager().getMessage("PleaseTakeAnItem"));
		} else {
		    Material material = is.getType();
		    if(Main.isPhysicsDisabled(material)) {
			Main.setPhysicsEnabled(material);
			s.sendMessage(Main.getMessageManager().getMessage("PhysicsEnabled", "item", material.name()));
		    } else {
			Main.setPhysicsDisabled(material);
			s.sendMessage(Main.getMessageManager().getMessage("PhysicsDisabled", "item", material.name()));
		    }
		}
	    } else {
		s.sendMessage(Main.getMessageManager().getMessage("OnlyForPlayers"));
	    }
	} else {
	    s.sendMessage(Main.getMessageManager().getMessage("NoPermission"));
	}
	return true;
    }

}
