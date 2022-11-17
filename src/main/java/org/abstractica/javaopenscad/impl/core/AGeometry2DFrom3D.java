package org.abstractica.javaopenscad.impl.core;

import org.abstractica.javaopenscad.intf.OpenSCADGeometry2DFrom3D;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry3D;

public abstract class AGeometry2DFrom3D extends AGeometryNode implements OpenSCADGeometry2DFrom3D
{
	protected AGeometry2DFrom3D() {}

	@Override
	public OpenSCADGeometry2DFrom3D add(OpenSCADGeometry3D child)
	{
		addChild((AGeometry) child);
		return this;
	}
}
