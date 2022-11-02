package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.core.AGeometry2DFrom2D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class Mirror2DImpl extends AGeometry2DFrom2D
{
	private final double normX;
	private final double normY;

	public Mirror2DImpl(double normX, double normY)
	{
		this.normX = normX;
		this.normY = normY;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("mirror([" + normX + ", " + normY + "])");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(normX);
		collector.add(normY);
	}
}
