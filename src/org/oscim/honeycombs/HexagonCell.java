/*
 * Copyright 2013
 *
 * This program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.oscim.honeycombs;

import org.oscim.core.PointD;
import org.oscim.utils.MathUtils;

public class HexagonCell {

	private PointD[] points;
	private final double side;
	private double h;
	private double radius;
	private final PointD center;
	private final double px;
	private final double py;

	public int x, y, z, q, r;

	public HexagonCell(int q, int r, double side, double center_x,
			double center_y) {
		this.q = q;
		this.r = r;
		this.x = q;
		this.z = r;
		this.y = -this.x - this.z;
		this.px = center_x;
		this.py = center_y + side;
		this.side = side;
		this.center = new PointD(center_x, center_y);
		CalculateVertices();

	}

	public HexagonCell(int x, int y, int z, double side, double center_x,
			double center_y) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.q = x;
		this.r = z;
		this.px = center_x;
		this.py = center_y + side;
		this.side = side;
		this.center = new PointD(center_x, center_y);
		CalculateVertices();

	}

	private void CalculateVertices() {
		h = MathUtils.sinDeg(30) * side;
		radius = MathUtils.cosDeg(30) * side;

		points = new PointD[6];
		points[0] = new PointD(px, py);
		points[1] = new PointD(px + radius, py + h);
		points[2] = new PointD(px + radius, py + side + h);
		points[3] = new PointD(px, py + side + h + h);
		points[4] = new PointD(px - radius, py + side + h);
		points[5] = new PointD(px - radius, py + h);

	}

	public void printHexagon() {
		System.out.println(q + ", " + r);
	}

	public PointD[] getPoints() {
		return points;
	}

	public PointD getCenter() {
		return center;
	}

}
