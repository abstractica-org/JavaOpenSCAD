package org.abstractica.javaopenscad.tests;


import org.abstractica.javaopenscad.impl.JavaOpenSCADImpl;
import org.abstractica.javaopenscad.impl.core.identifier.AllStrings;

import org.abstractica.javaopenscad.intf.OpenSCADGeometry2D;
import org.abstractica.javaopenscad.JavaOpenSCAD;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextAlignment;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextAttributes;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextFont;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextSize;


import java.io.IOException;


public class TestText2D
{
	public static void main(String[] args) throws IOException
	{
		JavaOpenSCAD os = new JavaOpenSCADImpl(true);
		OpenSCADTextFont font = os.textFont("Consolas", "Regular","en", "latin");
		OpenSCADTextSize size = os.textSize(10/0.7, 1);
		OpenSCADTextAlignment alignment = os.textAlignment(OpenSCADTextAlignment.Direction.LEFT_TO_RIGHT,
				OpenSCADTextAlignment.Horizontal.CENTER, OpenSCADTextAlignment.Vertical.BASELINE);
		OpenSCADTextAttributes attr = os.textAttributes(font, size, alignment);
		OpenSCADGeometry2D geometry = os.text("Hello OpenSCAD!", attr, 64);

		os.generateOpenSCADFile("OpenSCAD/output.scad", geometry);
		System.out.println(AllStrings.listAllStrings());
	}
}
