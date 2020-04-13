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
package test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.sussex.es597.*;

/**
 *
 * @author Edoardo Sanguineti
 */
public class GraphTest {
    
    public GraphTest() {
    }
    
    @Test
    public void constructTest() {
        ArrayList<Vertex> a = new ArrayList<>();
        ArrayList<Edge> b = new ArrayList<>();
    
        Edge e = new Edge("DLR");
        Edge e2 = new Edge("DLR Southern");
        Edge r = new Edge("Jubilee Line");
        
        Vertex v = new Vertex("London City Airport");
        v.addIncidenceEdge(e);
        
        Vertex w = new Vertex("Pontoon Dock");
        w.addIncidenceEdge(e);
        
        Vertex z = new Vertex("Canary Wharf");
        z.addIncidenceEdge(r);
        z.addIncidenceEdge(e2);
        
        e.setEndpoints(v, w);
        e2.setEndpoints(z, null);
        r.setEndpoints(z, null);
        
        a.add(v);a.add(z);a.add(w);
        b.add(e);b.add(e2);b.add(r);
        
        Graph g = new Graph(a,b);  
        
        assertEquals("DLR",g.removeEdge(e));
    }
    
    @Test
    public void testMethods() {
        ArrayList<Vertex> a = new ArrayList<>();
        ArrayList<Edge> b = new ArrayList<>();
    
        Edge e = new Edge("DLR");
        Edge e2 = new Edge("DLR Southern");
        Edge r = new Edge("Jubilee Line");
        
        Vertex v = new Vertex("London City Airport");
        v.addIncidenceEdge(e);
        
        Vertex w = new Vertex("Pontoon Dock");
        w.addIncidenceEdge(e);
        
        Vertex z = new Vertex("Canary Wharf");
        z.addIncidenceEdge(r);
        z.addIncidenceEdge(e2);
        
        e.setEndpoints(v, w);
        e2.setEndpoints(z, null);
        r.setEndpoints(z, null);
        
        a.add(v);a.add(z);a.add(w);
        b.add(e);b.add(e2);b.add(r);
        
        Graph g = new Graph(a,b);       
        
        assertEquals(v,g.opposite(e, w));
        assertEquals(w,g.opposite(e, v));
        assertEquals(true,g.areAdjacent(v, w));
        assertEquals(false,g.areAdjacent(v, z));
        
        ArrayList<Edge> testIncident = new ArrayList<>();
        testIncident.add(e2);testIncident.add(r);
        assertEquals(testIncident.get(0),g.incidentEdges(z).get(1)); //Inverted order       
        
    }
    
    @Test
    public void testMethods2() {
        ArrayList<Vertex> a = new ArrayList<>();
        ArrayList<Edge> b = new ArrayList<>();
    
        Edge e = new Edge("DLR");
        Edge e2 = new Edge("DLR Southern");
        Edge r = new Edge("Jubilee Line");
        
        Vertex v = new Vertex("London City Airport");
        v.addIncidenceEdge(e);
        
        Vertex w = new Vertex("Pontoon Dock");
        w.addIncidenceEdge(e);
        
        Vertex z = new Vertex("Canary Wharf");
        z.addIncidenceEdge(r);
        z.addIncidenceEdge(e2);
        
        e.setEndpoints(v, w);
        e2.setEndpoints(z, null);
        r.setEndpoints(z, null);
        
        a.add(v);a.add(z);a.add(w);
        b.add(e);b.add(e2);b.add(r);
        
        Graph g = new Graph(a,b);
        
        assertEquals(e,g.getEdge(v, w));
        assertEquals("DLR",g.rename(e, "New Expensive Line"));
        assertEquals("New Expensive Line",g.getEdge(v, w).getElement());
    }
    
    @Test
    public void testBfTraverse() {
        ArrayList<Vertex> a = new ArrayList<>();
        ArrayList<Edge> b = new ArrayList<>();
    
        Edge e = new Edge("DLR");
        Edge e2 = new Edge("DLR Southern");
        Edge r = new Edge("Jubilee Line");
        
        Vertex v = new Vertex("London City Airport");
        v.addIncidenceEdge(e);
        
        Vertex w = new Vertex("Pontoon Dock");
        w.addIncidenceEdge(e);
        w.addIncidenceEdge(e2);
        
        Vertex z = new Vertex("Canary Wharf");
        z.addIncidenceEdge(r);
        z.addIncidenceEdge(e2);
        
        Vertex m = new Vertex("North Greenwich");
        m.addIncidenceEdge(r);
        
        e.setEndpoints(v, w);
        e2.setEndpoints(z, w);
        r.setEndpoints(z, m);
        
        a.add(v);a.add(z);a.add(w);a.add(m);
        b.add(e);b.add(e2);b.add(r);
        
        Graph g = new Graph(a,b);
        
        assertEquals(m,g.opposite(r, z));
        assertEquals("North Greenwich",g.opposite(r, z).getElement());
        assertEquals(r,g.getEdge(m, z));
        
        g.bfTraverse(v); //Doesn't simply crash
        
        g.allReachable(v).forEach((l) -> {
            //London City Airport is not expected to be printed
            System.out.println(l);
        });
        
        assertEquals(true,g.allConnected());
    }
    
    @Test
    public void testConnected() {
        ArrayList<Vertex> a = new ArrayList<>();
        ArrayList<Edge> b = new ArrayList<>();
    
        Edge e = new Edge("DLR");
        Edge e2 = new Edge("DLR Southern");
        Edge r = new Edge("Jubilee Line");
        
        Vertex v = new Vertex("London City Airport");
        v.addIncidenceEdge(e);
        
        Vertex w = new Vertex("Pontoon Dock");
        w.addIncidenceEdge(e);
        w.addIncidenceEdge(e2);
        
        Vertex z = new Vertex("Canary Wharf");
        z.addIncidenceEdge(r);
        z.addIncidenceEdge(e2);
        
        Vertex m = new Vertex("North Greenwich");
        m.addIncidenceEdge(r);
        
        e.setEndpoints(v, w);
        e2.setEndpoints(z, w);
        r.setEndpoints(z, m);
        
        a.add(v);a.add(z);a.add(w);a.add(m);
        b.add(e);b.add(e2);b.add(r);
        
        Graph g = new Graph(a,b);      
        
        assertEquals(true,g.allConnected());
        g.bfTraverse();
    }
    
}
