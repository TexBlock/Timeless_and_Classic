package timeless_and_classic.core.types;

import com.mrcrayfish.guns.init.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;
import timeless_and_classic.util.Process;

import java.util.Map;

public class AutomaticTimelessGunItem extends TimelessGunItem {

    private final ForgeConfigSpec.IntValue TRIG_MAX;

    public AutomaticTimelessGunItem(Process<Properties> properties, ForgeConfigSpec.IntValue TRIG_MAX) {
        super(properties);
        this.TRIG_MAX = TRIG_MAX;
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        Map<Enchantment, Integer> x = EnchantmentHelper.deserializeEnchantments(stack.getEnchantmentTagList());
        if (TRIG_MAX.get() == 0) {
            x.remove(ModEnchantments.TRIGGER_FINGER.get());
        } else if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TRIGGER_FINGER.get(), stack) > TRIG_MAX.get()) {
            x.remove(ModEnchantments.TRIGGER_FINGER.get());
            x.put(ModEnchantments.TRIGGER_FINGER.get(), TRIG_MAX.get());
        }
        EnchantmentHelper.setEnchantments(x, stack);
    }
}