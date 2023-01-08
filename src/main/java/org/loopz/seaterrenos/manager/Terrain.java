package org.loopz.seaterrenos.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Terrain {

    private int id;
    private String owner;
    private String name;
    private String icon;
    private List<String> desc;
    private double minX;
    private double maxX;
    private double minZ;
    private double maxZ;

    private Permissions permissions;

    private Map<String, Boolean> friends;

    public Terrain(int id, String owner, String name, String icon, List<String> desc, double minX, double maxX, double minZ, double maxZ) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.icon = icon;
        this.desc = desc;
        this.minX = minX;
        this.maxX = maxX;
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.friends = new HashMap<>();
        this.permissions = new Permissions();
    }

    public Map<String, Boolean> getFriends() {
        return friends;
    }

    public void setFriends(Map<String, Boolean> friends) {
        this.friends = friends;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String nam) {
        this.name = name;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getDesc() {
        return this.desc;
    }

    public void setDesc(List<String> desc) {
        this.desc = desc;
    }

    public double getMinX() {
        return this.minX;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public double getMaxX() {
        return this.maxX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public double getMinZ() {
        return this.minZ;
    }

    public void setMinZ(double minZ) {
        this.minZ = minZ;
    }

    public double getMaxZ() {
        return this.maxZ;
    }

    public void setMaxZ(double maxZ) {
        this.maxZ = maxZ;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

}

