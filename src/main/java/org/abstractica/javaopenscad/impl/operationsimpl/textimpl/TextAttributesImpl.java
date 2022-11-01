package org.abstractica.javaopenscad.impl.operationsimpl.textimpl;

import org.abstractica.javaopenscad.intf.text.TextAlignment;
import org.abstractica.javaopenscad.intf.text.TextAttributes;
import org.abstractica.javaopenscad.intf.text.TextFont;
import org.abstractica.javaopenscad.intf.text.TextSize;
import org.abstractica.javaopenscad.impl.ArgumentCollector;
import org.abstractica.javaopenscad.impl.HasArguments;

public class TextAttributesImpl implements TextAttributes, HasArguments
{
	private final TextFontImpl font;
	private final TextSizeImpl size;
	private final TextAlignmentImpl alignment;

	public TextAttributesImpl(TextFontImpl font, TextSizeImpl size, TextAlignmentImpl alignment)
	{
		this.font = font;
		this.size = size;
		this.alignment = alignment;
	}

	@Override
	public TextFont font()
	{
		return font;
	}

	@Override
	public TextSize size()
	{
		return size;
	}

	@Override
	public TextAlignment alignment()
	{
		return alignment;
	}

	@Override
	public void collectArguments(ArgumentCollector collector)
	{
		collector.add(font);
		collector.add(size);
		collector.add(alignment);
	}
}
