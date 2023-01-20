package org.abstractica.javaopenscad.impl.core;

import org.abstractica.javaopenscad.intf.OpenSCADVector2D;

public class OpenSCADVector2DImpl implements OpenSCADVector2D
{
	private final double x;
	private final double y;

	public OpenSCADVector2DImpl(double x, double y)
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
}
