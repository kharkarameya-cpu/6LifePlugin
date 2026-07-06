package org.succeptivitydev.xlifesmp.CustomStuff.Items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.succeptivitydev.xlifesmp.ThirdLifePlugin;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.NamedTextColor;

public class Items {

	public static ItemStack getReviveItem(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;

		meta.displayName(Component.text("Revive Token").color(TextColor.color(0x00FFFF))); // Cyan

		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Use to revive an eliminated player").color(NamedTextColor.GRAY));
		lore.add(Component.text("Right-click a kicked player to revive them").color(NamedTextColor.GRAY));
		meta.lore(lore);

		NamespacedKey key = new NamespacedKey(plugin, "revive_item");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);

		item.setItemMeta(meta);
		return item;
	}

	public static boolean isReviveItem(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "revive_item");
		Byte v = meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE);
		return v != null && v == (byte)1;
	}

	public static ItemStack getBloodLustSword(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;

		meta.displayName(Component.text("Blood Lust Sword").color(TextColor.color(0xFF0000))); // Red

		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Only for the bloodthirsty.").color(NamedTextColor.GRAY));
		lore.add(Component.text("Grants strength in combat.").color(NamedTextColor.GRAY));
		meta.lore(lore);

		NamespacedKey key = new NamespacedKey(plugin, "blood_lust_sword");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);

		item.setItemMeta(meta);
		return item;
	}

	public static boolean isBloodLustSword(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "blood_lust_sword");
		Byte v = meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE);
		return v != null && v == (byte)1;
	}

	public static ItemStack getLifeOrb(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.EMERALD);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;

		meta.displayName(Component.text("Life Orb").color(TextColor.color(0x00FF00))); // Green

		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("A symbol of life and vitality.").color(NamedTextColor.GRAY));
		lore.add(Component.text("Grants regeneration.").color(NamedTextColor.GRAY));
		meta.lore(lore);

		NamespacedKey key = new NamespacedKey(plugin, "life_orb");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);

		item.setItemMeta(meta);
		return item;
	}

	public static boolean isLifeOrb(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "life_orb");
		Byte v = meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE);
		return v != null && v == (byte)1;
	}

	// ===== GREEN ITEMS =====

	public static ItemStack getBackpack(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.CHEST);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Backpack").color(TextColor.color(0x00FF00)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("+9 inventory slots").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "backpack");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isBackpack(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "backpack");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	public static ItemStack getLumberAxe(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Lumber Axe").color(TextColor.color(0x00FF00)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Chops entire trees").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "lumber_axe");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isLumberAxe(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "lumber_axe");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	public static ItemStack getFarmerCharm(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.GOLDEN_HOE);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Farmer's Charm").color(TextColor.color(0x00FF00)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Crops grow faster").color(NamedTextColor.GRAY));
		lore.add(Component.text("Auto-replants").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "farmer_charm");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isFarmerCharm(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "farmer_charm");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	public static ItemStack getWindBoots(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Wind Boots").color(TextColor.color(0x00FF00)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Fall damage reduction").color(NamedTextColor.GRAY));
		lore.add(Component.text("Small speed boost").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "wind_boots");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isWindBoots(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "wind_boots");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	public static ItemStack getEmergencyTotem(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Emergency Totem").color(TextColor.color(0x00FF00)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Once per hour:").color(NamedTextColor.GRAY));
		lore.add(Component.text("Prevents death, leaves you at 1❤").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "emergency_totem");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isEmergencyTotem(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "emergency_totem");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	// ===== YELLOW ITEMS =====

	public static ItemStack getTrackingCompass(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.COMPASS);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Tracking Compass").color(TextColor.color(0xFFFF00)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Tracks nearest GREEN within 500 blocks").color(NamedTextColor.GRAY));
		lore.add(Component.text("Cooldown: 30 seconds").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "tracking_compass");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isTrackingCompass(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "tracking_compass");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	public static ItemStack getSmokeBomb(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.GUNPOWDER);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Smoke Bomb").color(TextColor.color(0xFFFF00)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Throw for blindness + darkness").color(NamedTextColor.GRAY));
		lore.add(Component.text("Great for escape").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "smoke_bomb");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isSmokeBomb(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "smoke_bomb");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	public static ItemStack getSnareTrap(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Snare Trap").color(TextColor.color(0xFFFF00)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Hidden trap").color(NamedTextColor.GRAY));
		lore.add(Component.text("Applies slowness + weakness").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "snare_trap");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isSnareTrap(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "snare_trap");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	public static ItemStack getHunterCloak(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Hunter Cloak").color(TextColor.color(0xFFFF00)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Mobs ignore you").color(NamedTextColor.GRAY));
		lore.add(Component.text("Great for stealth").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "hunter_cloak");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isHunterCloak(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "hunter_cloak");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	public static ItemStack getScoutBow(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.BOW);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Scout Bow").color(TextColor.color(0xFFFF00)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Arrows apply glowing + weakness").color(NamedTextColor.GRAY));
		lore.add(Component.text("No extra damage").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "scout_bow");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isScoutBow(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "scout_bow");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	// ===== RED ITEMS =====

	public static ItemStack getBloodSword(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Blood Sword").color(TextColor.color(0xFF0000)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("On player kill: heal 3❤").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "blood_sword");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isBloodSword(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "blood_sword");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	public static ItemStack getFrenzyPendant(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Frenzy Pendant").color(TextColor.color(0xFF0000)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Below 30% HP: gain Speed I").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "frenzy_pendant");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isFrenzyPendant(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "frenzy_pendant");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	public static ItemStack getLastStandShield(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.SHIELD);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Last Stand Shield").color(TextColor.color(0xFF0000)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("While blocking below 20% HP:").color(NamedTextColor.GRAY));
		lore.add(Component.text("Gain Resistance I for 5s").color(NamedTextColor.GRAY));
		lore.add(Component.text("Cooldown: 3 minutes").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "last_stand_shield");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isLastStandShield(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "last_stand_shield");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	public static ItemStack getChaosPearl(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.ENDER_PEARL);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Chaos Pearl").color(TextColor.color(0xFF0000)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Teleports + blinds nearby players").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "chaos_pearl");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isChaosPearl(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "chaos_pearl");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	// ===== SHARED/LEGENDARY ITEMS =====

	public static ItemStack getSoulCrystal(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.AMETHYST_SHARD);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Soul Crystal").color(TextColor.color(0x9D4EDD)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Revives an eliminated player").color(NamedTextColor.GRAY));
		lore.add(Component.text("Very expensive to craft").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "soul_crystal");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isSoulCrystal(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "soul_crystal");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	public static ItemStack getBeaconCompass(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.RECOVERY_COMPASS);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Beacon Compass").color(TextColor.color(0x9D4EDD)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Tracks world spawn").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "beacon_compass");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isBeaconCompass(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "beacon_compass");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	public static ItemStack getRecallScroll(ThirdLifePlugin plugin) {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return item;
		meta.displayName(Component.text("Recall Scroll").color(TextColor.color(0x9D4EDD)));
		List<Component> lore = new ArrayList<>();
		lore.add(Component.text("Teleports home after 10 seconds").color(NamedTextColor.GRAY));
		lore.add(Component.text("Cancelled if hit").color(NamedTextColor.GRAY));
		meta.lore(lore);
		NamespacedKey key = new NamespacedKey(plugin, "recall_scroll");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isRecallScroll(ItemStack item, ThirdLifePlugin plugin) {
		if (item == null || !item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(plugin, "recall_scroll");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != null;
	}

	/**
	 * Checks if a specific item is enabled in the configuration
	 * @param itemName the name of the item (e.g., "backpack", "blood-sword")
	 * @param category the category (e.g., "items.green", "items.red") or null for basic items
	 * @param plugin the plugin instance
	 * @return true if the item is enabled, false otherwise
	 */
	public static boolean isItemEnabled(String itemName, String category, ThirdLifePlugin plugin) {
		if (category == null || category.isEmpty()) {
			// Basic items
			return plugin.getConfig().getBoolean("items." + itemName, true);
		}
		return plugin.getConfig().getBoolean("items." + category + "." + itemName, true);
	}
}
