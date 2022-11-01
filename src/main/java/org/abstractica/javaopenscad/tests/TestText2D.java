package org.abstractica.javaopenscad.tests;


import org.abstractica.javaopenscad.impl.JavaOpenSCADImpl;
import org.abstractica.javaopenscad.impl.operationsimpl.identifier.AllStrings;

import org.abstractica.javaopenscad.intf.Geometry2D;
import org.abstractica.javaopenscad.intf.JavaOpenSCAD;
import org.abstractica.javaopenscad.intf.text.TextAlignment;
import org.abstractica.javaopenscad.intf.text.TextAttributes;
import org.abstractica.javaopenscad.intf.text.TextFont;
import org.abstractica.javaopenscad.intf.text.TextSize;


import java.io.IOException;


public class TestText2D
{
	public static void main(String[] args) throws IOException
	{
		JavaOpenSCAD os = new JavaOpenSCADImpl();
		TextFont font = os.textFont("Consolas", "Regular","en", "latin");
		TextSize size = os.textSize(10/0.7, 1);
		TextAlignment alignment = os.textAlignment(TextAlignment.Direction.LEFT_TO_RIGHT,
				TextAlignment.Horizontal.CENTER, TextAlignment.Vertical.BASELINE);
		TextAttributes attr = os.textAttributes(font, size, alignment);
		Geometry2D geometry = os.text("Hello OpenSCAD!", attr, 64);

		os.generateOpenSCADFile("OpenSCAD/output.scad", geometry);
		System.out.println(AllStrings.listAllStrings());
	}
}
