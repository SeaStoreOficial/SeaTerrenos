package org.loopz.seaterrenos.sqlite;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.loopz.seaterrenos.Main;
import org.loopz.seaterrenos.api.TerrainAPI;
import org.loopz.seaterrenos.manager.Terrain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {

    private final Main plugin;

    public DataBase(Main plugin) {
        this.plugin = plugin;
    }

    public void createTable() {
        PreparedStatement stm = null;

        try {
            stm = this.plugin.connection.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS SeaTerrenos (id INTEGER PRIMARY KEY, owner varChar(16) NOT NULL, name varChar(16) NOT NULL, icon varChar(20) NOT NULL, desc TEXT NOT NULL, x_min INTEGER NOT NULL, x_max INTEGER NOT NULL, z_min INTEGER NOT NULL, z_max INTEGER NOT NULL, friends TEXT, placeb BOOLEAN, breakb BOOLEAN, breaks BOOLEAN, interact BOOLEAN, openc BOOLEAN, killmobs BOOLEAN, interactd BOOLEAN, adm BOOLEAN);");
            stm.executeUpdate();
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            try {
                assert stm != null;
                stm.close();
            } catch (SQLException var10) {
                var10.printStackTrace();
            }

        }
    }

    public void createTablePlants() {
        PreparedStatement stm = null;

        try {
            stm = this.plugin.connection.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS SeaTerrenosPlants (owner TEXT PRIMARY KEY, sugar_cane INTEGER, cactus INTEGER, nether_warts INTEGER, melon INTEGER, wheat INTEGER, potato INTEGER, carrot INTEGER);");
            stm.executeUpdate();
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            try {
                assert stm != null;
                stm.close();
            } catch (SQLException var10) {
                var10.printStackTrace();
            }

        }
    }

    public void saveAreas() {
        PreparedStatement stmt = null;
        try {
            stmt = this.plugin.connection.getConnection().prepareStatement("INSERT OR REPLACE INTO SeaTerrenos (id, owner, name, icon, desc, x_min, z_min, x_max, z_max, friends, placeb, breakb, breaks, interact, openc, killmobs, interactd, adm) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            for (Terrain terrain : TerrainAPI.getAllTerrains()) {
                String desc = String.join( ",", terrain.getDesc());
                stmt.setInt(1, terrain.getId());
                stmt.setString(2, terrain.getOwner().toUpperCase());
                stmt.setString(3, terrain.getName());
                stmt.setString(4, terrain.getIcon());
                stmt.setString(5, desc);
                stmt.setDouble(6, terrain.getMinX());
                stmt.setDouble(7, terrain.getMinZ());
                stmt.setDouble(8, terrain.getMaxX());
                stmt.setDouble(9, terrain.getMaxZ());
                stmt.setString(10, TerrainAPI.getTerrainFriends(terrain.getFriends()));
                stmt.setBoolean(11, terrain.getPermissions().isPlaceBlocks());
                stmt.setBoolean(12, terrain.getPermissions().isBreakBlocks());
                stmt.setBoolean(13, terrain.getPermissions().isBreakSpawners());
                stmt.setBoolean(14, terrain.getPermissions().isInteract());
                stmt.setBoolean(15, terrain.getPermissions().isOpenChest());
                stmt.setBoolean(16, terrain.getPermissions().isKillMobs());
                stmt.setBoolean(17, terrain.getPermissions().isInteractDoors());
                stmt.setBoolean(18, terrain.getPermissions().isAdministrator());
                stmt.executeUpdate();
            }
        } catch (Exception var) {
            var.printStackTrace();
        } finally {
            try {
                assert stmt != null;
                stmt.close();
            } catch (SQLException var) {
                var.printStackTrace();
            }
        }
    }

    public void loadAreas() {
        PreparedStatement stm = null;
        try {
            stm = this.plugin.connection.getConnection().prepareStatement("SELECT * FROM SeaTerrenos");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String owner = rs.getString("owner");
                String name = rs.getString("name");
                String icon = rs.getString("icon");
                String desc = rs.getString("desc");
                double minX = rs.getDouble("x_min");
                double minZ = rs.getDouble("z_min");
                double maxX = rs.getDouble("x_max");
                double maxZ = rs.getDouble("z_max");
                boolean placeb = rs.getBoolean("placeb");
                boolean breakb = rs.getBoolean("breakb");
                boolean breaks = rs.getBoolean("breaks");
                boolean interact = rs.getBoolean("interact");
                boolean openc = rs.getBoolean("openc");
                boolean killmobs = rs.getBoolean("killmobs");
                boolean interactd = rs.getBoolean("interactd");
                boolean adm = rs.getBoolean("adm");
                String[] elementos = desc.split(",");
                List<String> desclist = new ArrayList<>(Arrays.asList(elementos));
                TerrainAPI.addTerrain(id, owner.toUpperCase(), name, icon, desclist, minX, maxX, minZ, maxZ);
                TerrainAPI.getTerrainById(id).getPermissions().setPlaceBlocks(placeb);
                TerrainAPI.getTerrainById(id).getPermissions().setBreakBlocks(breakb);
                TerrainAPI.getTerrainById(id).getPermissions().setBreakSpawners(breaks);
                TerrainAPI.getTerrainById(id).getPermissions().setInteract(interact);
                TerrainAPI.getTerrainById(id).getPermissions().setOpenChest(openc);
                TerrainAPI.getTerrainById(id).getPermissions().setKillMobs(killmobs);
                TerrainAPI.getTerrainById(id).getPermissions().setInteractDoors(interactd);
                TerrainAPI.getTerrainById(id).getPermissions().setAdministrator(adm);
                String[] friends = rs.getString("friends").split(";");
                for (String friend : friends) {
                    TerrainAPI.getTerrainById(id).getFriends().put(friend, true);
                }
            }
        } catch (Exception var) {
            var.printStackTrace();
        } finally {
            try {
                assert stm != null;
                stm.close();
            } catch (SQLException var) {
                var.printStackTrace();
            }
        }
    }


    /*
    public boolean isInTerrain(Location loc) {
        PreparedStatement stm = null;
        try {
            stm = this.plugin.connection.getConnection().prepareStatement("SELECT * FROM SeaTerrenos WHERE x_min <= ? AND ? <= x_max AND z_min <= ? AND ? <= z_max");
            stm.setInt(1, loc.getBlockX());
            stm.setInt(2, loc.getBlockX());
            stm.setInt(3, loc.getBlockZ());
            stm.setInt(4, loc.getBlockZ());
            ResultSet rs = stm.executeQuery();
            return rs.next();
        } catch (Exception var) {
            var.printStackTrace();
        } finally {
            try {
                assert stm != null;
                stm.close();
            } catch (SQLException var) {
                var.printStackTrace();
            }
        }
        return false;
    }
*/
    /*
    public boolean isOwner(Player p) {
        PreparedStatement stm = null;
        try {
            stm = this.plugin.connection.getConnection().prepareStatement("SELECT * FROM SeaTerrenos WHERE owner = ? AND x_min <= ? AND x_max >= ? AND z_min <= ? AND z_max >= ?");
            stm.setString(1, p.getName().toUpperCase());
            stm.setInt(2, p.getLocation().getBlockX());
            stm.setInt(3, p.getLocation().getBlockX());
            stm.setInt(4, p.getLocation().getBlockZ());
            stm.setInt(5, p.getLocation().getBlockZ());
            ResultSet rs = stm.executeQuery();
            return rs.next();
        } catch (Exception var) {
            var.printStackTrace();
        } finally {
            try {
                assert stm != null;
                stm.close();
            } catch (SQLException var) {
                var.printStackTrace();
            }
        }
        return false;
    }
 */
    /*
    public void createTerrain(String Owner, World world, double xmin, double xmax, double zmin, double zmax) {
        PreparedStatement stm = null;

        try {
            stm = this.plugin.connection.getConnection().prepareStatement("INSERT INTO SeaTerrenos (owner, x_min, x_max, z_min, z_max) VALUES (?,?,?,?,?)");
            stm.setString(1, Owner.toUpperCase());
            stm.setDouble(2, xmin);
            stm.setDouble(3, xmax);
            stm.setDouble(4, zmin);
            stm.setDouble(5, zmax);
            stm.executeUpdate();
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            try {
                assert stm != null;
                stm.close();
            } catch (SQLException var13) {
                var13.printStackTrace();
            }
        }
    }

    */
    /*
    public boolean hasTerrain(double xMax, double xMin, double zMax, double zMin) {
        PreparedStatement stm = null;
        try {
            stm = this.plugin.connection.getConnection().prepareStatement("SELECT * FROM SeaTerrenos WHERE x_min <= " + xMax + " AND x_max >= " + xMin + " AND z_min <= " + zMax + " AND z_max >= " + zMin);
            ResultSet rs = stm.executeQuery();
            return rs.next();
        } catch (Exception var) {
            var.printStackTrace();
        } finally {
            try {
                assert stm != null;
                stm.close();
            } catch (SQLException var) {
                var.printStackTrace();
            }
        }
        return false;
    }
    */
}
