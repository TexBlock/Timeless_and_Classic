package timeless_and_classic.util;

public class TimelessClientUtil
{
/*
    public static final ResourceLocation null_texture = new ResourceLocation(HeroesUnited.MODID + ":textures/null.png");

    public static <M extends PlayerModel<AbstractClientPlayerEntity>> void renderModel(PlayerRenderer renderer, M model, AbstractClientPlayerEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, IVertexBuilder builder, int light, int overlay, float red, float green, float blue, float alpha, float limbSwing, float limbSwingAmount, float ageInTicks, float headPitch, float netHeadYaw) {
        MinecraftForge.EVENT_BUS.post(new HUSetRotationAnglesEvent(entity, model, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch));
        HUClientUtil.copyAnglesToWear(model);

        if (!MinecraftForge.EVENT_BUS.post(new HURenderPlayerEvent.Pre(entity, renderer, matrixStack, buffer, builder, light, overlay, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch))) {
            model.renderToBuffer(matrixStack, builder, light, overlay, red, green, blue, alpha);
        }
        MinecraftForge.EVENT_BUS.post(new HURenderPlayerEvent.Post(entity, renderer, matrixStack, buffer, builder, light, overlay, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch));
    }

    public static int getLivingOverlay(LivingEntity entity) {
        return LivingRenderer.getOverlayCoords(entity, 0.0F);
    }

    public static void drawArmWithLightning(MatrixStack matrix, IRenderTypeBuffer bufferIn, PlayerRenderer renderer, AbstractClientPlayerEntity player, HandSide side, double y , int packedLightIn, Color color) {
        for (int i = 0; i < 3; i++) {
            matrix.pushPose();
            renderer.getModel().translateToHand(side, matrix);
            matrix.scale(0.05F, 0.06F, 0.05F);
            matrix.translate(i * (side == HandSide.LEFT ? 1 : -1), 10, 0);
            renderLightning(player.level.random, matrix, bufferIn, packedLightIn, y, i, color);
            matrix.popPose();
        }
    }

    public static void hideSuitPlayerWear(PlayerEntity player, PlayerModel model) {
        if (player.getItemBySlot(HEAD).getItem() instanceof SuitItem) {
            model.hat.visible = false;
        }
        if (player.getItemBySlot(CHEST).getItem() instanceof SuitItem) {
            model.jacket.visible = false;
            model.rightSleeve.visible = false;
            model.leftSleeve.visible = false;
        }

        if (player.getItemBySlot(FEET).getItem() instanceof SuitItem
                || player.getItemBySlot(LEGS).getItem() instanceof SuitItem) {
            model.rightPants.visible = false;
            model.leftPants.visible = false;
        }
    }

    public static class CustomRenderState extends RenderState.TexturingState {
        public CustomRenderState(Runnable start, Runnable end) {
            super("offset_texturing_custom", start, end);
        }
    }

    public static ModelRenderer getModelRendererById(PlayerModel model, String name) {
        switch (name) {
            case "bipedHead": return model.head;
            case "bipedBody": return model.body;
            case "bipedRightArm": return model.rightArm;
            case "bipedLeftArm": return model.leftArm;
            case "bipedRightLeg": return model.rightLeg;
            case "bipedLeftLeg": return model.leftLeg;
            default: return null;
        }
    }

    public static void resetModelRenderer(ModelRenderer renderer) {
        renderer.xRot = renderer.yRot = renderer.zRot = 0.0F;
        renderer.setPos(0, 0, 0);
    }

    public static void copyAnglesToWear(PlayerModel model) {
        model.hat.copyFrom(model.head);
        model.jacket.copyFrom(model.body);
        model.rightSleeve.copyFrom(model.rightArm);
        model.leftSleeve.copyFrom(model.leftArm);
        model.leftPants.copyFrom(model.leftLeg);
        model.rightPants.copyFrom(model.rightLeg);
    }

    public static void copyModelRotations(ModelRenderer to, ModelRenderer from) {
        to.xRot = from.xRot;
        to.yRot = from.yRot;
        to.zRot = from.zRot;
    }

    public static void renderLightning(Random random, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, double y, int j, Color color) {
        float[] afloat = new float[8], afloat1 = new float[8];
        float f = 0.0F;
        float f1 = 0.0F;
        IVertexBuilder builder = bufferIn.getBuffer(HUClientUtil.HURenderTypes.LASER);
        Matrix4f m4f = matrixStackIn.last().pose();
        long seed = random.nextLong();
        Random randPrev = new Random(seed), rand = new Random(seed);

        for (int i = 7; i >= 0; --i) {
            afloat[i] = f;
            afloat1[i] = f1;
            f += (float) (randPrev.nextInt(11) - 5);
            f1 += (float) (randPrev.nextInt(11) - 5);
        }


        for (int k = 0; k < 3; ++k) {
            int l = 7;
            int i1 = 0;
            if (k > 0) {
                l = 7 - k;
                i1 = l - 2;
            }

            float f2 = afloat[l] - f;
            float f3 = afloat1[l] - f1;

            for (int j1 = l; j1 >= i1; --j1) {
                float f4 = f2;
                float f5 = f3;
                f2 += (float) (rand.nextInt(11) - 5);
                f3 += (float) (rand.nextInt(11) - 5);

                float f6 = 0.1F + j * 0.05F;

                renderLightningPart(m4f, builder, f2, f3, j1, (float)y, f4, f5, f6, false, false, true, false, packedLightIn, color);
                renderLightningPart(m4f, builder, f2, f3, j1, (float)y, f4, f5, f6, true, false, true, true, packedLightIn, color);
                renderLightningPart(m4f, builder, f2, f3, j1, (float)y, f4, f5, f6, true, true, false, true, packedLightIn, color);
                renderLightningPart(m4f, builder, f2, f3, j1, (float)y, f4, f5, f6, false, true, false, false, packedLightIn, color);
            }
        }
    }

    private static void renderLightningPart(Matrix4f matrix4f, IVertexBuilder builder, float x, float z, int y, float y2, float x2, float z2, float additional, boolean p_229116_12_, boolean p_229116_13_, boolean p_229116_14_, boolean p_229116_15_, int packedLight, Color color) {
        builder.vertex(matrix4f, x + (p_229116_12_ ? additional : -additional), y * y2, z + (p_229116_13_ ? additional : -additional)).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F).uv2(packedLight).endVertex();
        builder.vertex(matrix4f, x2 + (p_229116_12_ ? additional : -additional), (y + 1) * y2, z2 + (p_229116_13_ ? additional : -additional)).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F).uv2(packedLight).endVertex();
        builder.vertex(matrix4f, x2 + (p_229116_14_ ? additional : -additional), (y + 1) * y2, z2 + (p_229116_15_ ? additional : -additional)).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F).uv2(packedLight).endVertex();
        builder.vertex(matrix4f, x + (p_229116_14_ ? additional : -additional), y * y2, z + (p_229116_15_ ? additional : -additional)).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F).uv2(packedLight).endVertex();
    }

    public static ResourceLocation fileToTexture(File file) {
        NativeImage nativeImage = null;
        try {
            nativeImage = NativeImage.read(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Minecraft.getInstance().getTextureManager().register("file_" + System.currentTimeMillis(), new DynamicTexture(nativeImage));
    }

 */
}
