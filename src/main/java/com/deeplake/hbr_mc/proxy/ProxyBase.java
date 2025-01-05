package com.deeplake.hbr_mc.proxy;

public abstract class ProxyBase {
    public void registerLayers() {
    }

    public boolean isServer()
    {
        //not recommeneded, unchecked.
        return false;
    }
}
