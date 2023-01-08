package org.loopz.seaterrenos.inventorypage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.loopz.seaterrenos.api.TerrainAPI;
import org.loopz.seaterrenos.manager.Terrain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InventoryPage {

    public void showPlayerTerrains(Player player, int page) {
        int itemsPerPage = 45; // Número de itens por página

        Terrain terrain = TerrainAPI.getTerrainByOwner(player.getName().toUpperCase());
        Collection<Terrain> temp = new ArrayList<>();
        temp.add(terrain);
        ArrayList<Terrain> terrains = new ArrayList<>(temp);

        int totalPages = (int) Math.ceil((double) terrains.size() / itemsPerPage);

        if (page < 1) {
            page = 1;
        }
        if (page > totalPages) {
            page = totalPages;
        }

        Inventory inventory = Bukkit.createInventory(null, 54, "Jogadores Online - Página " + page + " de " + totalPages);

        for (int i = (page - 1) * itemsPerPage; i < page * itemsPerPage; i++) {
            if (i >= terrains.size()) {
                break;
            }
            Terrain terreno = terrains.get(i);
            ItemStack item = new ItemStack(Material.GRASS);
            ItemMeta meta = (ItemMeta) item.getItemMeta();
            meta.setDisplayName(a.getOwner());
            item.setItemMeta(meta);
            inventory.addItem(item);
        }

        if (page > 1) {
            ItemStack previousButton = new ItemStack(Material.ARROW);
            ItemMeta previousMeta = previousButton.getItemMeta();
            previousMeta.setDisplayName(ChatColor.GREEN + "Página Anterior");
            previousButton.setItemMeta(previousMeta);
            inventory.setItem(45, previousButton);
        }
        if (page < totalPages) {
            ItemStack nextButton = new ItemStack(Material.ARROW);
            ItemMeta nextMeta = nextButton.getItemMeta();
            nextMeta.setDisplayName(ChatColor.GREEN + "Próxima Página");
            nextButton.setItemMeta(nextMeta);
            inventory.setItem(53, nextButton);
        }

        player.openInventory(inventory);
    }
    
}
