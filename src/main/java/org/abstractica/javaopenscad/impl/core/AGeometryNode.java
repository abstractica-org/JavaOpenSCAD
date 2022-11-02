package org.abstractica.javaopenscad.impl.core;

import org.abstractica.code.codebuilder.CodeBuilder;
import org.abstractica.code.codebuilder.blockbuilder.BlockBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AGeometryNode extends AGeometry
{
	private boolean closed;
	private final List<AGeometry> children;

	protected AGeometryNode()
	{
		this.closed = false;
		this.children = new ArrayList<>();
	}

	protected void addChild(AGeometry child)
	{
		if(closed)
		{
			throw new RuntimeException("This node has been used in a module and is closed for change!");
		}
		children.add(child);
	}

	@Override
	public final void collectArguments(ArgumentCollector collector)
	{
		collector.add(getClass());
		getArguments(collector);
		for(AGeometry child : children)
		{
			child.collectArguments(collector);
		}
		closed = true;
	}

	@Override
	protected void doGenerateCall(CodeBuilder cb, Map<Integer, AModule> usedModules)
	{
		getCallHeader(cb);
		BlockBuilder childBlock = cb.block();
		for(AGeometry child : children)
		{
			child.generateCall(childBlock, usedModules);
		}
		childBlock.endBlock();
	}
}
