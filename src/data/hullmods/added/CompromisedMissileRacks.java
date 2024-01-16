package data.hullmods.added;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.impl.campaign.DModManager;
import com.fs.starfarer.api.loading.WeaponSlotAPI;
import utils.DModUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static data.hullmods.vanilla.CompromisedStructure.modifyCost;
import static utils.DModUtils.*;

public class CompromisedMissileRacks extends BaseHullMod {
    public static float MISSILE_AMMO_MULT = 0.6666f;

    @Override
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        // Only apply if the ship has a lot of missile mounts, otherwise add a random new D-mod
        if (!isMissileShip(stats.getVariant())) {
            replaceDMod(stats, id);
            return;
        }

        // modifyMult stacks multiplicatively with expanded missile racks, skill etc
        stats.getMissileAmmoBonus().modifyMult(id, dModMult(MISSILE_AMMO_MULT, stats));

        modifyCost(hullSize, stats, id);
    }

    @Override
    public String getDescriptionParam(int index, ShipAPI.HullSize hullSize, ShipAPI ship) {
        return getMultDescriptionParam(index, ship, new float[]{MISSILE_AMMO_MULT});
    }
}
