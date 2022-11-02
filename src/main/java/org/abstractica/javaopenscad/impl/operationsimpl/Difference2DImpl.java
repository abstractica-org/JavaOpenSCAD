package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.core.AGeometry2DFrom2D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class Difference2DImpl extends AGeometry2DFrom2D
{
	public Difference2DImpl() {}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("difference()");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{}
}
