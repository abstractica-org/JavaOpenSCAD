package org.abstractica.javaopenscad.impl.core;

import org.abstractica.javaopenscad.intf.OpenSCADVector3D;

public class OpenSCADVector3DImpl implements OpenSCADVector3D
{
	private final double x;
	private final double y;
	private final double z;

	public OpenSCADVector3DImpl(double x, double y, double z)
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
}
