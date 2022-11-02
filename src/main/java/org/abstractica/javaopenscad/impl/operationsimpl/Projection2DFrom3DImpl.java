package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.core.AGeometry2DFrom3D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class Projection2DFrom3DImpl extends AGeometry2DFrom3D
{
	private final boolean cutAtZeroZ;

	public Projection2DFrom3DImpl(boolean cutAtZeroZ)
	{
		this.cutAtZeroZ = cutAtZeroZ;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("projection(cut = " + cutAtZeroZ + ")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(cutAtZeroZ);
	}
}
