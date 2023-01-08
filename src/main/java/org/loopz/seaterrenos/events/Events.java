package org.loopz.seaterrenos.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.loopz.seaterrenos.api.TerrainAPI;
import org.loopz.seaterrenos.manager.Terrain;

public class Events implements Listener {

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        Player p = event.getPlayer();

        if (TerrainAPI.isInTerrain(event.getBlock().getLocation())) {
            Terrain terrain = TerrainAPI.getTerrain(event.getBlock().getLocation());
            if (!terrain.getOwner().equals(p.getName().toUpperCase()) && !terrain.getFriends().containsKey(p.getName())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void placeblock(BlockPlaceEvent event) {
        Player p = event.getPlayer();

        if (TerrainAPI.isInTerrain(event.getBlock().getLocation())) {
            Terrain terrain = TerrainAPI.getTerrain(event.getBlock().getLocation());
            if (!terrain.getOwner().equals(p.getName().toUpperCase()) && !terrain.getFriends().containsKey(p.getName())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void explode(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) {
            return;
        }
        if (TerrainAPI.isInTerrain(event.getClickedBlock().getLocation())) {
            Player p = event.getPlayer();
            Terrain terrain = TerrainAPI.getTerrain(event.getClickedBlock().getLocation());
            if (!terrain.getOwner().equals(p.getName().toUpperCase()) && !terrain.getFriends().containsKey(p.getName())) {
                event.setCancelled(true);
                return;
            }
        }
    }
}
