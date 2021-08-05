package timeless_and_classic.client.render.gun.geckoModel;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import timeless_and_classic.core.timeless_and_classic;

public class GlockTest extends AnimatedGeoModel
{

    @Override
    public ResourceLocation getModelLocation(Object o) {
        return new ResourceLocation(timeless_and_classic.ID, "geo/glock17test.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Object o) {
        return new ResourceLocation(timeless_and_classic.ID, "textures/item/jack.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Object o) {
        return new ResourceLocation(timeless_and_classic.ID, "animations/jackinthebox.animation.json");
    }
}
