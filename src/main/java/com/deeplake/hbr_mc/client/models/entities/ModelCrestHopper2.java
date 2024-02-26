package com.deeplake.hbr_mc.client.models.entities;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

import com.deeplake.hbr_mc.init.util.CommonDef;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCrestHopper2 extends ModelBase {
	private final ModelRenderer Crest_Hopper;
	private final ModelRenderer All_Body;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer All_Leg;
	private final ModelRenderer Right_leg;
	private final ModelRenderer Right_front_leg;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer Right_front_Left_front_leg;
	private final ModelRenderer cube_r16;
	private final ModelRenderer cube_r17;
	private final ModelRenderer cube_r18;
	private final ModelRenderer cube_r19;
	private final ModelRenderer cube_r20;
	private final ModelRenderer cube_r21;
	private final ModelRenderer cube_r22;
	private final ModelRenderer cube_r23;
	private final ModelRenderer cube_r24;
	private final ModelRenderer cube_r25;
	private final ModelRenderer cube_r26;
	private final ModelRenderer cube_r27;
	private final ModelRenderer Right_middle_leg;
	private final ModelRenderer cube_r28;
	private final ModelRenderer cube_r29;
	private final ModelRenderer Right_middle_Left_front_leg;
	private final ModelRenderer cube_r30;
	private final ModelRenderer cube_r31;
	private final ModelRenderer cube_r32;
	private final ModelRenderer cube_r33;
	private final ModelRenderer cube_r34;
	private final ModelRenderer cube_r35;
	private final ModelRenderer cube_r36;
	private final ModelRenderer cube_r37;
	private final ModelRenderer cube_r38;
	private final ModelRenderer Right_hind_leg;
	private final ModelRenderer cube_r39;
	private final ModelRenderer cube_r40;
	private final ModelRenderer Right_hind_Left_front_leg;
	private final ModelRenderer cube_r41;
	private final ModelRenderer cube_r42;
	private final ModelRenderer cube_r43;
	private final ModelRenderer cube_r44;
	private final ModelRenderer cube_r45;
	private final ModelRenderer cube_r46;
	private final ModelRenderer cube_r47;
	private final ModelRenderer cube_r48;
	private final ModelRenderer cube_r49;
	private final ModelRenderer Left_leg;
	private final ModelRenderer Left_front_leg;
	private final ModelRenderer cube_r50;
	private final ModelRenderer cube_r51;
	private final ModelRenderer cube_r52;
	private final ModelRenderer cube_r53;
	private final ModelRenderer Left_front_Left_front_leg;
	private final ModelRenderer cube_r54;
	private final ModelRenderer cube_r55;
	private final ModelRenderer cube_r56;
	private final ModelRenderer cube_r57;
	private final ModelRenderer cube_r58;
	private final ModelRenderer cube_r59;
	private final ModelRenderer cube_r60;
	private final ModelRenderer cube_r61;
	private final ModelRenderer cube_r62;
	private final ModelRenderer cube_r63;
	private final ModelRenderer cube_r64;
	private final ModelRenderer cube_r65;
	private final ModelRenderer Left_middle_leg;
	private final ModelRenderer cube_r66;
	private final ModelRenderer cube_r67;
	private final ModelRenderer Left_middle_Left_front_leg;
	private final ModelRenderer cube_r68;
	private final ModelRenderer cube_r69;
	private final ModelRenderer cube_r70;
	private final ModelRenderer cube_r71;
	private final ModelRenderer cube_r72;
	private final ModelRenderer cube_r73;
	private final ModelRenderer cube_r74;
	private final ModelRenderer cube_r75;
	private final ModelRenderer cube_r76;
	private final ModelRenderer Left_hind_leg;
	private final ModelRenderer cube_r77;
	private final ModelRenderer cube_r78;
	private final ModelRenderer Left_hind_Left_front_leg;
	private final ModelRenderer cube_r79;
	private final ModelRenderer cube_r80;
	private final ModelRenderer cube_r81;
	private final ModelRenderer cube_r82;
	private final ModelRenderer cube_r83;
	private final ModelRenderer cube_r84;
	private final ModelRenderer cube_r85;
	private final ModelRenderer cube_r86;
	private final ModelRenderer cube_r87;

	public ModelCrestHopper2() {
		textureWidth = 256;
		textureHeight = 256;

		Crest_Hopper = new ModelRenderer(this);
		Crest_Hopper.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		All_Body = new ModelRenderer(this);
		All_Body.setRotationPoint(0.0F, -31.5582F, 0.0175F);
		Crest_Hopper.addChild(All_Body);
		All_Body.cubeList.add(new ModelBox(All_Body, 0, 0, -4.0F, -4.4418F, -13.0175F, 8, 8, 25, 0.0F, false));
		All_Body.cubeList.add(new ModelBox(All_Body, 0, 33, -2.0F, -15.4418F, -3.0175F, 4, 44, 4, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(2.0F, 28.5582F, 0.9825F);
		All_Body.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.0873F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 44, 79, -4.0F, -25.0F, -4.0F, 4, 25, 4, 0.01F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(2.0F, -15.4418F, 0.9825F);
		All_Body.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.1745F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 26, 92, -4.0F, 0.0F, -4.0F, 4, 11, 4, 0.01F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(4.0F, -4.9271F, -13.0175F);
		All_Body.addChild(cube_r3);
		setRotationAngle(cube_r3, 1.4835F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 36, 66, -8.0F, 0.0F, -1.0F, 8, 6, 1, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(4.0F, -4.6845F, 13.5682F);
		All_Body.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.7854F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 82, -8.0F, 0.0F, -1.0F, 8, 7, 7, 0.01F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(4.0F, -4.9271F, -13.0175F);
		All_Body.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.7854F, 0.0F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 78, 83, -8.0F, 0.0F, -6.0F, 8, 6, 6, 0.01F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(4.0F, -5.3916F, 12.8611F);
		All_Body.addChild(cube_r6);
		setRotationAngle(cube_r6, -1.5272F, 0.0F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 0, -8.0F, 0.0F, 0.0F, 8, 22, 1, 0.01F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(4.0F, 4.5079F, 12.8611F);
		All_Body.addChild(cube_r7);
		setRotationAngle(cube_r7, 1.5272F, 0.0F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 60, 83, -8.0F, -22.0F, 0.0F, 8, 22, 1, 0.01F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(9.171F, -1.548F, 1.9825F);
		All_Body.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.1309F, 0.1309F, -0.829F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 48, 63, -5.9867F, -6.3403F, -12.5719F, 4, 4, 12, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(9.171F, -1.548F, 1.9825F);
		All_Body.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.1309F, -0.1309F, -0.829F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 68, 67, -5.9867F, -6.3403F, 0.5719F, 4, 4, 12, 0.09F, false));

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(5.2515F, -1.6886F, -0.0175F);
		All_Body.addChild(cube_r10);
		setRotationAngle(cube_r10, -0.1309F, -0.1309F, 0.829F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 16, 66, -7.3098F, 3.8732F, -7.9582F, 4, 4, 12, 0.0F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(-6.0515F, -1.6886F, 1.9825F);
		All_Body.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.1309F, 0.1309F, 0.829F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 66, 36, 0.0F, -4.0F, 0.0F, 4, 4, 12, 0.09F, false));

		All_Leg = new ModelRenderer(this);
		All_Leg.setRotationPoint(0.0F, -28.0F, 0.0F);
		Crest_Hopper.addChild(All_Leg);
		

		Right_leg = new ModelRenderer(this);
		Right_leg.setRotationPoint(0.0F, 1.0F, 0.0F);
		All_Leg.addChild(Right_leg);
		

		Right_front_leg = new ModelRenderer(this);
		Right_front_leg.setRotationPoint(3.6216F, -0.9763F, 5.1332F);
		Right_leg.addChild(Right_front_leg);
		

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(9.0F, -8.0F, 12.0F);
		Right_front_leg.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.6779F, -0.6002F, -0.9591F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, 86, 45, -5.0F, -2.0F, -2.0F, 5, 1, 2, 0.0F, false));

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(8.5657F, -8.7373F, 11.4825F);
		Right_front_leg.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.8608F, -0.1666F, -1.4292F);
		cube_r13.cubeList.add(new ModelBox(cube_r13, 70, 36, -3.3F, -2.0F, -2.0F, 2, 1, 2, 0.0F, false));
		cube_r13.cubeList.add(new ModelBox(cube_r13, 41, 118, -4.0F, -1.0F, -2.0F, 4, 1, 2, 0.0F, false));

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(3.9055F, -3.143F, 4.3729F);
		Right_front_leg.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.3441F, -0.8192F, -0.456F);
		cube_r14.cubeList.add(new ModelBox(cube_r14, 86, 37, -7.5F, -1.0F, -1.0F, 15, 2, 2, 0.0F, false));

		cube_r15 = new ModelRenderer(this);
		cube_r15.setRotationPoint(3.9055F, -3.143F, 4.3729F);
		Right_front_leg.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.5031F, -0.7471F, -0.6807F);
		cube_r15.cubeList.add(new ModelBox(cube_r15, 86, 41, -7.5F, -1.0F, -1.0F, 15, 2, 2, 0.01F, false));

		Right_front_Left_front_leg = new ModelRenderer(this);
		Right_front_Left_front_leg.setRotationPoint(8.2787F, -4.2781F, 9.3827F);
		Right_front_leg.addChild(Right_front_Left_front_leg);
		

		cube_r16 = new ModelRenderer(this);
		cube_r16.setRotationPoint(3.0F, 3.0F, 1.0F);
		Right_front_Left_front_leg.addChild(cube_r16);
		setRotationAngle(cube_r16, -0.8025F, -0.178F, 1.1796F);
		cube_r16.cubeList.add(new ModelBox(cube_r16, 56, 79, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

		cube_r17 = new ModelRenderer(this);
		cube_r17.setRotationPoint(6.822F, 19.614F, 6.7764F);
		Right_front_Left_front_leg.addChild(cube_r17);
		setRotationAngle(cube_r17, -0.8439F, -0.3267F, 1.3447F);
		cube_r17.cubeList.add(new ModelBox(cube_r17, 66, 29, -18.0F, 0.0F, 0.0F, 18, 2, 2, 0.0F, false));

		cube_r18 = new ModelRenderer(this);
		cube_r18.setRotationPoint(7.2499F, 28.3396F, 9.5107F);
		Right_front_Left_front_leg.addChild(cube_r18);
		setRotationAngle(cube_r18, -0.7209F, -0.544F, 1.0385F);
		cube_r18.cubeList.add(new ModelBox(cube_r18, 89, 117, 2.1105F, -1.738F, -2.0F, 4, 1, 3, 0.03F, false));

		cube_r19 = new ModelRenderer(this);
		cube_r19.setRotationPoint(7.2499F, 28.3396F, 9.5107F);
		Right_front_Left_front_leg.addChild(cube_r19);
		setRotationAngle(cube_r19, -0.5374F, -0.7254F, 0.7318F);
		cube_r19.cubeList.add(new ModelBox(cube_r19, 23, 107, -0.6497F, 0.1799F, -2.0F, 7, 1, 3, 0.02F, false));

		cube_r20 = new ModelRenderer(this);
		cube_r20.setRotationPoint(7.2499F, 28.3396F, 9.5107F);
		Right_front_Left_front_leg.addChild(cube_r20);
		setRotationAngle(cube_r20, -0.7574F, -0.4851F, 1.1126F);
		cube_r20.cubeList.add(new ModelBox(cube_r20, 67, 114, -1.2614F, -1.928F, -2.0F, 4, 2, 3, 0.0F, false));

		cube_r21 = new ModelRenderer(this);
		cube_r21.setRotationPoint(5.9643F, 24.3396F, 7.9786F);
		Right_front_Left_front_leg.addChild(cube_r21);
		setRotationAngle(cube_r21, -0.7002F, -0.5724F, 0.9995F);
		cube_r21.cubeList.add(new ModelBox(cube_r21, 51, 114, -1.293F, 0.1051F, -2.0F, 5, 1, 3, -0.01F, false));

		cube_r22 = new ModelRenderer(this);
		cube_r22.setRotationPoint(5.9643F, 24.3396F, 7.9786F);
		Right_front_Left_front_leg.addChild(cube_r22);
		setRotationAngle(cube_r22, -0.8335F, -0.2975F, 1.3106F);
		cube_r22.cubeList.add(new ModelBox(cube_r22, 106, 86, -1.8844F, -1.7509F, -2.0F, 7, 2, 3, 0.01F, false));

		cube_r23 = new ModelRenderer(this);
		cube_r23.setRotationPoint(5.5144F, 24.3396F, 7.4424F);
		Right_front_Left_front_leg.addChild(cube_r23);
		setRotationAngle(cube_r23, -0.7399F, -0.5148F, 1.0762F);
		cube_r23.cubeList.add(new ModelBox(cube_r23, 15, 115, -5.0F, -1.7F, -2.0F, 5, 1, 3, -0.01F, false));

		cube_r24 = new ModelRenderer(this);
		cube_r24.setRotationPoint(5.5144F, 24.3396F, 7.4424F);
		Right_front_Left_front_leg.addChild(cube_r24);
		setRotationAngle(cube_r24, -0.8492F, -0.2324F, 1.3708F);
		cube_r24.cubeList.add(new ModelBox(cube_r24, 0, 113, -6.0F, -2.0F, -2.0F, 6, 2, 3, 0.0F, false));

		cube_r25 = new ModelRenderer(this);
		cube_r25.setRotationPoint(0.2746F, 9.7553F, 2.0849F);
		Right_front_Left_front_leg.addChild(cube_r25);
		setRotationAngle(cube_r25, -0.8012F, -0.3931F, 1.2154F);
		cube_r25.cubeList.add(new ModelBox(cube_r25, 117, 56, 0.0F, -2.0F, -2.0F, 4, 1, 2, 0.0F, false));
		cube_r25.cubeList.add(new ModelBox(cube_r25, 97, 22, 0.0F, -1.0F, -2.0F, 11, 1, 2, 0.0F, false));

		cube_r26 = new ModelRenderer(this);
		cube_r26.setRotationPoint(-1.5554F, -0.8699F, -0.096F);
		Right_front_Left_front_leg.addChild(cube_r26);
		setRotationAngle(cube_r26, -0.8555F, -0.1996F, 1.4002F);
		cube_r26.cubeList.add(new ModelBox(cube_r26, 58, 106, 0.0F, -2.0F, -2.0F, 11, 2, 2, 0.0F, false));

		cube_r27 = new ModelRenderer(this);
		cube_r27.setRotationPoint(6.822F, 19.614F, 6.7764F);
		Right_front_Left_front_leg.addChild(cube_r27);
		setRotationAngle(cube_r27, -0.8335F, -0.2975F, 1.3106F);
		cube_r27.cubeList.add(new ModelBox(cube_r27, 16, 52, -23.0F, 0.0F, 0.0F, 23, 2, 2, 0.01F, false));

		Right_middle_leg = new ModelRenderer(this);
		Right_middle_leg.setRotationPoint(3.0362F, -1.5966F, -1.0F);
		Right_leg.addChild(Right_middle_leg);
		

		cube_r28 = new ModelRenderer(this);
		cube_r28.setRotationPoint(0.4638F, 0.5966F, 0.0F);
		Right_middle_leg.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.0F, 0.0F, -0.5672F);
		cube_r28.cubeList.add(new ModelBox(cube_r28, 95, 18, -0.5F, -1.0F, -1.0F, 14, 2, 2, 0.01F, false));

		cube_r29 = new ModelRenderer(this);
		cube_r29.setRotationPoint(6.3548F, -2.1504F, 0.0F);
		Right_middle_leg.addChild(cube_r29);
		setRotationAngle(cube_r29, 0.0F, 0.0F, -0.3054F);
		cube_r29.cubeList.add(new ModelBox(cube_r29, 78, 95, -7.0F, -2.0F, -1.0F, 14, 2, 2, 0.0F, false));

		Right_middle_Left_front_leg = new ModelRenderer(this);
		Right_middle_Left_front_leg.setRotationPoint(11.7621F, -5.3652F, -0.3478F);
		Right_middle_leg.addChild(Right_middle_Left_front_leg);
		

		cube_r30 = new ModelRenderer(this);
		cube_r30.setRotationPoint(14.856F, 33.7547F, -0.3596F);
		Right_middle_Left_front_leg.addChild(cube_r30);
		setRotationAngle(cube_r30, -0.0202F, 0.0387F, 0.7414F);
		cube_r30.cubeList.add(new ModelBox(cube_r30, 18, 111, -7.8238F, -1.9731F, -1.1466F, 8, 2, 2, 0.01F, false));

		cube_r31 = new ModelRenderer(this);
		cube_r31.setRotationPoint(9.4912F, 28.1322F, -0.1956F);
		Right_middle_Left_front_leg.addChild(cube_r31);
		setRotationAngle(cube_r31, -0.0038F, 0.0435F, 1.1344F);
		cube_r31.cubeList.add(new ModelBox(cube_r31, 118, 31, -2.3F, -1.0F, -1.0F, 4, 2, 2, 0.0F, false));

		cube_r32 = new ModelRenderer(this);
		cube_r32.setRotationPoint(14.856F, 33.7547F, -0.3596F);
		Right_middle_Left_front_leg.addChild(cube_r32);
		setRotationAngle(cube_r32, -0.0309F, 0.0308F, 0.4359F);
		cube_r32.cubeList.add(new ModelBox(cube_r32, 79, 112, -7.2386F, -1.8288F, -1.1466F, 8, 2, 2, 0.0F, false));

		cube_r33 = new ModelRenderer(this);
		cube_r33.setRotationPoint(2.7449F, 5.5351F, 1.3478F);
		Right_middle_Left_front_leg.addChild(cube_r33);
		setRotationAngle(cube_r33, 0.0F, 0.0F, 1.2654F);
		cube_r33.cubeList.add(new ModelBox(cube_r33, 57, 58, -1.0F, -2.0F, -3.0F, 19, 2, 3, 0.0F, false));

		cube_r34 = new ModelRenderer(this);
		cube_r34.setRotationPoint(6.5723F, 3.8664F, 2.3478F);
		Right_middle_Left_front_leg.addChild(cube_r34);
		setRotationAngle(cube_r34, 0.0F, 0.0436F, 1.3526F);
		cube_r34.cubeList.add(new ModelBox(cube_r34, 41, 20, 0.0F, 0.0F, -2.0F, 26, 3, 2, 0.01F, false));

		cube_r35 = new ModelRenderer(this);
		cube_r35.setRotationPoint(6.5723F, 3.8664F, 2.3478F);
		Right_middle_Left_front_leg.addChild(cube_r35);
		setRotationAngle(cube_r35, 0.0F, 0.0F, 1.3526F);
		cube_r35.cubeList.add(new ModelBox(cube_r35, 16, 43, 0.0F, 0.0F, -4.0F, 26, 3, 2, 0.01F, false));

		cube_r36 = new ModelRenderer(this);
		cube_r36.setRotationPoint(2.0723F, -3.9279F, 2.3478F);
		Right_middle_Left_front_leg.addChild(cube_r36);
		setRotationAngle(cube_r36, 0.0F, 0.0F, 1.0472F);
		cube_r36.cubeList.add(new ModelBox(cube_r36, 104, 25, 0.0F, 0.0F, -4.0F, 9, 2, 4, 0.01F, false));

		cube_r37 = new ModelRenderer(this);
		cube_r37.setRotationPoint(2.0723F, -3.9279F, 2.3478F);
		Right_middle_Left_front_leg.addChild(cube_r37);
		setRotationAngle(cube_r37, 0.0F, 0.7418F, 1.1781F);
		cube_r37.cubeList.add(new ModelBox(cube_r37, 16, 72, 0.0F, 0.0F, -3.0F, 3, 3, 3, 0.0F, false));

		cube_r38 = new ModelRenderer(this);
		cube_r38.setRotationPoint(2.0723F, -3.9279F, 2.3478F);
		Right_middle_Left_front_leg.addChild(cube_r38);
		setRotationAngle(cube_r38, 0.0F, 0.0F, 1.1781F);
		cube_r38.cubeList.add(new ModelBox(cube_r38, 100, 79, 0.0F, 0.0F, -4.0F, 9, 3, 4, 0.0F, false));

		Right_hind_leg = new ModelRenderer(this);
		Right_hind_leg.setRotationPoint(3.0837F, -0.5966F, -3.6985F);
		Right_leg.addChild(Right_hind_leg);
		

		cube_r39 = new ModelRenderer(this);
		cube_r39.setRotationPoint(-0.6692F, 0.5966F, -0.0254F);
		Right_hind_leg.addChild(cube_r39);
		setRotationAngle(cube_r39, -0.3298F, 0.4703F, -0.6469F);
		cube_r39.cubeList.add(new ModelBox(cube_r39, 95, 8, -0.5F, -1.0F, -1.0F, 14, 2, 2, 0.01F, false));

		cube_r40 = new ModelRenderer(this);
		cube_r40.setRotationPoint(4.2993F, -2.1504F, -3.1907F);
		Right_hind_leg.addChild(cube_r40);
		setRotationAngle(cube_r40, -0.1893F, 0.538F, -0.3578F);
		cube_r40.cubeList.add(new ModelBox(cube_r40, 95, 13, -7.0F, -2.0F, -1.0F, 14, 2, 2, 0.0F, false));

		Right_hind_Left_front_leg = new ModelRenderer(this);
		Right_hind_Left_front_leg.setRotationPoint(8.5428F, -5.4133F, -6.1313F);
		Right_hind_leg.addChild(Right_hind_Left_front_leg);
		

		cube_r41 = new ModelRenderer(this);
		cube_r41.setRotationPoint(12.4662F, 33.8028F, -8.5434F);
		Right_hind_Left_front_leg.addChild(cube_r41);
		setRotationAngle(cube_r41, 0.3931F, 0.443F, 0.8434F);
		cube_r41.cubeList.add(new ModelBox(cube_r41, 41, 110, -7.8238F, -1.9731F, -1.1466F, 8, 2, 2, 0.01F, false));

		cube_r42 = new ModelRenderer(this);
		cube_r42.setRotationPoint(8.0563F, 27.4835F, -5.5549F);
		Right_hind_Left_front_leg.addChild(cube_r42);
		setRotationAngle(cube_r42, 0.5028F, 0.3096F, 1.1393F);
		cube_r42.cubeList.add(new ModelBox(cube_r42, 118, 35, -2.2645F, -0.5582F, -1.0F, 4, 2, 2, 0.0F, false));

		cube_r43 = new ModelRenderer(this);
		cube_r43.setRotationPoint(12.4662F, 33.8028F, -8.5434F);
		Right_hind_Left_front_leg.addChild(cube_r43);
		setRotationAngle(cube_r43, 0.2365F, 0.5385F, 0.5139F);
		cube_r43.cubeList.add(new ModelBox(cube_r43, 61, 110, -7.2386F, -1.8288F, -1.1466F, 8, 2, 2, 0.0F, false));

		cube_r44 = new ModelRenderer(this);
		cube_r44.setRotationPoint(6.9345F, 3.9145F, -1.8092F);
		Right_hind_Left_front_leg.addChild(cube_r44);
		setRotationAngle(cube_r44, 0.5596F, 0.1536F, 1.4093F);
		cube_r44.cubeList.add(new ModelBox(cube_r44, 41, 10, 0.0F, 0.0F, -2.0F, 26, 3, 2, 0.01F, false));

		cube_r45 = new ModelRenderer(this);
		cube_r45.setRotationPoint(6.9345F, 3.9145F, -1.8092F);
		Right_hind_Left_front_leg.addChild(cube_r45);
		setRotationAngle(cube_r45, 0.5564F, 0.1166F, 1.386F);
		cube_r45.cubeList.add(new ModelBox(cube_r45, 41, 15, 0.0F, 0.0F, -4.0F, 26, 3, 2, 0.01F, false));

		cube_r46 = new ModelRenderer(this);
		cube_r46.setRotationPoint(3.1393F, -3.8797F, 0.6087F);
		Right_hind_Left_front_leg.addChild(cube_r46);
		setRotationAngle(cube_r46, 0.5042F, 0.272F, 1.1177F);
		cube_r46.cubeList.add(new ModelBox(cube_r46, 0, 103, 0.0F, 0.0F, -4.0F, 9, 2, 4, 0.01F, false));

		cube_r47 = new ModelRenderer(this);
		cube_r47.setRotationPoint(3.1692F, 5.5832F, -0.596F);
		Right_hind_Left_front_leg.addChild(cube_r47);
		setRotationAngle(cube_r47, 0.546F, 0.1623F, 1.3109F);
		cube_r47.cubeList.add(new ModelBox(cube_r47, 63, 53, -1.0F, -2.0F, -3.0F, 19, 2, 3, 0.0F, false));

		cube_r48 = new ModelRenderer(this);
		cube_r48.setRotationPoint(3.1393F, -3.8797F, 0.6087F);
		Right_hind_Left_front_leg.addChild(cube_r48);
		setRotationAngle(cube_r48, 0.7992F, 0.8058F, 1.7523F);
		cube_r48.cubeList.add(new ModelBox(cube_r48, 103, 117, 0.0F, 0.0F, -3.0F, 3, 3, 3, 0.0F, false));

		cube_r49 = new ModelRenderer(this);
		cube_r49.setRotationPoint(3.1393F, -3.8797F, 0.6087F);
		Right_hind_Left_front_leg.addChild(cube_r49);
		setRotationAngle(cube_r49, 0.532F, 0.2071F, 1.2347F);
		cube_r49.cubeList.add(new ModelBox(cube_r49, 78, 99, 0.0F, 0.0F, -4.0F, 9, 3, 4, 0.0F, false));

		Left_leg = new ModelRenderer(this);
		Left_leg.setRotationPoint(0.0F, 1.0F, 0.0F);
		All_Leg.addChild(Left_leg);
		

		Left_front_leg = new ModelRenderer(this);
		Left_front_leg.setRotationPoint(-3.6216F, -0.9763F, 5.1332F);
		Left_leg.addChild(Left_front_leg);
		

		cube_r50 = new ModelRenderer(this);
		cube_r50.setRotationPoint(-9.0F, -8.0F, 12.0F);
		Left_front_leg.addChild(cube_r50);
		setRotationAngle(cube_r50, 0.6779F, 0.6002F, 0.9591F);
		cube_r50.cubeList.add(new ModelBox(cube_r50, 72, 33, 0.0F, -2.0F, -2.0F, 5, 1, 2, 0.0F, false));

		cube_r51 = new ModelRenderer(this);
		cube_r51.setRotationPoint(-8.5657F, -8.7373F, 11.4825F);
		Left_front_leg.addChild(cube_r51);
		setRotationAngle(cube_r51, 0.8608F, 0.1666F, 1.4292F);
		cube_r51.cubeList.add(new ModelBox(cube_r51, 16, 21, 1.3F, -2.0F, -2.0F, 2, 1, 2, 0.0F, false));
		cube_r51.cubeList.add(new ModelBox(cube_r51, 118, 39, 0.0F, -1.0F, -2.0F, 4, 1, 2, 0.0F, false));

		cube_r52 = new ModelRenderer(this);
		cube_r52.setRotationPoint(-3.9055F, -3.143F, 4.3729F);
		Left_front_leg.addChild(cube_r52);
		setRotationAngle(cube_r52, 0.3441F, 0.8192F, 0.456F);
		cube_r52.cubeList.add(new ModelBox(cube_r52, 68, 63, -7.5F, -1.0F, -1.0F, 15, 2, 2, 0.0F, false));

		cube_r53 = new ModelRenderer(this);
		cube_r53.setRotationPoint(-3.9055F, -3.143F, 4.3729F);
		Left_front_leg.addChild(cube_r53);
		setRotationAngle(cube_r53, 0.5031F, 0.7471F, 0.6807F);
		cube_r53.cubeList.add(new ModelBox(cube_r53, 86, 33, -7.5F, -1.0F, -1.0F, 15, 2, 2, 0.01F, false));

		Left_front_Left_front_leg = new ModelRenderer(this);
		Left_front_Left_front_leg.setRotationPoint(-8.2787F, -4.2781F, 9.3827F);
		Left_front_leg.addChild(Left_front_Left_front_leg);
		

		cube_r54 = new ModelRenderer(this);
		cube_r54.setRotationPoint(-3.0F, 3.0F, 1.0F);
		Left_front_Left_front_leg.addChild(cube_r54);
		setRotationAngle(cube_r54, -0.8025F, 0.178F, -1.1796F);
		cube_r54.cubeList.add(new ModelBox(cube_r54, 36, 73, 0.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

		cube_r55 = new ModelRenderer(this);
		cube_r55.setRotationPoint(-6.822F, 19.614F, 6.7764F);
		Left_front_Left_front_leg.addChild(cube_r55);
		setRotationAngle(cube_r55, -0.8439F, 0.3267F, -1.3447F);
		cube_r55.cubeList.add(new ModelBox(cube_r55, 66, 25, 0.0F, 0.0F, 0.0F, 18, 2, 2, 0.0F, false));

		cube_r56 = new ModelRenderer(this);
		cube_r56.setRotationPoint(-7.2499F, 28.3396F, 9.5107F);
		Left_front_Left_front_leg.addChild(cube_r56);
		setRotationAngle(cube_r56, -0.7209F, 0.544F, -1.0385F);
		cube_r56.cubeList.add(new ModelBox(cube_r56, 78, 116, -6.1105F, -1.738F, -2.0F, 4, 1, 3, 0.03F, false));

		cube_r57 = new ModelRenderer(this);
		cube_r57.setRotationPoint(-7.2499F, 28.3396F, 9.5107F);
		Left_front_Left_front_leg.addChild(cube_r57);
		setRotationAngle(cube_r57, -0.5374F, 0.7254F, -0.7318F);
		cube_r57.cubeList.add(new ModelBox(cube_r57, 106, 91, -6.3503F, 0.1799F, -2.0F, 7, 1, 3, 0.02F, false));

		cube_r58 = new ModelRenderer(this);
		cube_r58.setRotationPoint(-7.2499F, 28.3396F, 9.5107F);
		Left_front_Left_front_leg.addChild(cube_r58);
		setRotationAngle(cube_r58, -0.7574F, 0.4851F, -1.1126F);
		cube_r58.cubeList.add(new ModelBox(cube_r58, 30, 87, -2.7386F, -1.928F, -2.0F, 4, 2, 3, 0.0F, false));

		cube_r59 = new ModelRenderer(this);
		cube_r59.setRotationPoint(-5.9643F, 24.3396F, 7.9786F);
		Left_front_Left_front_leg.addChild(cube_r59);
		setRotationAngle(cube_r59, -0.7002F, 0.5724F, -0.9995F);
		cube_r59.cubeList.add(new ModelBox(cube_r59, 35, 114, -3.707F, 0.1051F, -2.0F, 5, 1, 3, -0.01F, false));

		cube_r60 = new ModelRenderer(this);
		cube_r60.setRotationPoint(-5.9643F, 24.3396F, 7.9786F);
		Left_front_Left_front_leg.addChild(cube_r60);
		setRotationAngle(cube_r60, -0.8335F, 0.2975F, -1.3106F);
		cube_r60.cubeList.add(new ModelBox(cube_r60, 23, 82, -5.1156F, -1.7509F, -2.0F, 7, 2, 3, 0.01F, false));

		cube_r61 = new ModelRenderer(this);
		cube_r61.setRotationPoint(-5.5144F, 24.3396F, 7.4424F);
		Left_front_Left_front_leg.addChild(cube_r61);
		setRotationAngle(cube_r61, -0.7399F, 0.5148F, -1.0762F);
		cube_r61.cubeList.add(new ModelBox(cube_r61, 114, 114, 0.0F, -1.7F, -2.0F, 5, 1, 3, -0.01F, false));

		cube_r62 = new ModelRenderer(this);
		cube_r62.setRotationPoint(-5.5144F, 24.3396F, 7.4424F);
		Left_front_Left_front_leg.addChild(cube_r62);
		setRotationAngle(cube_r62, -0.8492F, 0.2324F, -1.3708F);
		cube_r62.cubeList.add(new ModelBox(cube_r62, 99, 112, 0.0F, -2.0F, -2.0F, 6, 2, 3, 0.0F, false));

		cube_r63 = new ModelRenderer(this);
		cube_r63.setRotationPoint(-0.2746F, 9.7553F, 2.0849F);
		Left_front_Left_front_leg.addChild(cube_r63);
		setRotationAngle(cube_r63, -0.8012F, 0.3931F, -1.2154F);
		cube_r63.cubeList.add(new ModelBox(cube_r63, 105, 56, -4.0F, -2.0F, -2.0F, 4, 1, 2, 0.0F, false));
		cube_r63.cubeList.add(new ModelBox(cube_r63, 97, 0, -11.0F, -1.0F, -2.0F, 11, 1, 2, 0.0F, false));

		cube_r64 = new ModelRenderer(this);
		cube_r64.setRotationPoint(1.5554F, -0.8699F, -0.096F);
		Left_front_Left_front_leg.addChild(cube_r64);
		setRotationAngle(cube_r64, -0.8555F, 0.1996F, -1.4002F);
		cube_r64.cubeList.add(new ModelBox(cube_r64, 104, 52, -11.0F, -2.0F, -2.0F, 11, 2, 2, 0.0F, false));

		cube_r65 = new ModelRenderer(this);
		cube_r65.setRotationPoint(-6.822F, 19.614F, 6.7764F);
		Left_front_Left_front_leg.addChild(cube_r65);
		setRotationAngle(cube_r65, -0.8335F, 0.2975F, -1.3106F);
		cube_r65.cubeList.add(new ModelBox(cube_r65, 16, 48, 0.0F, 0.0F, 0.0F, 23, 2, 2, 0.01F, false));

		Left_middle_leg = new ModelRenderer(this);
		Left_middle_leg.setRotationPoint(-3.0362F, -1.5966F, -1.0F);
		Left_leg.addChild(Left_middle_leg);
		

		cube_r66 = new ModelRenderer(this);
		cube_r66.setRotationPoint(-0.4638F, 0.5966F, 0.0F);
		Left_middle_leg.addChild(cube_r66);
		setRotationAngle(cube_r66, 0.0F, 0.0F, 0.5672F);
		cube_r66.cubeList.add(new ModelBox(cube_r66, 88, 75, -13.5F, -1.0F, -1.0F, 14, 2, 2, 0.01F, false));

		cube_r67 = new ModelRenderer(this);
		cube_r67.setRotationPoint(-6.3548F, -2.1504F, 0.0F);
		Left_middle_leg.addChild(cube_r67);
		setRotationAngle(cube_r67, 0.0F, 0.0F, 0.3054F);
		cube_r67.cubeList.add(new ModelBox(cube_r67, 95, 3, -7.0F, -2.0F, -1.0F, 14, 2, 2, 0.0F, false));

		Left_middle_Left_front_leg = new ModelRenderer(this);
		Left_middle_Left_front_leg.setRotationPoint(-11.7621F, -5.3652F, -0.3478F);
		Left_middle_leg.addChild(Left_middle_Left_front_leg);
		

		cube_r68 = new ModelRenderer(this);
		cube_r68.setRotationPoint(-14.856F, 33.7547F, -0.3596F);
		Left_middle_Left_front_leg.addChild(cube_r68);
		setRotationAngle(cube_r68, -0.0202F, -0.0387F, -0.7414F);
		cube_r68.cubeList.add(new ModelBox(cube_r68, 102, 108, -0.1762F, -1.9731F, -1.1466F, 8, 2, 2, 0.01F, false));

		cube_r69 = new ModelRenderer(this);
		cube_r69.setRotationPoint(-9.4912F, 28.1322F, -0.1956F);
		Left_middle_Left_front_leg.addChild(cube_r69);
		setRotationAngle(cube_r69, -0.0038F, -0.0435F, -1.1344F);
		cube_r69.cubeList.add(new ModelBox(cube_r69, 29, 118, -1.7F, -1.0F, -1.0F, 4, 2, 2, 0.0F, false));

		cube_r70 = new ModelRenderer(this);
		cube_r70.setRotationPoint(-14.856F, 33.7547F, -0.3596F);
		Left_middle_Left_front_leg.addChild(cube_r70);
		setRotationAngle(cube_r70, -0.0309F, -0.0308F, -0.4359F);
		cube_r70.cubeList.add(new ModelBox(cube_r70, 0, 109, -0.7614F, -1.8288F, -1.1466F, 8, 2, 2, 0.0F, false));

		cube_r71 = new ModelRenderer(this);
		cube_r71.setRotationPoint(-2.7449F, 5.5351F, 1.3478F);
		Left_middle_Left_front_leg.addChild(cube_r71);
		setRotationAngle(cube_r71, 0.0F, 0.0F, -1.2654F);
		cube_r71.cubeList.add(new ModelBox(cube_r71, 16, 56, -18.0F, -2.0F, -3.0F, 19, 2, 3, 0.0F, false));

		cube_r72 = new ModelRenderer(this);
		cube_r72.setRotationPoint(-6.5723F, 3.8664F, 2.3478F);
		Left_middle_Left_front_leg.addChild(cube_r72);
		setRotationAngle(cube_r72, 0.0F, -0.0436F, -1.3526F);
		cube_r72.cubeList.add(new ModelBox(cube_r72, 41, 0, -26.0F, 0.0F, -2.0F, 26, 3, 2, 0.01F, false));

		cube_r73 = new ModelRenderer(this);
		cube_r73.setRotationPoint(-6.5723F, 3.8664F, 2.3478F);
		Left_middle_Left_front_leg.addChild(cube_r73);
		setRotationAngle(cube_r73, 0.0F, 0.0F, -1.3526F);
		cube_r73.cubeList.add(new ModelBox(cube_r73, 41, 5, -26.0F, 0.0F, -4.0F, 26, 3, 2, 0.01F, false));

		cube_r74 = new ModelRenderer(this);
		cube_r74.setRotationPoint(-2.0723F, -3.9279F, 2.3478F);
		Left_middle_Left_front_leg.addChild(cube_r74);
		setRotationAngle(cube_r74, 0.0F, 0.0F, -1.0472F);
		cube_r74.cubeList.add(new ModelBox(cube_r74, 100, 102, -9.0F, 0.0F, -4.0F, 9, 2, 4, 0.01F, false));

		cube_r75 = new ModelRenderer(this);
		cube_r75.setRotationPoint(-2.0723F, -3.9279F, 2.3478F);
		Left_middle_Left_front_leg.addChild(cube_r75);
		setRotationAngle(cube_r75, 0.0F, -0.7418F, -1.1781F);
		cube_r75.cubeList.add(new ModelBox(cube_r75, 68, 67, -3.0F, 0.0F, -3.0F, 3, 3, 3, 0.0F, false));

		cube_r76 = new ModelRenderer(this);
		cube_r76.setRotationPoint(-2.0723F, -3.9279F, 2.3478F);
		Left_middle_Left_front_leg.addChild(cube_r76);
		setRotationAngle(cube_r76, 0.0F, 0.0F, -1.1781F);
		cube_r76.cubeList.add(new ModelBox(cube_r76, 98, 45, -9.0F, 0.0F, -4.0F, 9, 3, 4, 0.0F, false));

		Left_hind_leg = new ModelRenderer(this);
		Left_hind_leg.setRotationPoint(-3.0837F, -0.5966F, -3.6985F);
		Left_leg.addChild(Left_hind_leg);
		

		cube_r77 = new ModelRenderer(this);
		cube_r77.setRotationPoint(0.6692F, 0.5966F, -0.0254F);
		Left_hind_leg.addChild(cube_r77);
		setRotationAngle(cube_r77, -0.3298F, -0.4703F, 0.6469F);
		cube_r77.cubeList.add(new ModelBox(cube_r77, 88, 67, -13.5F, -1.0F, -1.0F, 14, 2, 2, 0.01F, false));

		cube_r78 = new ModelRenderer(this);
		cube_r78.setRotationPoint(-4.2993F, -2.1504F, -3.1907F);
		Left_hind_leg.addChild(cube_r78);
		setRotationAngle(cube_r78, -0.1893F, -0.538F, 0.3578F);
		cube_r78.cubeList.add(new ModelBox(cube_r78, 88, 71, -7.0F, -2.0F, -1.0F, 14, 2, 2, 0.0F, false));

		Left_hind_Left_front_leg = new ModelRenderer(this);
		Left_hind_Left_front_leg.setRotationPoint(-8.5428F, -5.4133F, -6.1313F);
		Left_hind_leg.addChild(Left_hind_Left_front_leg);
		

		cube_r79 = new ModelRenderer(this);
		cube_r79.setRotationPoint(-12.4662F, 33.8028F, -8.5434F);
		Left_hind_Left_front_leg.addChild(cube_r79);
		setRotationAngle(cube_r79, 0.3931F, -0.443F, -0.8434F);
		cube_r79.cubeList.add(new ModelBox(cube_r79, 82, 108, -0.1762F, -1.9731F, -1.1466F, 8, 2, 2, 0.01F, false));

		cube_r80 = new ModelRenderer(this);
		cube_r80.setRotationPoint(-8.0563F, 27.4835F, -5.5549F);
		Left_hind_Left_front_leg.addChild(cube_r80);
		setRotationAngle(cube_r80, 0.5028F, -0.3096F, -1.1393F);
		cube_r80.cubeList.add(new ModelBox(cube_r80, 0, 118, -1.7355F, -0.5582F, -1.0F, 4, 2, 2, 0.0F, false));

		cube_r81 = new ModelRenderer(this);
		cube_r81.setRotationPoint(-12.4662F, 33.8028F, -8.5434F);
		Left_hind_Left_front_leg.addChild(cube_r81);
		setRotationAngle(cube_r81, 0.2365F, -0.5385F, -0.5139F);
		cube_r81.cubeList.add(new ModelBox(cube_r81, 108, 97, -0.7614F, -1.8288F, -1.1466F, 8, 2, 2, 0.0F, false));

		cube_r82 = new ModelRenderer(this);
		cube_r82.setRotationPoint(-6.9345F, 3.9145F, -1.8092F);
		Left_hind_Left_front_leg.addChild(cube_r82);
		setRotationAngle(cube_r82, 0.5596F, -0.1536F, -1.4093F);
		cube_r82.cubeList.add(new ModelBox(cube_r82, 16, 33, -26.0F, 0.0F, -2.0F, 26, 3, 2, 0.01F, false));

		cube_r83 = new ModelRenderer(this);
		cube_r83.setRotationPoint(-6.9345F, 3.9145F, -1.8092F);
		Left_hind_Left_front_leg.addChild(cube_r83);
		setRotationAngle(cube_r83, 0.5564F, -0.1166F, -1.386F);
		cube_r83.cubeList.add(new ModelBox(cube_r83, 16, 38, -26.0F, 0.0F, -4.0F, 26, 3, 2, 0.01F, false));

		cube_r84 = new ModelRenderer(this);
		cube_r84.setRotationPoint(-3.1393F, -3.8797F, 0.6087F);
		Left_hind_Left_front_leg.addChild(cube_r84);
		setRotationAngle(cube_r84, 0.5042F, -0.272F, -1.1177F);
		cube_r84.cubeList.add(new ModelBox(cube_r84, 100, 59, -9.0F, 0.0F, -4.0F, 9, 2, 4, 0.01F, false));

		cube_r85 = new ModelRenderer(this);
		cube_r85.setRotationPoint(-3.1692F, 5.5832F, -0.596F);
		Left_hind_Left_front_leg.addChild(cube_r85);
		setRotationAngle(cube_r85, 0.546F, -0.1623F, -1.3109F);
		cube_r85.cubeList.add(new ModelBox(cube_r85, 16, 61, -18.0F, -2.0F, -3.0F, 19, 2, 3, 0.0F, false));

		cube_r86 = new ModelRenderer(this);
		cube_r86.setRotationPoint(-3.1393F, -3.8797F, 0.6087F);
		Left_hind_Left_front_leg.addChild(cube_r86);
		setRotationAngle(cube_r86, 0.7992F, -0.8058F, -1.7523F);
		cube_r86.cubeList.add(new ModelBox(cube_r86, 16, 66, -3.0F, 0.0F, -3.0F, 3, 3, 3, 0.0F, false));

		cube_r87 = new ModelRenderer(this);
		cube_r87.setRotationPoint(-3.1393F, -3.8797F, 0.6087F);
		Left_hind_Left_front_leg.addChild(cube_r87);
		setRotationAngle(cube_r87, 0.532F, -0.2071F, -1.2347F);
		cube_r87.cubeList.add(new ModelBox(cube_r87, 0, 96, -9.0F, 0.0F, -4.0F, 9, 3, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Crest_Hopper.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		float legRaise = 0.1f;
		float legOmega = 0.35f;
		All_Body.rotateAngleY = (180+netHeadYaw) * CommonDef.DEG_TO_RAD;
		All_Body.rotateAngleX = -headPitch * CommonDef.DEG_TO_RAD;
		All_Body.offsetY = (float) (-0.1 + 0.05 * Math.sin(legOmega/2f * ageInTicks));

		if (entityIn.onGround)
		{
			float dy1 = (float) (legRaise * Math.sin(legOmega * ageInTicks));
			float dy2 = (float) (legRaise * Math.sin(legOmega * ageInTicks+Math.PI));
			Right_front_leg.offsetY = dy1;
			Left_front_leg.offsetY = dy2;
			Right_middle_leg.offsetY = dy2;
			Left_middle_leg.offsetY = dy1;
			Right_hind_leg.offsetY = dy1;
			Left_hind_leg.offsetY = dy2;
		}
		else {
			Right_front_leg.offsetY = legRaise * 2;
			Left_front_leg.offsetY = legRaise * 2;
			Right_middle_leg.offsetY = legRaise;
			Left_middle_leg.offsetY = legRaise;
			Right_hind_leg.offsetY = legRaise;
			Left_hind_leg.offsetY = legRaise;
		}
	}
}