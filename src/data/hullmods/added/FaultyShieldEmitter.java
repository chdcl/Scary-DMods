package data.hullmods.added;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;

import static data.hullmods.vanilla.CompromisedStructure.modifyCost;
import static utils.DModUtils.dModMult;
import static utils.DModUtils.getMultDescriptionParam;

public class FaultyShieldEmitter extends BaseHullMod {
    public static float SHIELD_ARC_MULT = 0.75f;
    public static float SHIELD_SPEED_MULT = 0.5f;

    @Override
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getShieldArcBonus().modifyMult(id, dModMult(SHIELD_ARC_MULT, stats));

        stats.getShieldUnfoldRateMult().modifyMult(id, dModMult(SHIELD_SPEED_MULT, stats));

        modifyCost(hullSize, stats, id);
    }

    @Override
    public String getDescriptionParam(int index, ShipAPI.HullSize hullSize, ShipAPI ship) {
        return getMultDescriptionParam(index, ship, new float[]{SHIELD_ARC_MULT, SHIELD_SPEED_MULT});
    }
}
