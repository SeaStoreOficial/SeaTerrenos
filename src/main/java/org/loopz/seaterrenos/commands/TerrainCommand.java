package org.loopz.seaterrenos.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.loopz.seaterrenos.Main;
import org.loopz.seaterrenos.api.TerrainAPI;
import org.loopz.seaterrenos.fastinv.FastInv;

import java.util.ArrayList;
import java.util.List;

public class TerrainCommand implements CommandExecutor, Listener {

    public static ItemStack getItem(Material material, int amount, int data, String displayname, ArrayList<String> lore) {
        final ItemStack item = new ItemStack(material, amount, (byte) data);
        final ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayname);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public void inv(Player player) {
        FastInv inv = new FastInv(45, "Terrenos - Menu");
        int terrains = 0;
        for (org.loopz.seaterrenos.manager.Terrain terrain : TerrainAPI.getAllTerrains()) {
            if (terrain.getOwner().equals(player.getName().toUpperCase())) {
                terrains++;
            }
        }
        final ArrayList<String> loreList = new ArrayList<>();
        loreList.add("§7");
        loreList.add("§7Terrenos: §9" + terrains);
        loreList.add("§7Patrimonio: §2105K");
        final ArrayList<String> loreList2 = new ArrayList<>();
        loreList2.add("§7");
        loreList2.add("§7Clique para §e§ncriar§7 um novo terreno.");
        inv.setItem(4, getItem(Material.NAME_TAG, 1, 0, "§aInformações", loreList));
        inv.setItem(22, getItem(Material.GRASS, 1, 0, "§aCriar terreno §7(Clique direito)", loreList2));

        inv.addClickHandler(e -> {
                    if (e.getCurrentItem().getItemMeta() == null) {
                        return;
                    }
                    e.setCancelled(true);
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aCriar terreno §7(Clique direito)")) {
                        Main.terraincreator.add(player.getName());
                        player.sendMessage("§eDigite a area do terreno a ser comprado. \n§ePara §7§ncancelar§e digite §7'cancelar'.");
                        player.closeInventory();
                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aMeus terrenos §7(Clique direito)")) {
                        player.sendMessage("§eEm desenvolvimento...");
                        player.closeInventory();
                    }
                }
        );
        inv.open(player);
    }

    public void invOwner(Player player) {
        FastInv inv = new FastInv(45, "Meu terreno - Menu");
        final ArrayList<String> loreList = new ArrayList<>();
        loreList.add("§7");
        loreList.add("§7Area: §d3x3");
        loreList.add("§7Valor: §25K");
        final ArrayList<String> loreList2 = new ArrayList<>();
        loreList2.add("§7");
        loreList2.add("§7Clique para §e§nadicionar§7 um membro ao seu terreno.");
        final ArrayList<String> loreList3 = new ArrayList<>();
        loreList3.add("§7");
        loreList3.add("§7Clique para §e§nalterar§7 as opções do seu terreno.");
        inv.setItem(4, getItem(Material.NAME_TAG, 1, 0, "§aInformações", loreList));
        inv.setItem(21, getItem(Material.DOUBLE_PLANT, 1, 0, "§aAdicionar membro §7(Clique direito)", loreList2));
        inv.setItem(23, getItem(Material.REDSTONE_COMPARATOR, 1, 0, "§aOpções §7(Clique direito)", loreList3));
        inv.addClickHandler(e -> {
                    e.setCancelled(true);
                    if (e.getCurrentItem().getItemMeta() == null) {
                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aOpções §7(Clique direito)")) {
                        invOption(player);
                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAdicionar membro §7(Clique direito)")) {
                        Main.terrainaddmember.add(player.getName());
                        player.sendMessage("§eDigite o nome do membro a ser convidado. \n§ePara §7§ncancelar§e digite §7'cancelar'.");
                        player.closeInventory();
                    }
                }
        );
        inv.open(player);
    }

    public void invOption(Player player) {
        FastInv inv = new FastInv(45, "Meu terreno - Opções");
        final ArrayList<String> loreList = new ArrayList<>();
        loreList.add("§7");
        loreList.add("§7Altera as §e§npermissões§7 dos membros do terreno.");
        final ArrayList<String> loreList2 = new ArrayList<>();
        loreList2.add("§7");
        loreList2.add("§7Gerencia as §e§nplantações§7 dos seus terrenos.");
        final ArrayList<String> loreList3 = new ArrayList<>();
        loreList3.add("§7");
        loreList3.add("§7Clique para §e§nalterar§7 o design do seu terreno.");
        inv.setItem(21, getItem(Material.SKULL_ITEM, 1, 0, "§aPermissões terreno §7(Clique direito)", loreList));
        inv.setItem(22, getItem(Material.SUGAR_CANE, 1, 0, "§aPlantações §7(Clique direito)", loreList2));
        inv.setItem(23, getItem(Material.REDSTONE_COMPARATOR, 1, 0, "§aDesign §7(Clique direito)", loreList3));
        inv.addClickHandler(e -> {
                    e.setCancelled(true);
                    if (e.getCurrentItem().getItemMeta() == null) {
                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPlantações §7(Clique direito)")) {

                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPermissões terreno §7(Clique direito)")) {

                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aDesign §7(Clique direito)")) {
                    invDesign(player);

                    }
                }
        );
        inv.open(player);
    }


    public void invPermissions(Player player) {
        FastInv inv = new FastInv(45, "Meu terreno - Opções");
        final ArrayList<String> loreList = new ArrayList<>();
        loreList.add("§7");
        loreList.add("§7Clique para desativar essa §e§npermissões§7 dos membros.");
        final ArrayList<String> loreList2 = new ArrayList<>();
        loreList2.add("§7");
        loreList.add("§7Clique para ativar essa §e§npermissões§7 dos membros.");
        inv.setItem(21, getItem(Material.STAINED_GLASS_PANE, 1, 5, "§aColocar blocos §7(Ativado)", loreList));
       inv.addClickHandler(e -> {
                    e.setCancelled(true);
                    if (e.getCurrentItem().getItemMeta() == null) {
                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPlantações §7(Clique direito)")) {

                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPermissões terreno §7(Clique direito)")) {

                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aDesign §7(Clique direito)")) {
                        invDesign(player);

                    }
                }
        );
        inv.open(player);
    }

    public void invDesign(Player player) {
        FastInv inv = new FastInv(45, "Meu terreno - Design");
        final ArrayList<String> loreList = new ArrayList<>();
        loreList.add("§7");
        loreList.add("§7Altera o §e§nnome§7 do terreno.");
        final ArrayList<String> loreList2 = new ArrayList<>();
        loreList2.add("§7");
        loreList2.add("§7Altera o §e§nicone§7 do terreno.");
        final ArrayList<String> loreList3 = new ArrayList<>();
        loreList3.add("§7");
        loreList3.add("§7Altera a §e§ndescrição§7 do terreno.");
        inv.setItem(21, getItem(Material.NAME_TAG, 1, 0, "§aNome §7(Clique direito)", loreList));
        inv.setItem(22, getItem(Material.getMaterial(TerrainAPI.getTerrainByOwner(player.getName().toUpperCase()).getIcon()), 1, 0, "§aIcone §7(Clique direito)", loreList2));
        inv.setItem(23, getItem(Material.SIGN, 1, 0, "§aDescrição §7(Clique direito)", loreList3));
        inv.addClickHandler(e -> {
                    e.setCancelled(true);
                    if (e.getCurrentItem().getItemMeta() == null) {
                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aNome §7(Clique direito)")) {
                        Main.terrainsetname.add(player.getName());
                        player.sendMessage("§eDigite o novo nome do terreno. \n§ePara §7§ncancelar§e digite §7'cancelar'.");
                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aIcone §7(Clique direito)")) {
                        Main.terrainseticon.add(player.getName());
                        player.sendMessage("§eDigite o nome do novo icone do terreno. \n§ePara §7§ncancelar§e digite §7'cancelar'.");
                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aDescrição §7(Clique direito)")) {
                        Main.terrainsetdesc.add(player.getName());
                        player.sendMessage("§eDigite a nova descrição do terreno. \n§ePara §7§ncancelar§e digite §7'cancelar'.");
                    }
                }
        );
        inv.open(player);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cHey! Você não pode fazer isto.");
            return false;
        }
        if (cmd.getName().equalsIgnoreCase("terreno")) {
            Player p = (Player) sender;
            if (TerrainAPI.isInTerrain(p.getLocation())) {
                if (TerrainAPI.getTerrainByLocation(p.getLocation()).getOwner().equals(p.getName().toUpperCase())) {
                    invOwner(p);
                    return false;
                }
                if (TerrainAPI.getTerrainByLocation(p.getLocation()).getFriends().containsKey(p.getName())) {
                    //inv friend
                    return false;
                }
                //info terrain
            }
            inv(p);
            return false;
        }
        return false;
    }

    public void buyTerrain(Player p, int area) {
        double x = p.getLocation().getBlockX();
        double z = p.getLocation().getBlockZ();
        double xMin = x - area + 1;
        double xMax = x + area - 1;
        double zMin = z - area + 1;
        double zMax = z + area - 1;

        if (TerrainAPI.hasTerrain(p, xMin, xMax, zMin, zMax)) {
            p.sendMessage("§cSeu terreno está invadindo outro terreno.");
            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5f, 1f);
            return;
        }

        int id = TerrainAPI.getAllTerrains().size() + 1;
        List<String> desc = new ArrayList<>();
        desc.add(" ");
        desc.add(ChatColor.translateAlternateColorCodes('&', "&7Descrição do seu terreno"));
        TerrainAPI.addTerrain(id, p.getName().toUpperCase(), "§aTerreno " + id, "GRASS", desc, xMin, xMax, zMin, zMax);

        World world = p.getWorld();
        for (double x2 = xMin + 1; x2 <= xMax - 1; x2++) {
            world.getBlockAt((int) x2, (int) p.getLocation().getY(), (int) zMin).setType(Material.FENCE);
            world.getBlockAt((int) x2, (int) p.getLocation().getY(), (int) zMax).setType(Material.FENCE);
        }

        for (double z2 = zMin + 1; z2 <= zMax - 1; z2++) {
            world.getBlockAt((int) xMin, (int) p.getLocation().getY(), (int) z2).setType(Material.FENCE);
            world.getBlockAt((int) xMax, (int) p.getLocation().getY(), (int) z2).setType(Material.FENCE);
        }

        world.getBlockAt((int) xMin, (int) p.getLocation().getY(), (int) zMin).setType(Material.FENCE);
        world.getBlockAt((int) xMax, (int) p.getLocation().getY(), (int) zMin).setType(Material.FENCE);
        world.getBlockAt((int) xMin, (int) p.getLocation().getY(), (int) zMax).setType(Material.FENCE);
        world.getBlockAt((int) xMax, (int) p.getLocation().getY(), (int) zMax).setType(Material.FENCE);

        p.sendMessage("§eTerreno comprado com sucesso.");
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 5f, 1f);
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (Main.terraincreator.contains(p.getName())) {
            e.setCancelled(true);
            if (e.getMessage().equalsIgnoreCase("cancelar")) {
                Main.terraincreator.remove(p.getName());
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 5f, 1f);
                p.sendMessage("§cOperação cancelada com sucesso.");
                return;
            }
            int area;
            try {
                area = Integer.parseInt(e.getMessage());
            } catch (NumberFormatException exception) {
                p.sendMessage("§cUtilize somente numeros.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            if (area <= 1) {
                p.sendMessage("§cUtilize somente numeros positivos.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            if (area % 2 == 0) {
                p.sendMessage("§cUtilize apenas numeros impares.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            if (area > 39) {
                p.sendMessage("§cEste terreno é grande de mais, no momento estamos na versão beta e para evitar bugs, não é suportado terrenos maiores que 39 blocos quadrados.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            Bukkit.getScheduler().runTask(Main.getPlugin(Main.class), () -> buyTerrain(p, area));
            Main.terraincreator.remove(p.getName());
        }
    }

    @EventHandler
    public void friend(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (Main.terrainaddmember.contains(p.getName())) {
            e.setCancelled(true);
            if (e.getMessage().equalsIgnoreCase("cancelar")) {
                Main.terrainaddmember.remove(p.getName());
                p.sendMessage("§cOperação cancelada com sucesso.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            Player target = Bukkit.getPlayerExact(e.getMessage());
            if (target == null) {
                p.sendMessage("§cO jogador não existe ou não está online.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            if (target == p) {
                p.sendMessage("§cVocê não pode adicionar você mesmo.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            org.loopz.seaterrenos.manager.Terrain terrain = TerrainAPI.getTerrainByLocation(p.getLocation());
            if (terrain == null) {
                p.sendMessage("§cVocê precisa estar dentro de um terreno seu para adicionar um amigo.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            TerrainAPI.addTerrainFriend(terrain, target.getName());
            p.sendMessage("§eVocê adicionou o jogador §7§n" + target.getName() + "§e ao seu terreno. \n§7Cuidado, este jogador agora possuí as permissões que estão permitidas nas configurações do terreno. \n§7Nós não poderemos devolver itens, reconstruir construções ou qualquer coisa do genero, mesmo que o jogador seja punido nós não poderemos fazer nada a respeito do que ele causou, é de total responsabilidade sua.");
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 5f, 1f);
            Main.terrainaddmember.remove(p.getName());
        }
    }

    @EventHandler
    public void name(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (Main.terrainsetname.contains(p.getName())) {
            e.setCancelled(true);
            if (e.getMessage().equalsIgnoreCase("cancelar")) {
                Main.terrainsetname.remove(p.getName());
                p.sendMessage("§cOperação cancelada com sucesso.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            boolean isValid = e.getMessage().matches( "^[a-zA-Z0-9&]+$" );
            if (!isValid) {
                p.sendMessage("§cNome inválido, utilize somente letras e numeros.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            org.loopz.seaterrenos.manager.Terrain terrain = TerrainAPI.getTerrainByLocation(p.getLocation());
            if (terrain == null) {
                p.sendMessage("§cVocê precisa estar dentro de um terreno seu para alterar o nome.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            terrain.setName(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
            p.sendMessage("§eO nome do seu terreno foi alterado para §7§n" + terrain.getName() + "§e com sucesso.");
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 5f, 1f);
            Main.terrainsetname.remove(p.getName());
        }
    }

    @EventHandler
    public void icon(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (Main.terrainseticon.contains(p.getName())) {
            e.setCancelled(true);
            if (e.getMessage().equalsIgnoreCase("cancelar")) {
                Main.terrainseticon.remove(p.getName());
                p.sendMessage("§cOperação cancelada com sucesso.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            if (Material.getMaterial(e.getMessage().toUpperCase()) == null) {
                p.sendMessage("§cIcone inválido, utilize o nome de algum item, por exemplo: §7§nDIAMOND_SWORD");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            org.loopz.seaterrenos.manager.Terrain terrain = TerrainAPI.getTerrainByLocation(p.getLocation());
            if (terrain == null) {
                p.sendMessage("§cVocê precisa estar dentro de um terreno seu para alterar o icone.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            terrain.setIcon(e.getMessage());
            p.sendMessage("§eO icone do seu terreno foi alterado para §7§n" + terrain.getIcon().toUpperCase() + "§e com sucesso.");
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 5f, 1f);
            Main.terrainseticon.remove(p.getName());
        }
    }

    @EventHandler
    public void desc(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (Main.terrainsetdesc.contains(p.getName())) {
            e.setCancelled(true);
            if (e.getMessage().equalsIgnoreCase("cancelar")) {
                Main.terrainsetdesc.remove(p.getName());
                p.sendMessage("§cOperação cancelada com sucesso.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            boolean isValid = e.getMessage().matches( "^[a-zA-Z0-9&]+$" );
            if (!isValid) {
                p.sendMessage("§cDescrição inválida, utilize somente letras e numeros.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            org.loopz.seaterrenos.manager.Terrain terrain = TerrainAPI.getTerrainByLocation(p.getLocation());
            if (terrain == null) {
                p.sendMessage("§cVocê precisa estar dentro de um terreno seu para alterar a sua descrição.");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5F, 1F);
                return;
            }
            List<String> desc = new ArrayList<>();
            desc.add(" ");
            desc.add(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
            terrain.setDesc(desc);
            p.sendMessage("§eA descrição do seu terreno foi alterado para §7§n" + e.getMessage() + "§e com sucesso.");
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 5f, 1f);
            Main.terrainsetdesc.remove(p.getName());
        }
    }

}
