package org.loopz.seaterrenos.manager;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class TerrainDAO {

    public final Map<Integer, Terrain> areas = new HashMap<>();


    public Terrain getTerrain(int id) {
        return this.areas.get(id);
    }

    public void addTerrain(int id, String owner, String name, String icon, List<String> desc, double minX, double maxX, double minZ, double maxZ) {
        Terrain terrain = new Terrain(id, owner.toUpperCase(), name, icon, desc, minX, maxX, minZ, maxZ);
        this.areas.put(id, terrain);
    }

    public void addTerrainFriend(Terrain terrain, String friend) {
        terrain.getFriends().put(friend, true);
    }

    public void removeTerrainFriend(Terrain terrain, String friend) {
        terrain.getFriends().remove(friend);
    }

    public String getTerrainFriends(Map<String, Boolean> friends) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Boolean> entry : friends.entrySet()) {
            sb.append(entry.getKey());
            sb.append(";");
        }
        return sb.toString();
    }

    public boolean isInTerrain(Location loc) {
        int playerX = loc.getBlockX();
        int playerZ = loc.getBlockZ();

        for (Terrain terrain : areas.values()) {
            if (playerX >= terrain.getMinX() && playerX <= terrain.getMaxX() && playerZ >= terrain.getMinZ() && playerZ <= terrain.getMaxZ()) {
                return true;
            }
        }

        return false;
    }

    public List<Terrain> getAllAreas() {
        return new ArrayList<>(areas.values());
    }

    public Terrain getTerrainByLocation(Location location) {
        double locationX = location.getX();
        double locationZ = location.getZ();

        for (Terrain terrain : areas.values()) {
            double areaX1 = terrain.getMinX();
            double areaX2 = terrain.getMaxX();
            double areaZ1 = terrain.getMinZ();
            double areaZ2 = terrain.getMaxZ();

            if (locationX >= areaX1 && locationX <= areaX2 && locationZ >= areaZ1 && locationZ <= areaZ2) {
                return terrain;
            }
        }

        return null;
    }


    public Terrain getTerrainByOwner(String ownerName) {
        for (Terrain terrain : areas.values()) {
            if (terrain.getOwner().equals(ownerName)) {
                return terrain;
            }
        }
        return null;
    }

    public boolean hasTerrain(Player p, double xMin, double xMax, double zMin, double zMax) {
        for (Terrain terrain : areas.values()) {
            if ((xMin < terrain.getMaxX()) && (xMax > terrain.getMinX()) && (zMin < terrain.getMaxZ()) && (zMax > terrain.getMinZ())) {
                return true;
            }
            if (!Objects.equals(terrain.getOwner().toUpperCase(), p.getName().toUpperCase())) {
                if (!(xMin - 1 > terrain.getMaxX()) && !(xMax + 1 < terrain.getMinX()) && !(zMin - 1 > terrain.getMaxZ()) && !(zMax + 1 < terrain.getMinZ())) {
                    return true;
                }
            } else {
                if (!(xMin + 1 > terrain.getMaxX()) && !(xMax - 1 < terrain.getMinX()) && !(zMin + 1 > terrain.getMaxZ()) && !(zMax - 1 < terrain.getMinZ())) {
                    return true;
                }
                if (!(xMin > terrain.getMaxX()) && !(xMax < terrain.getMinX()) && !(zMin > terrain.getMaxZ()) && !(zMax < terrain.getMinZ())) {
                    return true;
                }
            }
        }
        return false;
    }
}
