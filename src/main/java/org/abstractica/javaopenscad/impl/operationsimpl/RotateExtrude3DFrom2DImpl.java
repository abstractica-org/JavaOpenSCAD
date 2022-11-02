package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.core.AGeometry3DFrom2D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class RotateExtrude3DFrom2DImpl extends AGeometry3DFrom2D
{
	private final double angleDeg;
	private final int angularResolution;
	private final int convexity;

	public RotateExtrude3DFrom2DImpl(double angleDeg, int angularResolution, int convexity)
	{
		this.angleDeg = angleDeg;
		this.angularResolution = angularResolution;
		this.convexity = convexity;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("rotate_extrude(angle = " + angleDeg +
				", convexity = " + convexity +
				", $fn = " + angularResolution + ")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(angleDeg);
		collector.add(angularResolution);
		collector.add(convexity);
	}
}
