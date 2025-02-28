package org.abstractica.javaopenscad.impl;


import org.abstractica.cmdline.CmdLine;
import org.abstractica.code.codebuilder.CodeBuilder;
import org.abstractica.code.codebuilder.impl.CodeBuilderImpl;
import org.abstractica.code.codebuilder.textoutput.TextOutput;
import org.abstractica.code.codebuilder.textoutput.impl.StringBuilderTextOutput;
import org.abstractica.javaopenscad.impl.core.*;
import org.abstractica.javaopenscad.impl.core.identifier.AllStrings;
import org.abstractica.javaopenscad.impl.core.identifier.Identifier;

import org.abstractica.javaopenscad.JavaOpenSCAD;
import org.abstractica.javaopenscad.intf.*;

import org.abstractica.javaopenscad.intf.text.OpenSCADTextAlignment;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextAttributes;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextFont;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextSize;
import org.abstractica.javaopenscad.impl.operationsimpl.*;

import org.abstractica.javaopenscad.impl.operationsimpl.textimpl.*;
import org.abstractica.javaopenscad.stl.STL;
import org.abstractica.javaopenscad.stl.STLVector3D;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaOpenSCADImpl implements JavaOpenSCAD
{
	private final String moduleCacheDirectoryName;
	private final Map<Integer, AGeometry> uniqueModules;

	public JavaOpenSCADImpl(boolean useCache)
	{
		this.uniqueModules = new HashMap<>();
		if(!useCache)
		{
			this.moduleCacheDirectoryName = null;
		}
		else
		{
			this.moduleCacheDirectoryName = System.getProperty("user.dir").replace("\\", "/") +
								"/OpenSCAD/ModuleCache";
			String allStringsFileName = moduleCacheDirectoryName + "/AllStrings/AllStrings.txt";
			java.nio.file.Path path = Paths.get(allStringsFileName);
			if(Files.exists(path))
			{
				AllStrings.readFromFile(allStringsFileName);
			}
		}
	}

	public JavaOpenSCADImpl(String cacheDirectory)
	{
		this.uniqueModules = new HashMap<>();
		this.moduleCacheDirectoryName = cacheDirectory;
		String allStringsFileName = moduleCacheDirectoryName + "/AllStrings/AllStrings.txt";
		java.nio.file.Path path = Paths.get(allStringsFileName);
		if(Files.exists(path))
		{
			AllStrings.readFromFile(allStringsFileName);
		}
	}

	@Override
	public OpenSCADVector2D vector2D(double x, double y)
	{
		return new OpenSCADVector2DImpl(x, y);
	}

	@Override
	public OpenSCADGeometry2D polygon2D(Iterable<OpenSCADVector2D> vertices)
	{
		ArrayList<OpenSCADVector2D> vertexList = new ArrayList<>();
		ArrayList<Integer> path = new ArrayList<>();
		for(OpenSCADVector2D vertex : vertices)
		{
			path.add(vertexList.size());
			vertexList.add(vertex);
		}
		path.trimToSize();
		vertexList.trimToSize();
		List<List<Integer>> paths = new ArrayList<>(1);
		paths.add(path);
		return new Polygon2DImpl(vertexList, paths);
	}

	@Override
	public OpenSCADGeometry2D polygon2D(Iterable<OpenSCADVector2D> vertices, Iterable<? extends Iterable<Integer>> paths)
	{
		ArrayList<OpenSCADVector2D> vertexList = new ArrayList<>();
		for(OpenSCADVector2D vertex : vertices)
		{
			vertexList.add(vertex);
		}
		vertexList.trimToSize();
		ArrayList<List<Integer>> pathListList = new ArrayList<>();
		for(Iterable<Integer> path : paths)
		{
			ArrayList<Integer> pathList = new ArrayList<>();
			for(Integer i : path)
			{
				pathList.add(i);
			}
			pathList.trimToSize();
			pathListList.add(pathList);
		}
		pathListList.trimToSize();
		return new Polygon2DImpl(vertexList, pathListList);
	}

	@Override
	public OpenSCADGeometry2DFrom2D translate2D(double x, double y)
	{
		return new Translate2DImpl(x, y);
	}

	@Override
	public OpenSCADGeometry2DFrom2D rotate2D(double deg)
	{
		return new Rotate2DImpl(deg);
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
		return new OpenSCADVector3DImpl(x, y, z);
	}

	@Override
	public OpenSCADGeometry3DFrom3D multMatrix3D(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23)
	{
		return new MultMatrix3DImpl(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23);
	}

	@Override
	public OpenSCADGeometry3D polyhedron3D(Iterable<OpenSCADVector3D> vertices, Iterable<? extends Iterable<Integer>> faces)
	{
		ArrayList<OpenSCADVector3D> vertexList = new ArrayList<>();
		for(OpenSCADVector3D vertex : vertices)
		{
			vertexList.add(vertex);
		}
		vertexList.trimToSize();
		ArrayList<List<Integer>> faceListList = new ArrayList<>();
		for(Iterable<Integer> face : faces)
		{
			ArrayList<Integer> faceList = new ArrayList<>();
			for(Integer i : face)
			{
				faceList.add(i);
			}
			faceList.trimToSize();
			faceListList.add(faceList);
		}
		faceListList.trimToSize();
		return new Polyhedron3DImpl(vertexList, faceListList);
	}

	@Override
	public OpenSCADGeometry2DFrom3D project(boolean cutAtZeroZ)
	{
		return new Projection2DFrom3DImpl(cutAtZeroZ);
	}

	@Override
	public OpenSCADGeometry3DFrom3D color3D(double r, double g, double b, double a)
	{
		return new Color3DImpl(r, g, b, a);
	}

	@Override
	public OpenSCADGeometry3DFrom3D color3D(double r, double g, double b)
	{
		return color3D(r, g, b, 1);
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
	public OpenSCADGeometry3DFrom2D linearExtrude(double height,
	                                              double twistDeg,
	                                              double scale,
	                                              int slices,
	                                              boolean centerZ)
	{
		return new LinearExtrude3DFrom2DImpl(height, twistDeg, scale, slices, centerZ);
	}

	@Override
	public OpenSCADGeometry3DFrom2D rotateExtrude(double angleDeg, int angularResolution)
	{
		return new RotateExtrude3DFrom2DImpl(angleDeg, angularResolution);
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
		AGeometry geo = uniqueModules.get(geometryId);
		if(geo == null)
		{
			geo = (AGeometry) geometry;
			uniqueModules.put(geometryId, geo);
		}
		return new Module3DImpl(geo, geometryId);
	}

	@Override
	public OpenSCADVector2D getMin2D(OpenSCADGeometry2D geometry)
	{
		AGeometry geo = (AGeometry) geometry;
		BoundingBox bb = geo.getBoundingBox();
		if(bb == null)
		{
			bb = calculateBoundingBox(geometry);
			geo.setBoundingBox(bb);
		}
		return vector2D(bb.xMin, bb.yMin);
	}

	@Override
	public OpenSCADVector2D getMax2D(OpenSCADGeometry2D geometry)
	{
		AGeometry geo = (AGeometry) geometry;
		BoundingBox bb = geo.getBoundingBox();
		if(bb == null)
		{
			bb = calculateBoundingBox(geometry);
			geo.setBoundingBox(bb);
		}
		return vector2D(bb.xMax, bb.yMax);
	}

	@Override
	public OpenSCADVector3D getMin3D(OpenSCADGeometry3D geometry)
	{
		AGeometry geo = (AGeometry) geometry;
		BoundingBox bb = geo.getBoundingBox();
		if(bb == null)
		{
			bb = calculateBoundingBox(geometry);
			geo.setBoundingBox(bb);
		}
		return vector3D(bb.xMin, bb.yMin, bb.zMin);
	}

	@Override
	public OpenSCADVector3D getMax3D(OpenSCADGeometry3D geometry)
	{
		AGeometry geo = (AGeometry) geometry;
		BoundingBox bb = geo.getBoundingBox();
		if(bb == null)
		{
			bb = calculateBoundingBox(geometry);
			geo.setBoundingBox(bb);
		}
		return vector3D(bb.xMax, bb.yMax, bb.zMax);
	}

	@Override
	public OpenSCADGeometry3D cacheGeometry3D(OpenSCADGeometry3D geometry) throws IOException
	{
		if(moduleCacheDirectoryName == null)
		{
			if(geometry instanceof AModule)
			{
				return geometry;
			}
			return module(geometry);
		}
		//Check if this is already a stl loader

		if(geometry instanceof Module3DImpl)
		{
			Module3DImpl amod = (Module3DImpl) geometry;
			AGeometry geo = amod.getGeometry();
			if(geo instanceof LoadFile3DImpl)
			{
				return geometry;
			}
		}
		int geometryId = getId(geometry);
		AGeometry res = (AGeometry) load3MF(moduleCacheDirectoryName + "/M" + geometryId + ".3mf");
		int loaderId = getId(res);
		AGeometry geo = uniqueModules.get(loaderId);
		if(geo == null)
		{
			geo = res;
			uniqueModules.put(loaderId, geo);
			cache3MF("M" + geometryId, geometry);
		}
		return new Module3DImpl(geo, loaderId);
	}

	@Override
	public void generateOpenSCADFile(String fileName, OpenSCADGeometry geometry) throws IOException
	{
		java.nio.file.Path path = Paths.get(fileName);
		TextOutput out = new StringBuilderTextOutput();
		CodeBuilderImpl cb = new CodeBuilderImpl(   out,
				"    ",
				"{", "}",
				"(", ", ", ")"  );
		generate(cb,(AGeometry) geometry);
		java.nio.file.Path parentDir = path.getParent();

		if (parentDir != null && !Files.exists(parentDir))
		{
			Files.createDirectories(parentDir);
		}
		Files.writeString(path, cb.toString());
		if(moduleCacheDirectoryName != null)
		{
			String allStringsFileName = moduleCacheDirectoryName + "/AllStrings/AllStrings.txt";
			AllStrings.writeToFile(allStringsFileName);
		}
	}

	@Override
	public OpenSCADGeometry3D loadSTL(String fileName)
	{
		Path path = Paths.get(fileName).toAbsolutePath();
		String absoluteFileName = path.toString().replace("\\", "/");
		return new LoadFile3DImpl(absoluteFileName);
	}

	@Override
	public void saveSTL(String fileName, OpenSCADGeometry3D geometry) throws IOException
	{
		saveSTL(fileName, geometry, true);
	}

	@Override
	public OpenSCADGeometry3D load3MF(String fileName) throws IOException
	{
		Path path = Paths.get(fileName).toAbsolutePath();
		String absoluteFileName = path.toString().replace("\\", "/");
		return new LoadFile3DImpl(absoluteFileName);
	}

	@Override
	public void save3MF(String fileName, OpenSCADGeometry3D geometry) throws IOException
	{
		save3MF(fileName, geometry, true);
	}

	private BoundingBox calculateBoundingBox(OpenSCADGeometry2D geometry)
	{
		OpenSCADGeometry3DFrom2D linExtrude = linearExtrude(1, 0, 1, 1, false);
		linExtrude.add(geometry);
		return calculateBoundingBox(linExtrude);
	}

	private BoundingBox calculateBoundingBox(OpenSCADGeometry3D geometry)
	{
		STL stl;
		try
		{
			String fileName = System.getProperty("java.io.tmpdir").replace('\\', '/')
				+ "tmpMinMax.stl";
			saveSTL(fileName, geometry);
			stl = STL.load(fileName);
			Files.delete(Paths.get(fileName));
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		STLVector3D min = stl.getMin();
		STLVector3D max = stl.getMax();
		return new BoundingBox(min.x, min.y, min.z, max.x, max.y, max.z);
	}

	private void cache3MF(String name, OpenSCADGeometry3D geometry) throws IOException
	{
		String prefix = moduleCacheDirectoryName + "/" + name;
		String threeMFFileName = prefix + ".3mf";
		String scadFileName = prefix + ".scad";
		if(!Files.exists(Paths.get(threeMFFileName)) || !Files.exists(Paths.get(scadFileName)))
		{
			save3MF(threeMFFileName, geometry, false);
		}
	}

	private void saveSTL(String fileName, OpenSCADGeometry3D geometry, boolean deleteScadFile) throws IOException
	{
		if(!fileName.endsWith(".stl"))
		{
			throw new IllegalArgumentException("File name must end with .stl");
		}
		String prefix = fileName.substring(0, fileName.length() - 4);
		String scadFileName = prefix + ".scad";
		generateOpenSCADFile(scadFileName, geometry);
		String cmd = "openscad --backend=manifold --export-format binstl -o \"" + prefix + ".stl\" \"" + prefix + ".scad\"";
		int exitCode = CmdLine.runCommand(cmd);
		if(deleteScadFile)
		{
			Files.delete(Paths.get(scadFileName));
		}
		if(exitCode != 0)
		{
			throw new RuntimeException("OpenSCAD failed with exit code " + exitCode);
		}
	}

	private void save3MF(String fileName, OpenSCADGeometry3D geometry, boolean deleteScadFile) throws IOException
	{
		if(!fileName.endsWith(".3mf"))
		{
			throw new IllegalArgumentException("File name must end with .3mf");
		}
		String prefix = fileName.substring(0, fileName.length() - 4);
		String scadFileName = prefix + ".scad";
		generateOpenSCADFile(scadFileName, geometry);
		String cmd = "openscad --backend=manifold --export-format 3mf -o \"" + prefix + ".3mf\" \"" + prefix + ".scad\"";
		int exitCode = CmdLine.runCommand(cmd);
		if(deleteScadFile)
		{
			Files.delete(Paths.get(scadFileName));
		}
		if(exitCode != 0)
		{
			throw new RuntimeException("OpenSCAD failed with exit code " + exitCode);
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
