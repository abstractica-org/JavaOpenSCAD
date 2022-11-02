package org.abstractica.javaopenscad.impl.operationsimpl.polygonimpl;

import org.abstractica.javaopenscad.intf.polygon.Vector2D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.javaopenscad.impl.core.HasArguments;

public class Vector2DImpl implements Vector2D, HasArguments
{
	private final double x;
	private final double y;

	public Vector2DImpl(double x, double y)
	{
		this.x = x;
		this.y = y;
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
	public void collectArguments(ArgumentCollector collector)
	{
		collector.add(x);
		collector.add(y);
	}
}
