package data.hullmods.added;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;

import static data.hullmods.vanilla.CompromisedStructure.modifyCost;
import static utils.DModUtils.*;

public class DegradedMissileLoader extends BaseHullMod {
    public static float MISSILE_ROF_MULT = 0.6f;
    public static float MISSILE_AMMO_REGEN_MULT = 0.6f;

    @Override
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        // Only apply if the ship has a lot of missile mounts, otherwise add a random new D-mod
        if (!isMissileShip(stats.getVariant())) {
            replaceDMod(stats, id);
            return;
        }

        stats.getMissileRoFMult().modifyMult(id, dModMult(MISSILE_ROF_MULT, stats));
        stats.getMissileAmmoRegenMult().modifyMult(id, dModMult(MISSILE_AMMO_REGEN_MULT, stats));

        modifyCost(hullSize, stats, id);
    }

    @Override
    public String getDescriptionParam(int index, ShipAPI.HullSize hullSize, ShipAPI ship) {
        return getMultDescriptionParam(index, ship, new float[]{MISSILE_ROF_MULT, MISSILE_AMMO_REGEN_MULT});
    }
}
