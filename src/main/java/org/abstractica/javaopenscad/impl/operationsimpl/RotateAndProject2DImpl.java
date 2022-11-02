package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.core.AGeometry2DFrom2D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class RotateAndProject2DImpl extends AGeometry2DFrom2D
{
	private final double xDeg;
	private final double yDeg;
	private final double zDeg;

	public RotateAndProject2DImpl(double xDeg, double yDeg, double zDeg)
	{
		this.xDeg = xDeg;
		this.yDeg = yDeg;
		this.zDeg = zDeg;
	}

	public double xDeg()
	{
		return xDeg;
	}

	public double yDeg()
	{
		return yDeg;
	}

	public double zDeg()
	{
		return zDeg;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("rotate([" + xDeg + ", " + yDeg + ", " + zDeg + "])");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(xDeg);
		collector.add(yDeg);
		collector.add(zDeg);
	}
}
