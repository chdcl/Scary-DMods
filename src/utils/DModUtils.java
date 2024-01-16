package utils;

import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.impl.campaign.DModManager;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.loading.WeaponSlotAPI;

import java.util.*;

import static data.hullmods.vanilla.CompromisedStructure.getCostDescParam;

public class DModUtils {
    public static float dModMult(float mult, MutableShipStatsAPI stats) {
        float effect = stats.getDynamic().getValue(Stats.DMOD_EFFECT_MULT);
        return mult + (1f - mult) * (1f - effect);
    }

    public static String getMultDescriptionParam(int index, ShipAPI ship, float[] mults) {
        if (index > mults.length) {
            return null;
        }
        if (index == mults.length) {
            return getCostDescParam(0, 0);
        }
        float val = dModMult(mults[index], ship.getMutableStats());
        return Math.round((1f - val) * 100f) + "%";
    }

    public static void replaceDMod(MutableShipStatsAPI stats, String id) {
        DModManager.removeDMod(stats.getVariant(), id);
        DModManager.addDMods(stats.getFleetMember(), false, 1, new Random());
    }

    public static Map<ShipAPI.HullSize, Integer> reqNumMissileSlots;
    static {
        reqNumMissileSlots = new HashMap<>();
        reqNumMissileSlots.put(ShipAPI.HullSize.FRIGATE, 2);
        reqNumMissileSlots.put(ShipAPI.HullSize.DESTROYER, 4);
        reqNumMissileSlots.put(ShipAPI.HullSize.CRUISER, 6);
        reqNumMissileSlots.put(ShipAPI.HullSize.CAPITAL_SHIP, 8);
    }
    public static boolean isMissileShip(ShipVariantAPI variant) {
        if (!reqNumMissileSlots.containsKey(variant.getHullSize())) {
            return false;
        }

        List<WeaponSlotAPI> weaponSlots = variant.getHullSpec().getAllWeaponSlotsCopy();
        int numMissileSlots = 0;
        for (WeaponSlotAPI slot : weaponSlots) {
            WeaponAPI.WeaponType[] missileTypes = {WeaponAPI.WeaponType.MISSILE, WeaponAPI.WeaponType.SYNERGY, WeaponAPI.WeaponType.COMPOSITE, WeaponAPI.WeaponType.UNIVERSAL };
            if (Arrays.asList(missileTypes).contains(slot.getWeaponType())) {
                // small = 1, medium = 2, large = 4
                numMissileSlots += (int) Math.pow(2, slot.getSlotSize().ordinal());
            }
        }

        return numMissileSlots >= reqNumMissileSlots.get(variant.getHullSize());
    }
}
