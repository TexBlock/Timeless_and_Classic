package timeless_and_classic.core.registry;

import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.init.ModEnchantments;
import com.mrcrayfish.guns.item.AmmoItem;
import com.mrcrayfish.guns.item.GunItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import timeless_and_classic.core.Config;
import timeless_and_classic.core.timeless_and_classic;
import timeless_and_classic.core.types.TimelessGunItem;

import java.util.Map;


/**
 * Author: ClumsyAlien, codebase and design based off Mr.Pineapple's original addon
 */
public class ItemRegistry {

    // Code saved for later, this is for registering your own attachment attribute to add on to, this one increases accuracy by 25%

    //public static final IGunModifier COMPENSATOR_MODIFIER = new IGunModifier() {
    //    public float modifyProjectileSpread(float spread) {
    //        return spread * 0.75F;
    //    }
    //};



    // Create an object to hold all your items to be registered!
    public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, timeless_and_classic.ID);
    /*
        Register each item, your variable name can be whatever you wish, as long as you keep track of your variable name incase you wish to add more functionality to your weapon!

            ~~~RegistryObject<GunItem>~~~
        Think of a RegistryObject as a new anything we add on to Minecraft, the <> is what type of something we wish to add!

        For this example I am creating a GunItem! This is from the Crayfish mod, we can reuse the GunItem class to create new Guns for our addon, placing our class type in the <>
        will tell Minecraft to create a new GunItem (<GunItem>), and search of all it's resources!

            ~~~ITEM_REGISTRY.register("m1911", () -> new GunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP))~~~
        Now we actually add the item into Minecraft! This will simply tell Minecraft that we expect a new item to be created, the "m1911" string is what I would call, the internal name!

        The internal name is what I call this piece, your internal name is what you will use to define the actual models, and data for your new item!

        (your_internalName).json would be an example for your file name, when creating a GunItem you are required to have both a model defined, and a data file defined!
        Take a look at my project structure, I would recommend you create something very similar, only with replacing "timeless_and_classic" with your own modId!


        () -> new GunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP))
        {

            public boolean hasEffect(ItemStack stack) {
                return false;
            }
        });

        The end of our .register(); method is where we actually finish up creating our GunItem! First we use a lambda to create our new GunItem with the bare minimum...

        ~~~new GunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP)~~~
        Here we are also saying the GunItem can only be in a stack of 1 (Unstackable) and we can also find it in the creative inventory via our own created Group!

        Now this is where I ClumsyAlien do something tricky and well, hacky, I do not recommend doing this at all and keeping your new GunItem registry looking like this...
        ~~~public static final RegistryObject<GunItem> M1911 = ITEM_REGISTRY.register("m1911", () -> new GunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP)));~~~

    ------------------------------------------------------------------------------------------------------------------------------- If creating a basic weapon stop here!

        Once you understand the basic concept of adding an item we can talk about adding extra pieces onto our new Item or in this case, GunItem (GunItem implements Item, we have all the same possibilities as an item)
            Here I actually give Minecraft an extra little instruction, for this example when my m1911 gets enchanted, it will not render the enchantment glow no matter what!

            (sticking this right after running our lambda will allow adding methods / overrides without creating our own GunItem! Currently I will use this as development of my own
            GunTypes are not ready, and I only wish to add one extra detail to the current guns)
            {
                public boolean hasEffect(ItemStack stack)
                {
                    return false;
                }
            }
    */
    public static final RegistryObject<TimelessGunItem> M1911 = ITEM_REGISTRY.register("m1911", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }
    });

    public static final RegistryObject<TimelessGunItem> M1894 = ITEM_REGISTRY.register("m1894", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP)){ public boolean hasEffect(ItemStack stack) {
        return false;
    }});

    public static final RegistryObject<TimelessGunItem> M1851 = ITEM_REGISTRY.register("m1851", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP)){ public boolean hasEffect(ItemStack stack) {
        return false;
    }});

    public static final RegistryObject<TimelessGunItem> M1928 = ITEM_REGISTRY.register("m1928", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }

        @Override
        public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
        {
            Map<Enchantment, Integer> x =  EnchantmentHelper.deserializeEnchantments(stack.getEnchantmentTagList());
            if(Config.COMMON.M1928_trigMax.get() == 0)
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
            }
            else if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TRIGGER_FINGER.get(), stack) > Config.COMMON.M1928_trigMax.get().intValue())
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
                x.put(ModEnchantments.TRIGGER_FINGER.get(), Config.COMMON.M1928_trigMax.get().intValue());
            }
            EnchantmentHelper.setEnchantments(x, stack);
        }
    });

    public static final RegistryObject<TimelessGunItem> MOSIN = ITEM_REGISTRY.register("mosin", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP)){ public boolean hasEffect(ItemStack stack) {
        return false;
    }});

    public static final RegistryObject<TimelessGunItem> AK47 = ITEM_REGISTRY.register("ak47", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }

        @Override
        public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
        {
            Map<Enchantment, Integer> x =  EnchantmentHelper.deserializeEnchantments(stack.getEnchantmentTagList());
            if(Config.COMMON.AK47_trigMax.get() == 0)
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
            }
            else if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TRIGGER_FINGER.get(), stack) > Config.COMMON.AK47_trigMax.get().intValue())
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
                x.put(ModEnchantments.TRIGGER_FINGER.get(), Config.COMMON.AK47_trigMax.get().intValue());
            }
            EnchantmentHelper.setEnchantments(x, stack);
        }
    });
    public static final RegistryObject<TimelessGunItem> M60 = ITEM_REGISTRY.register("m60", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }

        @Override
        public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
        {
            Map<Enchantment, Integer> x =  EnchantmentHelper.deserializeEnchantments(stack.getEnchantmentTagList());
            if(Config.COMMON.M60_trigMax.get() == 0)
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
            }
            else if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TRIGGER_FINGER.get(), stack) > Config.COMMON.M60_trigMax.get().intValue())
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
                x.put(ModEnchantments.TRIGGER_FINGER.get(), Config.COMMON.M60_trigMax.get().intValue());
            }
            EnchantmentHelper.setEnchantments(x, stack);
        }
    });
    public static final RegistryObject<TimelessGunItem> M1917 = ITEM_REGISTRY.register("m1917", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1))//.group(timeless_and_classic.GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }
    });
    public static final RegistryObject<TimelessGunItem> GLOCK_17 = ITEM_REGISTRY.register("glock_17", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.MODERN_GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }
/*
        @Override
        public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
        {
            if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TRIGGER_FINGER.get(), stack) > 0)
            {
                Map<Enchantment, Integer> x =  EnchantmentHelper.deserializeEnchantments(stack.getEnchantmentTagList());
                CompoundNBT y = stack.serializeNBT();

                if (x.containsKey(ModEnchantments.TRIGGER_FINGER.get()) == true)
                {
                    if (y.getBoolean("Auto") == false)
                    {
                        y.remove("Auto");
                        y.putBoolean("Auto",true);
                    }
                }
                stack.deserializeNBT(y);
            }
        }

 */
    });
    public static final RegistryObject<TimelessGunItem> DP_28 = ITEM_REGISTRY.register("dp28", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }

        @Override
        public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
        {
            Map<Enchantment, Integer> x =  EnchantmentHelper.deserializeEnchantments(stack.getEnchantmentTagList());
            if(Config.COMMON.DP28_trigMax.get() == 0)
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
            }
            else if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TRIGGER_FINGER.get(), stack) > Config.COMMON.DP28_trigMax.get().intValue())
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
                x.put(ModEnchantments.TRIGGER_FINGER.get(), Config.COMMON.DP28_trigMax.get().intValue());
            }
            EnchantmentHelper.setEnchantments(x, stack);
        }
    });
    public static final RegistryObject<TimelessGunItem> M16A1 = ITEM_REGISTRY.register("m16a1", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }

        @Override
        public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
        {
            Map<Enchantment, Integer> x =  EnchantmentHelper.deserializeEnchantments(stack.getEnchantmentTagList());
            if(Config.COMMON.M16A1_trigMax.get() == 0)
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
            }
            else if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TRIGGER_FINGER.get(), stack) > Config.COMMON.M16A1_trigMax.get().intValue())
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
                x.put(ModEnchantments.TRIGGER_FINGER.get(), Config.COMMON.M16A1_trigMax.get().intValue());
            }
            EnchantmentHelper.setEnchantments(x, stack);
        }
    });
    public static final RegistryObject<TimelessGunItem> MK18 = ITEM_REGISTRY.register("mk18", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1))//.group(timeless_and_classic.MODERN_GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }
    });
    public static final RegistryObject<TimelessGunItem> STI2011 = ITEM_REGISTRY.register("sti2011", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.MODERN_GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }
    });
    public static final RegistryObject<TimelessGunItem> AK74 = ITEM_REGISTRY.register("ak74", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.MODERN_GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }

        @Override
        public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
        {
            Map<Enchantment, Integer> x =  EnchantmentHelper.deserializeEnchantments(stack.getEnchantmentTagList());
            if(Config.COMMON.AK74_trigMax.get() == 0)
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
            }
            else if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TRIGGER_FINGER.get(), stack) > Config.COMMON.AK74_trigMax.get().intValue())
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
                x.put(ModEnchantments.TRIGGER_FINGER.get(), Config.COMMON.AK74_trigMax.get().intValue());
            }
            EnchantmentHelper.setEnchantments(x, stack);
        }
    });
    public static final RegistryObject<TimelessGunItem> M92FS = ITEM_REGISTRY.register("m92fs", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.MODERN_GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }
    });
    public static final RegistryObject<TimelessGunItem> AR15_HELLMOUTH = ITEM_REGISTRY.register("ar_15_hellmouth", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.MODERN_GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }

        @Override
        public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
        {
            Map<Enchantment, Integer> x =  EnchantmentHelper.deserializeEnchantments(stack.getEnchantmentTagList());
            if(Config.COMMON.AR15HM_trigMax.get() == 0)
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
            }
            else if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TRIGGER_FINGER.get(), stack) > Config.COMMON.AR15HM_trigMax.get().intValue())
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
                x.put(ModEnchantments.TRIGGER_FINGER.get(), Config.COMMON.AR15HM_trigMax.get().intValue());
            }
            EnchantmentHelper.setEnchantments(x, stack);
        }
    });
    public static final RegistryObject<TimelessGunItem> AR15_P = ITEM_REGISTRY.register("ar_15_p", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.MODERN_GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }

        @Override
        public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
        {
            Map<Enchantment, Integer> x =  EnchantmentHelper.deserializeEnchantments(stack.getEnchantmentTagList());
            if(Config.COMMON.AR15P_trigMax.get() == 0)
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
            }
            else if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TRIGGER_FINGER.get(), stack) > Config.COMMON.AR15P_trigMax.get().intValue())
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
                x.put(ModEnchantments.TRIGGER_FINGER.get(), Config.COMMON.AR15P_trigMax.get().intValue());
            }
            EnchantmentHelper.setEnchantments(x, stack);
        }
    });
    public static final RegistryObject<TimelessGunItem> VECTOR45 = ITEM_REGISTRY.register("vector45", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.MODERN_GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }

        @Override
        public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
        {
            Map<Enchantment, Integer> x =  EnchantmentHelper.deserializeEnchantments(stack.getEnchantmentTagList());
            if(Config.COMMON.VECTOR45_trigMax.get() == 0)
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
            }
            else if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TRIGGER_FINGER.get(), stack) > Config.COMMON.AR15P_trigMax.get().intValue())
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
                x.put(ModEnchantments.TRIGGER_FINGER.get(), Config.COMMON.VECTOR45_trigMax.get().intValue());
            }
            EnchantmentHelper.setEnchantments(x, stack);
        }
    });
    public static final RegistryObject<TimelessGunItem> MICRO_UZI = ITEM_REGISTRY.register("micro_uzi", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.MODERN_GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }

        @Override
        public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
        {
            Map<Enchantment, Integer> x =  EnchantmentHelper.deserializeEnchantments(stack.getEnchantmentTagList());
            if(Config.COMMON.VECTOR45_trigMax.get() == 0)
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
            }
            else if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TRIGGER_FINGER.get(), stack) > Config.COMMON.AR15P_trigMax.get().intValue())
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
                x.put(ModEnchantments.TRIGGER_FINGER.get(), Config.COMMON.VECTOR45_trigMax.get().intValue());
            }
            EnchantmentHelper.setEnchantments(x, stack);
        }
    });
    public static final RegistryObject<TimelessGunItem> M4 = ITEM_REGISTRY.register("m4", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.MODERN_GROUP))
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }

        @Override
        public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
        {
            Map<Enchantment, Integer> x =  EnchantmentHelper.deserializeEnchantments(stack.getEnchantmentTagList());
            if(Config.COMMON.AR15P_trigMax.get() == 0)
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
            }
            else if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TRIGGER_FINGER.get(), stack) > Config.COMMON.AR15P_trigMax.get().intValue())
            {
                x.remove(ModEnchantments.TRIGGER_FINGER.get());
                x.put(ModEnchantments.TRIGGER_FINGER.get(), Config.COMMON.AR15P_trigMax.get().intValue());
            }
            EnchantmentHelper.setEnchantments(x, stack);
        }
    });
    public static final RegistryObject<TimelessGunItem> M1911_NETHER = ITEM_REGISTRY.register("m1911_nether", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP).isImmuneToFire())
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }
        public int getItemEnchantability() {
            return 12;
        }
    });
    public static final RegistryObject<TimelessGunItem> MOSBERG590 = ITEM_REGISTRY.register("mosberg590", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP).isImmuneToFire())
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }
    });
    public static final RegistryObject<TimelessGunItem> DB_SHORT = ITEM_REGISTRY.register("db_short", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP).isImmuneToFire())
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }
    });
    public static final RegistryObject<TimelessGunItem> DB_LONG = ITEM_REGISTRY.register("db_long", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP).isImmuneToFire())
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }
    });
    public static final RegistryObject<TimelessGunItem> WALTHER_PPK = ITEM_REGISTRY.register("walther_ppk", () -> new TimelessGunItem(new Item.Properties().maxStackSize(1).group(timeless_and_classic.GROUP).isImmuneToFire())
    {
        public boolean hasEffect(ItemStack stack) {
            return false;
        }
    });
    // Here I also create some new Ammunition for my mod! Not a necessary piece as you can continue using the original "cgm:" ammo!
    public static final RegistryObject<AmmoItem> MAGNUM_BULLET = ITEM_REGISTRY.register("magnumround", () -> new AmmoItem(new Item.Properties().maxStackSize(64).group(timeless_and_classic.AMMO_GROUP)));
    public static final RegistryObject<AmmoItem> BULLET_45 = ITEM_REGISTRY.register("round45", () -> new AmmoItem(new Item.Properties().maxStackSize(64).group(timeless_and_classic.AMMO_GROUP)));
    public static final RegistryObject<AmmoItem> BULLET_30_WIN = ITEM_REGISTRY.register("win_30-30", () -> new AmmoItem(new Item.Properties().maxStackSize(64).group(timeless_and_classic.AMMO_GROUP)));
    public static final RegistryObject<AmmoItem> BULLET_308 = ITEM_REGISTRY.register("bullet_308", () -> new AmmoItem(new Item.Properties().maxStackSize(64).group(timeless_and_classic.AMMO_GROUP)));
    public static final RegistryObject<AmmoItem> BULLET_556 = ITEM_REGISTRY.register("nato_556_bullet", () -> new AmmoItem(new Item.Properties().maxStackSize(64).group(timeless_and_classic.AMMO_GROUP)));
    public static final RegistryObject<AmmoItem> BULLET_9 = ITEM_REGISTRY.register("9mm_round", () -> new AmmoItem(new Item.Properties().maxStackSize(64).group(timeless_and_classic.AMMO_GROUP)));
    public static final RegistryObject<AmmoItem> BULLET_10g = ITEM_REGISTRY.register("10_gauge_round", () -> new AmmoItem(new Item.Properties().maxStackSize(64).group(timeless_and_classic.AMMO_GROUP)));

}
