package org.loopz.seaterrenos.sqlite;

import org.bukkit.Bukkit;
import org.loopz.seaterrenos.Main;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    private final Main plugin;
    public java.sql.Connection con;

    public Connection(Main plugin) {
        this.plugin = plugin;
    }

    public boolean isConnected() {
        return this.con != null;
    }

    public void openConnectionSQLITE() {
        try {
            File file = new File(plugin.getDataFolder(), "terrenos.db");
            Class.forName("org.sqlite.JDBC");
            this.con = DriverManager.getConnection("jdbc:sqlite:" + file);
            Bukkit.getConsoleSender().sendMessage("§a[SeaTerrenos] - Ativado com sucesso.");
            plugin.db.createTable();
            plugin.db.createTablePlants();
        } catch (Exception var2) {
            Bukkit.getConsoleSender().sendMessage("§c[SeaTerrenos] - Não foi possivel ligar o plugin.");
            var2.printStackTrace();
            plugin.getPluginLoader().disablePlugin(Main.getPlugin(Main.class));
        }

    }

    public void disconnect() {
        if (this.isConnected()) {
            try {
                this.con.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }

    }

    public java.sql.Connection getConnection() {
        return this.con;
    }


}
