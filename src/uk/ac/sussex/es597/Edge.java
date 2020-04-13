/*
 * Copyright (C) 2020 Edoardo Sanguineti
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.ac.sussex.es597;

/**
 * Represents a Train line
 * @author Edoardo Sanguineti
 *
 */
public class Edge {
	private String name;
	private Vertex endPointA,endPointB;
    
	public Edge(String name) {
		this.name = name;
		endPointA = null;
		endPointB = null;
	}
	
	/**
	 * Returns the name of the Line between stations
	 * @return the Name
	 */
	public String getElement() {
		return name;
	}
	
	/**
	 * Changes the name of the Train Line.
         *  Usage is discouraged, it's preferred to have a rename method in the main Graph class
	 * @param s Name
	 */
	public void setElement(String s) {
		name = s;
	}
	
	/**
	 * Sets the end points of the Edge (Stations)
	 * @param a Vertex
	 * @param b Vertex
	 */
	public void setEndpoints(Vertex a, Vertex b) {
		endPointA = a;
		endPointB = b;
	}
	
	/**
	 * Fetches the first end point
	 * @return Vertex a
	 */
	public Vertex getEndPointA() {
		return endPointA;
	}
	
	/**
	 * Fetches the second end point
	 * @return Vertex b
	 */
	public Vertex getEndPointB() {
		return endPointB;
	}
        
        /**
         * Returns a String (the name) of the Line
         * @return String representation
         */
        @Override
        public String toString() {
            return this.getElement();
        }
}
