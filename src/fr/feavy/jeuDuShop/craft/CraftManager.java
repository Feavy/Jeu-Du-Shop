package fr.feavy.jeuDuShop.craft;

import com.sun.istack.internal.NotNull;
import fr.feavy.jeuDuShop.item.Item;
import fr.feavy.jeuDuShop.item.ItemType;
import fr.feavy.jeuDuShop.item.ItemSet;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class CraftManager {
    private final static Map<ItemType, Craft> CRAFTS = new EnumMap<>(ItemType.class);

    private static void registerCraft(@NotNull Craft craft) {
        CRAFTS.put(craft.getResultItem().getType(), craft);
    }

    public static Craft[] getRealisableCrafts(@NotNull ItemSet inventory) {
        return (Craft[]) CRAFTS.values().stream().filter(craft -> craft.isRealizable(inventory)).toArray();
    }

    public static boolean canCraft(@NotNull ItemType itemType, @NotNull ItemSet inventory) {
        return CRAFTS.get(itemType).isRealizable(inventory);
    }

    /**
     * Get an optional Craft which is present if it is realizable
     * @param itemType
     * @param inventory
     * @return
     */
    public static Optional<Craft> getRealizableCraft(@NotNull ItemType itemType, @NotNull ItemSet inventory) {
        Craft craft = CRAFTS.get(itemType);
        if(!craft.isRealizable(inventory))
            return Optional.empty();

        return Optional.ofNullable(craft);
    }

    static {
        registerCraft(new Craft(
                new Item[] {
                        new Item(ItemType.WOOD_LOG, 1)
                },
                new Item(ItemType.WOOD_PLANKS, 4))
        );
    }
}
