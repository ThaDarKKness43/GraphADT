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

import java.util.ArrayList;

/**
 * Represents a Train station
 * incidenceColl represents the edges incident to this Vertex, because this implementation is unordered we don't have two lists for
 * outgoing and incoming edges
 * @author Edoardo Sanguineti
 *
 */
public class Vertex {
	private String name;
	private ArrayList<Edge> incidenceColl; 
	
	public Vertex(String name) {
		this.name = name;
		incidenceColl = new ArrayList<Edge>();
	}
	
	/**
	 * Returns the name of the Train station
	 * @return the Name
	 */
	public String getElement() {
		return name;
	}
	
	/**
	 * Changes the name of the Train Station. 
         * Usage is discouraged, it's preferred to have a rename method in the main Graph class
	 * @param s The name
	 */
	public void setElement(String s) {
		name = s;
	}
	
	/**
	 * Adds the specified Edge to the list containing the adjacent Edges of this Vortex (Train Lines)
	 * @param e The name of the Line
	 */
	public void addIncidenceEdge(Edge e) {
		incidenceColl.add(e);
	}
	
	/**
	 * Returns the list of adjacent Edges
	 * @return List Edges
	 */
	public ArrayList<Edge> getIncidenceColl() {
		return incidenceColl;
	}
        
        /**
         * Returns a String (the name) of the Station
         * @return String representation
         */
        @Override
        public String toString() {
            return this.getElement();
        }

}

