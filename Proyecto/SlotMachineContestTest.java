

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * The test class SlotMachineContestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SlotMachineContestTest
{
    private SlotMachineContest solver;
    @Before
    public void setup(){
        solver = new SlotMachineContest();
    }   
    @Test
    public void testsimulate(){
        assertTrue(solver.simulate(1) >=0);
    }
    @Test
    public void testsolve(){
      assertTrue(solver.solve(2) >=0);  
    }
    @Test
    public void testSolveone(){
        assertEquals(0,solver.simulate(0));
    }
    @Test
    public void testSimulateinvalid(){
        assertEquals(0,solver.simulate(0));
    }
    
}