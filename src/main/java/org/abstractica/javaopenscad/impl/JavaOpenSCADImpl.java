package org.abstractica.javaopenscad.impl;

import org.abstractica.cmdline.CmdLine;
import org.abstractica.code.codebuilder.CodeBuilder;
import org.abstractica.code.codebuilder.impl.CodeBuilderImpl;
import org.abstractica.code.codebuilder.textoutput.TextOutput;
import org.abstractica.code.codebuilder.textoutput.impl.StringBuilderTextOutput;
import org.abstractica.javaopenscad.JavaOpenSCAD;
import org.abstractica.javaopenscad.impl.core.AGeometry;
import org.abstractica.javaopenscad.impl.core.AModule;
import org.abstractica.javaopenscad.impl.core.Module2DImpl;
import org.abstractica.javaopenscad.impl.core.Module3DImpl;
import org.abstractica.javaopenscad.impl.core.identifier.AllStrings;
import org.abstractica.javaopenscad.impl.core.identifier.Identifier;
import org.abstractica.javaopenscad.impl.operationsimpl.polyhedronimpl.Geometry3DFromPolyhedron3DImpl;
import org.abstractica.javaopenscad.impl.operationsimpl.polyhedronimpl.Polyhedron3DImpl;
import org.abstractica.javaopenscad.impl.operationsimpl.polyhedronimpl.Vector3DImpl;
import org.abstractica.javaopenscad.intf.*;
import org.abstractica.javaopenscad.intf.OpenSCADPath;
import org.abstractica.javaopenscad.intf.OpenSCADPolygon2D;
import org.abstractica.javaopenscad.intf.OpenSCADVector2D;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextAlignment;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextAttributes;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextFont;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextSize;
import org.abstractica.javaopenscad.impl.operationsimpl.*;
import org.abstractica.javaopenscad.impl.operationsimpl.polygonimpl.Geometry2DFromPolygon2DImpl;
import org.abstractica.javaopenscad.impl.operationsimpl.polygonimpl.PathImpl;
import org.abstractica.javaopenscad.impl.operationsimpl.polygonimpl.Polygon2DImpl;
import org.abstractica.javaopenscad.impl.operationsimpl.polygonimpl.Vector2DImpl;
import org.abstractica.javaopenscad.impl.operationsimpl.textimpl.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JavaOpenSCADImpl implements JavaOpenSCAD
{
	private final String moduleDirectoryName;
	private final boolean binarySTL;
	private final Map<Integer, AGeometry> uniqueModules;

	public JavaOpenSCADImpl(boolean useCache, boolean binarySTL)
	{
		this.binarySTL = binarySTL;
		this.uniqueModules = new HashMap<>();
		if(!useCache)
		{
			this.moduleDirectoryName = null;
		}
		else
		{
			this.moduleDirectoryName = System.getProperty("user.dir").replace("\\", "/") +
								"/OpenSCAD/ModuleCache";
			String allStringsFileName = moduleDirectoryName + "/AllStrings/AllStrings.txt";
			java.nio.file.Path path = Paths.get(allStringsFileName);
			if(Files.exists(path))
			{
				AllStrings.readFromFile(allStringsFileName);
			}
		}
	}

	public JavaOpenSCADImpl(boolean useCache)
	{
		this(useCache, true);
	}

	@Override
	public OpenSCADVector2D vector2D(double x, double y)
	{
		return new Vector2DImpl(x, y);
	}

	@Override
	public OpenSCADPath path(Iterable<Integer> path)
	{
		return new PathImpl(path);
	}

	@Override
	public OpenSCADPolygon2D polygon2D(Iterable<OpenSCADVector2D> vertices, int convexity)
	{
		return new Polygon2DImpl(vertices, null, convexity);
	}

	@Override
	public OpenSCADPolygon2D polygon2D(Iterable<OpenSCADVector2D> vertices, Iterable<OpenSCADPath> paths, int convexity)
	{
		return new Polygon2DImpl(vertices, paths, convexity);
	}

	@Override
	public OpenSCADGeometry2D polygon2DGeometry(OpenSCADPolygon2D polygon)
	{
		return module(new Geometry2DFromPolygon2DImpl(polygon));
	}

	@Override
	public OpenSCADGeometry2DFrom2D translate2D(double x, double y)
	{
		return new Translate2DImpl(x, y);
	}

	@Override
	public OpenSCADGeometry2DFrom2D rotate2D(double deg)
	{
		return new RotateAndProject2DImpl(0,0, deg);
	}

	@Override
	public OpenSCADGeometry2DFrom2D rotateAndProject2D(double xDeg, double yDeg, double zDeg)
	{
		return new RotateAndProject2DImpl(xDeg, yDeg, zDeg);
	}

	@Override
	public OpenSCADGeometry2DFrom2D scale2D(double x, double y)
	{
		return new Scale2DImpl(x, y);
	}

	@Override
	public OpenSCADGeometry2DFrom2D resize2D(double x, double y, boolean autoX, boolean autoY)
	{
		return new Resize2DImpl(x, y, autoX, autoY);
	}

	@Override
	public OpenSCADGeometry2DFrom2D mirror2D(double normX, double normY)
	{
		return new Mirror2DImpl(normX, normY);
	}

	@Override
	public OpenSCADGeometry2DFrom2D union2D()
	{
		return new Union2DImpl();
	}

	@Override
	public OpenSCADGeometry2DFrom2D intersection2D()
	{
		return new Intersection2DImpl();
	}

	@Override
	public OpenSCADGeometry2DFrom2D difference2D()
	{
		return new Difference2DImpl();
	}

	@Override
	public OpenSCADGeometry2DFrom2D hull2D()
	{
		return new Hull2DImpl();
	}

	@Override
	public OpenSCADGeometry2DFrom2D minkowsky2D()
	{
		return new Minkowsky2DImpl();
	}

	@Override
	public OpenSCADGeometry2DFrom2D offset2D(double delta, boolean chamfer)
	{
		return new Offset2DImpl(delta, chamfer);
	}

	@Override
	public OpenSCADGeometry2DFrom2D offsetRound2D(double radius, int angularResolution)
	{
		return new OffsetRound2DImpl(radius, angularResolution);
	}

	@Override
	public OpenSCADGeometry2DFrom2D color2D(double r, double g, double b, double a)
	{
		return null;
	}

	@Override
	public OpenSCADTextFont textFont(String fontName, String fontStyle, String language, String script)
	{
		return new TextFontImpl(fontName, fontStyle, language, script);
	}

	@Override
	public OpenSCADTextSize textSize(double size, double spacing)
	{
		return new TextSizeImpl(size, spacing);
	}

	@Override
	public OpenSCADTextAlignment textAlignment(OpenSCADTextAlignment.Direction direction, OpenSCADTextAlignment.Horizontal horizontal, OpenSCADTextAlignment.Vertical vertical)
	{
		return new TextAlignmentImpl(direction, horizontal, vertical);
	}

	@Override
	public OpenSCADTextAttributes textAttributes(OpenSCADTextFont font, OpenSCADTextSize size, OpenSCADTextAlignment alignment)
	{
		return new TextAttributesImpl((TextFontImpl) font, (TextSizeImpl) size, (TextAlignmentImpl) alignment);
	}

	@Override
	public OpenSCADGeometry2D text(String text, OpenSCADTextAttributes attributes, int angularResolution)
	{
		return new Text2DImpl(text, (TextAttributesImpl) attributes, angularResolution);
	}

	@Override
	public OpenSCADVector3D vector3D(double x, double y, double z)
	{
		return new Vector3DImpl(x, y, z);
	}

	@Override
	public OpenSCADPolyhedron3D polyhedron3D(Iterable<OpenSCADVector3D> vertices, Iterable<OpenSCADPath> faces, int convexity)
	{
		return new Polyhedron3DImpl(vertices, faces, convexity);
	}

	@Override
	public OpenSCADGeometry3D polyhedron3DGeometry(OpenSCADPolyhedron3D polyhedron)
	{
		return module(new Geometry3DFromPolyhedron3DImpl(polyhedron));
	}

	@Override
	public OpenSCADGeometry2DFrom3D project(boolean cutAtZeroZ)
	{
		return new Projection2DFrom3DImpl(cutAtZeroZ);
	}

	@Override
	public OpenSCADGeometry3DFrom3D translate3D(double x, double y, double z)
	{
		return new Translate3DImpl(x, y, z);
	}

	@Override
	public OpenSCADGeometry3DFrom3D rotate3D(double xDeg, double yDeg, double zDeg)
	{
		return new Rotate3DImpl(xDeg, yDeg, zDeg);
	}

	@Override
	public OpenSCADGeometry3DFrom3D scale3D(double x, double y, double z)
	{
		return new Scale3DImpl(x, y, z);
	}

	@Override
	public OpenSCADGeometry3DFrom3D resize3D(double x, double y, double z, boolean autoX, boolean autoY, boolean autoZ)
	{
		return new Resize3DImpl(x, y, z, autoX, autoY, autoZ);
	}

	@Override
	public OpenSCADGeometry3DFrom3D mirror3D(double normX, double normY, double normZ)
	{
		return new Mirror3DImpl(normX, normY, normZ);
	}

	@Override
	public OpenSCADGeometry3DFrom3D union3D()
	{
		return new Union3DImpl();
	}

	@Override
	public OpenSCADGeometry3DFrom3D intersection3D()
	{
		return new Intersection3DImpl();
	}

	@Override
	public OpenSCADGeometry3DFrom3D difference3D()
	{
		return new Difference3DImpl();
	}

	@Override
	public OpenSCADGeometry3DFrom3D hull3D()
	{
		return new Hull3DImpl();
	}

	@Override
	public OpenSCADGeometry3DFrom3D minkowsky3D()
	{
		return new Minkowsky3DImpl();
	}

	@Override
	public OpenSCADGeometry3DFrom2D linearExtrude(double height, double twistDeg, double scale, int slices, int convexity)
	{
		return new LinearExtrude3DFrom2DImpl(height, twistDeg, scale, slices, convexity);
	}

	@Override
	public OpenSCADGeometry3DFrom2D rotateExtrude(double angleDeg, int angularResolution, int convexity)
	{
		return new RotateExtrude3DFrom2DImpl(angleDeg, angularResolution, convexity);
	}

	@Override
	public OpenSCADGeometry2D module(OpenSCADGeometry2D geometry)
	{
		int id = getId(geometry);
		AGeometry geo = uniqueModules.get(id);
		if(geo == null)
		{
			geo = (AGeometry) geometry;
			uniqueModules.put(id, geo);
		}
		return new Module2DImpl(geo, id);
	}

	@Override
	public OpenSCADGeometry3D module(OpenSCADGeometry3D geometry)
	{
		int geometryId = getId(geometry);
		if(moduleDirectoryName == null)
		{
			AGeometry geo = uniqueModules.get(geometryId);
			if(geo == null)
			{
				geo = (AGeometry) geometry;
				uniqueModules.put(geometryId, geo);
			}
			return new Module3DImpl(geo, geometryId);
		}

		AGeometry res = (AGeometry) loadSTL(moduleDirectoryName + "/M" + geometryId + ".stl");
		int loaderId = getId(res);
		AGeometry geo = uniqueModules.get(loaderId);
		if(geo == null)
		{
			geo = (AGeometry) res;
			uniqueModules.put(loaderId, geo);
			saveSTL("M" + geometryId, geometry);
		}
		return new Module3DImpl(geo, loaderId);
	}

	@Override
	public void generateOpenSCADFile(String fileName, OpenSCADGeometry geometry)
	{
		java.nio.file.Path path = Paths.get(fileName);
		TextOutput out = new StringBuilderTextOutput();
		CodeBuilderImpl cb = new CodeBuilderImpl(   out,
				"    ",
				"{", "}",
				"(", ", ", ")"  );
		generate(cb,(AGeometry) geometry);
		java.nio.file.Path parentDir = path.getParent();
		try
		{
			if (parentDir != null && !Files.exists(parentDir))
				Files.createDirectories(parentDir);
			Files.writeString(path, cb.toString());
		}catch(IOException e)
		{
			throw new RuntimeException("Could not create OpenSCAD file: " + fileName, e);
		}
		if(moduleDirectoryName != null)
		{
			String allStringsFileName = moduleDirectoryName + "/AllStrings/AllStrings.txt";
			AllStrings.writeToFile(allStringsFileName);
		}
	}

	@Override
	public OpenSCADGeometry3D loadSTL(String fileName)
	{
		return new LoadSTL3DImpl(fileName);
	}

	@Override
	public void saveSTL(String name, OpenSCADGeometry3D geometry)
	{
		String prefix = moduleDirectoryName + "/" + name;
		String scadFile = prefix+".scad";
		if(!Files.exists(Paths.get(scadFile)))
		{
			generateOpenSCADFile(prefix + ".scad", geometry);
			String exportFormat = "--export-format ";
			exportFormat += binarySTL ? "binstl " : "asciistl ";
			String cmd = "openscad " + exportFormat + " -o " + prefix + ".stl " + prefix + ".scad";
			CmdLine.runCommand(cmd);
		}
	}

	private int getId(OpenSCADGeometry geometry)
	{
		Identifier identifier = new Identifier((AGeometry) geometry);
		return identifier.getUniqueId();
	}

	private void generate(CodeBuilder cb, AGeometry module)
	{
		Map<Integer, AModule> usedModules = new HashMap<>();
		Map<Integer, AModule> createdModules = new HashMap<>();
		module.generateCall(cb, usedModules);
		while(!usedModules.isEmpty())
		{
			Map<Integer, AModule> newUsedModules = new HashMap<>();
			for(AModule mod : usedModules.values())
			{
				mod.generateModule(cb, newUsedModules);
				createdModules.put(mod.id(), mod);
			}
			usedModules = new HashMap<>();
			for(AModule mod : newUsedModules.values())
			{
				if(!createdModules.containsKey(mod.id()))
				{
					usedModules.put(mod.id(), mod);
				}
			}
		}
	}
}
