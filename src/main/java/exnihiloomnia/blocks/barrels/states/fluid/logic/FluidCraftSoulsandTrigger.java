package exnihiloomnia.blocks.barrels.states.fluid.logic;

import exnihiloomnia.blocks.barrels.architecture.BarrelLogic;
import exnihiloomnia.blocks.barrels.states.BarrelStates;
import exnihiloomnia.blocks.barrels.tileentity.TileEntityBarrel;
import exnihiloomnia.fluids.ENOFluids;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;

public class FluidCraftSoulsandTrigger extends BarrelLogic {

	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) {
        return item.getItem() == Item.getItemFromBlock(Blocks.SAND)
                && barrel.getFluid().getFluid() == ENOFluids.WITCHWATER
                && barrel.getFluidTank().getFluidAmount() == barrel.getFluidTank().getCapacity();
    }

	@Override
	public boolean onUseItem(EntityPlayer player, EnumHand hand, TileEntityBarrel barrel, ItemStack item) {
		if (item.getItem() == Item.getItemFromBlock(Blocks.SAND)
		    && barrel.getFluid().getFluid() == ENOFluids.WITCHWATER
		    && barrel.getFluidTank().getFluidAmount() == barrel.getFluidTank().getCapacity()) {
			
			barrel.setState(BarrelStates.OUTPUT);
			barrel.setContents(new ItemStack(Blocks.SOUL_SAND, 1));

			barrel.getWorld().playSound(null, barrel.getPos(), SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 0.12f, 4.5f);
			
			return true;
		}
		
		return false;
	}
}
