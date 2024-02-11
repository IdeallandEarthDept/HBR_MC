// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


public class ModelSlasher extends ModelBase {
	private final ModelRenderer base;
	private final ModelRenderer outerRingRotate;
	private final ModelRenderer outerRing;
	private final ModelRenderer thick2;
	private final ModelRenderer innerRingV3;
	private final ModelRenderer thin2;
	private final ModelRenderer innerRingV4;
	private final ModelRenderer blade;
	private final ModelRenderer innerRingV5;
	private final ModelRenderer innerRingRotate;
	private final ModelRenderer innerRing;
	private final ModelRenderer thick;
	private final ModelRenderer innerRingV;
	private final ModelRenderer thin;
	private final ModelRenderer innerRingV2;
	private final ModelRenderer centerBallRotate;
	private final ModelRenderer center;

	public ModelSlasher() {
		textureWidth = 64;
		textureHeight = 64;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		outerRingRotate = new ModelRenderer(this);
		outerRingRotate.setRotationPoint(0.0F, 0.0F, 0.0F);
		base.addChild(outerRingRotate);
		

		outerRing = new ModelRenderer(this);
		outerRing.setRotationPoint(0.0F, 0.0F, 0.0F);
		outerRingRotate.addChild(outerRing);
		setRotationAngle(outerRing, 0.0F, 0.0F, 0.7854F);
		

		thick2 = new ModelRenderer(this);
		thick2.setRotationPoint(0.0F, 0.0F, 0.0F);
		outerRing.addChild(thick2);
		thick2.cubeList.add(new ModelBox(thick2, 0, 16, -7.0F, 6.0F, -1.0F, 13, 1, 2, 0.0F, false));
		thick2.cubeList.add(new ModelBox(thick2, 0, 13, -7.0F, -7.0F, -1.0F, 13, 1, 2, 0.0F, false));

		innerRingV3 = new ModelRenderer(this);
		innerRingV3.setRotationPoint(2.0F, 0.0F, 0.0F);
		thick2.addChild(innerRingV3);
		setRotationAngle(innerRingV3, 0.0F, 0.0F, 1.5708F);
		innerRingV3.cubeList.add(new ModelBox(innerRingV3, 0, 19, -6.0F, 8.0F, -1.0F, 12, 1, 2, 0.0F, false));
		innerRingV3.cubeList.add(new ModelBox(innerRingV3, 0, 10, -7.0F, -5.0F, -1.0F, 14, 1, 2, 0.0F, false));

		thin2 = new ModelRenderer(this);
		thin2.setRotationPoint(0.0F, 0.0F, 0.5F);
		outerRing.addChild(thin2);
		thin2.cubeList.add(new ModelBox(thin2, 0, 26, -5.5F, 5.5F, -1.0F, 11, 2, 1, 0.0F, false));
		thin2.cubeList.add(new ModelBox(thin2, 25, 25, -5.5F, -7.5F, -1.0F, 11, 2, 1, 0.0F, false));

		innerRingV4 = new ModelRenderer(this);
		innerRingV4.setRotationPoint(0.0F, 0.0F, 0.0F);
		thin2.addChild(innerRingV4);
		setRotationAngle(innerRingV4, 0.0F, 0.0F, 1.5708F);
		innerRingV4.cubeList.add(new ModelBox(innerRingV4, 0, 7, -7.5F, 5.5F, -1.0F, 15, 2, 1, 0.0F, false));
		innerRingV4.cubeList.add(new ModelBox(innerRingV4, 0, 4, -7.5F, -7.5F, -1.0F, 15, 2, 1, 0.0F, false));

		blade = new ModelRenderer(this);
		blade.setRotationPoint(0.0F, 0.0F, 1.0F);
		outerRing.addChild(blade);
		blade.cubeList.add(new ModelBox(blade, 0, 24, -6.5F, 6.5F, -1.0F, 13, 2, 0, 0.0F, false));
		blade.cubeList.add(new ModelBox(blade, 0, 22, -6.5F, -8.5F, -1.0F, 13, 2, 0, 0.0F, false));

		innerRingV5 = new ModelRenderer(this);
		innerRingV5.setRotationPoint(0.0F, 0.0F, 0.0F);
		blade.addChild(innerRingV5);
		setRotationAngle(innerRingV5, 0.0F, 0.0F, 1.5708F);
		innerRingV5.cubeList.add(new ModelBox(innerRingV5, 0, 2, -8.5F, 6.5F, -1.0F, 17, 2, 0, 0.0F, false));
		innerRingV5.cubeList.add(new ModelBox(innerRingV5, 0, 0, -8.5F, -8.5F, -1.0F, 17, 2, 0, 0.0F, false));

		innerRingRotate = new ModelRenderer(this);
		innerRingRotate.setRotationPoint(0.0F, 0.0F, 0.0F);
		base.addChild(innerRingRotate);
		

		innerRing = new ModelRenderer(this);
		innerRing.setRotationPoint(0.0F, 0.0F, 0.0F);
		innerRingRotate.addChild(innerRing);
		setRotationAngle(innerRing, 0.0F, 0.0F, 0.7854F);
		

		thick = new ModelRenderer(this);
		thick.setRotationPoint(0.0F, 0.0F, 0.0F);
		innerRing.addChild(thick);
		thick.cubeList.add(new ModelBox(thick, 28, 14, -5.0F, 4.0F, -1.0F, 10, 1, 2, 0.0F, false));
		thick.cubeList.add(new ModelBox(thick, 26, 20, -5.0F, -5.0F, -1.0F, 10, 1, 2, 0.0F, false));

		innerRingV = new ModelRenderer(this);
		innerRingV.setRotationPoint(0.0F, 0.0F, 0.0F);
		thick.addChild(innerRingV);
		setRotationAngle(innerRingV, 0.0F, 0.0F, 1.5708F);
		innerRingV.cubeList.add(new ModelBox(innerRingV, 30, 11, -4.0F, 4.0F, -1.0F, 8, 1, 2, 0.0F, false));
		innerRingV.cubeList.add(new ModelBox(innerRingV, 30, 8, -4.0F, -5.0F, -1.0F, 8, 1, 2, 0.0F, false));

		thin = new ModelRenderer(this);
		thin.setRotationPoint(0.0F, 0.0F, 0.5F);
		innerRing.addChild(thin);
		thin.cubeList.add(new ModelBox(thin, 0, 32, -3.5F, 3.5F, -1.0F, 9, 2, 1, 0.0F, false));
		thin.cubeList.add(new ModelBox(thin, 0, 29, -5.5F, -5.5F, -1.0F, 9, 2, 1, 0.0F, false));

		innerRingV2 = new ModelRenderer(this);
		innerRingV2.setRotationPoint(0.0F, 0.0F, 0.0F);
		thin.addChild(innerRingV2);
		setRotationAngle(innerRingV2, 0.0F, 0.0F, 1.5708F);
		innerRingV2.cubeList.add(new ModelBox(innerRingV2, 30, 17, -3.5F, 3.5F, -1.0F, 9, 2, 1, 0.0F, false));
		innerRingV2.cubeList.add(new ModelBox(innerRingV2, 32, 4, -5.5F, -5.5F, -1.0F, 9, 2, 1, 0.0F, false));

		centerBallRotate = new ModelRenderer(this);
		centerBallRotate.setRotationPoint(0.0F, 0.0F, 0.0F);
		base.addChild(centerBallRotate);
		

		center = new ModelRenderer(this);
		center.setRotationPoint(0.0F, 0.0F, 0.0F);
		centerBallRotate.addChild(center);
		center.cubeList.add(new ModelBox(center, 20, 28, -2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 6, 35, -1.0F, -1.0F, 2.0F, 2, 2, 1, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 0, 35, -1.0F, -1.0F, -3.0F, 2, 2, 1, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 34, 0, -0.5F, -0.5F, -5.0F, 1, 1, 2, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 32, 28, -0.5F, -0.5F, 3.0F, 1, 1, 2, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 34, 34, -1.0F, -3.0F, -1.0F, 2, 1, 2, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 34, 34, -1.0F, 2.0F, -1.0F, 2, 1, 2, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 12, 35, -3.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 12, 35, 2.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 26, 23, 2.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));
		center.cubeList.add(new ModelBox(center, 26, 23, -3.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		base.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}