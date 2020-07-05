package com.dfsek.antipiston;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;
    @Override
    public void onEnable() {
        instance = this;
        Metrics metrics = new Metrics(this, 8097);
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new EventHandler(), this);
        ConfigUtil.load(this, this.getLogger());

    }
    public static Main getInstance() {
        return instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            ConfigUtil.load(this, this.getLogger());
            sender.sendMessage("Config file reloaded.");
            return true;
        }
        return false;
    }
}
