package org.abstractica.javaopenscad.impl.core;

public class BoundingBox
{
	public final double xMin;
	public final double xMax;
	public final double yMin;
	public final double yMax;
	public final double zMin;
	public final double zMax;

	public BoundingBox(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax)
	{
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.zMin = zMin;
		this.zMax = zMax;
	}
}
