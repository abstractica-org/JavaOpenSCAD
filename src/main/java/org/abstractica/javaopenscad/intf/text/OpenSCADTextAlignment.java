package org.abstractica.javaopenscad.intf.text;

public interface OpenSCADTextAlignment
{
	enum Direction {LEFT_TO_RIGHT, RIGHT_TO_LEFT, TOP_TO_BOTTOM, BOTTOM_TO_TOP}
	enum Horizontal {LEFT, CENTER, RIGHT}
	enum Vertical{TOP, CENTER, BASELINE, BOTTOM}

	Direction direction();
	Horizontal horizontal();
	Vertical vertical();
}
