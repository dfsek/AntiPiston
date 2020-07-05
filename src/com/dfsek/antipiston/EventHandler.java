package com.dfsek.antipiston;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

import java.util.List;

public class EventHandler implements Listener {
    @org.bukkit.event.EventHandler(priority = EventPriority.HIGHEST)
    public void pistonOverride(BlockPistonExtendEvent e) {
        e.setCancelled(handlePiston(e.getBlocks(), e.getBlock().getLocation()));
    }

    @org.bukkit.event.EventHandler(priority = EventPriority.HIGHEST)
    public void pistonOverride(BlockPistonRetractEvent e) {
        e.setCancelled(handlePiston(e.getBlocks(), e.getBlock().getLocation()));
    }
    public boolean handlePiston(List<Block> blocks, Location l) {
        for(Block b : blocks) {
            if(ConfigUtil.masterListIsWhitelist != ConfigUtil.masterMaterialList.contains(b.getType())) {
                if(ConfigUtil.debug) Main.getInstance().getLogger().info("Prevented piston move.");
                return true;
            } else if(ConfigUtil.debug) Main.getInstance().getLogger().info("Allowed piston move.");
        }
        return false;
    }
}
