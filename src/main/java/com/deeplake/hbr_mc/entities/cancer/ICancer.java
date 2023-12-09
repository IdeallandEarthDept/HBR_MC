package com.deeplake.hbr_mc.entities.cancer;

public interface ICancer {
    default float getInitShield()
    {
        return 20;
    }

    default int getLevel()
    {
        return 1;
    }

    default int getXPWorth()
    {
        return getLevel() * 10;
    }
}
