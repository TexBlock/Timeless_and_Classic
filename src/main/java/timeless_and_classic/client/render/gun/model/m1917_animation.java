package timeless_and_classic.client.render.gun.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.math.vector.Vector3f;
import timeless_and_classic.client.SpecialModels;

/*
 * Because the revolver has a rotating chamber, we need to render it in a
 * different way than normal items. In this case we are overriding the model.
 */

/**
 * Author: ClumsyAlien, based off Mr. Pineapple
 */
public class m1917_animation implements IOverrideModel {

    //The render method, similar to what is in DartEntity. We can render the item
    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {

        RenderUtil.renderModel(SpecialModels.M1917.getModel(), stack, matrices, renderBuffer, light, overlay);
        //Always push
        matrices.push();

        //We're getting the cooldown tracker for the item - items like the sword, ender pearl, and chorus fruit all have this too.
        CooldownTracker tracker = Minecraft.getInstance().player.getCooldownTracker();
        float cooldown = tracker.getCooldown(stack.getItem(), Minecraft.getInstance().getRenderPartialTicks());
        cooldown = (float) easeInOutBack(cooldown);

        //Here we're moving the model into position
        matrices.translate(-0.570*cooldown, -5.0 * 0.0625, 0);

        //We rotate the chamber part of the model according to the cooldown variable above, which is manipulated by the method provided below.
        matrices.rotate(Vector3f.ZN.rotationDegrees(45F * cooldown));
        //Then move it
        matrices.translate(0.0, 5.0 * 0.0625, 0.0);

        if(cooldown!=0)
            matrices.translate(0.0, 0.248*cooldown, 0.0);


        //Render the chamber part of the gun
        RenderUtil.renderModel(SpecialModels.M1917_CYLINDER.getModel(), stack, matrices, renderBuffer, light, overlay);

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
