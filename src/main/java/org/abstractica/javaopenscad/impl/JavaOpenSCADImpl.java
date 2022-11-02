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
import org.abstractica.javaopenscad.impl.operationsimpl.identifier.Identifier;
import org.abstractica.javaopenscad.intf.*;
import org.abstractica.javaopenscad.intf.polygon.Path;
import org.abstractica.javaopenscad.intf.polygon.Polygon2D;
import org.abstractica.javaopenscad.intf.polygon.Vector2D;
import org.abstractica.javaopenscad.intf.text.TextAlignment;
import org.abstractica.javaopenscad.intf.text.TextAttributes;
import org.abstractica.javaopenscad.intf.text.TextFont;
import org.abstractica.javaopenscad.intf.text.TextSize;
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

	public JavaOpenSCADImpl(String moduleDirectoryName, boolean binarySTL)
	{
		this.moduleDirectoryName = moduleDirectoryName;
		this.binarySTL = binarySTL;
		this.uniqueModules = new HashMap<>();
	}

	public JavaOpenSCADImpl()
	{
		this(null, false);
	}

	@Override
	public Vector2D vector2D(double x, double y)
	{
		return new Vector2DImpl(x, y);
	}

	@Override
	public Path path(Iterable<Integer> path)
	{
		return new PathImpl(path);
	}

	@Override
	public Polygon2D polygon2D(Iterable<Vector2D> vertices)
	{
		return new Polygon2DImpl(vertices, null);
	}

	@Override
	public Polygon2D polygon2D(Iterable<Vector2D> vertices, Iterable<Path> paths)
	{
		return new Polygon2DImpl(vertices, paths);
	}

	@Override
	public Geometry2D polygon2DGeometry(Polygon2D polygon)
	{
		return new Geometry2DFromPolygon2DImpl(polygon);
	}

	@Override
	public Geometry2DFrom2D translate2D(double x, double y)
	{
		return new Translate2DImpl(x, y);
	}

	@Override
	public Geometry2DFrom2D rotate2D(double deg)
	{
		return new RotateAndProject2DImpl(0,0, deg);
	}

	@Override
	public Geometry2DFrom2D rotateAndProject2D(double xDeg, double yDeg, double zDeg)
	{
		return new RotateAndProject2DImpl(xDeg, yDeg, zDeg);
	}

	@Override
	public Geometry2DFrom2D scale2D(double x, double y)
	{
		return new Scale2DImpl(x, y);
	}

	@Override
	public Geometry2DFrom2D resize2D(double x, double y, boolean autoX, boolean autoY)
	{
		return new Resize2DImpl(x, y, autoX, autoY);
	}

	@Override
	public Geometry2DFrom2D mirror2D(double normX, double normY)
	{
		return new Mirror2DImpl(normX, normY);
	}

	@Override
	public Geometry2DFrom2D union2D()
	{
		return new Union2DImpl();
	}

	@Override
	public Geometry2DFrom2D intersection2D()
	{
		return new Intersection2DImpl();
	}

	@Override
	public Geometry2DFrom2D difference2D()
	{
		return new Difference2DImpl();
	}

	@Override
	public Geometry2DFrom2D hull2D()
	{
		return new Hull2DImpl();
	}

	@Override
	public Geometry2DFrom2D minkowsky2D()
	{
		return new Minkowsky2DImpl();
	}

	@Override
	public Geometry2DFrom2D offset2D(double delta, boolean chamfer)
	{
		return new Offset2DImpl(delta, chamfer);
	}

	@Override
	public Geometry2DFrom2D offsetRound2D(double radius, int angularResolution)
	{
		return new OffsetRound2DImpl(radius, angularResolution);
	}

	@Override
	public Geometry2DFrom2D color2D(double r, double g, double b, double a)
	{
		return null;
	}

	@Override
	public TextFont textFont(String fontName, String fontStyle, String language, String script)
	{
		return new TextFontImpl(fontName, fontStyle, language, script);
	}

	@Override
	public TextSize textSize(double size, double spacing)
	{
		return new TextSizeImpl(size, spacing);
	}

	@Override
	public TextAlignment textAlignment(TextAlignment.Direction direction, TextAlignment.Horizontal horizontal, TextAlignment.Vertical vertical)
	{
		return new TextAlignmentImpl(direction, horizontal, vertical);
	}

	@Override
	public TextAttributes textAttributes(TextFont font, TextSize size, TextAlignment alignment)
	{
		return new TextAttributesImpl((TextFontImpl) font, (TextSizeImpl) size, (TextAlignmentImpl) alignment);
	}

	@Override
	public Geometry2D text(String text, TextAttributes attributes, int angularResolution)
	{
		return new Text2DImpl(text, (TextAttributesImpl) attributes, angularResolution);
	}

	@Override
	public Geometry2DFrom3D project(boolean cutAtZeroZ)
	{
		return new Projection2DFrom3DImpl(cutAtZeroZ);
	}

	@Override
	public Geometry3DFrom3D translate3D(double x, double y, double z)
	{
		return new Translate3DImpl(x, y, z);
	}

	@Override
	public Geometry3DFrom3D rotate3D(double xDeg, double yDeg, double zDeg)
	{
		return new Rotate3DImpl(xDeg, yDeg, zDeg);
	}

	@Override
	public Geometry3DFrom3D scale3D(double x, double y, double z)
	{
		return new Scale3DImpl(x, y, z);
	}

	@Override
	public Geometry3DFrom3D resize3D(double x, double y, double z, boolean autoX, boolean autoY, boolean autoZ)
	{
		return new Resize3DImpl(x, y, z, autoX, autoY, autoZ);
	}

	@Override
	public Geometry3DFrom3D mirror3D(double normX, double normY, double normZ)
	{
		return new Mirror3DImpl(normX, normY, normZ);
	}

	@Override
	public Geometry3DFrom3D union3D()
	{
		return new Union3DImpl();
	}

	@Override
	public Geometry3DFrom3D intersection3D()
	{
		return new Intersection3DImpl();
	}

	@Override
	public Geometry3DFrom3D difference3D()
	{
		return new Difference3DImpl();
	}

	@Override
	public Geometry3DFrom3D hull3D()
	{
		return new Hull3DImpl();
	}

	@Override
	public Geometry3DFrom3D minkowsky3D()
	{
		return new Minkowsky3DImpl();
	}

	@Override
	public Geometry3DFrom2D linearExtrude(double height, double twistDeg, double scale, int slices, int convexity)
	{
		return new LinearExtrude3DFrom2DImpl(height, twistDeg, scale, slices, convexity);
	}

	@Override
	public Geometry3DFrom2D rotateExtrude(double angleDeg, int angularResolution, int convexity)
	{
		return new RotateExtrude3DFrom2DImpl(angleDeg, angularResolution, convexity);
	}

	@Override
	public Geometry2D module(Geometry2D geometry)
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
	public Geometry3D module(Geometry3D geometry)
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
	public void generateOpenSCADFile(String fileName, Geometry geometry)
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
	}

	@Override
	public Geometry3D loadSTL(String fileName)
	{
		return new LoadSTL3DImpl(fileName);
	}

	@Override
	public void saveSTL(String name, Geometry3D geometry)
	{
		String prefix = moduleDirectoryName + "/" + name;
		generateOpenSCADFile(prefix +".scad", geometry);
		String exportFormat = "--export-format ";
		exportFormat += binarySTL ? "binstl " : "asciistl ";
		String cmd = "openscad " + exportFormat + " -o " + prefix + ".stl " + prefix +".scad";
		boolean succes = CmdLine.runCommand(cmd);
		if(!succes) throw new RuntimeException("Could not generate: " + prefix + ".stl");
	}

	private int getId(Geometry geometry)
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
