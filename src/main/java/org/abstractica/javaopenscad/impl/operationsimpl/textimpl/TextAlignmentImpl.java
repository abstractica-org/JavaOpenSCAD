package org.abstractica.javaopenscad.impl.operationsimpl.textimpl;

import org.abstractica.javaopenscad.intf.text.OpenSCADTextAlignment;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.javaopenscad.impl.core.HasArguments;

public class TextAlignmentImpl implements OpenSCADTextAlignment, HasArguments
{
	private final Direction direction;
	private final Horizontal horizontal;
	private final Vertical vertical;

	public TextAlignmentImpl(Direction direction, Horizontal horizontal, Vertical vertical)
	{
		this.direction = direction;
		this.horizontal = horizontal;
		this.vertical = vertical;
	}

	@Override
	public Direction direction()
	{
		return direction;
	}

	@Override
	public Horizontal horizontal()
	{
		return horizontal;
	}

	@Override
	public Vertical vertical()
	{
		return vertical;
	}

	@Override
	public void collectArguments(ArgumentCollector collector)
	{
		collector.add(direction.ordinal());
		collector.add(horizontal.ordinal());
		collector.add(vertical.ordinal());
	}
}
