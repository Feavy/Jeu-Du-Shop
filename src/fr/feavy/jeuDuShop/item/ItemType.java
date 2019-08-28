package fr.feavy.jeuDuShop.item;

import fr.feavy.jeuDuShop.craft.Craft;

import java.util.Optional;

public enum ItemType {
    WOOD_LOG("Bûche de bois", "Bûches de bois", 0.3f),
    COMMON_ITEM("", "", 0.25f),
    STONE("Pirre", "Pierres", 0.23f),
    COAL("Charbon","Charbons", 0.13f),
    IRON("Lingot de fer", "Lingots de fer", 0.5f),
    GOLD("Lingot d'or", "Lingots d'or", 0.3f),
    DIAMOND("Diamant", "Diamants", 0.1f),

    WOOD_PLANKS("Planche de bois", "Planches de bois", .25f/1f, 20);

    private final String name, pluralName;
    private final float rarity;
    private final int buyPrice, sellPrice;

    ItemType(String name, String pluralName, float rarity) {
        this(name, pluralName, rarity, (int)(6 * 1/rarity));
    }

    ItemType(String name, String pluralName, float rarity, int sellPrice) {
        this.name = name;
        this.pluralName = pluralName;
        this.rarity = rarity;
        this.sellPrice = sellPrice;
        this.buyPrice = (int)(getSellPrice() * 1.5f);
    }

    public String getName() {
        return name;
    }

    public String getPluralName() {
        return pluralName;
    }

    public float getRarity() {
        return rarity;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }
}