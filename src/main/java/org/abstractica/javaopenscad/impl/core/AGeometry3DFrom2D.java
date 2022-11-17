package org.abstractica.javaopenscad.impl.core;

import org.abstractica.javaopenscad.intf.OpenSCADGeometry2D;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry3DFrom2D;

public abstract class AGeometry3DFrom2D extends AGeometryNode implements OpenSCADGeometry3DFrom2D
{
	protected AGeometry3DFrom2D() {}

	@Override
	public OpenSCADGeometry3DFrom2D add(OpenSCADGeometry2D child)
	{
		addChild((AGeometry) child);
		return this;
	}
}
