package mr.minecraft15.blockphysics;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import mr.minecraft15.blockphysics.commands.PhysicsCommand;
import mr.minecraft15.blockphysics.listener.PhysicsListener;
import mr.minecraft15.blockphysics.manager.MessageManager;

public class Main extends JavaPlugin {

    private static Main instance;
    private static MessageManager messageManager;
    private static ArrayList<String> physicsDisabled = new ArrayList<String>();

    @Override
    public void onEnable() {
	instance = this;

	final FileConfiguration cfg = getConfig();
	cfg.options().copyDefaults(true);

	cfg.addDefault("BlockPhysics.Active", true);
	cfg.addDefault("BlockPhysics.DisabledMaterials",
		Arrays.asList(new String[] { "LADDER", "VINE", "WATER_LILY", "SNOW", "TORCH", "CARPET", "SIGN",
			"PAINTING", "ITEM_FRAME", "STONE_BUTTON", "STONE_PLATE", "WOOD_PLATE", "LEVER",
			"REDSTONE_TORCH_ON", "TRAP_DOOR", "TRIPWIRE_HOOK", "WOOD_BUTTON", "GOLD_PLATE", "IRON_PLATE",
			"IRON_TRAPDOOR", "REDSTONE", "DIODE", "REDSTONE_COMPARATOR", "WOOD_DOOR", "IRON_DOOR",
			"SPRUCE_DOOR_ITEM", "BIRCH_DOOR_ITEM", "JUNGLE_DOOR_ITEM", "ACACIA_DOOR_ITEM",
			"DARK_OAK_DOOR_ITEM", "ACTIVATOR_RAIL", "RAILS", "DETECTOR_RAIL", "POWERED_RAIL" }));
	cfg.addDefault("Prefix", "&8[BlockPhysics&8]&7");

	cfg.addDefault("Messages.OnlyForPlayers", "%prefix% This command is only for players!");
	cfg.addDefault("Messages.PleaseTakeAnItem",
		"%prefix% Please take an item in your hand, which you want to configure!");
	cfg.addDefault("Messages.NoPermission", "%prefix% You don't have enough permissions to do this!");
	cfg.addDefault("Messages.PhysicsDisabled", "%prefix% Disabled the physics for the item '%item%'!");
	cfg.addDefault("Messages.PhysicsEnabled", "%prefix% Enabled the physics for the item '%item%'!");
	saveConfig();

	physicsDisabled = (ArrayList<String>) cfg.getStringList("BlockPhysics.DisabledMaterials");

	setMessageManager(new MessageManager(cfg, cfg.getString("Prefix")));

	getCommand("BlockPhysics").setExecutor(new PhysicsCommand());

	Bukkit.getPluginManager().registerEvents(new PhysicsListener(), this);

    }

    public static Main getInstance() {
	return instance;
    }

    public static MessageManager getMessageManager() {
	return messageManager;
    }

    public void setMessageManager(final MessageManager messageManager) {
	Main.messageManager = messageManager;
    }

    public static boolean isPhysicsDisabled(Material material) {
	return physicsDisabled.contains(material.name());
    }

    public static void setPhysicsDisabled(Material material) {
	physicsDisabled.add(material.name());
	getInstance().getConfig().set("BlockPhysics.DisabledMaterials", physicsDisabled);
	getInstance().saveConfig();
    }

    public static void setPhysicsEnabled(Material material) {
	physicsDisabled.remove(material.name());
	getInstance().getConfig().set("BlockPhysics.DisabledMaterials", physicsDisabled);
	getInstance().saveConfig();
    }
}
