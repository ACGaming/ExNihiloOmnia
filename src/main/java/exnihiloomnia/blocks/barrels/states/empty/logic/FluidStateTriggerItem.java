package exnihiloomnia.blocks.barrels.states.empty.logic;

import exnihiloomnia.blocks.barrels.architecture.BarrelLogic;
import exnihiloomnia.blocks.barrels.states.BarrelStates;
import exnihiloomnia.blocks.barrels.tileentity.TileEntityBarrel;
import exnihiloomnia.util.helpers.InventoryHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FluidStateTriggerItem extends BarrelLogic {
	
	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item)  {
		FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(item);
		
		return fluid != null;
	}
	
	@Override
	public boolean onUseItem(EntityPlayer player, EnumHand hand, TileEntityBarrel barrel, ItemStack item) {
		FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(item);

		if (fluid != null && fluid.amount > 0) {
			if (player != null) {
				if (!player.capabilities.isCreativeMode) {
					InventoryHelper.consumeItem(null, item);
					player.setHeldItem(hand, InventoryHelper.getContainer(item));
				}
			}
			else {
				InventoryHelper.consumeItem(null, item);
				barrel.addOutput(InventoryHelper.getContainer(item));
			}

			barrel.getFluidTank().fill(fluid, true);
			barrel.setState(BarrelStates.FLUID);
			barrel.getWorld().playSound(null, barrel.getPos(),SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 0.2f, 0.8f);
		}

		return false;
	}
}
