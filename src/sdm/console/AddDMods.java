package sdm.console;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.ShipVariantAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.DModManager;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import org.lazywizard.console.BaseCommand;
import org.lazywizard.console.Console;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddDMods implements BaseCommand {
    @Override
    public CommandResult runCommand(String args, CommandContext context) {
        String[] args_ = args.split(" ");
        int amount = 5;
        int fleet_index = 0;
        if (args_.length > 0) {
            amount = Integer.parseInt(args_[0]);
        }
        if (args_.length > 1) {
            fleet_index = Integer.parseInt(args_[1]);
        }
        List<FleetMemberAPI> ships = Global.getSector().getPlayerFleet().getFleetData().getMembersListCopy();
        fleet_index = Math.min(fleet_index, ships.size() - 1);
        FleetMemberAPI ship = ships.get(fleet_index);

        Console.showMessage(String.format("Adding %s mods to ship %s (index %s)",
                amount, ship.getShipName(), fleet_index));

        // Clear D-mods
        ShipVariantAPI variant = ship.getVariant();
        for (String id : new ArrayList<>(variant.getHullMods())) {
            if (DModManager.getMod(id).hasTag(Tags.HULLMOD_DMOD)) {
                if (variant.getHullSpec().getBuiltInMods().contains(id)) continue;
                DModManager.removeDMod(variant, id);
            }
        }

        // Add <amount> D-mods
        DModManager.addDMods(ship, false, amount, new Random());
        return CommandResult.SUCCESS;
    }
}
