package org.loopz.seaterrenos.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.loopz.seaterrenos.Main;
import org.loopz.seaterrenos.manager.Terrain;

import java.util.List;
import java.util.Map;

public class TerrainAPI {

    private static Main plugin;

    public TerrainAPI(Main plugin) {
        TerrainAPI.plugin = plugin;
    }

    public static void addTerrain(int id, String owner, String name, String icon, List<String> desc, double minX, double maxX, double minZ, double maxZ) {
        plugin.terrainDAO.addTerrain(id, owner, name, icon, desc, minX, maxX, minZ, maxZ);
    }

    public static void removeTerrainFriend(Terrain terrain, String friend) {
        plugin.terrainDAO.removeTerrainFriend(terrain, friend);
    }

    public static void addTerrainFriend(Terrain terrain, String friend) {
        plugin.terrainDAO.addTerrainFriend(terrain, friend);
    }

    public static Terrain getTerrain(Location location) {
        return plugin.terrainDAO.getTerrainByLocation(location);
    }

    public static Terrain getTerrainById(int id) {
        return plugin.terrainDAO.getTerrain(id);
    }

    public static boolean hasTerrain(Player p, double xMin, double xMax, double zMin, double zMax) {
        return plugin.terrainDAO.hasTerrain(p, xMin, xMax, zMin, zMax);
    }

    public static String getTerrainFriends(Map<String, Boolean> friends) {
        return plugin.terrainDAO.getTerrainFriends(friends);
    }

    public static boolean isInTerrain(Location location) {
        return plugin.terrainDAO.isInTerrain(location);
    }

    public static Terrain getTerrainByLocation(Location location) {
        return plugin.terrainDAO.getTerrainByLocation(location);
    }

    public static List<Terrain> getAllTerrains() {
        return plugin.terrainDAO.getAllAreas();
    }

    public static Terrain getTerrainByOwner(String owner) { return plugin.terrainDAO.getTerrainByOwner(owner); }
}
