package org.abstractica.javaopenscad.impl.core;

import org.abstractica.code.codebuilder.CodeBuilder;

import java.util.Map;

public abstract class AGeometryLeaf extends AGeometry
{
	@Override
	public final void collectArguments(ArgumentCollector collector)
	{
		collector.add(getClass());
		getArguments(collector);
	}

	@Override
	protected void doGenerateCall(CodeBuilder cb, Map<Integer, AModule> usedModules)
	{
		getCallHeader(cb);
		cb.println(";");
	}
}
