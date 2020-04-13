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

import uk.ac.sussex.es597.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Edoardo Sanguineti
 */
public class EdgeTest {
    
    public EdgeTest() {
    }
    
    @Test
    public void testEndPoints() {
        Edge e = new Edge("DLR");
        Vertex v = new Vertex("London City Airport");
        v.addIncidenceEdge(e);
        Vertex w = new Vertex("Pontoon Dock");
        w.addIncidenceEdge(e);
        e.setEndpoints(v, w);
        
        assertEquals("DLR", e.getElement());
        assertEquals(e.getEndPointA(),v);
        assertEquals(e.getEndPointB(),w);
        
        assertEquals(e,v.getIncidenceColl().get(0));
        assertEquals(e,w.getIncidenceColl().get(0));        
    }
    
}
