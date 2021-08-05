package timelessandclassic.items;

import com.mrcrayfish.guns.item.AmmoItem;
import timelessandclassic.util.Process;

import static timelessandclassic.core.timeless_and_classic.AMMO_GROUP;

public class TimelessAmmoItem extends AmmoItem {
	public TimelessAmmoItem() {
		this(properties -> properties);
	}
	
	public TimelessAmmoItem(Process<Properties> properties) {
		super(properties.process(new Properties().maxStackSize(64).group(AMMO_GROUP)));
	}
}
