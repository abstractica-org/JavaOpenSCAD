package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.core.AGeometry2D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polygon2DImpl extends AGeometry2D
{
	private final List<Double> vertices;
	private final List<List<Integer>> paths;

	public Polygon2DImpl(List<Double> vertices, List<List<Integer>> paths)
	{
		if(paths == null) throw new IllegalArgumentException("paths == null");
		if(vertices == null) throw new IllegalArgumentException("vertices == null");
		if(vertices.size() % 2 != 0)
		{
			throw new IllegalArgumentException("Vertices must be a multiple of 2");
		}
		int numberOfVertices = vertices.size() / 2;
		List<Double> tmpVertices = new ArrayList<>();
		for(Double d : vertices)
		{
			tmpVertices.add(d);
		}
		this.vertices = Collections.unmodifiableList(tmpVertices);
		List<List<Integer>> tmpPaths = new ArrayList<>();
		for(List<Integer> path : paths)
		{
			if(path.size() < 3)
			{
				throw new IllegalArgumentException("Path must have at least 3 vertices");
			}
			List<Integer> tmpPath = new ArrayList<>();
			for(Integer i : path)
			{
				if(i < 0 || i >= numberOfVertices)
				{
					throw new IllegalArgumentException("Path index out of bounds");
				}
				tmpPath.add(i);
			}
			tmpPaths.add(Collections.unmodifiableList(tmpPath));
		}
		this.paths = Collections.unmodifiableList(tmpPaths);
	}

	public Polygon2DImpl(List<Double> vertices)
	{
		if(vertices == null) throw new IllegalArgumentException("vertices == null");
		if(vertices.size() % 2 != 0)
		{
			throw new IllegalArgumentException("Vertices must be a multiple of 2");
		}
		int numberOfVertices = vertices.size() / 2;
		List<Double> tmpVertices = new ArrayList<>();
		for(Double d : vertices)
		{
			tmpVertices.add(d);
		}
		this.vertices = Collections.unmodifiableList(tmpVertices);
		List<List<Integer>> tmpPaths = new ArrayList<>();
		List<Integer> tmpPath = new ArrayList<>();
		for(int i = 0; i < numberOfVertices; i++)
		{
			tmpPath.add(i);
		}
		tmpPaths.add(Collections.unmodifiableList(tmpPath));
		this.paths = Collections.unmodifiableList(tmpPaths);
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		//Vertices
		cb.println("polygon");
		cb.println("(");
		cb.indent();
		//-->>
		cb.println("points =");
		cb.println("[");
		cb.indent();
		//-->>
		for(int i = 0; i < vertices.size(); i += 2)
		{
			if(i != 0) cb.print(",");
			cb.print("[" + vertices.get(i) + ", " + vertices.get(i + 1) + "]");
		}
		cb.println();
		cb.undent();
		cb.println("],");

		//Paths
		cb.println("paths =");
		cb.println("[");
		cb.indent();
		// -->
		boolean first1 = true;
		for(List<Integer> face : paths)
		{
			if(first1)
			{
				first1 = false;
			}
			else
			{
				cb.println(", ");
			}
			cb.print("[");
			boolean first2 = true;
			for(Integer i : face)
			{
				if(first2)
				{
					first2 = false;
				}
				else
				{
					cb.print(", ");
				}
				cb.print(Integer.toString(i));
			}
			cb.print("]");
			cb.println();
		}
		cb.undent();
		// <-
		cb.println("]");
		//cb.print(", convexity = ");
		//cb.println(Integer.toString(polygon.convexity()));
		cb.undent();
		// <
		cb.print(")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		for(Double d: vertices)
		{
			collector.add(d);
		}

		for (List<Integer> path : paths)
		{
			for(Integer i : path)
			{
				collector.add(i);
			}
		}
	}
}
