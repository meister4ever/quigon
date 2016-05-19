import creation.Game;
import creation.GameBuilderImp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
@SuppressWarnings("CPD-START")
public class WolfSheepCabbageTest {
    @Test
    public void gameTestWolfSheepCabbage() {
        GameBuilderImp gameBuilderImp = new WolfSheepBuilder();
        Game wolfSheep = gameBuilderImp.build();
        WolfSheepConstants wsConstants = new WolfSheepConstants();
        String takeCabbageCommand = wsConstants.take + " " + wsConstants.cabbage;
        assertEquals(wolfSheep.receiveCommands(takeCabbageCommand), wsConstants.ok);
        String crossNorthCommand = wsConstants.cross + " " + wsConstants.northShore;
        assertEquals(wolfSheep.receiveCommands(crossNorthCommand), wsConstants.wolfOnSouth);
        String leaveCabbageCommand = wsConstants.leave + " " + wsConstants.cabbage;
        assertEquals(wolfSheep.receiveCommands(leaveCabbageCommand), wsConstants.ok);
        String takeSheepCommand = wsConstants.take + " " + wsConstants.sheep;
        assertEquals(wolfSheep.receiveCommands(takeSheepCommand), wsConstants.ok);
        assertEquals(wolfSheep.receiveCommands(crossNorthCommand), wsConstants.youCrossed);
        String leaveSheepCommand = wsConstants.leave + " " + wsConstants.sheep;
        assertEquals(wolfSheep.receiveCommands(leaveSheepCommand), wsConstants.ok);
        String crossSouthCommand = wsConstants.cross + " " + wsConstants.southShore;
        assertEquals(wolfSheep.receiveCommands(crossSouthCommand), wsConstants.youCrossed);
        assertEquals(wolfSheep.receiveCommands(takeCabbageCommand), wsConstants.ok);
        assertEquals(wolfSheep.receiveCommands(crossNorthCommand), wsConstants.youCrossed);
        assertEquals(wolfSheep.receiveCommands(leaveCabbageCommand), wsConstants.ok);
        assertEquals(wolfSheep.receiveCommands(takeSheepCommand), wsConstants.ok);
        assertEquals(wolfSheep.receiveCommands(crossSouthCommand), wsConstants.youCrossed);
        assertEquals(wolfSheep.receiveCommands(leaveSheepCommand), wsConstants.ok);
        String takeWolfCommand = wsConstants.take + " " + wsConstants.wolf;
        assertEquals(wolfSheep.receiveCommands(takeWolfCommand), wsConstants.ok);
        assertEquals(wolfSheep.receiveCommands(crossNorthCommand), wsConstants.youCrossed);
        String leaveWolfCommand = wsConstants.leave + " " + wsConstants.wolf;
        assertEquals(wolfSheep.receiveCommands(leaveWolfCommand), wsConstants.ok);
        assertEquals(wolfSheep.receiveCommands(crossSouthCommand), wsConstants.youCrossed);
        assertEquals(wolfSheep.receiveCommands(takeSheepCommand), wsConstants.ok);
        assertEquals(wolfSheep.receiveCommands(crossNorthCommand), wsConstants.youCrossed);
        assertEquals(wolfSheep.receiveCommands(leaveSheepCommand), gameBuilderImp.winText);
    }
}
