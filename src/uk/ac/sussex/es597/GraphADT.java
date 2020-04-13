package uk.ac.sussex.es597;

import java.util.ArrayList; 
/** 
 * A graph API. Appropriate implementation should provide many of the basic requirements of a graph ADT.   
 * @author Bill Keller 
 * @version 1.0
 * @since 1.0 
 * VERY IMPORTANT: DO NOT EDIT THIS INTERFACE IN ANY WAY! 
 */

public interface GraphADT {
	
 /**     
  * Insert a new vertex with name n into the graph. Return the new vertex object     
  * @param n String     
  * @return the new Vertex     
  */
 public Vertex insertVertex(String n); 
 
 /**     
  * Remove the given vertex from the graph. The name of the vertex is returned, or null if the      
  * graph does not contain the vertex.     
  * @param v Vertex   
  * @return the name of the vertex or null.     
  */
 public String removeVertex(Vertex v); 
 
 /**     
  * Build a new edge with end vertices v and w and name n and insert into the graph. Return the new Edge object.     
  * NB: The vertices u and v must already be in the graph.     
  * @param v Vertex     
  * @param w Vertex     
  * @param n String     
  * @return the new Edge     
  */
 public Edge insertEdge(Vertex v, Vertex w, String n); 
 
 /**     
  * Remove the edge e from the graph. The name of the edge is returned (or null if the edge is not in the graph).     
  * @param e the edge to be removed     
  * @return the name or null if e does not exist     
  */
 public String removeEdge(Edge e); 
 
 /**  
  * Return the endpoint vertex of edge e that is 'opposite' its other endpoint vertex v. Return null if e does not exist or does not have endpoint v     
  * @param e Edge     
  * @param v Vertex     
  * @return a vertex or null     
  */
 public Vertex opposite(Edge e, Vertex v);
 
 /**     
  * Return a collection containing of all of the vertices in the graph.    
  * @return a list of vertices     
  */
 public ArrayList<Vertex> vertices(); 
 
 /**     
  * Return a collection of all of the edges in the graph.     
  * @return a list of edges     
  */
 public ArrayList<Edge> edges(); 
 
 /**     
  * Checks whether two vertices are adjacent (i.e. joined by a single edge) or not.     
  * @param v a vertex     
  * @param w a vertex     
  * @return true if v and we are adjacent and false otherwise.     
  */
 public boolean areAdjacent(Vertex v, Vertex w); 
 
 /**    
  * Finds and returns the set of edges that are incident to a given vertex.     
  * @param v the vertex     
  * @return a list of edges     
  */
 public ArrayList<Edge> incidentEdges(Vertex v); 
 
 /**    
  * Rename vertex v as n; returns the old vertex name     
  * @param v a vertex     
  * @param n the new name     
  * @return the old edge name     
  */
 public String rename(Vertex v, String n); 
 
 /**     
  * Rename edge e as n; returns the old edge name     
  * @param e an edge     
  * @param n the new name     
  * @return the old edge name     
  */
 public String rename(Edge e, String n);
 
}
