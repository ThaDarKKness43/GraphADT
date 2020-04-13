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

import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.sussex.es597.*;

/**
 *
 * @author Edoardo Sanguineti
 */
public class VertexTest {
    
    public VertexTest() {
    }
    
    @Test 
    public void testList() {
        Edge e = new Edge("DLR");
        Edge r = new Edge("Jubilee Line");
        Vertex v = new Vertex("Canary Wharf");
        
        v.addIncidenceEdge(e);
        v.addIncidenceEdge(r);
        e.setEndpoints(v, null);
        r.setEndpoints(v,null);
        
        assertEquals("DLR",v.getIncidenceColl().get(0).getElement());
        assertEquals(v,r.getEndPointA());       
    }
    
}
