package timeless_and_classic.core.types;

import com.mrcrayfish.guns.item.AmmoItem;
import timeless_and_classic.util.Process;

import static timeless_and_classic.core.timeless_and_classic.AMMO_GROUP;

public class TimelessAmmoItem extends AmmoItem {
    public TimelessAmmoItem() {
        this(properties -> properties);
    }

    public TimelessAmmoItem(Process<Properties> properties) {
        super(properties.process(new Properties().maxStackSize(64).group(AMMO_GROUP)));
    }
}