package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.AGeometry3DFrom3D;
import org.abstractica.javaopenscad.impl.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class Mirror3DImpl extends AGeometry3DFrom3D
{
	private final double normX;
	private final double normY;
	private final double normZ;

	public Mirror3DImpl(double normX, double normY, double normZ)
	{
		this.normX = normX;
		this.normY = normY;
		this.normZ = normZ;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("mirror([" + normX + ", " + normY + ", " + normZ + "])");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(normX);
		collector.add(normY);
		collector.add(normZ);
	}
}
