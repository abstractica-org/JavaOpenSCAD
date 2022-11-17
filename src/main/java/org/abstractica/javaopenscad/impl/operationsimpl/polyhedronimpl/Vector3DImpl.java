package org.abstractica.javaopenscad.impl.operationsimpl.polyhedronimpl;

import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.javaopenscad.impl.core.HasArguments;
import org.abstractica.javaopenscad.intf.Vector3D;

public class Vector3DImpl implements Vector3D, HasArguments
{
	private final double x;
	private final double y;
	private final double z;

	public Vector3DImpl(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public double x()
	{
		return x;
	}

	@Override
	public double y()
	{
		return y;
	}

	@Override
	public double z()
	{
		return z;
	}

	@Override
	public void collectArguments(ArgumentCollector collector)
	{
		collector.add(x);
		collector.add(y);
		collector.add(z);
	}
}
