package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.code.codebuilder.CodeBuilder;
import org.abstractica.javaopenscad.impl.core.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polyhedron3DImpl extends AGeometry3D
{
	private final List<Double> vertices;
	private final List<List<Integer>> faces;

	public Polyhedron3DImpl(List<Double> vertices, List<List<Integer>> faces)
	{
		if(vertices == null) throw new IllegalArgumentException("vertices == null");
		if(faces == null) throw new IllegalArgumentException("faces == null");
		if(vertices.size() % 3 != 0) throw new IllegalArgumentException("vertices.size() % 3 != 0");
		int numberOfVertices = vertices.size() / 3;
		List<Double> tmpVertices = new ArrayList<>();
		for(Double d : vertices)
		{
			tmpVertices.add(d);
		}
		this.vertices = Collections.unmodifiableList(tmpVertices);

		List<List<Integer>> tmpPaths = new ArrayList<>();
		for (List<Integer> face : faces)
		{
			List<Integer> tmpFace = new ArrayList<>();
			for(Integer i : face)
			{
				if(i < 0 || i >= numberOfVertices)
				{
					throw new IllegalArgumentException("i < 0 || i >= numberOfVertices");
				}
				tmpFace.add(i);
			}
			if(tmpFace.size() < 3)
			{
				throw new IllegalArgumentException("face.size() < 3");
			}
			tmpPaths.add(Collections.unmodifiableList(tmpFace));
		}
		this.faces = Collections.unmodifiableList(tmpPaths);
	}

	@Override
	protected void getArguments(ArgumentCollector collector)
	{
		for(Double d: vertices)
		{
			collector.add(d);
		}

		for (List<Integer> face : faces)
		{
			for(Integer i : face)
			{
				collector.add(i);
			}
		}
	}

	@Override
	protected void getCallHeader(CodeBuilder cb)
	{
		//Vertices
		cb.println("polyhedron");
		cb.println("(");
		cb.indent();
		// ->
		// Points
		cb.println("points =");
		cb.println("[");
		cb.indent();
		// -->
		for(int i = 0; i < vertices.size(); i += 3)
		{
			if(i != 0) cb.print(", ");
			cb.print("[");
			cb.print(Double.toString(vertices.get(i + 0)));
			cb.print(", ");
			cb.print(Double.toString(vertices.get(i + 1)));
			cb.print(", ");
			cb.print(Double.toString(vertices.get(i + 2)));
			cb.print("]");
		}
		cb.println();
		cb.undent();
		// <-
		cb.println("]");

		cb.println(",");

		//Faces
		cb.println("faces =");
		cb.println("[");
		cb.indent();
		// -->
		boolean first1 = true;
		for(List<Integer> face : faces)
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
		//cb.println(Integer.toString(polyhedron.convexity()));
		cb.undent();
		// <
		cb.print(")");
	}
}
