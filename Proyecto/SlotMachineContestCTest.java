

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class SlotMachineContestCTest.
 *
 * @author  Juan Miguel Nope Ascencio
 * @author Samuel Ricardo Rojas Barragán
 * @version 1.0 (2026)
 */
public class SlotMachineContestCTest
{
    @Test
    public void accordingNaRbShouldSolve(){
        SlotMachineContest solver = new SlotMachineContest();
        assertTrue(solver.solve(3) >=0);
    }
    @Test 
    public void accordingNaRbShouldfail(){
        SlotMachineContest solver = new SlotMachineContest();
        assertEquals(0,solver.solve(-5));
    }
    
}