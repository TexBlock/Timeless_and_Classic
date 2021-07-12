package timeless_and_classic.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import timeless_and_classic.core.timeless_and_classic;

/*
 * This class will be used to register special models (like the grenade launcher)
 * We can 'copy' from the SpecialModels class in the base gun mod as there
 * isn't an interface provided to implement.
 */

/**
 * Author: Mr. Pineapple
 */
@EventBusSubscriber(modid = timeless_and_classic.ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public enum SpecialModels {
    //The values in this class are stored here so we can call upon them.
    M1911("m1911"),
    M1911_SLIDE("m1911_slide"),
    M1911_STANDARD_MAG("m1911_standard_mag"),
    M1911_LONG_MAG("m1911_long_mag"),
    M1851("m1851"),
    M1851_CYLINDER("m1851_cylinder"),
    M1851_HAMMER("m1851_hammer"),
    M1928("m1928"),
    M1928_BOLT("m1928_bolt"),
    M1928_STICK_MAG("m1928_stick_mag"),
    M1928_DRUM_MAG("m1928_drum_mag"),
    MOSIN("mosin"),
    MOSIN_BOLT("mosin_bolt"),
    AK47("ak47"),
    AK47_BOLT("ak47_bolt"),
    M60("m60"),
    M60_sMAG("m60_standard_mag"),
    M60_eMAG("m60_extended_mag"),
    AK47_OPTIC_MOUNT("ak47_mount"),
    M1917("m1917"),
    M1917_CYLINDER("m1917_cylinder"),
    GLOCK_17("glock_17"),
    GLOCK_17_SLIDE("glock_17_slide"),
    GLOCK_17_STANDARD_MAG("glock_17_standard_mag"),
    GLOCK_17_EXTENDED_MAG("glock_17_extended_mag"),
    GLOCK_17_SUPPRESSOR_OVERIDE("glock_17_suppressor"),
    DP_28("dp28"),
    DP_28_MAG("dp28_mag"),
    DP_28_BOLT("dp28_bolt"),
    M16_A1("m16a1"),
    M16_A1_HANDLE("m16a1_handle"),
    M16_A1_STANDARD_MAG("m16a1_standard_mag"),
    M16_A1_EXTENDED_MAG("m16a1_extended_mag"),
    M16_A1_FRONT_SIGHT("m16a1_front_sight"),
    MK18_BODY("mk18_body"),
    MK18_BOLT("mk18_bolt"),
    MK18_SIGHTS("mk18_sights"),
    MK18_STOCK("mk18_stock"),
    MK18_STANDARD_MAG("mk18_standard_mag"),
    STI2011_BODY("sti2011_body"),
    STI2011_SLIDE("sti2011_slide"),
    STI2011_STANDARD_MAG("sti2011_standard_mag"),
    STI2011_RAIL("sti2011_rail"),
    AK74("ak74"),
    M92FS("m92fs"),
    M92FS_SLIDE("m92fs_slide"),
    M92FS_STANDARD_MAG("m92fs_standard_mag"),
    M92FS_EXTENDED_MAG("m92fs_extended_mag"),
    AR15_HELLMOUTH_BODY("ar_15_hellmouth_body"),
    AR15_HELLMOUTH_BUTT_HEAVY("ar_15_hellmouth_butt_heavy"),
    AR15_HELLMOUTH_BUTT_LIGHTWEIGHT("ar_15_hellmouth_butt_lightweight"),
    AR15_HELLMOUTH_SUPPRESSOR("ar_15_hellmouth_suppressor"),
    AR15_HELLMOUTH_MUZZLE("ar_15_hellmouth_muzzle"),
    AR15_HELLMOUTH_BUTT_TACTICAL("ar_15_hellmouth_butt_tactical"),
    AR15_HELLMOUTH_TACTICAL_GRIP("ar_15_hellmouth_tactical_grip"),
    AR15_HELLMOUTH_LIGHTWEIGHT_GRIP("ar_15_hellmouth_lightweight_grip"),
    AR15_P_BODY("ar_15_p_body"),
    AR15_P_IRONS("ar_15_p_irons"),
    AR15_P_MUZZLE("ar_15_p_muzzle"),
    AR15_P_SUPPRESSOR("ar_15_p_suppressor"),
    AR15_P_TACTICAL_GRIP("ar_15_p_tactical_grip"),
    AR15_BOLT("ar_15_bolt"),
    VECTOR45_BODY("vector45_body"),
    VECTOR45_BOLT("vector45_bolt"),
    VECTOR45_EXTENDED_MAG("vector45_extended_mag"),
    VECTOR45_GRIP("vector45_grip"),
    VECTOR45_HEAVY_STOCK("vector45_heavy_stock"),
    VECTOR45_LIGHT_STOCK("vector45_light_stock"),
    VECTOR45_SIGHT("vector45_sight"),
    VECTOR45_SILENCER("vector45_silencer"),
    VECTOR45_STANDARD_MAG("vector45_standard_mag"),
    VECTOR45_TACTICAL_STOCK("vector45_tactical_stock"),
    MICRO_UZI_BODY("micro_uzi_body"),
    MICRO_UZI_BOLT("micro_uzi_bolt"),
    MICRO_UZI_SIGHT("micro_uzi_sight"),
    MICRO_UZI_SILENCER("micro_uzi_silencer");

    //Variables
    private final ResourceLocation modelLocation;
    private final boolean specialModel;
    @OnlyIn(Dist.CLIENT)
    private IBakedModel cachedModel;

    SpecialModels(String modelName) {
        //Get the file path for the special modes, and set them to true (the are going to be special models)
        this(new ResourceLocation(timeless_and_classic.ID, "special/" + modelName), true);
    }

    //Second Constructor to feed variables
    SpecialModels(ResourceLocation resourceLocation, boolean specialModel) {
        this.modelLocation = resourceLocation;
        this.specialModel = specialModel;
    }

    //Get the item's model
    @OnlyIn(Dist.CLIENT)
    public IBakedModel getModel() {
        if (this.cachedModel == null) {
            IBakedModel model = Minecraft.getInstance().getModelManager().getModel(this.modelLocation);
            if (model == Minecraft.getInstance().getModelManager().getMissingModel()) {
                return model;
            }
            this.cachedModel = model;
        }
        return this.cachedModel;
    }

    //Register a new model to that item
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void register(ModelRegistryEvent event) {
        for (SpecialModels model : values()) {
            if (model.specialModel) {
                ModelLoader.addSpecialModel(model.modelLocation);
            }
        }
    }
    //TODO finish comment
}
