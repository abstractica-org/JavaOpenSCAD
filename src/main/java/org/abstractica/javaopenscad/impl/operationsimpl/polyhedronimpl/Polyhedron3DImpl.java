package org.abstractica.javaopenscad.impl.operationsimpl.polyhedronimpl;

import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.javaopenscad.impl.core.HasArguments;
import org.abstractica.javaopenscad.impl.operationsimpl.polygonimpl.PathImpl;
import org.abstractica.javaopenscad.impl.operationsimpl.polygonimpl.Vector2DImpl;
import org.abstractica.javaopenscad.intf.Polyhedron3D;
import org.abstractica.javaopenscad.intf.Vector3D;
import org.abstractica.javaopenscad.intf.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polyhedron3DImpl implements Polyhedron3D, HasArguments
{
	private final List<Vector3D> vertices;
	private final List<Path> faces;
	private final int convexity;

	public Polyhedron3DImpl(Iterable<Vector3D> vertices, Iterable<Path> faces, int convexity)
	{
		this.convexity = convexity;
		List<Vector3D> tmpVertices = new ArrayList<>();
		for(Vector3D v : vertices)
		{
			if(!(v instanceof Vector3DImpl))
			{
				throw new IllegalArgumentException("Vector3D must be of internal type Vector3DImpl");
			}
			tmpVertices.add(v);
		}
		this.vertices = Collections.unmodifiableList(tmpVertices);

		List<Path> tmpPaths = new ArrayList<>();
		for (Path path : faces)
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
	public List<Vector3D> vertices()
	{
		return vertices;
	}

	@Override
	public List<Path> faces()
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
		for(Vector3D v: vertices)
		{
			collector.add((Vector3DImpl) v);
		}

		for (Path face : faces)
		{
			collector.add((PathImpl) face);
		}
	}
}
