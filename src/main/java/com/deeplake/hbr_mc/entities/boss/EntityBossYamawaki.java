package com.deeplake.hbr_mc.entities.boss;

import com.deeplake.hbr_mc.entities.ai.boss.EntityAIBoomSequence;
import com.deeplake.hbr_mc.entities.ai.boss.EntityAIDashSequence;
import net.minecraft.world.World;

public class EntityBossYamawaki extends EntityBossBase {
    EntityAIBoomSequence aiBoomSequence = new EntityAIBoomSequence(this);
    EntityAIDashSequence aiDashSequence = new EntityAIDashSequence(this);
    public EntityBossYamawaki(World worldIn) {
        super(worldIn);
        attack_all_players = 1;
        melee_atk = true;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        setAttr(32, 0.4, 6.0, 2, 16);
        set6Attr(50);
    }

    @Override
    protected void applyEntityAI() {
        super.applyEntityAI();
        this.tasks.addTask(1, aiBoomSequence);
        this.tasks.addTask(1, aiDashSequence);
    }

    int skillCD = 0;
    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        if (getAttackTarget() != null) {

            if (skillCD > 0) {
                skillCD--;
            }
            else {
                skillCD = 100;
                if (rand.nextBoolean())
                {
                    if (!aiBoomSequence.isActivated()) {
                        aiBoomSequence.activate();
                    }
                }
                else {
                    if (!aiDashSequence.isActivated()) {
                        aiDashSequence.activate();
                    }
                }
            }
        }
    }
}
