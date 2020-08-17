package evgenyt.nomansskyships.data;

import java.util.Objects;

public class NMSShip {

    private final String name;
    private String imageUrl = "";
    private String galaxy = "";
    private String region = "";
    private String star = "";
    private String coordinates = "";
    private String inventory = "";
    private String cost = "";
    private String damage = "";
    private String shield = "";
    private String warp = "";
    private String platform = "";

    public NMSShip(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NMSShip nmsShip = (NMSShip) o;
        return name.equals(nmsShip.name);
    }

    public void checkData() {
        if (coordinates.contains("a href") || coordinates.contains("td"))
            coordinates = "?";
        if (damage.contains("a href") || damage.contains("td"))
            damage = "?";
        if (platform.contains("a href") || platform.contains("td"))
            platform = "?";
        if (region.contains("index") || region.contains("td"))
            region = "?";
        if (star.contains("index") || star.contains("td"))
            star = "?";
        if (cost.contains("lt"))
            cost = "?";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getWarp() {
        return warp;
    }

    public void setWarp(String warp) {
        this.warp = warp;
    }

    public String getShield() {
        return shield;
    }

    public void setShield(String shield) {
        this.shield = shield;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(String galaxy) {
        this.galaxy = galaxy;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
