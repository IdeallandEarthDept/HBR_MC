package com.deeplake.hbr_mc.client.models.entities;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.deeplake.hbr_mc.init.util.CommonDef;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMarionette extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer legs;
	private final ModelRenderer neck;
	private final ModelRenderer head;
	private final ModelRenderer body;

	public ModelMarionette() {
		textureWidth = 32;
		textureHeight = 32;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		legs = new ModelRenderer(this);
		legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(legs);
		legs.cubeList.add(new ModelBox(legs, 16, 0, -1.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F, false));
		legs.cubeList.add(new ModelBox(legs, 0, 0, 0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F, false));

		neck = new ModelRenderer(this);
		neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(neck);
		neck.cubeList.add(new ModelBox(neck, 15, 11, -1.0F, -7.0F, -1.0F, 2, 2, 2, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -6.5F, 0.0F);
		neck.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -2.5F, -4.5F, -2.5F, 5, 5, 5, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 11, -2.0F, -5.0F, -1.5F, 4, 3, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	float headSwing = 10 * CommonDef.DEG_TO_RAD;

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		head.rotateAngleX = (float) (headSwing * Math.sin(ageInTicks * 0.1f));
		head.rotateAngleZ = (float) (headSwing * Math.cos(ageInTicks * 0.1f));
	}
}