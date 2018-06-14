package powercrystals.minefactoryreloaded.farmables.grindables;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.mob.IFactoryGrindable;
import powercrystals.minefactoryreloaded.api.mob.MobDrop;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GrindableSlime implements IFactoryGrindable
{
	protected Class<? extends EntityLivingBase> grindable;
	protected ArrayList<MobDrop> drops;
	protected int dropSize;

	public GrindableSlime(Class<? extends EntityLivingBase> slime, MobDrop[] drops, int dropSize)
	{
		grindable = slime;
		ArrayList<MobDrop> q = new ArrayList<MobDrop>();
		q.addAll(Arrays.asList(drops));
		this.drops = q;
		this.dropSize = dropSize;
	}

	public GrindableSlime(Class<? extends EntityLivingBase> slime, MobDrop drop, int dropSize)
	{
		this(slime, new MobDrop[]{drop}, dropSize);
	}

	public GrindableSlime(Class<? extends EntityLivingBase> slime, NonNullList<ItemStack> drops, int dropSize)
	{
		grindable = slime;
		ArrayList<MobDrop> q = new ArrayList<MobDrop>();
		for (@Nonnull ItemStack drop : drops)
			q.add(new MobDrop(10, drop));
		this.drops = q;
		this.dropSize = dropSize;
	}

	public GrindableSlime(Class<? extends EntityLivingBase> slime, @Nonnull ItemStack drop, int dropSize)
	{
		this(slime, new MobDrop[]{new MobDrop(10, drop), new MobDrop(20, ItemStack.EMPTY)}, dropSize);
	}

	@Override
	public Class<? extends EntityLivingBase> getGrindableEntity() {
		return grindable;
	}

	@Override
	public List<MobDrop> grind(World world, EntityLivingBase entity, Random random)
	{
		if (shouldDrop((EntitySlime)entity))
			return drops;
		return null;
	}
	
	protected boolean shouldDrop(EntitySlime slime)
	{
		return slime.getSlimeSize() > dropSize;
	}

	@Override
	public boolean processEntity(EntityLivingBase entity) {
		return false;
	}

}
