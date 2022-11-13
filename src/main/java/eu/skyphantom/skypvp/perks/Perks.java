package eu.skyphantom.skypvp.perks;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum Perks {

    SPEEDBUILD, RESPAWNPLUS, FALLDAMAGE, NIGHTVISION, JUMPBOOST, FIRE_RESISTANCE, NO_HUNGER, MINER, DOUBLEDROP;

    public PotionEffect getEffect(Perks perk) {
        switch (perk) {
            default:
            case MINER:
            case NO_HUNGER:
            case FALLDAMAGE:
            case DOUBLEDROP:
            case RESPAWNPLUS:
                return null;
            case SPEEDBUILD:
                return new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 1, false, false);
            case NIGHTVISION:
                return new PotionEffect(PotionEffectType.NIGHT_VISION, 99999, 1, false, false);
            case JUMPBOOST:
                return new PotionEffect(PotionEffectType.JUMP, 99999, 1, false, false);
            case FIRE_RESISTANCE:
                return new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 99999, 1, false, false);
        }
    }

}
