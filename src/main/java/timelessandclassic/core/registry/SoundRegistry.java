
package timelessandclassic.core.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import timelessandclassic.core.timeless_and_classic;

/**
 * Author: ClumsyAlien, codebase and design based off Mr.Pineapple's original addon
 */

public class SoundRegistry {
    // A Deferred Register which will be called in our main class to register all of the sounds, the very same
    public static final DeferredRegister<SoundEvent> SOUND_REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, timeless_and_classic.ID);

    // Register the name of the sound via the Object name for each block in our sounds.json
    public static final RegistryObject<SoundEvent> M1911_FIRE = register("item.fire_m1911");
    public static final RegistryObject<SoundEvent> M1911_FIRES = register("item.fire_m1911s");
    public static final RegistryObject<SoundEvent> MOSIN_NAGANT_FIRE = register("item.fire_mosin-nagant");
    public static final RegistryObject<SoundEvent> FIRE_30 = register("item.fire_30-30");
    public static final RegistryObject<SoundEvent> AK74_FIRE = register("item.fire_ak74");
    public static final RegistryObject<SoundEvent> AK74_FIREs = register("item.fire_ak74s");
    public static final RegistryObject<SoundEvent> AK47_FIRE = register("item.fire_ak47");
    public static final RegistryObject<SoundEvent> AK47_FIREs = register("item.fire_ak47s");
    public static final RegistryObject<SoundEvent> DP28_FIRE = register("item.fire_dp28");
    public static final RegistryObject<SoundEvent> GLOCK_FIRE = register("item.fire_glock");
    public static final RegistryObject<SoundEvent> GLOCK_FIREs = register("item.fire_glocks");
    public static final RegistryObject<SoundEvent> M16_FIRE = register("item.fire_m16");
    public static final RegistryObject<SoundEvent> M16_FIREs = register("item.fire_m16s");
    public static final RegistryObject<SoundEvent> M60_FIRE = register("item.fire_m60");
    public static final RegistryObject<SoundEvent> M92FS_FIRE = register("item.fire_m92fs");
    public static final RegistryObject<SoundEvent> M92FS_FIREs = register("item.fire_m92fss");
    public static final RegistryObject<SoundEvent> M1851_FIRE = register("item.fire_m1851");
    public static final RegistryObject<SoundEvent> THOMPSON_FIRE = register("item.fire_thompson");
    public static final RegistryObject<SoundEvent> DB_FIRE = register("item.fire_db");

    private static RegistryObject<SoundEvent> register(String key) {
        return SOUND_REGISTRY.register(key, () -> new SoundEvent(new ResourceLocation(timeless_and_classic.ID, key)));
    }
}
