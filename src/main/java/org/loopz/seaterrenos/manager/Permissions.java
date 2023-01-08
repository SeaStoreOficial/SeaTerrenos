package org.loopz.seaterrenos.manager;

public class Permissions {

    private boolean placeBlocks;
    private boolean breakBlocks;
    private boolean breakSpawners;
    private boolean interact;
    private boolean openChest;
    private boolean killMobs;
    private boolean interactDoors;
    private boolean administrator;

    public Permissions() {
        this.placeBlocks = false;
        this.breakBlocks = false;
        this.breakSpawners = false;
        this.interact = false;
        this.openChest = false;
        this.killMobs = false;
        this.interactDoors = false;
        this.administrator = false;
    }

    public boolean isPlaceBlocks() {
        return placeBlocks;
    }

    public boolean isBreakBlocks() {
        return breakBlocks;
    }

    public boolean isBreakSpawners() {
        return breakSpawners;
    }

    public boolean isInteract() {
        return interact;
    }

    public boolean isOpenChest() {
        return openChest;
    }

    public boolean isKillMobs() {
        return killMobs;
    }

    public boolean isInteractDoors() {
        return interactDoors;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setPlaceBlocks(boolean placeBlocks) {
        this.placeBlocks = placeBlocks;
    }

    public void setBreakBlocks(boolean breakBlocks) {
        this.breakBlocks = breakBlocks;
    }

    public void setBreakSpawners(boolean breakSpawners) {
        this.breakSpawners = breakSpawners;
    }

    public void setInteract(boolean interact) {
        this.interact = interact;
    }

    public void setOpenChest(boolean openChest) {
        this.openChest = openChest;
    }

    public void setKillMobs(boolean killMobs) {
        this.killMobs = killMobs;
    }

    public void setInteractDoors(boolean interactDoors) {
        this.interactDoors = interactDoors;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
}
