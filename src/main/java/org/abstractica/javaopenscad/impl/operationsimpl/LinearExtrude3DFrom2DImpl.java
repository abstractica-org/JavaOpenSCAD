package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.core.AGeometry3DFrom2D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class LinearExtrude3DFrom2DImpl extends AGeometry3DFrom2D
{
	private final double height;
	private final double twistDeg;
	private final double scale;
	private final int slices;
	private final boolean centerZ;

	public LinearExtrude3DFrom2DImpl(double height,
	                                 double twistDeg,
	                                 double scale,
	                                 int slices,
	                                 boolean centerZ)
	{
		this.height = height;
		this.twistDeg = twistDeg;
		this.scale = scale;
		this.slices = slices;
		this.centerZ = centerZ;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(height);
		collector.add(twistDeg);
		collector.add(scale);
		collector.add(slices);
		collector.add(centerZ);
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("linear_extrude(height = " + height +
				", twist = " + twistDeg +
				", scale = " + scale +
				", slices = " + slices +
				", center = " + centerZ + ")");
	}


}
