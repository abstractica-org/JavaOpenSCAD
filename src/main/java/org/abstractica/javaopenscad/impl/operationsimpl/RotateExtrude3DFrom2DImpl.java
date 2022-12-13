package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.core.AGeometry3DFrom2D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class RotateExtrude3DFrom2DImpl extends AGeometry3DFrom2D
{
	private final double angleDeg;
	private final int angularResolution;

	public RotateExtrude3DFrom2DImpl(double angleDeg, int angularResolution)
	{
		this.angleDeg = angleDeg;
		this.angularResolution = angularResolution;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("rotate_extrude(angle = " + angleDeg +
				", $fn = " + angularResolution + ")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(angleDeg);
		collector.add(angularResolution);
	}
}
