package org.abstractica.javaopenscad.impl.operationsimpl.polyhedronimpl;

import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.javaopenscad.impl.core.HasArguments;
import org.abstractica.javaopenscad.impl.operationsimpl.polygonimpl.PathImpl;
import org.abstractica.javaopenscad.intf.OpenSCADPolyhedron3D;
import org.abstractica.javaopenscad.intf.OpenSCADVector3D;
import org.abstractica.javaopenscad.intf.OpenSCADPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polyhedron3DImpl implements OpenSCADPolyhedron3D, HasArguments
{
	private final List<OpenSCADVector3D> vertices;
	private final List<OpenSCADPath> faces;
	private final int convexity;

	public Polyhedron3DImpl(Iterable<OpenSCADVector3D> vertices, Iterable<OpenSCADPath> faces, int convexity)
	{
		this.convexity = convexity;
		List<OpenSCADVector3D> tmpVertices = new ArrayList<>();
		for(OpenSCADVector3D v : vertices)
		{
			if(!(v instanceof Vector3DImpl))
			{
				throw new IllegalArgumentException("Vector3D must be of internal type Vector3DImpl");
			}
			tmpVertices.add(v);
		}
		this.vertices = Collections.unmodifiableList(tmpVertices);

		List<OpenSCADPath> tmpPaths = new ArrayList<>();
		for (OpenSCADPath path : faces)
		{
			if (!(path instanceof PathImpl))
			{
				throw new IllegalArgumentException("Path must be of internal type PathImpl");
			}
			tmpPaths.add(path);
		}
		this.faces = Collections.unmodifiableList(tmpPaths);
	}

	@Override
	public List<OpenSCADVector3D> vertices()
	{
		return vertices;
	}

	@Override
	public List<OpenSCADPath> faces()
	{
		return faces;
	}

	@Override
	public int convexity()
	{
		return convexity;
	}

	@Override
	public void collectArguments(ArgumentCollector collector)
	{
		for(OpenSCADVector3D v: vertices)
		{
			collector.add((Vector3DImpl) v);
		}

		for (OpenSCADPath face : faces)
		{
			collector.add((PathImpl) face);
		}
	}
}
