package org.succeptivitydev.xlifesmp.CustomStuff;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.succeptivitydev.xlifesmp.ThirdLifePlugin;
import org.succeptivitydev.xlifesmp.CustomStuff.Items.Items;


public class RecipeManager {

	private final ThirdLifePlugin plugin;

	public RecipeManager(ThirdLifePlugin plugin) {
		this.plugin = plugin;
	}

	public void registerRecipes() {
		// Basic items
		if (Items.isItemEnabled("revive-token", null, plugin)) registerReviveItemRecipe();
		if (Items.isItemEnabled("blood-lust-sword", null, plugin)) registerBloodLustSwordRecipe();
		if (Items.isItemEnabled("life-orb", null, plugin)) registerLifeOrbRecipe();

		// Green items
		if (Items.isItemEnabled("backpack", "green", plugin)) registerBackpackRecipe();
		if (Items.isItemEnabled("lumber-axe", "green", plugin)) registerLumberAxeRecipe();
		if (Items.isItemEnabled("farmer-charm", "green", plugin)) registerFarmerCharmRecipe();
		if (Items.isItemEnabled("wind-boots", "green", plugin)) registerWindBootsRecipe();
		if (Items.isItemEnabled("emergency-totem", "green", plugin)) registerEmergencyTotemRecipe();

		// Yellow items
		if (Items.isItemEnabled("tracking-compass", "yellow", plugin)) registerTrackingCompassRecipe();
		if (Items.isItemEnabled("smoke-bomb", "yellow", plugin)) registerSmokeBombRecipe();
		if (Items.isItemEnabled("snare-trap", "yellow", plugin)) registerSnareTraRecipe();
		if (Items.isItemEnabled("hunter-cloak", "yellow", plugin)) registerHunterCloakRecipe();
		if (Items.isItemEnabled("scout-bow", "yellow", plugin)) registerScoutBowRecipe();

		// Red items
		if (Items.isItemEnabled("blood-sword", "red", plugin)) registerBloodSwordRecipe();
		if (Items.isItemEnabled("frenzy-pendant", "red", plugin)) registerFrenzyPendantRecipe();
		if (Items.isItemEnabled("last-stand-shield", "red", plugin)) registerLastStandShieldRecipe();
		if (Items.isItemEnabled("chaos-pearl", "red", plugin)) registerChaosPearlRecipe();

		// Legendary items
		if (Items.isItemEnabled("soul-crystal", "legendary", plugin)) registerSoulCrystalRecipe();
		if (Items.isItemEnabled("beacon-compass", "legendary", plugin)) registerBeaconCompassRecipe();
		if (Items.isItemEnabled("recall-scroll", "legendary", plugin)) registerRecallScrollRecipe();
	}

	/**
	 * Shaped Recipe for the Revive Item.
	 * Recipe: Netherite Block, Nether Star, Dragon Breath, Blaze Rod, Netherite Ingot, Beacon
	 *   N E N
	 *   X T B
	 *   D E Z
	 * Where N = NETHERITE_BLOCK, E = NETHERITE_INGOT, X = NETHER_STAR, T = TOTEM_OF_UNDYING, B = DRAGON_BREATH, D = BLAZE_ROD, Z = BEACON
	 */
	private void registerReviveItemRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "revive_item");
		ItemStack result = Items.getReviveItem(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("NEN", "XTB", "DEZ");
		recipe.setIngredient('N', Material.NETHERITE_BLOCK);
		recipe.setIngredient('X', Material.NETHER_STAR);
		recipe.setIngredient('B', Material.DRAGON_BREATH);
		recipe.setIngredient('D', Material.BLAZE_ROD);
		recipe.setIngredient('E', Material.NETHERITE_INGOT);
		recipe.setIngredient('Z', Material.BEACON);
		try {
			plugin.getServer().addRecipe(recipe);
		} catch (IllegalStateException ignored) {
		}
	}

	/**
	 * Shaped Recipe for the Blood Lust Sword.
	 * Only red-named players can craft this (enforced by CraftingListener).
	 * Recipe: Netherite Ingot at top, Diamond Blocks in middle, Stick at bottom.
	 *   N
	 *   D
	 *   S
	 * Where N = NETHERITE_INGOT, D = DIAMOND_BLOCK, S = STICK
	 */
	private void registerBloodLustSwordRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "blood_lust_sword");
		ItemStack result = Items.getBloodLustSword(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);

		recipe.shape("N",
		             "D",
		             "S");
		recipe.setIngredient('N', Material.NETHERITE_INGOT);
		recipe.setIngredient('D', Material.DIAMOND_BLOCK);
		recipe.setIngredient('S', Material.STICK);

		try {
			plugin.getServer().addRecipe(recipe);
		} catch (IllegalStateException ignored) {
		}
	}

	/**
	 * Shaped Recipe for the Life Orb.
	 * Recipe: Emerald in center, surrounded by Gold Ingots.
	 *   G G G
	 *   G E G
	 *   G G G
	 * Where G = GOLD_INGOT, E = EMERALD
	 */
	private void registerLifeOrbRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "life_orb");
		ItemStack result = Items.getLifeOrb(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);

		recipe.shape("GGG",
		             "GEG",
		             "GGG");		
		recipe.setIngredient('G', Material.GOLD_INGOT);
		recipe.setIngredient('E', Material.EMERALD);

		try {
			plugin.getServer().addRecipe(recipe);
		} catch (IllegalStateException ignored) {
		}
	}

	// GREEN ITEM RECIPES

	private void registerBackpackRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "backpack");
		ItemStack result = Items.getBackpack(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("LLL", "LCL", "LLL");
		recipe.setIngredient('L', Material.LEATHER);
		recipe.setIngredient('C', Material.CHEST);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	private void registerLumberAxeRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "lumber_axe");
		ItemStack result = Items.getLumberAxe(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("DDD", "DST", "DT ");
		recipe.setIngredient('D', Material.DIAMOND);
		recipe.setIngredient('S', Material.STICK);
		recipe.setIngredient('T', Material.OAK_LOG);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	private void registerFarmerCharmRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "farmer_charm");
		ItemStack result = Items.getFarmerCharm(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape(" W ", "WGW", " W ");
		recipe.setIngredient('W', Material.WHEAT);
		recipe.setIngredient('G', Material.GOLDEN_HOE);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	private void registerWindBootsRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "wind_boots");
		ItemStack result = Items.getWindBoots(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("FEF", "FBF", "F F");
		recipe.setIngredient('F', Material.FEATHER);
		recipe.setIngredient('E', Material.ELYTRA);
		recipe.setIngredient('B', Material.DIAMOND_BOOTS);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	private void registerEmergencyTotemRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "emergency_totem");
		ItemStack result = Items.getEmergencyTotem(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("AEA", "ATA", "AEA");
		recipe.setIngredient('A', Material.AMETHYST_SHARD);
		recipe.setIngredient('E', Material.EMERALD);
		recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	// YELLOW ITEM RECIPES

	private void registerTrackingCompassRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "tracking_compass");
		ItemStack result = Items.getTrackingCompass(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("GCG", "GGG", "GCG");
		recipe.setIngredient('G', Material.GOLD_INGOT);
		recipe.setIngredient('C', Material.COMPASS);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	private void registerSmokeBombRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "smoke_bomb");
		ItemStack result = Items.getSmokeBomb(plugin);
		result.setAmount(4);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("CCC", "CGC", "CCC");
		recipe.setIngredient('C', Material.CHARCOAL);
		recipe.setIngredient('G', Material.GUNPOWDER);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	private void registerSnareTraRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "snare_trap");
		ItemStack result = Items.getSnareTrap(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("SLS", "LSL", "SLS");
		recipe.setIngredient('S', Material.STRING);
		recipe.setIngredient('L', Material.LEAD);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	private void registerHunterCloakRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "hunter_cloak");
		ItemStack result = Items.getHunterCloak(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("LSL", "LCL", "LSL");
		recipe.setIngredient('L', Material.LEATHER);
		recipe.setIngredient('S', Material.STRING);
		recipe.setIngredient('C', Material.LEATHER_CHESTPLATE);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	private void registerScoutBowRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "scout_bow");
		ItemStack result = Items.getScoutBow(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("GBG", "SBS", "GBG");
		recipe.setIngredient('G', Material.GLOWSTONE);
		recipe.setIngredient('B', Material.BOW);
		recipe.setIngredient('S', Material.STRING);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	// RED ITEM RECIPES

	private void registerBloodSwordRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "blood_sword");
		ItemStack result = Items.getBloodSword(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("RRR", "RNR", "RNR");
		recipe.setIngredient('R', Material.REDSTONE_BLOCK);
		recipe.setIngredient('N', Material.NETHERITE_SWORD);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	private void registerFrenzyPendantRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "frenzy_pendant");
		ItemStack result = Items.getFrenzyPendant(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape(" B ", "BNB", " B ");
		recipe.setIngredient('B', Material.BLAZE_ROD);
		recipe.setIngredient('N', Material.NETHER_STAR);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	private void registerLastStandShieldRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "last_stand_shield");
		ItemStack result = Items.getLastStandShield(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("DDD", "DSH", "DDD");
		recipe.setIngredient('D', Material.DIAMOND);
		recipe.setIngredient('S', Material.SHIELD);
		recipe.setIngredient('H', Material.HEART_OF_THE_SEA);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	private void registerChaosPearlRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "chaos_pearl");
		ItemStack result = Items.getChaosPearl(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("BBB", "BEB", "BBB");
		recipe.setIngredient('B', Material.BLAZE_POWDER);
		recipe.setIngredient('E', Material.ENDER_PEARL);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	// LEGENDARY ITEM RECIPES

	private void registerSoulCrystalRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "soul_crystal");
		ItemStack result = Items.getSoulCrystal(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("DED", "ESE", "DED");
		recipe.setIngredient('D', Material.DIAMOND);
		recipe.setIngredient('E', Material.EMERALD);
		recipe.setIngredient('S', Material.AMETHYST_SHARD);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	private void registerBeaconCompassRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "beacon_compass");
		ItemStack result = Items.getBeaconCompass(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("GCG", "CBH", "GCG");
		recipe.setIngredient('G', Material.GOLD_INGOT);
		recipe.setIngredient('C', Material.COMPASS);
		recipe.setIngredient('B', Material.BEACON);
		recipe.setIngredient('H', Material.HEART_OF_THE_SEA);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}

	private void registerRecallScrollRecipe() {
		NamespacedKey key = new NamespacedKey(plugin, "recall_scroll");
		ItemStack result = Items.getRecallScroll(plugin);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape("EPE", "PEP", "EPE");
		recipe.setIngredient('E', Material.EMERALD);
		recipe.setIngredient('P', Material.PAPER);
		try { plugin.getServer().addRecipe(recipe); } catch (IllegalStateException ignored) {}
	}
}
