package timeless_and_classic.items;

import com.mrcrayfish.guns.common.Gun;

import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.util.GunEnchantmentHelper;
import com.mrcrayfish.guns.util.GunModifierHelper;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.KeybindTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import timeless_and_classic.core.Config;
import timeless_and_classic.util.Process;

import static timeless_and_classic.core.timeless_and_classic.GROUP;

public class TimelessGunItem extends GunItem {
    public TimelessGunItem(Process<Properties> properties)
    {
        super(properties.process(new Properties().maxStackSize(1).group(GROUP)));
    }
    
    public TimelessGunItem() {
        this(properties -> properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {
        Gun modifiedGun = this.getModifiedGun(stack);
        Item ammo = (Item)ForgeRegistries.ITEMS.getValue(modifiedGun.getProjectile().getItem());
        if (ammo != null) {
            tooltip.add((new TranslationTextComponent("info.cgm.ammo_type", new Object[]{(new TranslationTextComponent(ammo.getTranslationKey())).mergeStyle(TextFormatting.GOLD)})).mergeStyle(TextFormatting.DARK_GRAY));
        }

        String additionalDamageText = "";
        CompoundNBT tagCompound = stack.getTag();
        float additionalDamage;
        if (tagCompound != null && tagCompound.contains("AdditionalDamage", 99)) {
            additionalDamage = tagCompound.getFloat("AdditionalDamage");
            additionalDamage += GunModifierHelper.getAdditionalDamage(stack);
            if (additionalDamage > 0.0F) {
                additionalDamageText = TextFormatting.GREEN + " +" + ItemStack.DECIMALFORMAT.format((double)additionalDamage);
            } else if (additionalDamage < 0.0F) {
                additionalDamageText = TextFormatting.RED + " " + ItemStack.DECIMALFORMAT.format((double)additionalDamage);
            }
        }

        additionalDamage = modifiedGun.getProjectile().getDamage();
        additionalDamage = GunModifierHelper.getModifiedProjectileDamage(stack, additionalDamage);
        additionalDamage = GunEnchantmentHelper.getAcceleratorDamage(stack, additionalDamage);
        tooltip.add((new TranslationTextComponent("info.cgm.damage", new Object[]{TextFormatting.GOLD + ItemStack.DECIMALFORMAT.format((double)additionalDamage) + additionalDamageText})).mergeStyle(TextFormatting.DARK_GRAY));
        if (tagCompound != null) {
            if (tagCompound.getBoolean("IgnoreAmmo")) {
                tooltip.add((new TranslationTextComponent("info.cgm.ignore_ammo")).mergeStyle(TextFormatting.AQUA));
            } else {
                int ammoCount = tagCompound.getInt("AmmoCount");
                tooltip.add((new TranslationTextComponent("info.cgm.ammo", new Object[]{TextFormatting.GOLD.toString() + ammoCount + "/" + GunEnchantmentHelper.getAmmoCapacity(stack, modifiedGun)})).mergeStyle(TextFormatting.DARK_GRAY));
            }
        }
        boolean auto = modifiedGun.getGeneral().isAuto();
        int autoRate = modifiedGun.getGeneral().getRate();

        // modifiedGun.serializeNBT().remove("rate"); putInt("rate",modifiedGun.serializeNBT().getInt("rate")+1).;

        if (auto)
        {
            tooltip.add((new TranslationTextComponent("info.timeless_and_classic.auto", new Object[]{TextFormatting.GOLD.toString() + "This weapon is automatic!"})).mergeStyle(TextFormatting.RED));
        }
        tooltip.add((new TranslationTextComponent("info.cgm.attachment_help", new Object[]{(new KeybindTextComponent("key.cgm.attachments")).getString().toUpperCase(Locale.ENGLISH)})).mergeStyle(TextFormatting.YELLOW));

    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return (Integer)Objects.requireNonNull(TextFormatting.GOLD.getColor());
    }
    @Override
    public boolean showDurabilityBar(ItemStack stack)
    {
        if (Config.COMMON.ammoProgressBar.get()) {
            CompoundNBT tagCompound = stack.getOrCreateTag();
            Gun modifiedGun = this.getModifiedGun(stack);
            return !tagCompound.getBoolean("IgnoreAmmo") && tagCompound.getInt("AmmoCount") != GunEnchantmentHelper.getAmmoCapacity(stack, modifiedGun);
        }
        else
            return false;
    }
    
    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }
}