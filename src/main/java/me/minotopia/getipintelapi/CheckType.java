package me.minotopia.getipintelapi;

import org.jetbrains.annotations.Nullable;

/**
 * Full documentation at <a href="http://getipintel.net/#flagstbl">http://getipintel.net/#flagstbl</a>
 */
public enum CheckType {
    /**
     * Bot, proxy and vpn ips are detected through static lists (still these static lists are updated)
     * <p>The result is either 1 or 0</p>
     * <p>Response time should be <60ms</p>
     */
    STATIC_BANS_ONLY('m'),
    /**
     * Static bans check (bot, proxy and vpn) with the fast parts of dynamic checks
     * <p>The dynamic checks are mostly bad IP checks (<a href="http://getipintel.net/index.php#bad_IP">http://getipintel.net/index.php#bad_IP</a>), but can alos get more proxy and vpn ips</p>
     * <p>Response time should be <130ms</p>
     */
    PARTLY_DYNAMIC_CHECKS('b'),
    /**
     * Static bans check (bot, proxy and vpn) which puts time-consuming dynamic checks into the background
     * <p>do another lookup after 5 seconds to get the reuslt of the background lookups</p>
     * <p>Response time should be <130ms</p>
     */
    DYNAMIC_CHECKS(null, ' '),
    /**
     * Static bans check (bot, proxy and vpn) and all dynamic checks are done.
     * This does a real time check and <strong>the query duration can be up to 5 seconds</strong>
     * <p>Response time up to 5000ms</p>
     */
    FULL_LOOKUP('f');

    @Nullable
    private String flag = "flag";
    private final char flagValue;

    CheckType(@Nullable String flag, char flagValue) {
        this.flag = flag;
        this.flagValue = flagValue;
    }

    CheckType(char flagValue) {
        this.flagValue = flagValue;
    }

    @Nullable
    public String getFlag() {
        return flag;
    }

    public char getFlagValue() {
        return flagValue;
    }
}
