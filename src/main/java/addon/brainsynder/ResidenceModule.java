package addon.brainsynder;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.FlagPermissions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import simplepets.brainsynder.addon.AddonConfig;
import simplepets.brainsynder.addon.presets.RegionModule;
import simplepets.brainsynder.api.Namespace;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.debug.DebugLevel;

@Namespace(namespace = "Residence")
public class ResidenceModule extends RegionModule {
    private FlagData SPAWN, MOUNTING, MOVING, RIDING;

    @Override
    public boolean shouldEnable() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("Residence");
        if (plugin == null) {
            SimplePets.getDebugLogger().debug(DebugLevel.ERROR, "Residence wasn't found!");
            SimplePets.getDebugLogger().debug(DebugLevel.ERROR, "Please download it from: https://www.spigotmc.org/resources/11480/");
            return false;
        }
        return true;
    }

    @Override
    public void loadDefaults(AddonConfig config) {
        config.addDefault("checks.spawning.flag.name", "allow-pet-spawn", "What should the flag for spawning a pet in a residence be named?");
        config.addDefault("checks.spawning.flag.default-value", true, "What should the default value of the flag be?");

        config.addDefault("checks.mounting.flag.name", "allow-pet-mounting", "What should the flag for mounting a pet in a residence be named?");
        config.addDefault("checks.mounting.flag.default-value", true, "What should the default value of the flag be?");

        config.addDefault("checks.moving.flag.name", "allow-pet-enter", "What should the flag for when a pet is walking in a residence be named?");
        config.addDefault("checks.moving.flag.default-value", true, "What should the default value of the flag be?");

        config.addDefault("checks.riding.flag.name", "allow-pet-riding", "What should the flag for is riding their pet in a residence be named?");
        config.addDefault("checks.riding.flag.default-value", true, "What should the default value of the flag be?");

        super.loadDefaults(config);
        SimplePets.getDebugLogger().debug(SimplePets.ADDON, "Adding pet related flags to Residence:");

        SPAWN = new FlagData(config.getString("checks.spawning.flag.name"), config.getBoolean("checks.spawning.flag.default-value"));
        SimplePets.getDebugLogger().debug(SimplePets.ADDON, "- '"+SPAWN.flagName()+"' with default value: "+SPAWN.defaultValue());
        FlagPermissions.addFlag(SPAWN.flagName());

        MOUNTING = new FlagData(config.getString("checks.mounting.flag.name"), config.getBoolean("checks.mounting.flag.default-value"));
        SimplePets.getDebugLogger().debug(SimplePets.ADDON, "- '"+MOUNTING.flagName()+"' with default value: "+MOUNTING.defaultValue());
        FlagPermissions.addFlag(MOUNTING.flagName());

        MOVING = new FlagData(config.getString("checks.moving.flag.name"), config.getBoolean("checks.moving.flag.default-value"));
        SimplePets.getDebugLogger().debug(SimplePets.ADDON, "- '"+MOVING.flagName()+"' with default value: "+MOVING.defaultValue());
        FlagPermissions.addFlag(MOVING.flagName());

        RIDING = new FlagData(config.getString("checks.riding.flag.name"), config.getBoolean("checks.riding.flag.default-value"));
        SimplePets.getDebugLogger().debug(SimplePets.ADDON, "- '"+RIDING.flagName()+"' with default value: "+RIDING.defaultValue());
        FlagPermissions.addFlag(RIDING.flagName());
    }

    private boolean checkResidence (PetUser user, Location location, FlagData flagData) {
        ClaimedResidence residence = Residence.getInstance().getResidenceManager().getByLoc(location);
        if (residence == null) return true;
        return residence.getPermissions().playerHas(user.getOwnerName(), flagData.flagName(), flagData.defaultValue());
    }

    @Override
    public boolean isSpawningAllowed(PetUser petUser, Location location) {
        if (SPAWN == null) return true;
        return checkResidence(petUser, location, SPAWN);
    }

    @Override
    public boolean isMovingAllowed(PetUser petUser, Location location) {
        if (MOVING == null) return true;
        return checkResidence(petUser, location, MOVING);
    }

    @Override
    public boolean isRidingAllowed(PetUser petUser, Location location) {
        if (RIDING == null) return true;
        return checkResidence(petUser, location, RIDING);
    }

    @Override
    public boolean isMountingAllowed(PetUser petUser, Location location) {
        if (MOUNTING == null) return true;
        return checkResidence(petUser, location, MOUNTING);
    }

    public static final class FlagData {
        private final String flagName;
        private final boolean defaultValue;

        public FlagData(String flagName, boolean defaultValue) {
            this.flagName = flagName;
            this.defaultValue = defaultValue;
        }

        public String flagName() {
            return flagName;
        }

        public boolean defaultValue() {
            return defaultValue;
        }
    }
}
