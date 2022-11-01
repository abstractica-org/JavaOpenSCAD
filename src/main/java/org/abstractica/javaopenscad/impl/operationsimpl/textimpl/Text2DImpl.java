package org.abstractica.javaopenscad.impl.operationsimpl.textimpl;

import org.abstractica.javaopenscad.intf.text.TextAlignment;
import org.abstractica.javaopenscad.impl.AGeometry2D;
import org.abstractica.javaopenscad.impl.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class Text2DImpl extends AGeometry2D
{
	private final String text;
	private final TextAttributesImpl attributes;
	private final int resolution;

	public Text2DImpl(String text, TextAttributesImpl attributes, int resolution)
	{
		this.text = text;
		this.attributes = attributes;
		this.resolution = resolution;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(text);
		collector.add(attributes);
		collector.add(resolution);
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.println("text");
		cb.println("(");
		cb.indent();

		// text
		cb.print("text = \"");
		cb.print(text.replace("\"", "\\\""));
		//cb.print(text.replace("\"", "\\\"").replace("\\", "\\\\"));
		cb.println("\",");

		// size
		cb.print("size = ");
		cb.print(Double.toString(attributes.size().size()));
		cb.println(",");

		// font
		cb.print("font = \"");
		cb.print(attributes.font().fontName());
		cb.print(":style=");
		cb.print(attributes.font().fontStyle());
		cb.println("\",");

		// halign
		cb.print("halign = \"");
		cb.print(halign(attributes.alignment().horizontal()));
		cb.println("\",");

		// valign
		cb.print("valign = \"");
		cb.print(valign(attributes.alignment().vertical()));
		cb.println("\",");

		// spacing
		cb.print("spacing = ");
		cb.print(Double.toString(attributes.size().spacing()));
		cb.println(",");

		// direction
		cb.print("direction = \"");
		cb.print(direction(attributes.alignment().direction()));
		cb.println("\",");

		// language
		cb.print("language = \"");
		cb.print(attributes.font().language());
		cb.println("\",");

		// script
		cb.print("script = \"");
		cb.print(attributes.font().script());
		cb.println("\",");

		// language
		cb.print("$fn = ");
		cb.println(Integer.toString(resolution));

		// end of text
		cb.undent();
		cb.print(")");
	}

	private String halign(TextAlignment.Horizontal horizontal)
	{
		return switch(horizontal)
		{
			case LEFT -> "left";
			case CENTER -> "center";
			case RIGHT -> "right";
		};
	}

	private String valign(TextAlignment.Vertical vertical)
	{
		return switch(vertical)
		{
			case TOP -> "top";
			case CENTER -> "center";
			case BASELINE -> "baseline";
			case BOTTOM -> "bottom";
		};
	}

	private String direction(TextAlignment.Direction direction)
	{
		return switch(direction)
		{
			case LEFT_TO_RIGHT -> "ltr";
			case RIGHT_TO_LEFT -> "rtl";
			case TOP_TO_BOTTOM -> "ttb";
			case BOTTOM_TO_TOP -> "btt";
		};
	}
}
