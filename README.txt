Small mod to make D-mods more impactful, mostly reverting nerfs from patch 0.95.1a.
D-mods are in src/data/hullmods/, with either the original values left as a comment or a comment saying that the mod was unchanged.

If you have Console Commands, it adds a janky command for testing D-mods: SDM_AddDMods <amount> <fleet_index>.
You might need to remove existing mods from a ship for the command to work.

This mod combines the D-mod java sources from starsector-data/data/hullmods and starfarer.api.zip/com/fs/starfarer/api/impl/hullmods
into a single package and overwrites the csv because the ones in com.fs.starfarer wouldn't get overwritten otherwise.
It's useful for being able to modify the description anyway.