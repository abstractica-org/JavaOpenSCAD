package org.abstractica.javaopenscad.impl.core;

public class BoundingBox
{
	public final double xMin;
	public final double yMin;
	public final double zMin;
	public final double xMax;
	public final double yMax;
	public final double zMax;

	public BoundingBox(double xMin, double yMin, double zMin, double xMax, double yMax, double zMax)
	{
		this.xMin = xMin;
		this.yMin = yMin;
		this.zMin = zMin;
		this.xMax = xMax;
		this.yMax = yMax;
		this.zMax = zMax;
	}
}
