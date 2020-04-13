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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Undirected graph, adjacency list structure 
 * @author Edoardo Sanguineti
 *
 */
public class Graph implements GraphADT {
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	
	public Graph(ArrayList<Vertex> vertices, ArrayList<Edge> edges) {
		this.vertices = vertices;
		this.edges= edges;
	}

	@Override
	public Vertex insertVertex(String n) {
		Vertex v = new Vertex(n);
		vertices.add(v);
		return v;
	}

	@Override
	public String removeVertex(Vertex v) {
		if(!vertices.contains(v))
		    return null;
		
		vertices.remove(v);
		return v.getElement();
	}

	@Override
	public Edge insertEdge(Vertex v, Vertex w, String n) {
		Edge e = new Edge(n);
		e.setEndpoints(v, w);
		
		edges.add(e);
		return e;
	}

	@Override
	public String removeEdge(Edge e) {
		if(!edges.contains(e))
		    return null;
		
		edges.remove(e);
		return e.getElement();
	}
        
        /**
         * This method returns the Train Station that can be directly reached given a neighbouring Ltation and the Line.
         * If the starting Line and The Station are disconnected the method immediately returns null, if there is no station on the other side the method also returns null
         * @param e The Line
         * @param v The neighbouring Station
         * @return The Station on the other side of the Line or null
         */
	@Override
	public Vertex opposite(Edge e, Vertex v) {
		if(!v.getIncidenceColl().contains(e))
			return null;
		
		if(e.getEndPointA().equals(v))
			return e.getEndPointB();
		
		else if(e.getEndPointB().equals(v))
			return e.getEndPointA();
		
		//At this point it will return null because there is no vertex on the other end of the line
		return null;
	}

	@Override
	public ArrayList<Vertex> vertices() {
		return vertices;
	}

	@Override
	public ArrayList<Edge> edges() {
		return edges;
	}

        /**
         * This method uses the Collections class to make use of the static method disjoint to quickly check if there are any common Edges between two Vertices
         * @param v Vertex
         * @param w Vertex
         * @return True if Adjacent, false if otherwise
         */
	@Override
	public boolean areAdjacent(Vertex v, Vertex w) {
		return !Collections.disjoint(v.getIncidenceColl(), w.getIncidenceColl()); //The Java developers do the job for us
	}

	@Override
	public ArrayList<Edge> incidentEdges(Vertex v) {
		return v.getIncidenceColl();
	}

	@Override
	public String rename(Vertex v, String n) {
                String oldName = v.getElement();
		v.setElement(n);
		return oldName;
	}

	@Override
	public String rename(Edge e, String n) {
            String oldName = e.getElement();
		e.setElement(n);
		return oldName;
	}
	
	/**
	 * Returns the number of Vertices
	 * @return int Vertices
	 */
	public int numVertices() {
		return vertices.size();
	}
	
	/**
	 * Returns the number of Edges
	 * @return int Edges
	 */
	public int numEdges() {
		return edges.size();
	}
	
	/**
	 * Returns the Edge between two vertices or null if they are not adjacent
	 * @param v Vertex
	 * @param w Vertex
	 * @return The Edge in between
	 */
	public Edge getEdge(Vertex v, Vertex w) {
		if(areAdjacent(v,w)) {
			List<Edge> list = v.getIncidenceColl();
			for(Edge e : list) {
				if(opposite(e,v).equals(w)) //Once we find the second Vertex we know we have our Edge
					return e;
			}
		}
		
		return null;
	}
	
        /**
         * Perform a breadth-first traversal of the rail network, starting from a given station.
         * Visited Stations are added to a Set so that we know we have already visited them. 
         * We also use a queue to make sure that all its neighbour vertices are marked first then we move on.
         * By that I mean we look for all the neighbouring Stations, if they are not in the Set they get added and queued, if we already marked them leave it.
         * We also use a temp List containing all incident Edges of a given Vertex, then we get the opposite Vertex of the Edge to progress.
         * I decided to use an ArrayDeque instead of another implementation of the Queue interface because it's faster, according to the documentation, for this purpose
         * @param v Starting station
         */
        public void bfTraverse(Vertex v) {
            HashSet<Vertex> visited = new HashSet<>();
            ArrayDeque<Vertex> queue = new ArrayDeque<>();
            
            //Temp List and variables
            Vertex w,OppSideVertex;
            List<Edge> incidentEdges;
            
            visited.add(v);
            queue.add(v);
            
            while (!queue.isEmpty()) { 
               w = queue.poll();             
               incidentEdges = w.getIncidenceColl();
            
               for(Edge e : incidentEdges) { //For every incident edge we get the opposite
                  OppSideVertex = opposite(e,w);
                  
                  if(OppSideVertex != null && !visited.contains(OppSideVertex)) {
                      visited.add(OppSideVertex);
                      queue.add(OppSideVertex);
                  }
               }       
            } 
        }
        
        /**
         * Performs a breadth-first traversal of the whole rail network (i.e. this method should work for both connected and non-connected graphs)
         */
        public void bfTraverse() {
            HashSet<Vertex> visited = new HashSet<>();
            ArrayDeque<Vertex> queue = new ArrayDeque<>();
            
            //Temp List and variables
            Vertex w,OppSideVertex;
            List<Edge> incidentEdges;
            boolean finished = false;
            
            visited.add(vertices.get(0));
            queue.add(vertices.get(0));
            
            while(!finished) {
               while (!queue.isEmpty()) { 
                  w = queue.poll();             
                  incidentEdges = w.getIncidenceColl();
            
                  for(Edge e : incidentEdges) { //For every incident edge we get the opposite
                     OppSideVertex = opposite(e,w);
                  
                     if(OppSideVertex != null && !visited.contains(OppSideVertex)) {
                         visited.add(OppSideVertex);
                         queue.add(OppSideVertex);
                     }
                  }       
               }
               
               if(visited.size() < vertices.size()) { //If the graph turns out to be disconnected from the Vertex 0
                   Iterator<Vertex> it = vertices.iterator(); 
                   boolean found = false;
                   
                   while(it.hasNext() && !found) {
                       Vertex h = it.next();
                       if(!visited.contains(h)) { //The first Vertex that is found to be undiscovered we add it to the queue
                           visited.add(h);
                           queue.add(h); 
                           found = true;
                       }
                   }
               }
               
               else
                   finished = true;
            }
            
        }
        
        /**
         * Returns a list (ArrayList) of all of the stations (vertices) that can be reached by rail when starting from v excluding v itself.
         * I'm using the same algorithm for the bf Traversal but instead of using an HashSet I'm directly using the preferred ArrayList data structure
         * @param v Starting Vertex
         * @return ArrayList All the reachable stations
         */
        public ArrayList<Vertex> allReachable(Vertex v) {
            ArrayList<Vertex> visited = new ArrayList<>();
            ArrayDeque<Vertex> queue = new ArrayDeque<>();
            
            //Temp List and variables
            Vertex w,OppSideVertex;
            List<Edge> incidentEdges;
            
            visited.add(v);
            queue.add(v);
            
            while (!queue.isEmpty()) { 
               w = queue.poll();             
               incidentEdges = w.getIncidenceColl();
            
               for(Edge e : incidentEdges) { //For every incident edge we get the opposite
                  OppSideVertex = opposite(e,w);
                  
                  if(OppSideVertex != null && !visited.contains(OppSideVertex)) {
                      visited.add(OppSideVertex);
                      queue.add(OppSideVertex);
                  }
               }       
            } 
            
            visited.remove(v);
            return visited;
        }
        
        /**
         * Returns true if all the stations are connected, false if otherwise.
         * Uses the same algorithm as before. At the end it checks if the number of Vertices visited starting from the first Vertex matches the size of the field Vertices
         * @return Boolean connected
         */
        public boolean allConnected() {
            HashSet<Vertex> visited = new HashSet<>();
            ArrayDeque<Vertex> queue = new ArrayDeque<>();
            
            
            Vertex w,OppSideVertex;
            List<Edge> incidentEdges;
            
            //Check if we have at least one vertex
            if(numVertices() == 0)
                return false;
            
            visited.add(vertices.get(0));
            queue.add(vertices.get(0));
            
            while (!queue.isEmpty()) { 
               w = queue.poll();             
               incidentEdges = w.getIncidenceColl();
            
               for(Edge e : incidentEdges) { 
                  OppSideVertex = opposite(e,w);
                  
                  if(OppSideVertex != null && !visited.contains(OppSideVertex)) {
                      visited.add(OppSideVertex);
                      queue.add(OppSideVertex);
                  }
               }       
            } 
            return visited.size() == vertices.size();
        }
        
        /**
         * Given two stations u and v, return a shortest route (path) between them, 
         * if one can be found; or return null to indicate that the two stations cannot be reached from one another. 
         * NB: The returned path is represented as a list of edges. 
         * Also, by ‘shortest’ route here we mean that the route should involve the fewest possible number of direct rail links (i.e. edges).
         * @param u Start Station  
         * @param v End Station
         * @return The shortest path or null
         */
        public ArrayList<Edge> mostDirectRoute(Vertex u, Vertex v) {
            //TODO
            return null; 
        }
        
	public static void main(String[] args) {
	}

}

