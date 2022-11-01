package org.abstractica.javaopenscad.impl.operationsimpl.polygonimpl;

import org.abstractica.javaopenscad.intf.polygon.Path;
import org.abstractica.javaopenscad.intf.polygon.Polygon2D;
import org.abstractica.javaopenscad.intf.polygon.Vector2D;
import org.abstractica.javaopenscad.impl.ArgumentCollector;
import org.abstractica.javaopenscad.impl.HasArguments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polygon2DImpl implements Polygon2D, HasArguments
{
	private final List<Vector2D> vertices;
	private final List<Path> paths;

	public Polygon2DImpl(Iterable<Vector2D> vertices, Iterable<Path> paths)
	{
		List<Vector2D> tmpVertices = new ArrayList<>();
		for(Vector2D v : vertices)
		{
			if(!(v instanceof Vector2DImpl))
			{
				throw new IllegalArgumentException("Vector2D must be of internal type Vector2DImpl");
			}
			tmpVertices.add(v);
		}
		this.vertices = Collections.unmodifiableList(tmpVertices);

		if(paths == null)
		{
			this.paths = null;
		}
		else
		{
			List<Path> tmpPaths = new ArrayList<>();
			for (Path path : paths)
			{
				if (!(path instanceof PathImpl))
				{
					throw new IllegalArgumentException("Path must be of internal type PathImpl");
				}
				tmpPaths.add(path);
			}
			this.paths = Collections.unmodifiableList(tmpPaths);
		}
	}

	@Override
	public List<Vector2D> vertices()
	{
		return vertices;
	}

	@Override
	public List<Path> paths()
	{
		return paths;
	}

	@Override
	public void collectArguments(ArgumentCollector collector)
	{
		for(Vector2D v: vertices)
		{
			collector.add((Vector2DImpl) v);
		}
		if(paths != null)
		{
			for (Path path : paths)
			{
				collector.add((PathImpl) path);
			}
		}
	}
}
