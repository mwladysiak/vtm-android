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

import java.util.ArrayList;

import org.oscim.core.PointD;
import org.oscim.core.PointF;

import android.util.Log;

public class HexagonGrid {

	public final HexagonCell[][] hexagons;
	private float side;
	private final int radius;

	public HexagonGrid(PointF centerPoint, int radius, float side) {

		double sqrt3 = Math.sqrt(3);
		System.out.println(sqrt3);
		this.radius = radius;

		int size = 2 * radius + 1;
		hexagons = new HexagonCell[size][size];

		int start = -radius;
		int stop = radius + 1;

		for (int i = 0; i < size; i++, start++, stop++) {

			for (int j = Math.max(start, 0); j < Math.min(stop, size); j++) {
				int q = j - radius - Math.min(radius, i - radius);
				int r = i - radius;
				double center_x = centerPoint.x + q * sqrt3 + r * sqrt3 * 0.5
						* side;
				double center_y = centerPoint.y + r * 1.5 * side;
				hexagons[i][j] = new HexagonCell(q, r, side, center_x, center_y);
				//				System.out.println("for i:" + i + " j:" + j
				//						+ " created hexagon q: " + q + " r: " + r + " x: "
				//						+ center_x + " y: " + center_y);
				//				System.out.println(hexagons[i][j].getCenter());
			}
		}

		for (int i = 0; i < size; i++) {
			for (int j = size; j > i; j--) {
				// System.out.print("  ");
			}
			//
			// for (int j = 0; j < size; j++) {
			// if (hexagons[i][j] != null) {
			// System.out.print(" o ");
			//
			// } else
			// System.out.print(" . ");
			// try {
			// Thread.sleep(0);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
			// System.out.println();

		}

	}

	public ArrayList<PointD> getHexagonPoints() {
		ArrayList<PointD> pointsList = new ArrayList<PointD>();

		for (int i = 0; i < 2 * radius + 1; i++) {
			for (int j = 0; j < 2 * radius + 1; j++) {
				if (hexagons[i][j] != null) {
					for (int k = 0; k < 6; k++) {
						pointsList.add(hexagons[i][j].getPoints()[k]);
					}
				}

			}
		}
		Log.v("mike", "returned " + pointsList.size() + " points.");

		return pointsList;
	}

	public HexagonCell getHexagon(int q, int r) {
		if (Math.abs(q + r) > radius) {
			System.out.println("q: " + q + ", r: " + r
					+ " hexagon does not exist!");
			return null;
		}
		return hexagons[r + radius][q + radius + Math.min(radius, r)];
	}

	public void setHexagon(int q, int r, HexagonCell cell) {
		if (Math.abs(q + r) > radius) {
			System.out.println("q: " + q + ", r: " + r
					+ " hexagon does not exist!");
			return;
		}
		if ((cell.q != q) || (cell.r != r)) {
			System.out.println("setHexagon parameters are incorrect");
			return;
		}
		hexagons[r + radius][q + radius + Math.min(radius, r)] = cell;
	}

	public void setHexagon(HexagonCell cell) {
		if (cell != null) {
			setHexagon(cell.q, cell.r, cell);
		}
	}

}
