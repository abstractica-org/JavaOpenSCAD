package org.abstractica.javaopenscad.impl;

import org.abstractica.code.codebuilder.CodeBuilder;
import org.abstractica.code.codebuilder.blockbuilder.BlockBuilder;

import java.util.Map;

public abstract class AModule extends AGeometryLeaf
{
	private final AGeometry geometry;
	private final int id;

	public AModule(AGeometry geometry, int id)
	{

		this.geometry = geometry;
		this.id = id;
	}

	public int id()
	{
		return id;
	}

	public void generateModule(CodeBuilder cb, Map<Integer, AModule> usedModules)
	{
		cb.println();
		cb.print("module ");
		getCallHeader(cb);
		BlockBuilder bodyBlock = cb.block();
		geometry.generateCall(bodyBlock, usedModules);
		bodyBlock.endBlock();
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("M" + id + "()");
	}

	@Override
	protected void doGenerateCall(CodeBuilder cb, Map<Integer, AModule> usedModules)
	{
		usedModules.put(id, this);
		getCallHeader(cb);
		cb.println(";");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(id);
	}
}
