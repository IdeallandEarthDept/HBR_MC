package com.deeplake.hbr_mc.client;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.DShieldUtil;
import com.deeplake.hbr_mc.items.seraph.SeraphUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.Stack;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Main.MODID)
public class RenderDP {
    //todo: there is a bug interacting with Neat.
    //When neat is out of render distance and hides the bar, it will disable item render.
    //this does not happen when my render system is turned off.
    @SubscribeEvent
    public static void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getMinecraft();

        if(!Minecraft.isGuiEnabled() || !ModConfig.GUI_CONF.RENDER_DP)
            return;

        Entity cameraEntity = mc.getRenderViewEntity();
        BlockPos renderingVector = cameraEntity.getPosition();
        Frustum frustum = new Frustum();

        float partialTicks = event.getPartialTicks();
        double viewX = cameraEntity.lastTickPosX + (cameraEntity.posX - cameraEntity.lastTickPosX) * partialTicks;
        double viewY = cameraEntity.lastTickPosY + (cameraEntity.posY - cameraEntity.lastTickPosY) * partialTicks;
        double viewZ = cameraEntity.lastTickPosZ + (cameraEntity.posZ - cameraEntity.lastTickPosZ) * partialTicks;
        frustum.setPosition(viewX, viewY, viewZ);


       List<Entity> entities = mc.world.loadedEntityList;

        for(Entity entity : entities)
            if(entity != null && entity instanceof EntityLivingBase && entity.isInRangeToRender3d(renderingVector.getX(), renderingVector.getY(), renderingVector.getZ()) && (entity.ignoreFrustumCheck || frustum.isBoundingBoxInFrustum(entity.getEntityBoundingBox())) && entity.isEntityAlive() && entity.getRecursivePassengers().isEmpty())
            {
                if (entity == mc.player && mc.gameSettings.thirdPersonView == 0)
                {
                    continue;
                }
                renderDP((EntityLivingBase) entity, partialTicks, cameraEntity);
            }
    }


    //ref: Neat
    public static void renderDP(EntityLivingBase passedEntity, float partialTicks, Entity viewPoint) {
        Stack<EntityLivingBase> ridingStack = new Stack();

        EntityLivingBase entity = passedEntity;
        ridingStack.push(entity);

        while(entity.getRidingEntity() != null && entity.getRidingEntity() instanceof EntityLivingBase) {
            entity = (EntityLivingBase) entity.getRidingEntity();
            ridingStack.push(entity);
        }

        Minecraft mc = Minecraft.getMinecraft();

        float pastTranslate = 0F;
        while(!ridingStack.isEmpty()) {
            entity = ridingStack.pop();

            String entityID = EntityList.getEntityString(entity);

            processing: {
                float distance = passedEntity.getDistance(viewPoint);
                if(distance > ModConfig.GUI_CONF.MAX_RENDER_HUD_DISTANCE || !passedEntity.canEntityBeSeen(viewPoint) || entity.isInvisible())
                    break processing;

                double x = passedEntity.lastTickPosX + (passedEntity.posX - passedEntity.lastTickPosX) * partialTicks;
                double y = passedEntity.lastTickPosY + (passedEntity.posY - passedEntity.lastTickPosY) * partialTicks;
                double z = passedEntity.lastTickPosZ + (passedEntity.posZ - passedEntity.lastTickPosZ) * partialTicks;

                float scale = 0.026666672F;
                float maxDP = getDPMax(entity);
                float remainDP = (float) DShieldUtil.getRemainDP(entity);

                if(maxDP <= 0)
                    break processing;

                RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

                GlStateManager.pushMatrix();
                GlStateManager.translate((float) (x - renderManager.viewerPosX), (float) (y - renderManager.viewerPosY + passedEntity.height + ModConfig.GUI_CONF.GUI_Y), (float) (z - renderManager.viewerPosZ));
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
                GlStateManager.scale(-scale, -scale, scale);
                boolean lighting = GL11.glGetBoolean(GL11.GL_LIGHTING);
                GlStateManager.disableLighting();
                GlStateManager.depthMask(false);
                GlStateManager.disableDepth();
                GlStateManager.disableTexture2D();
                GlStateManager.enableBlend();
//                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder buffer = tessellator.getBuffer();

                float padding = 1;
                int bgHeight = 1;
                int barHeight = 2;
                double size = ModConfig.GUI_CONF.RENDER_HUD_SIZE;

                int r = 0;
                int g = 255;
                int b = 255;
//
//                ItemStack stack = null;
//
//                if(entity instanceof IMob) {
//                    r = 255;
//                    g = 0;
//                    EnumCreatureAttribute attr = entity.getCreatureAttribute();
//                    switch(attr) {
//                        case ARTHROPOD:
//                            stack = new ItemStack(Items.SPIDER_EYE);
//                            break;
//                        case UNDEAD:
//                            stack = new ItemStack(Items.ROTTEN_FLESH);
//                            break;
//                        default:
//                            stack = new ItemStack(Items.SKULL, 1, 4);
//                    }
//                }

//                int armor = entity.getTotalArmorValue();

//                boolean useHue = !NeatConfig.colorByType;
//                if(useHue) {
//                    float hue = Math.max(0F, (remainDP / maxDP) / 3F - 0.07F);
//                    Color color = Color.getHSBColor(hue, 1F, 1F);
//                    r = color.getRed();
//                    g = color.getGreen();
//                    b = color.getBlue();
//                }

                GlStateManager.translate(0F, pastTranslate, 0F);

                float half = 0.5F;
//                String name = I18n.format(entity.getDisplayName().getFormattedText());
//                if(entity instanceof EntityLiving && ((EntityLiving) entity).hasCustomName())
//                    name = TextFormatting.BOLD + ((EntityLiving) entity).getCustomNameTag();
//                float namel = mc.fontRenderer.getStringWidth(name) * half;
//                if(namel + 20 > size * 2)
//                    size = namel / 2F + 10F;
                float healthSize = (float) (size * (remainDP / maxDP));

                // Background
                buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                buffer.pos(-size - padding, -bgHeight, 0.0D).color(0, 0, 0, 64).endVertex();
                buffer.pos(-size - padding, barHeight + padding, 0.0D).color(0, 0, 0, 64).endVertex();
                buffer.pos(size + padding, barHeight + padding, 0.0D).color(0, 0, 0, 64).endVertex();
                buffer.pos(size + padding, -bgHeight, 0.0D).color(0, 0, 0, 64).endVertex();
                tessellator.draw();

                // Health Bar
                buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                buffer.pos(-size, 0, 0.0D).color(r, g, b, 127).endVertex();
                buffer.pos(-size, barHeight, 0.0D).color(r, g, b, 127).endVertex();
                buffer.pos(healthSize * 2 - size, barHeight, 0.0D).color(r, g, b, 127).endVertex();
                buffer.pos(healthSize * 2 - size, 0, 0.0D).color(r, g, b, 127).endVertex();
                tessellator.draw();

                GlStateManager.disableBlend();
                GlStateManager.enableDepth();
                GlStateManager.depthMask(true);
                if(lighting)
                    GlStateManager.enableLighting();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.popMatrix();

//                pastTranslate -= bgHeight + barHeight + padding;
            }
        }
    }

    private static float getDPMax(EntityLivingBase entityLivingBase) {
        if (entityLivingBase instanceof EntityPlayer)
        {
            ItemStack stack = entityLivingBase.getHeldItemMainhand();
            if (SeraphUtil.isSeraph(stack))
            {
                return stack.getMaxDamage();
            }
            else
            {
                return 0;
            }
        }
        return (float) RegisterAttr.getAttrValue(entityLivingBase, RegisterAttr.DP_MAX);
    }
}
