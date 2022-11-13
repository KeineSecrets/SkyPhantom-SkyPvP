package eu.skyphantom.skypvp.crates;

import eu.skyphantom.skypvp.api.Config;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static eu.skyphantom.skypvp.utils.Utils.getRandom;

public class CrateHandler {

    String crate;
    static Config config;

    public CrateHandler(String crate) {
        this.crate = crate;
        this.config = new Config("plugins/SkyPvP/crate/", "crates.yml");
    }

    public void createCrate(CrateRarity rarity) {

        List<String> crates = config.getConfig().getStringList("crates");
        crates.add(crate);

        config.getConfig().set(crate + ".name", crate.toLowerCase());
        config.getConfig().set(crate + ".rarity", rarity.name().toLowerCase());
        config.getConfig().set(crate + ".created", System.currentTimeMillis());
        config.getConfig().set(crate + ".opened", 0);
        config.getConfig().set(crate + ".id", Utils.getRandomID());
        config.getConfig().set(crate + ".item", null);
        config.getConfig().set(crate + ".crateitems", new ArrayList<>());
        config.getConfig().set("crates", crates);
        config.saveConfig();

    }


    public List<ItemStack> getCrateItems() {
        List<ItemStack> crates = (List<ItemStack>) config.getConfig().getList(crate + ".crateitems");
        return crates;
    }

    public void addItem(ItemStack item) {
        List<ItemStack> crates = (List<ItemStack>) config.getConfig().getList(crate + ".crateitems");
        crates.add(item);
        config.getConfig().set(crate + ".crateitems", crates);
        config.saveConfig();
    }

    public double getCrateOpened() {
        return config.getConfig().getDouble(crate + ".opened");
    }

    public void setCrateOpened(double value) {
        config.getConfig().set(crate + ".opened", value);
        config.saveConfig();
    }

    public String getCratebyId(int value) {

        String s2 = null;

        for(String s : getCrates()) {
            if(config.getConfig().getInt(s + ".id") == value) {
                s2 = s;
            }
        }
        return s2;
    }

    public int getId() {
        return config.getConfig().getInt(crate + ".id");
    }

    public void setColorName(String value) {
        config.getConfig().set(crate + ".colorname", value);
        config.saveConfig();
    }

    public ItemStack getCrateItem() {
        ItemBuilder itemBuilder = new ItemBuilder(getItem()).setName("§8▎ §a§lCRATE§8 ▪ §7" + crate).lore(
                "",
                "§8▎ §c§l! §7Information",
                "§8▪ §7Diese Crate beinhaltet derzeit §a" + new DecimalFormat("#,###.##").format(getCrateItems().size()).replace(",", ".").replace(".", "§8'§a") + " §7Items§8.",
                "§8▪ §7Beim öffnen erhälst du §aeines §7der enthaltenen Items§8.",
                "",
                "§8▪ §7Diese Crate wurde bisher §a" + new DecimalFormat("#,###.##").format(getCrateOpened()).replace(",", ".").replace(".", "§8'§a") + "§8x §7geöffnet§8.",
                "§8▪ §7Diese Crate wurde am §a" + new SimpleDateFormat("dd.MM.yyyy, HH:mm").format(getCreated()) + " §7erstellt§8.",
                "",
                "§8▪ §7Seltenheit§8: §r" + getSeltenheit().getDisplay(),
                ""
        );

        return itemBuilder;
    }


    public void setItem(ItemStack item) {
        config.getConfig().set(crate + ".item", item);
        config.saveConfig();
    }

    public void deleteCrate() {
        List<String> crates = config.getConfig().getStringList("crates");
        crates.remove(crate);

        config.getConfig().set(crate, null);
        config.getConfig().set("crates", crates);
        config.saveConfig();
    }

    public boolean crateExist() {


        return config.getConfig().get(crate + ".name") != null;
    }

    public ItemStack getItem() {
        return config.getConfig().getItemStack(crate + ".item");
    }

    public List<String> getCrates() {
        List<String> crates = config.getConfig().getStringList("crates");

        return crates;
    }

    public CrateRarity getSeltenheit() {
        return CrateRarity.valueOf(config.getConfig().getString(crate + ".rarity").toUpperCase());
    }

    public long getCreated() {
        return config.getConfig().getLong(crate + ".created");
    }


    public ItemStack getRandomCrate() {
        List<String> list = getCrates();
        int size = getCrates().size();
        int index = getRandom().nextInt(size);
        return new CrateHandler(list.get(index)).getCrateItem();
    }



}
