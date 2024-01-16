package data.hullmods.added;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;

import static data.hullmods.vanilla.CompromisedStructure.modifyCost;
import static utils.DModUtils.*;

public class DegradedWeaponLoader extends BaseHullMod {
    public static float FIRE_RATE_MULT = 0.75f;
    public static float AMMO_REGEN_MULT = 0.75f;

    @Override
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getBallisticRoFMult().modifyMult(id, dModMult(FIRE_RATE_MULT, stats));
        stats.getEnergyRoFMult().modifyMult(id, dModMult(FIRE_RATE_MULT, stats));

        stats.getBallisticAmmoRegenMult().modifyMult(id, dModMult(AMMO_REGEN_MULT, stats));
        stats.getEnergyAmmoRegenMult().modifyMult(id, dModMult(AMMO_REGEN_MULT, stats));

        modifyCost(hullSize, stats, id);
    }

    @Override
    public String getDescriptionParam(int index, ShipAPI.HullSize hullSize, ShipAPI ship) {
        return getMultDescriptionParam(index, ship, new float[]{FIRE_RATE_MULT, AMMO_REGEN_MULT});
    }
}
