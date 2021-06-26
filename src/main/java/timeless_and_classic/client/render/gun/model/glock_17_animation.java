package timeless_and_classic.client.render.gun.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.init.ModEnchantments;
import com.mrcrayfish.guns.init.ModItems;
import com.mrcrayfish.guns.item.BarrelItem;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.item.attachment.IAttachment;
import com.mrcrayfish.guns.item.attachment.impl.Attachment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.math.vector.Vector3f;
import timeless_and_classic.client.SpecialModels;

/*
 * Because the revolver has a rotating chamber, we need to render it in a
 * different way than normal items. In this case we are overriding the model.
 */

/**
 * Author: Mr. Pineapple
 */
public class glock_17_animation implements IOverrideModel {

    //The render method, similar to what is in DartEntity. We can render the item
    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {

        matrices.translate(0.01, 0.1, -0.1);
        matrices.rotate(Vector3f.YP.rotationDegrees(-0.5F));

        /*
            // So this area will be tested for the item specific name, allowing the use of custom attachments
        if(Gun.getAttachment(IAttachment.Type.BARREL,stack).getItem().getName() != "")
        {
            RenderUtil.renderModel(SpecialModels.GLOCK_17_SUPPRESSOR_OVERIDE.getModel(), stack, matrices, renderBuffer, light, overlay);
        }
        */

        if(Gun.getAttachment(IAttachment.Type.BARREL,stack).getItem() == ModItems.SILENCER.get())
        {
            RenderUtil.renderModel(SpecialModels.GLOCK_17_SUPPRESSOR_OVERIDE.getModel(), stack, matrices, renderBuffer, light, overlay);
            //Gun.getAttachment(IAttachment.Type.BARREL, GunItem.getItemById(Item.getIdFromItem(stack.getItem())).getDefaultInstance())
        }

        if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.OVER_CAPACITY.get(), entity.getHeldItemMainhand()) > 0 && entity.getHeldItemMainhand().isItemEqual(stack))
        {
            RenderUtil.renderModel(SpecialModels.GLOCK_17_EXTENDED_MAG.getModel(), stack, matrices, renderBuffer, light, overlay);
        }
        else
        {
            RenderUtil.renderModel(SpecialModels.GLOCK_17_STANDARD_MAG.getModel(), stack, matrices, renderBuffer, light, overlay);
        }

        RenderUtil.renderModel(SpecialModels.GLOCK_17.getModel(), stack, matrices, renderBuffer, light, overlay);

            //Always push
            matrices.push();

            CooldownTracker tracker = Minecraft.getInstance().player.getCooldownTracker();
        float cooldownOg = tracker.getCooldown(stack.getItem(), Minecraft.getInstance().getRenderPartialTicks());
        float cooldown = (float) easeInOutBack(cooldownOg);

        if(Gun.hasAmmo(stack))
        {
            // Math provided by Bomb787 on GitHub and Curseforge!!!
            matrices.translate(0, 0, 0.175f * (-4.5 * Math.pow(cooldownOg-0.5, 2) + 1.0));
        }
        else if(!Gun.hasAmmo(stack))
        {
            if(cooldownOg > 0.5){
                // Math provided by Bomb787 on GitHub and Curseforge!!!
                matrices.translate(0, 0, 0.175f * (-4.5 * Math.pow(cooldownOg-0.5, 2) + 1.0));
            }
            else
            {
                matrices.translate(0, 0, 0.175f * (-4.5 * Math.pow(0.5-0.5, 2) + 1.0));
            }
        }

            RenderUtil.renderModel(SpecialModels.GLOCK_17_SLIDE.getModel(), stack, matrices, renderBuffer, light, overlay);

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
