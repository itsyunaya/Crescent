package com.itsyunaya.crescent.config;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.util.Identifier;

public class ConfigScreen extends BaseUIModelScreen<FlowLayout> {

    public ConfigScreen() {
        super(FlowLayout.class, DataSource.asset(Identifier.of("crescent", "configuimodel")));
    }

    protected void build(FlowLayout rootComponent) {

    }
}
