package timelessandclassic.common;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrcrayfish.guns.client.render.IHeldAnimation;
import com.mrcrayfish.guns.common.GripType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import timelessandclassic.client.render.pose.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: MrCrayfish
 */
public class CustomGripType extends GripType {
    public static final CustomGripType ONE_HANDED_M1911 = new CustomGripType(new ResourceLocation("timelessandclassic", "one_handed_m1911"), new OneHandedPoseHighRes_m1911());
    public static final CustomGripType ONE_HANDED_M1851 = new CustomGripType(new ResourceLocation("timelessandclassic", "one_handed_m1851"), new OneHandedPoseHighRes_m1851());
    public static final CustomGripType TWO_HANDED_M1894 = new CustomGripType(new ResourceLocation("timelessandclassic", "two_handed_hr_m1894"), new TwoHandedPoseHighRes_m1894());
    public static final CustomGripType TWO_HANDED_M1928 = new CustomGripType(new ResourceLocation("timelessandclassic", "two_handed_hr_m1928"), new TwoHandedPoseHighRes_m1928());
    public static final CustomGripType TWO_HANDED_MOSIN = new CustomGripType(new ResourceLocation("timelessandclassic", "two_handed_hr_mosin"), new TwoHandedPoseHighRes_m1928());
    public static final CustomGripType TWO_HANDED_AK47 = new CustomGripType(new ResourceLocation("timelessandclassic", "two_handed_hr_ak47"), new TwoHandedPoseHighRes_ak47());
    public static final CustomGripType TWO_HANDED_M60 = new CustomGripType(new ResourceLocation("timelessandclassic", "two_handed_hr_m60"), new TwoHandedPoseHighRes_m60());

    public static boolean applyBackTransforms(PlayerEntity player, MatrixStack matrixStack) {
        if (player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == Items.ELYTRA) {
            return false;
        }

        matrixStack.rotate(Vector3f.YP.rotationDegrees(180F));
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(180F));

        if (player.isCrouching()) {
            matrixStack.translate(0 * 0.0625, -7 * 0.0625, -4 * 0.0625);
            matrixStack.rotate(Vector3f.XP.rotationDegrees(30F));
        } else {
            matrixStack.translate(0 * 0.0625, -5 * 0.0625, -2 * 0.0625);
        }

        if (!player.getItemStackFromSlot(EquipmentSlotType.CHEST).isEmpty()) {
            matrixStack.translate(0, 0, -1 * 0.0625);
        }

        matrixStack.rotate(Vector3f.ZP.rotationDegrees(-45F));
        matrixStack.scale(0.5F, 0.5F, 0.5F);

        return true;
    }

    /**
     * The grip type map.
     */
    private static final Map<ResourceLocation, com.mrcrayfish.guns.common.GripType> gripTypeMap = new HashMap<>();

    static {
        registerType(ONE_HANDED_M1911);
        registerType(ONE_HANDED_M1851);
        registerType(TWO_HANDED_M1894);
        registerType(TWO_HANDED_M1928);
        registerType(TWO_HANDED_MOSIN);
        registerType(TWO_HANDED_AK47);
    }

    /**
     * Registers a new grip type. If the id already exists, the grip type will simply be ignored.
     *
     * @param type the get of the grip type
     */
    private final ResourceLocation id;
    private final IHeldAnimation heldAnimation;

    public static void registerType(GripType type) {
        gripTypeMap.putIfAbsent(type.getId(), type);
    }

    /**
     * Gets the grip type associated the the id. If the grip type does not exist, it will default to
     * one handed.
     *
     * @param id the id of the grip type
     * @return returns an get of the grip type or ONE_HANDED if it doesn't exist
     */
    public static com.mrcrayfish.guns.common.GripType getType(ResourceLocation id) {
        return gripTypeMap.getOrDefault(id, ONE_HANDED_M1911);
    }


    /**
     * Creates a new grip type.
     *
     * @param id            the id of the grip type
     * @param heldAnimation the animation functions to apply to the held weapon
     */
    public CustomGripType(ResourceLocation id, IHeldAnimation heldAnimation) {
        super(id, heldAnimation);
        this.id = id;
        this.heldAnimation = heldAnimation;
    }

}
