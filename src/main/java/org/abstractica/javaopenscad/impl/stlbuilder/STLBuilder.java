package org.abstractica.javaopenscad.impl.stlbuilder;

import org.abstractica.cmdline.CmdLine;

public class STLBuilder
{
	public static boolean build(int moduleId)
	{
		String inputFile = "OpenSCAD/Modules/M" + moduleId + ".scad";
		String outputFile = "OpenSCAD/Modules/M" + moduleId + ".stl";
		String cmd = "openscad -o " + outputFile + " " + inputFile;
		try
		{
			return CmdLine.runCommand(cmd);
		} catch (Exception e)
		{
			return false;
		}
	}
}
