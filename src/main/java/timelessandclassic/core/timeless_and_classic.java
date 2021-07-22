package timelessandclassic.core;

import com.mrcrayfish.guns.client.render.gun.ModelOverrides;
import com.mrcrayfish.guns.common.GripType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import timelessandclassic.client.TimelessKeyBinds;
import timelessandclassic.client.handlers.HClient;
import timelessandclassic.client.render.gun.model.*;
import timelessandclassic.client.render.pose.*;
import timelessandclassic.common.CustomGripType;
import timelessandclassic.common.network.HPacket;
import timelessandclassic.core.registry.*;


/**
 * Author: ClumsyAlien, codebase and design based off Mr.Pineapple's original addon
 */
@Mod(timeless_and_classic.ID)
public class timeless_and_classic {
    //This variable is our mods ID - this must be coherent across the project
    public static final String ID = "timelessandclassic";

    /*
     * This is our creative tab that we will add our items to.
     * If you wanted, you could just add them to the Gun Mods tab.
     * We pass in our ID to this so we can name it in the lang file.
     */
    public static final ItemGroup GROUP = new ItemGroup(ID) {
        //Here we create the icon for the tab
        //If you wanted a normal item here then you can just return an ItemStack
        @Override
        public ItemStack createIcon() {
            //Get the Item in a new ItemStack
            ItemStack stack = new ItemStack(ItemRegistry.M1911.get());
            //Here we add ammunition to the gun so it doesn't have the re-fill bar under the item
            stack.getOrCreateTag().putInt("AmmoCount", ItemRegistry.M1911.get().getGun().getGeneral().getMaxAmmo());
            //We now return the stack which has added ammunition
            return stack;
        }
    };
    public static final ItemGroup MODERN_GROUP = new ItemGroup("timeless_and_modern") {
        //Here we create the icon for the tab
        //If you wanted a normal item here then you can just return an ItemStack
        @Override
        public ItemStack createIcon() {
            //Get the Item in a new ItemStack
            ItemStack stack = new ItemStack(ItemRegistry.STI2011.get());
            //Here we add ammunition to the gun so it doesn't have the re-fill bar under the item
            stack.getOrCreateTag().putInt("AmmoCount", ItemRegistry.STI2011.get().getGun().getGeneral().getMaxAmmo());
            //We now return the stack which has added ammunition
            return stack;
        }
    };
    public static final ItemGroup AMMO_GROUP = new ItemGroup("timeless_and_ammunition") {
        //Here we create the icon for the tab
        //If you wanted a normal item here then you can just return an ItemStack
        @Override
        public ItemStack createIcon() {
            //Get the Item in a new ItemStack
            ItemStack stack = new ItemStack(ItemRegistry.BULLET_308.get());
            //We now return the stack which has added ammunition
            return stack;
        }
    };
    //What needs to be called the the event bus
    public timeless_and_classic() {
        //Here we add the config to the mod - remember to do this for the server and client if you have them

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.commonSpec);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
        //Register the Deferred Register from our Registry classes
        //EntityRegistry.ENTITY_REGISTRY.register(bus);
        ItemRegistry.ITEM_REGISTRY.register(bus);
        TimelessBlocks.REGISTER.register(bus);
        SoundRegistry.SOUND_REGISTRY.register(bus);
        TileEntities.REGISTER.register(bus);
        TimelessContainers.REGISTER.register(bus);
        TimelessRecipeRegistry.REGISTER.register(bus);
        //Call the setup methods from below and add them to the bus
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        // bus.addListener(this::clientLoad);
    }

    void commonSetup(FMLCommonSetupEvent event) {
        // ProjectileManager.getInstance().registerFactory(ItemRegistry.DART.get(), ((world, livingEntity, itemStack, gunItem, gun) -> new DartEntity(EntityRegistry.DART.get(), world, livingEntity, itemStack, gunItem, gun)));
        GripType.registerType(new CustomGripType(new ResourceLocation("timelessandclassic", "one_handed_m1911"), new OneHandedPoseHighRes_m1911()));
        GripType.registerType(new CustomGripType(new ResourceLocation("timelessandclassic", "one_handed_m1851"), new OneHandedPoseHighRes_m1851()));
        GripType.registerType(new CustomGripType(new ResourceLocation("timelessandclassic", "two_handed_m1894"), new TwoHandedPoseHighRes_m1894()));
        GripType.registerType(new CustomGripType(new ResourceLocation("timelessandclassic", "two_handed_m1928"), new TwoHandedPoseHighRes_m1928()));
        GripType.registerType(new CustomGripType(new ResourceLocation("timelessandclassic", "two_handed_mosin"), new TwoHandedPoseHighRes_mosin()));
        GripType.registerType(new CustomGripType(new ResourceLocation("timelessandclassic", "two_handed_ak47"), new TwoHandedPoseHighRes_ak47()));
        GripType.registerType(new CustomGripType(new ResourceLocation("timelessandclassic", "two_handed_m60"), new TwoHandedPoseHighRes_m60()));
        GripType.registerType(new CustomGripType(new ResourceLocation("timelessandclassic", "two_handed_vector"), new TwoHandedPoseHighRes_vector()));

        HPacket.init();
    }

    void clientSetup(FMLClientSetupEvent event) {
        ModelOverrides.register(ItemRegistry.M1911.get(), new m1911_animation());
        ModelOverrides.register(ItemRegistry.M1851.get(), new m1851_animation());
        ModelOverrides.register(ItemRegistry.M1928.get(), new m1928_animation());
        ModelOverrides.register(ItemRegistry.MOSIN.get(), new mosin_animation());
        ModelOverrides.register(ItemRegistry.AK47.get(), new ak47_animation());
        ModelOverrides.register(ItemRegistry.M60.get(), new m60_animation());
        ModelOverrides.register(ItemRegistry.M1917.get(), new m1917_animation());
        ModelOverrides.register(ItemRegistry.GLOCK_17.get(), new glock_17_animation());
        ModelOverrides.register(ItemRegistry.DP_28.get(), new dp28_animation());
        ModelOverrides.register(ItemRegistry.M16A1.get(), new m16a1_animation());
        ModelOverrides.register(ItemRegistry.MK18.get(), new mk18_animation());
        ModelOverrides.register(ItemRegistry.STI2011.get(), new sti2011_animation());
        ModelOverrides.register(ItemRegistry.AK74.get(), new ak74_animation());
        ModelOverrides.register(ItemRegistry.M92FS.get(), new m92fs_animation());
        ModelOverrides.register(ItemRegistry.AR15_HELLMOUTH.get(), new ar15_hellmouth_animation());
        ModelOverrides.register(ItemRegistry.AR15_P.get(), new ar15_p_animation());
        ModelOverrides.register(ItemRegistry.VECTOR45.get(), new vector45_animation());
        ModelOverrides.register(ItemRegistry.MICRO_UZI.get(), new micro_uzi_animation());
        ModelOverrides.register(ItemRegistry.M1911_NETHER.get(), new m1911_nether_animation());
        ModelOverrides.register(ItemRegistry.MOSBERG590.get(), new mosberg590_animation());
        ModelOverrides.register(ItemRegistry.WALTHER_PPK.get(), new walther_ppk_animation());
        ModelOverrides.register(ItemRegistry.M4.get(), new m4_animation());
        ModelOverrides.register(ItemRegistry.M24.get(), new m24_animation());

        HClient.setup();
        TimelessKeyBinds.register();

        RenderTypeLookup.setRenderLayer(TimelessBlocks.MAGNUMBOX.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TimelessBlocks.BOX_45.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TimelessBlocks.BOX_WIN_30.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TimelessBlocks.BOX_308.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TimelessBlocks.BOX_556.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TimelessBlocks.BOX_9.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TimelessBlocks.BOX_10g.get(), RenderType.getCutout());
    }


}
