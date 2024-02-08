package com.deeplake.hbr_mc.items.seraph;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.designs.SeraphAttrData;
import com.deeplake.hbr_mc.designs.SeraphBoostConst;
import com.deeplake.hbr_mc.designs.SeraphBoostUnit;
import com.deeplake.hbr_mc.entities.cancer.EntityCancer;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.RegisterItem;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.init.util.IDLNBTDef;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.deeplake.hbr_mc.items.ItemBase;
import com.deeplake.hbr_mc.items.ItemLottery;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.UUID;

import static com.deeplake.hbr_mc.designs.SeraphTeleportControl.SERAPH_TELEPORT;
import static com.deeplake.hbr_mc.designs.SeraphTeleportControl.SERAPH_TELEPORT_ROUGH;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ItemSeraphBase extends ItemBase {
    static boolean need_teleport_hint = true;
    static boolean need_teleport_hint_rough = true;
    public static final String SERAPH_MODIFIER_BASE = "Seraph modifier base";
    public static final int SLOT_ULTI = 1;
    public EnumSeraphRarity seraphRarity = EnumSeraphRarity.A;
    public EnumSeraphClass seraphClass = EnumSeraphClass.FIGHTER;
    public final EnumSeraphType type;

    UUID uuid = UUID.fromString("7cf25a1c-6768-4836-8d24-ec64ed2a4ef7");
    UUID uuidPer = UUID.fromString("c73c33a5-9de0-4619-81bf-6009a3a84c02");
    public ItemSeraphBase(String name, EnumSeraphType type) {
        this(name,type,EnumSeraphRarity.A);
    }

    public ItemSeraphBase(String name, EnumSeraphType type, EnumSeraphRarity rarity) {
        super(name);
        this.type = type;
        setSeraphRarity(rarity);
        setMaxStackSize(1);
        switch (rarity) {
            case SS:
                ItemLottery.addSS(this);
                break;
            case S:
                break;
            case A:
                ItemLottery.addA(this);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + rarity);
        }
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

    public static void eraseAttrDesc(ItemStack stack)
    {
        IDLNBTUtil.setInt(stack,"HideFlags",
                IDLNBTUtil.GetInt(stack, "HideFlags") | 2);
    }

    //onItemUseFirst?
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState state = worldIn.getBlockState(pos);

        //only server will know about NBTs
        if (!worldIn.isRemote)
        {
            boolean canTeleport = IDLNBTUtil.getPlayerIdeallandBoolSafe(player, SERAPH_TELEPORT);
            //canTeleport &= player.isSprinting();
            if (!canTeleport)
            {
                return EnumActionResult.PASS;
            }
        }
        else {
            //A simple hint that shows once per session.
            if (need_teleport_hint)
            {
                player.sendMessage(new TextComponentTranslation("hbr_mc.msg.teleport_command"));
                need_teleport_hint = false;
            }
            if (state.getBlockFaceShape(worldIn, pos, facing) == BlockFaceShape.UNDEFINED && need_teleport_hint_rough)
            {
                player.sendMessage(new TextComponentTranslation("hbr_mc.msg.teleport_command_rough"));
                need_teleport_hint_rough = false;
            }
            return EnumActionResult.PASS;
        }

        boolean success = false;
        Vec3d startPos = player.getPositionVector();
        ItemStack stack = player.getHeldItem(hand);

        if (SeraphUtil.isBroken(stack) || player.getCooldownTracker().hasCooldown(this))
        {
            return EnumActionResult.FAIL;
        }

        if (facing == EnumFacing.DOWN)
        {
            //todo: the top of half bricks, and carpets are undefined.
            return EnumActionResult.PASS;
        }

        if (!IDLNBTUtil.getPlayerIdeallandBoolSafe(player, SERAPH_TELEPORT_ROUGH) &&
                state.getBlockFaceShape(worldIn, pos, facing) == BlockFaceShape.UNDEFINED)
        {
            return EnumActionResult.PASS;
        }

        if (facing.getAxis() == EnumFacing.Axis.Y)
        {
            //teleport forward if player is not sneaking, otherwise teleport backward
            float distance = 5;
            Vec3d lookVecXZ = new Vec3d(player.getLookVec().x, 0, player.getLookVec().z).normalize();
            if (!player.isSneaking())
            {
                while (!success && distance > 3)
                {
                    success = player.attemptTeleport(player.posX + lookVecXZ.x * distance, player.posY + 1, player.posZ + lookVecXZ.z * distance);
                    distance--;
                }
            }
            else
            {
                while (!success && distance > 3) {
                    success = player.attemptTeleport(player.posX - lookVecXZ.x * distance, player.posY + 1, player.posZ - lookVecXZ.z * distance);
                    distance--;
                }
            }

        }
        else {
            //If right clicked on the side of a wall, teleport onto the top of it if possible
            //Max Y movement is 10 blocks
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(pos);
            int yTouch = pos.getY();
            int yPlayer = player.getPosition().getY();
            for (int y = yTouch; y <= yPlayer + 10; y++)
            {
                mutableBlockPos.setY(y);
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
            player.getCooldownTracker().setCooldown(this, 5);
            int distance = (int) startPos.distanceTo(player.getPositionVector());
            stack.damageItem(distance, player);
            worldIn.playSound(null,player.getPosition(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1f, 1f);
            return EnumActionResult.SUCCESS;
        }
        else {
            worldIn.playSound(null,player.getPosition(), SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.PLAYERS, 1f, 1f);
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        int level = SeraphUtil.getLevel(stack);
        int base = 600 + 7 * level;
        switch (seraphRarity) {
            case SS:
                return (int) (base*1.7f+200);
            case S:
                return (int) (base*1.5f+50);
            case A:
                return (int) (base*1.3f);
            default:
                throw new IllegalStateException("Unexpected value: " + seraphRarity);
        }
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

    private void addToMap(Multimap<String, AttributeModifier> multimap, IAttribute attr, ItemStack stack) {
        multimap.put(attr.getName(), new AttributeModifier(uuid, SERAPH_MODIFIER_BASE, getAttrValue(stack, attr), 0));
        multimap.put(attr.getName(), new AttributeModifier(uuidPer, SERAPH_MODIFIER_BASE, getAttrValuePercent(stack, attr), 1));
    }

    public float getAttrValue(ItemStack stack, IAttribute attr)
    {
        int base = SeraphUtil.getLevel(stack) + 7;
        switch (seraphRarity)
        {
            case SS:
                base+=15;
            case S:
                base+=5;
            default:
        }

        int boostLevel = IDLNBTUtil.GetInt(stack, IDLNBTDef.KEY_BOOST);
        List<SeraphBoostUnit> boostUnits = SeraphAttrData.boostSS.get(seraphClass);
        for (int i = 0; i < boostLevel; i++) {
            SeraphBoostUnit unit = boostUnits.get(i);
            if (unit.attr == attr || unit.attr == null)
            {
                switch (unit.type)
                {
                    case FIXED:
                        base += unit.value;
                        break;
                }
            }
        }

        return base;
    }

    public float getAttrValuePercent(ItemStack stack, IAttribute attr)
    {
        int boostLevel = IDLNBTUtil.GetInt(stack, IDLNBTDef.KEY_BOOST);
        List<SeraphBoostUnit> boostUnits = SeraphAttrData.boostSS.get(seraphClass);
        float base = SeraphAttrData.getData(seraphRarity, seraphClass).get(attr);
        for (int i = 0; i < boostLevel; i++) {
            SeraphBoostUnit unit = boostUnits.get(i);
            if (unit.attr == attr || unit.attr == null)
            {
                switch (unit.type)
                {
                    case PERCENT:
                        base += unit.value;
                        break;
                }
            }
        }
        base += SeraphBoostConst.attrPercentagePerBreak(seraphRarity) * SeraphUtil.getBreakThrough(stack);

        return base;
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
        if (playerIn.getCooldownTracker().hasCooldown(this))
        {
            return false;
        }
        if (target instanceof EntityPlayer)
        {
            castSkillFriend(stack, playerIn.getEntityWorld(), playerIn, target);
        }
        else if (target instanceof IMob || target instanceof EntityCancer){
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

        if (!canUseSkills(playerIn) || playerIn.getCooldownTracker().hasCooldown(this))
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
        switch (seraphRarity)
        {
            case SS:
                return 2;
            case S:
                break;
            case A:
                return 1;
            default:
                throw new IllegalStateException("Unexpected value: " + seraphRarity);
        }
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
            ItemStack stack = new ItemStack(this);
            eraseAttrDesc(stack);
            items.add(stack);

            stack = stack.copy();
            for (int i = 0; i < getMaxSkillSlot(stack); i++) {
                IDLNBTUtil.setInt(stack, SKILL_LEVEL[i], getSkillLevelMax(stack, i));
            }
            SeraphUtil.setLevel(stack, SeraphUtil.getMaxLevel(stack));
            int maxBoost = SeraphBoostConst.getMaxBoost(seraphRarity);
            IDLNBTUtil.setInt(stack, IDLNBTDef.KEY_BOOST, maxBoost);
            items.add(stack);

            //Max breakthrough
            stack = stack.copy();
            IDLNBTUtil.setInt(stack, IDLNBTDef.KEY_BREAK_THRU, SeraphBoostConst.getMaxBreakThrough(seraphRarity));
            SeraphUtil.setLevel(stack, SeraphUtil.getMaxLevel(stack));
            items.add(stack);
        }
    }

    protected void postCastSkill(EntityPlayer caster, World worldIn, ModConfig.SkillConf COMBAT, SoundEvent event) {
        currentBuffRateTotal = 0f;//reset, regardless of used or not
        caster.swingArm(caster.getActiveHand());
        caster.addStat(StatList.getObjectUseStats(this));
        worldIn.playSound(null, caster.getPosition(), event, SoundCategory.PLAYERS, 1f, 1f);
        ItemStack stack = caster.getHeldItemMainhand();
        int cd = CommonDef.TICK_PER_SECOND * COMBAT.SP;
        if (fasterCooldown(stack))
        {
            cd = (int) (cd * 0.67f);
        }
        setCoolDown(caster, cd);
    }

    public boolean fasterCooldown(ItemStack stack) {
        return this != RegisterItem.BRAVE_BLUE_SS && seraphRarity == EnumSeraphRarity.SS && SeraphUtil.getBreakThrough(stack) > 0;
    }

    public static float currentBuffRateTotal = 0f;
    protected static void calcAtkBuffSkill(EntityPlayer caster) {
        int maxCost = 2;
        int removed = 0;
        //normal-90%,65%,50%(Yamawaki)
        //elem-?,80%(Date),55%(Kula)
        Potion buff = RegisterEffects.SKILL_ATK_UP_GREATER;
        removed += spendBuff(caster, buff, maxCost);
        currentBuffRateTotal += removed * 0.90f;
        if (removed < maxCost)
        {
            maxCost -= removed;
            removed = spendBuff(caster, RegisterEffects.SKILL_ATK_UP, maxCost);
            currentBuffRateTotal += removed * 0.65f;
            if (removed < maxCost)
            {
                maxCost -= removed;
                removed = spendBuff(caster, RegisterEffects.SKILL_ATK_UP_LESSER, maxCost);
                currentBuffRateTotal += removed * 0.50f;
            }
        }
    }

    private static int spendBuff(EntityPlayer caster, Potion buff, int cost) {
        int lastBuffLv = EntityUtil.getBuffLevelIDL(caster, buff);//starts with 1
        EntityUtil.TryRemoveGivenBuff(caster, buff);
        if (lastBuffLv > cost)
        {
            EntityUtil.ApplyBuff(caster, buff,lastBuffLv-2,ModConfig.COMBAT.BUFF_TIME);
            return cost;
        }
        else {
            return lastBuffLv;
        }
    }

    //Used to bypass spawn protection
    public boolean canItemEditBlocks()
    {
        return true;
    }

    public static boolean canUseSkills(EntityLivingBase entityLiving) {
        return EntityUtil.getBuffLevelIDL(entityLiving, RegisterEffects.SELF_RECOIL) == 0;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return stack.isItemEnchanted() || seraphRarity == EnumSeraphRarity.SS;
    }

}
