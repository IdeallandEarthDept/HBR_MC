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

public class ModelSmallHopper extends ModelBase {
	private final ModelRenderer base;
	private final ModelRenderer leg;
	private final ModelRenderer bone2;
	private final ModelRenderer bone;
	private final ModelRenderer leg2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer leg3;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer leg4;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer center;

	public ModelSmallHopper() {
		textureWidth = 64;
		textureHeight = 64;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		leg = new ModelRenderer(this);
		leg.setRotationPoint(0.0F, 0.0F, 0.0F);
		base.addChild(leg);
		

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-7.0F, -10.5F, -7.0F);
		leg.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 16, 22, -1.0F, -4.5F, -1.0F, 2, 9, 2, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-7.5F, -3.0F, -7.0F);
		leg.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 24, 24, -0.5F, -3.0F, -1.0F, 1, 6, 2, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(0.0F, 0.0F, 0.0F);
		base.addChild(leg2);
		

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-7.0F, -10.5F, 7.0F);
		leg2.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 8, 22, -1.0F, -4.5F, -1.0F, 2, 9, 2, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-7.5F, -3.0F, 7.0F);
		leg2.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 24, 16, -0.5F, -3.0F, -1.0F, 1, 6, 2, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(0.0F, 0.0F, 0.0F);
		base.addChild(leg3);
		

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(7.0F, -10.5F, 7.0F);
		leg3.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 0, 22, -1.0F, -4.5F, -1.0F, 2, 9, 2, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(7.5F, -3.0F, 7.0F);
		leg3.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 24, 8, -0.5F, -3.0F, -1.0F, 1, 6, 2, 0.0F, false));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(0.0F, 0.0F, -1.0F);
		base.addChild(leg4);
		

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(7.0F, -10.5F, -6.0F);
		leg4.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 16, 11, -1.0F, -4.5F, -1.0F, 2, 9, 2, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(7.5F, -3.0F, -6.0F);
		leg4.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 24, 0, -0.5F, -3.0F, -1.0F, 1, 6, 2, 0.0F, false));

		center = new ModelRenderer(this);
		center.setRotationPoint(0.0F, -11.5F, 0.0F);
		base.addChild(center);
		center.cubeList.add(new ModelBox(center, 0, 0, -0.5F, 3.5F, -0.5F, 1, 2, 1, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 28, 6, -1.0F, -5.5F, -1.0F, 2, 2, 2, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 0, 11, -2.0F, -3.5F, -2.0F, 4, 7, 4, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 0, 0, -3.0F, -2.5F, -3.0F, 6, 5, 6, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		base.render(f5);
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
			leg.offsetY = dy1;
			leg2.offsetY = dy2;
			leg3.offsetY = dy1;
			leg4.offsetY = dy2;
		}
		else {
			leg.offsetY =  legRaise;
			leg2.offsetY = legRaise;
			leg3.offsetY = legRaise;
			leg4.offsetY = legRaise;
		}
	}
}