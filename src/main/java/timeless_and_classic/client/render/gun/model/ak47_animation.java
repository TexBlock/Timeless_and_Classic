package timeless_and_classic.client.render.gun.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;
import com.mrcrayfish.guns.init.ModEnchantments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import timeless_and_classic.client.SpecialModels;

/*
 * Because the revolver has a rotating chamber, we need to render it in a
 * different way than normal items. In this case we are overriding the model.
 */

/**
 * Author: Mr. Pineapple
 */
public class ak47_animation implements IOverrideModel {

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
    /*
        if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.OVER_CAPACITY.get(), entity.getHeldItemMainhand()) > 0 && entity.getHeldItemMainhand().isItemEqual(stack))
        {
            RenderUtil.renderModel(SpecialModels.M1928_DRUM_MAG.getModel(), stack, matrices, renderBuffer, light, overlay);
        }
        else
        {
            RenderUtil.renderModel(SpecialModels.M1928_STICK_MAG.getModel(), stack, matrices, renderBuffer, light, overlay);
        }
    */
        RenderUtil.renderModel(SpecialModels.AK47.getModel(), stack, matrices, renderBuffer, light, overlay);

            //Always push
            matrices.push();

            //We're getting the cooldown tracker for the item - items like the sword, ender pearl, and chorus fruit all have this too.
            CooldownTracker tracker = Minecraft.getInstance().player.getCooldownTracker();
            float cooldownOg = tracker.getCooldown(stack.getItem(), Minecraft.getInstance().getRenderPartialTicks());
            float cooldown = (float) easeInOutBack(cooldownOg);

            matrices.translate(0, 0, 0.248f * cooldown);

            RenderUtil.renderModel(SpecialModels.AK47_BOLT.getModel(), stack, matrices, renderBuffer, light, overlay);

            //Always pop
            matrices.pop();
    }

    //Same method from GrenadeLauncherModel, to make a smooth rotation of the chamber.
    private double easeInOutBack(double x) {
        double c1 = 1.70158;
        double c2 = c1 * 1.525;
        return (x < 0.5 ? (Math.pow(2 * x, 2) * ((c2 + 1) * 2 * x - c2)) / 2 : (Math.pow(2 * x - 2, 2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2);
    }

    //TODO comments
}
