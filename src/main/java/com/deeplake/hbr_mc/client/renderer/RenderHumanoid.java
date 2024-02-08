package com.deeplake.hbr_mc.client.renderer;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.entities.EntityBase;
import com.deeplake.hbr_mc.init.util.DShieldUtil;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//copied from IDL
@SideOnly(Side.CLIENT)
public class RenderHumanoid extends RenderBiped<EntityBase> {
    protected ResourceLocation RES_LOC;
    private static final ResourceLocation DEFAULT_RES_LOC = new ResourceLocation(  ":textures/entity/steve.png");

    float scale = 1f;

    public RenderHumanoid(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPlayer(0f, false), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this);
        this.addLayer(layerbipedarmor);
        RES_LOC = DEFAULT_RES_LOC;
    }

    public RenderHumanoid(RenderManager renderManagerIn, String TexturePath)
    {
        this(renderManagerIn);
        RES_LOC = new ResourceLocation(Main.MODID + ":textures/entity/"+TexturePath+".png");
    }

    public RenderHumanoid(RenderManager renderManagerIn, String TexturePath, float scale) {
        this(renderManagerIn, TexturePath);
        this.scale = scale;
    }

    public ModelPlayer getMainModel()
    {
        return (ModelPlayer)super.getMainModel();
    }

    public void doRender(EntityBase entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        double d0 = y;

        if (entity.isSneaking())
        {
            d0 = y - 0.125D;
        }

        this.setModelVisibilities(entity);

        if (DShieldUtil.isDPDepleted(entity))
        {
            //client only
            entity.setSneaking(true);
        }

//        GlStateManager.enableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
        super.doRender(entity, x, d0, z, entityYaw, partialTicks);
        //GlStateManager.disableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
    }

    private void setModelVisibilities(EntityLivingBase clientPlayer)
    {
        ModelPlayer modelplayer = this.getMainModel();

        ItemStack itemstack = clientPlayer.getHeldItemMainhand();
        ItemStack itemstack1 = clientPlayer.getHeldItemOffhand();
        modelplayer.setVisible(true);
        modelplayer.isSneak = clientPlayer.isSneaking();
        ModelBiped.ArmPose modelbiped$armpose = ModelBiped.ArmPose.EMPTY;
        ModelBiped.ArmPose modelbiped$armpose1 = ModelBiped.ArmPose.EMPTY;

        if (!itemstack.isEmpty())
        {
            modelbiped$armpose = ModelBiped.ArmPose.ITEM;

            if (clientPlayer.getItemInUseCount() > 0)
            {
                EnumAction enumaction = itemstack.getItemUseAction();

                if (enumaction == EnumAction.BLOCK)
                {
                    modelbiped$armpose = ModelBiped.ArmPose.BLOCK;
                }
                else if (enumaction == EnumAction.BOW)
                {
                    modelbiped$armpose = ModelBiped.ArmPose.BOW_AND_ARROW;
                }
            }
        }

        if (!itemstack1.isEmpty())
        {
            modelbiped$armpose1 = ModelBiped.ArmPose.ITEM;

            if (clientPlayer.getItemInUseCount() > 0)
            {
                EnumAction enumaction1 = itemstack1.getItemUseAction();

                if (enumaction1 == EnumAction.BLOCK)
                {
                    modelbiped$armpose1 = ModelBiped.ArmPose.BLOCK;
                }
                // FORGE: fix MC-88356 allow offhand to use bow and arrow animation
                else if (enumaction1 == EnumAction.BOW)
                {
                    modelbiped$armpose1 = ModelBiped.ArmPose.BOW_AND_ARROW;
                }
            }
        }

        if (clientPlayer.getPrimaryHand() == EnumHandSide.RIGHT)
        {
            modelplayer.rightArmPose = modelbiped$armpose;
            modelplayer.leftArmPose = modelbiped$armpose1;
        }
        else
        {
            modelplayer.rightArmPose = modelbiped$armpose1;
            modelplayer.leftArmPose = modelbiped$armpose;
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBase entity)
    {
        return RES_LOC;
    }

    double lastRotate = 0;
    double secondLastRotate = 0;

    protected void applyRotations(EntityBase entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        if (entityLiving.isEntityAlive() && entityLiving.isPlayerSleeping())
        {
//            GlStateManager.rotate(entityLiving.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(this.getDeathMaxRotation(entityLiving), 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
        }
        else if (entityLiving.isElytraFlying())
        {
            super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
            float f = (float)entityLiving.getTicksElytraFlying() + partialTicks;
            float f1 = MathHelper.clamp(f * f / 100.0F, 0.0F, 1.0F);
            GlStateManager.rotate(f1 * (-90.0F - entityLiving.rotationPitch), 1.0F, 0.0F, 0.0F);
            Vec3d vec3d = entityLiving.getLook(partialTicks);
            double d0 = entityLiving.motionX * entityLiving.motionX + entityLiving.motionZ * entityLiving.motionZ;
            double d1 = vec3d.x * vec3d.x + vec3d.z * vec3d.z;

            if (d0 > 0.0D && d1 > 0.0D)
            {
                double d2 = (entityLiving.motionX * vec3d.x + entityLiving.motionZ * vec3d.z) / (Math.sqrt(d0) * Math.sqrt(d1));
                double d3 = entityLiving.motionX * vec3d.z - entityLiving.motionZ * vec3d.x;
                if (Math.abs(d3) > 0.001)
                {
                    //It is unknown why it jiggles sometimes per 7 ticks.
                    double value = (Math.signum(d3) * Math.acos(d2)) * 180.0F / Math.PI;
                    value = (6f * lastRotate + value) / 7f;
                    GlStateManager.rotate((float) value, 0.0F, 1.0F, 0.0F);
                    lastRotate = value;
//                    secondLastRotate = lastRotate;
                }
            }
        }
        else
        {
            super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        }
    }

    @Override
    protected void preRenderCallback(EntityBase entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        GlStateManager.scale(scale, scale, scale);
    }
}
