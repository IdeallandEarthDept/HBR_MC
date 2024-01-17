package com.deeplake.hbr_mc.items.seraph;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.entities.cancer.EntityCancer;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.init.util.IDLNBTDef;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.deeplake.hbr_mc.items.ItemBase;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ItemSeraphBase extends ItemBase {
    public static final String SERAPH_MODIFIER_BASE = "Seraph modifier base";
    public EnumSeraphRarity seraphRarity = EnumSeraphRarity.A;
    UUID uuid = UUID.fromString("7cf25a1c-6768-4836-8d24-ec64ed2a4ef7");
    UUID uuidPer = UUID.fromString("c73c33a5-9de0-4619-81bf-6009a3a84c02");
    public ItemSeraphBase(String name) {
//        super(ToolMaterial.DIAMOND);
        super(name);
//        RegisterUtil.initItem(this, name);
//        setCreativeTab(ModTabs.TAB1);
        setMaxStackSize(1);
    }

    public void setSeraphRarity(EnumSeraphRarity seraphRarity) {
        this.seraphRarity = seraphRarity;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return oldStack == null || newStack == null || slotChanged || oldStack.getItemDamage() != newStack.getItemDamage();
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player) {
        return false;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    public int getRGBDurabilityForDisplay(ItemStack stack)
    {
        return MathHelper.hsvToRGB(Math.max(0.0F, (float) (1.5F + getDurabilityForDisplay(stack))) / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        //don't drop durability
        return true;
    }

    // As actual melee normal attack damage is dealt in EntityPlayer::attackTargetEntityWithCurrentItem
    //which calls attackEntityFrom, we really can not help with it much directly. Events are needed.

    //    public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase entity, EntityLivingBase attacker)
//    {
//        return this instanceof ItemAxe;
//    }
//
//    /**
//     * Is this Item a shield
//     * @param stack The ItemStack
//     * @param entity The Entity holding the ItemStack
//     * @return True if the ItemStack is considered a shield
//     */
//    public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity)
//    {
//        return stack.getItem() == Items.SHIELD;
//    }

    //onItemUseFirst?
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        boolean success = false;
        Vec3d startPos = player.getPositionVector();
        ItemStack stack = player.getHeldItem(hand);
        if (SeraphUtil.isBroken(stack) || player.getCooldownTracker().hasCooldown(this))
        {
            return EnumActionResult.FAIL;
        }
        if (facing.getAxis() == EnumFacing.Axis.Y)
        {
            //teleport forward if player is not sneaking, otherwise teleport backward
            float distance = 5;
            Vec3d lookVecXZ = new Vec3d(player.getLookVec().x, 0, player.getLookVec().z).normalize();
            if (!player.isSneaking())
            {
                success = player.attemptTeleport(player.posX + lookVecXZ.x * distance, player.posY + 1, player.posZ + lookVecXZ.z * distance);
            }
            else
            {
                success = player.attemptTeleport(player.posX - lookVecXZ.x * distance, player.posY + 1, player.posZ - lookVecXZ.z * distance);
            }

        }
        else {
            //If right clicked on the side of a wall, teleport onto the top of it if possible
            //Max Y movement is 10 blocks
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(pos);
            for (int dy = 0; dy <= 10; dy++)
            {
                int y = player.getPosition().getY();
                mutableBlockPos.setY(y + dy);
                if (worldIn.isAirBlock(mutableBlockPos))
                {
                    if (player.attemptTeleport(mutableBlockPos.getX()+0.5f, mutableBlockPos.getY(), mutableBlockPos.getZ()+0.5f))
                    {
                        success = true;
                        break;
                    }

                }
            }
        }

        if (success)
        {
            if (!worldIn.isRemote)
            {
                player.getCooldownTracker().setCooldown(this, 5);
                int distance = (int) startPos.distanceTo(player.getPositionVector());
                stack.damageItem(distance, player);
            }
            return EnumActionResult.SUCCESS;
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 1024;
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        if (damage > getMaxDamage(stack))
        {
            damage = getMaxDamage(stack);
            boolean isBroken = SeraphUtil.isBroken(stack);
            if (!isBroken)
            {
                IDLNBTUtil.SetBoolean(stack, IDLNBTDef.KEY_BROKEN, true);
            }
        }
        super.setDamage(stack, damage);
    }

//    @Override
//    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
//        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
//        if (!worldIn.isRemote)
//        {
//            if (SeraphUtil.isBroken(stack))
//            {
//                if (worldIn.getTotalWorldTime() % 24000 == 0)
//                {
//                    //todo: chance to repair
//                }
//                return;
//            }
//        }
//    }




    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

        if (slot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", SeraphUtil.getLevel(stack)+3, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));

            addToMap(multimap, RegisterAttr.STR, stack);
            addToMap(multimap, RegisterAttr.DEX, stack);
            addToMap(multimap, RegisterAttr.END, stack);
            addToMap(multimap, RegisterAttr.MEN, stack);
            addToMap(multimap, RegisterAttr.INT, stack);
            addToMap(multimap, RegisterAttr.LUC, stack);
        }

        return multimap;
    }

    private void addToMap(Multimap<String, AttributeModifier> multimap, IAttribute str, ItemStack stack) {
        multimap.put(str.getName(), new AttributeModifier(uuid, SERAPH_MODIFIER_BASE, getAttrValue(stack, str), 0));
    }

    public float getAttrValue(ItemStack stack, IAttribute attr)
    {
        return SeraphUtil.getLevel(stack) + 7;
    }


    //Cast Skill
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            if (entityLiving.isSneaking())
            {
                castSkillSneak(stack, worldIn, (EntityPlayer) entityLiving);
            }
            else {
                castSkillNonSneak(stack, worldIn, (EntityPlayer) entityLiving);
            }
        }
        return stack;
    }

    public void castSkillNonSneak(ItemStack stack, World worldIn, EntityPlayer player)
    {

    }

    public void castSkillSneak(ItemStack stack, World worldIn, EntityPlayer player)
    {

    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (SeraphUtil.isBroken(stack) || playerIn.getCooldownTracker().hasCooldown(this))
        {
            return false;
        }
        if (target instanceof EntityPlayer)
        {
            castSkillFriend(stack, playerIn.getEntityWorld(), playerIn, target);
        }
        else if (target instanceof EntityMob || target instanceof EntityCancer){
            castSkillEnemy(stack, playerIn.getEntityWorld(), playerIn, target);
        }
        //don't do anything on boats, sheep etc
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    public boolean castSkillFriend(ItemStack stack, World worldIn, EntityPlayer player, EntityLivingBase target)
    {
        return false;
    }

    public boolean castSkillEnemy(ItemStack stack, World worldIn, EntityPlayer player, EntityLivingBase target)
    {
        return false;
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 20;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!canUseSkills(playerIn) || SeraphUtil.isBroken(itemstack) || playerIn.getCooldownTracker().hasCooldown(this))
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
        else
        {
            playerIn.setActiveHand(handIn);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }

    public void setCoolDown(EntityPlayer caster, int ticks) {
        caster.getCooldownTracker().setCooldown(this, ticks);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onHit(LivingHurtEvent event)
    {
        EntityLivingBase entityLivingBase = event.getEntityLiving();
        if (entityLivingBase instanceof EntityPlayer)
        {
            //offhand buff won't take place, so if it works, it will take huge damage
//            ItemStack stack = SeraphUtil.getFirstSeraphNonBrokenInHand((EntityPlayer) entityLivingBase);
            ItemStack stack = entityLivingBase.getHeldItemMainhand();
            if (stack.getItem() instanceof ItemSeraphBase)
            {
                float amount = event.getAmount();
                event.setAmount(0);
                stack.setItemDamage((int) (stack.getItemDamage() + amount));
            }
        }
    }

    public int getSkillLimit(ItemStack stack, int slot)
    {
        return -1;
    }

    public static final String[] USAGE = {"usage_1","usage_2","usage_3"};
    public static final String[] SKILL_LEVEL = {"sLv_1","sLv_2","sLv_3"};

    public int getSkillLevel(ItemStack stack, int slot)
    {
        return IDLNBTUtil.GetInt(stack, SKILL_LEVEL[slot], 1);
    }

    public int getSkillLevelMax(ItemStack stack, int slot)
    {
        return 10;
    }

    public int getSkillLeft(ItemStack stack, int slot)
    {
        return getSkillLimit(stack, slot) - IDLNBTUtil.GetInt(stack, USAGE[slot]);
    }

    public boolean canUseSkill(ItemStack stack, int slot)
    {
        return getSkillLimit(stack, slot) > 0 && getSkillLeft(stack, slot) > 0;
    }

    public int getMaxSkillSlot(ItemStack stack)
    {
        return 2;
    }

    public void skillUseMark(ItemStack stack, int slot)
    {
        IDLNBTUtil.addInt(stack, USAGE[slot],1);
    }

    @Override
    public boolean getHasSubtypes() {
        return true;
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            items.add(new ItemStack(this));
            ItemStack stack = new ItemStack(this);
            for (int i = 0; i < getMaxSkillSlot(stack); i++) {
                IDLNBTUtil.setInt(stack, SKILL_LEVEL[i], getSkillLevelMax(stack, i));
            }
            SeraphUtil.setLevel(stack, SeraphUtil.getMaxLevel(stack));
            items.add(stack);
        }
    }

    protected void afterCastSkill(EntityPlayer caster, World worldIn, ModConfig.SkillConf COMBAT, SoundEvent event) {
        caster.swingArm(caster.getActiveHand());
        caster.addStat(StatList.getObjectUseStats(this));
        worldIn.playSound(null, caster.getPosition(), event, SoundCategory.PLAYERS, 1f, 1f);
        setCoolDown(caster, CommonDef.TICK_PER_SECOND * COMBAT.SP);
    }

    public static boolean canUseSkills(EntityLivingBase entityLiving) {
        return EntityUtil.getBuffLevelIDL(entityLiving, RegisterEffects.SELF_RECOIL) == 0;
    }
}
