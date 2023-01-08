package org.loopz.seaterrenos.manager;

public class Plants {

    private String owner;
    private int sugar_cane;
    private int cactus;
    private int nether_warts;
    private int melon;
    private int wheat;
    private int potato;
    private int carrot;

    public Plants(String owner, int sugar_cane, int cactus, int nether_warts, int melon, int wheat, int potato, int carrot) {
        this.owner = owner;
        this.sugar_cane = sugar_cane;
        this.cactus = cactus;
        this.nether_warts = nether_warts;
        this.melon = melon;
        this.wheat = wheat;
        this.potato = potato;
        this.carrot = carrot;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getSugarCane() {
        return sugar_cane;
    }

    public int getCactus() {
        return cactus;
    }

    public int getNetherWarts() {
        return nether_warts;
    }

    public int getMelon() {
        return melon;
    }
}
