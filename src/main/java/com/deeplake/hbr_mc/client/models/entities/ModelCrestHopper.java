// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports
package com.deeplake.hbr_mc.client.models.entities;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.deeplake.hbr_mc.init.util.CommonDef;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelCrestHopper extends ModelBase {
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg5;
	private final ModelRenderer leg6;
	private final ModelRenderer center;
	private final ModelRenderer bb_main;

	public ModelCrestHopper() {
		textureWidth = 32;
		textureHeight = 32;

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg1.cubeList.add(new ModelBox(leg1, 4, 7, 1.0F, -7.0F, -6.0F, 1, 7, 1, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg2.cubeList.add(new ModelBox(leg2, 14, 0, -3.0F, -6.0F, -6.0F, 1, 6, 1, 0.0F, false));
		leg2.cubeList.add(new ModelBox(leg2, 17, 6, -3.0F, -7.0F, -6.0F, 1, 1, 1, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg3.cubeList.add(new ModelBox(leg3, 12, 7, 4.0F, -6.0F, -1.0F, 1, 6, 1, 0.0F, false));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg4.cubeList.add(new ModelBox(leg4, 11, 14, -6.0F, -6.0F, -1.0F, 1, 6, 1, 0.0F, false));

		leg5 = new ModelRenderer(this);
		leg5.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg5.cubeList.add(new ModelBox(leg5, 8, 7, -5.0F, -7.0F, 3.0F, 1, 7, 1, 0.0F, false));

		leg6 = new ModelRenderer(this);
		leg6.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg6.cubeList.add(new ModelBox(leg6, 0, 7, 3.0F, -7.0F, 3.0F, 1, 7, 1, 0.0F, false));

		center = new ModelRenderer(this);
		center.setRotationPoint(0.0F, 24.0F, 0.0F);
		center.cubeList.add(new ModelBox(center, 9, 0, -1.0F, -9.0F, -1.0F, 1, 1, 1, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 0, 0, -1.5F, -6.0F, -3.0F, 2, 2, 5, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 0, 0, -1.0F, -4.0F, -1.0F, 1, 4, 1, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 15, 12, -1.0F, -4.0F, -1.5F, 1, 2, 2, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 0, 15, -1.0F, -8.0F, -1.5F, 1, 2, 2, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 16, 8, -4.0F, -8.0F, 2.0F, 1, 2, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 20, 4, -3.0F, -6.0F, 1.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 6, 15, 2.0F, -8.0F, 2.0F, 1, 2, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 3, 20, 1.0F, -6.0F, 1.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 18, 19, 3.0F, -7.0F, -1.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 19, 12, 2.0F, -8.0F, -1.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 19, 10, 1.0F, -7.0F, -1.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 19, -5.0F, -7.0F, -1.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 18, 17, -4.0F, -8.0F, -1.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 15, 18, -3.0F, -7.0F, -1.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 5, 18, -2.0F, -8.0F, -5.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 18, 2, -2.0F, -7.0F, -4.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 18, 0, 0.0F, -7.0F, -4.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 15, 16, 0.0F, -8.0F, -5.0F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		leg5.render(f5);
		leg6.render(f5);
		center.render(f5);
		bb_main.render(f5);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		float legRaise = 0.1f;
		float legOmega = 0.35f;
		center.rotateAngleY = netHeadYaw * CommonDef.DEG_TO_RAD;
		center.rotateAngleX = headPitch * CommonDef.DEG_TO_RAD;

		if (entityIn.onGround)
		{
			float dy1 = (float) (legRaise * Math.sin(legOmega * ageInTicks));
			float dy2 = (float) (legRaise * Math.sin(legOmega * ageInTicks+Math.PI));
			leg1.offsetY = dy1;
			leg2.offsetY = dy2;
			leg3.offsetY = dy2;
			leg4.offsetY = dy1;
			leg5.offsetY = dy1;
			leg6.offsetY = dy2;
		}
		else {
			leg1.offsetY = legRaise * 2;
			leg2.offsetY = legRaise * 2;
			leg3.offsetY = legRaise;
			leg4.offsetY = legRaise;
			leg5.offsetY = legRaise;
			leg6.offsetY = legRaise;
		}
	}
}