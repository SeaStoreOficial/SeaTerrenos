package org.loopz.seaterrenos;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.loopz.seaterrenos.api.TerrainAPI;
import org.loopz.seaterrenos.commands.TerrainCommand;
import org.loopz.seaterrenos.events.Events;
import org.loopz.seaterrenos.fastinv.FastInvManager;
import org.loopz.seaterrenos.manager.TerrainDAO;
import org.loopz.seaterrenos.sqlite.Connection;
import org.loopz.seaterrenos.sqlite.DataBase;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    public static ArrayList<String> terraincreator = new ArrayList<String>();
    public static ArrayList<String> terrainaddmember = new ArrayList<String>();
    public static ArrayList<String> terrainsetname = new ArrayList<String>();
    public static ArrayList<String> terrainseticon = new ArrayList<String>();
    public static ArrayList<String> terrainsetdesc = new ArrayList<String>();
    public TerrainDAO terrainDAO;
    public Connection connection;
    public DataBase db;

    @Override
    public void onLoad() {
        terrainDAO = new TerrainDAO();
        TerrainAPI ignored = new TerrainAPI(Main.getPlugin(Main.class));
    }

    @Override
    public void onEnable() {
        this.connection = new Connection(this);
        this.db = new DataBase(this);
        FastInvManager.register(this);
        saveDefaultConfig();
        this.connection.openConnectionSQLITE();
        getCommand("terreno").setExecutor(new TerrainCommand());
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginManager().registerEvents(new TerrainCommand(), this);
        db.loadAreas();

    }

    @Override
    public void onDisable() {
        db.saveAreas();
    }
}
