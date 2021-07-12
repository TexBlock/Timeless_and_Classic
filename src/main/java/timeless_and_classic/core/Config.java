package timeless_and_classic.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/*
 * This is our config class. In here we can specify things that can be changed by the player.
 * The config allows users to take more control of the mod. For instance should a biome spawn?
 *
 * In this case, currently, I am only letting the player decide the chance of a dart being lost.
 */

/**
 * Author: Mr.Pineapple (ClumsyAlien, I am currently keeping the file before stashing in a note file, to potentially poke around with later)
 */
public class Config {
    /*
     * This is an inner class of the config class.
     * This is the common config.
     * In the future we might have a client and server config.
     */
    public static class Common {
        //Initialise our value
        public ForgeConfigSpec.BooleanValue ammoProgressBar;

        // Limit the Trigger finger enchantment on each weapon
        public ForgeConfigSpec.IntValue M1928_trigMax;
        public ForgeConfigSpec.IntValue AK47_trigMax;
        public ForgeConfigSpec.IntValue M60_trigMax;
        public ForgeConfigSpec.IntValue DP28_trigMax;
        public ForgeConfigSpec.IntValue M16A1_trigMax;
        public ForgeConfigSpec.IntValue AK74_trigMax;
        public ForgeConfigSpec.IntValue AR15P_trigMax;
        public ForgeConfigSpec.IntValue AR15HM_trigMax;
        public ForgeConfigSpec.IntValue VECTOR45_trigMax;
        //Constructor for the Common Config
        Common(ForgeConfigSpec.Builder builder) {

            //What we will be pushing to the config, this has a title of common
            builder.push("client");
            {
                /*
                 * We can then add a comment (highly recommended) to inform the user what they are changing.
                 * Notice here that we are connecting it the value from above.
                 * Then in the case of an integer we need to set the range.
                 * This takes in the path (what it will be called). The default value. The minimum value. Then the max value.
                 */
                this.ammoProgressBar = builder.comment("Show the durabilityBar indicating ammo count per weapon").define("durabilityBar", true);
            }
            //Remember to pop this section
            builder.pop();
            builder.push("common");
            {
                this.M1928_trigMax = builder.comment("Maximum level of the Trigger Finger enchantment allowed on a weapon").defineInRange("m1928_trigMax", 0, 0, 10);
                this.AK47_trigMax = builder.comment("Maximum level of the Trigger Finger enchantment allowed on a weapon").defineInRange("ak47_trigMax", 1, 0, 10);
                this.M60_trigMax = builder.comment("Maximum level of the Trigger Finger enchantment allowed on a weapon").defineInRange("m60_trigMax", 0, 0, 10);
                this.DP28_trigMax = builder.comment("Maximum level of the Trigger Finger enchantment allowed on a weapon").defineInRange("dp28_trigMax", 1, 0, 10);
                this.M16A1_trigMax = builder.comment("Maximum level of the Trigger Finger enchantment allowed on a weapon").defineInRange("m16a1_trigMax", 1, 0, 10);
                this.AK74_trigMax = builder.comment("Maximum level of the Trigger Finger enchantment allowed on a weapon").defineInRange("ak74_trigMax", 1, 0, 10);
                this.AR15HM_trigMax = builder.comment("Maximum level of the Trigger Finger enchantment allowed on a weapon").defineInRange("ar15hm_trigMax", 0, 0, 10);
                this.AR15P_trigMax = builder.comment("Maximum level of the Trigger Finger enchantment allowed on a weapon").defineInRange("ar15p_trigMax", 0, 0, 10);
                this.VECTOR45_trigMax = builder.comment("Maximum level of the Trigger Finger enchantment allowed on a weapon").defineInRange("ar15p_trigMax", 0, 0, 10);

            }
        }
    }

    /*
     * Now we need to be able to access these values across our project.
     * We create a static variable of COMMON (same goes for server/client when added) so we can call the values in the mod.
     * Then we initialise them.
     */
    static final ForgeConfigSpec commonSpec;
    public static final Config.Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }
}
