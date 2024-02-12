package com.deeplake.hbr_mc.init.util;

import com.deeplake.hbr_mc.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ChancePicker {
    List<ChanceTuple> chanceTupleList = new LinkedList<>();

    int totalWeight = 0;

    public void addTuple(ChanceTuple tuple)
    {
        chanceTupleList.add(tuple);
        totalWeight+=tuple.weight;
    }

    public void addTuple(int weight, ItemStack stack)
    {
        chanceTupleList.add(new ChanceTuple(weight, stack));
        totalWeight+=weight;
    }

    public void addTuple(int weight, Item item)
    {
        addTuple(weight, item, 1, 1);
    }

    public void addTuple(int weight, Item item, int count)
    {
        addTuple(weight, item, count, count);
    }

    public void addTuple(int weight, Item item, int count, int max)
    {
        chanceTupleList.add(new ChanceTuple(weight, item, count, max));
        totalWeight+=weight;
    }

    public ItemStack getItem(Random random)
    {
        if (totalWeight <= 0)
        {
            return ItemStack.EMPTY;
        }
        int weight = random.nextInt(totalWeight);
        for (ChanceTuple tuple:
             chanceTupleList) {
            weight -= tuple.weight;
            if (weight <= 0)
            {
                return tuple.getStack(random);
            }
        }
        Main.LogWarning("tuple bugged: did not find item");
        return ItemStack.EMPTY;

    }
}
