import creation.GameBuilderImp;
import logic.LogicBuilder;
import logic.WrongLogicSymbolException;
import model.actions.*;
import model.elements.*;
import model.rulesexpressions.expressions.IExpression;
import model.rulesexpressions.rules.DoesNotHaveContainerRule;
import model.rulesexpressions.rules.DoesNotHaveState;
import model.rulesexpressions.rules.HasContainerRule;
import model.rulesexpressions.rules.HasStateRule;

public class TreasureHuntBuilder extends GameBuilderImp {
    @SuppressWarnings("CPD-START")

    public TreasureHuntBuilder() {
        gameName = "TreasureHunt";
        gameDescription = "Search for the treasure. But be careful. Better take some antidotes.";
    }

    //Character
    private ComplexElement character;

    //Rooms
    private ComplexElement roomCenter;
    private ComplexElement roomWest;
    private ComplexElement roomEast;
    private ComplexElement roomSouth;
    private ComplexElement roomNorth;

    //Doors
    private ComplexElement doorWtoC;
    private ComplexElement doorEtoC;
    private ComplexElement doorStoC;
    private ComplexElement doorNtoC;
    private ComplexElement doorCtoW;
    private ComplexElement doorCtoE;
    private ComplexElement doorCtoS;
    private ComplexElement doorCtoN;

    //Items
    private ComplexElement wardrobe;
    private ComplexElement boxInCenterRoom;
    private ComplexElement boxInTrunk;
    private ComplexElement boxInWardrobe;
    private ComplexElement boxInWestRoom;
    private ComplexElement trunkInSouthRoom;
    private ComplexElement trunkInNorthRoom;
    private ComplexElement trunkInEastRoom;
    private ComplexElement keyEast;
    private ComplexElement keyNorth;
    private ComplexElement keySouth;
    private ComplexElement antidote1;
    private ComplexElement antidote2;
    private ComplexElement treasure;

    //States
    private ComplexElement closedState;
    private ComplexElement openState;
    private ComplexElement poisonedState;

    //Room transition actions
    private Action passFromWestToCenter;
    private Action passFromEastToCenter;
    private Action passFromSouthToCenter;
    private Action passFromNorthToCenter;
    private Action passFromCenterToWest;
    private Action passFromCenterToEast;
    private Action passFromCenterToSouth;
    private Action passFromCenterToNorth;

    //Door actions
    private Action addOpenStateDoorE;
    private Action addOpenStateDoorN;
    private Action addOpenStateDoorS;
    private Action removeClosedStateDoorE;
    private Action removeClosedStateDoorN;
    private Action removeClosedStateDoorS;

    //Item actions
    private Action addOpenStateWardrobe;
    private Action addOpenStateTrunkEast;
    private Action addOpenStateTrunkNorth;
    private Action addOpenStateTrunkSouth;
    private Action addOpenStateBoxWest;
    private Action addOpenStateBoxCenter;
    private Action addOpenStateBoxTrunk;
    private Action addOpenStateBoxWardrobe;
    private Action removeClosedStateWardrobe;
    private Action removeClosedStateTrunkEast;
    private Action removeClosedStateTrunkNorth;
    private Action removeClosedStateTrunkSouth;
    private Action removeClosedStateBoxWest;
    private Action removeClosedStateBoxCenter;
    private Action removeClosedStateBoxTrunk;
    private Action removeClosedStateBoxWardrobe;
    private Action addKeyEast;
    private Action addKeyNorth;
    private Action addKeySouth;
    private Action addAntidote1;
    private Action addAntidote2;
    private Action addTreasure;
    private Action removeKeyEast;
    private Action removeKeyNorth;
    private Action removeKeySouth;
    private Action removeAntidote1;
    private Action removeAntidote2;
    private Action addPoisonState;
    private Action removePoisonState;

    //Rules
    private LogicBuilder logicBuilder = new LogicBuilder();
    private HasContainerRule characterIsInCenterRoom;
    private HasContainerRule characterIsInWestRoom;
    private HasContainerRule characterIsInEastRoom;
    private HasContainerRule characterIsInSouthRoom;
    private HasContainerRule characterIsInNorthRoom;
    private HasStateRule closedCEDoorRule;
    private HasStateRule closedCNDoorRule;
    private HasStateRule closedCSDoorRule;
    private HasStateRule openedCEDoorRule;
    private HasStateRule openedCNDoorRule;
    private HasStateRule openedCSDoorRule;
    private HasStateRule openedBoxCRule;
    private HasStateRule openedBoxERule;
    private HasStateRule openedBoxSRule;
    private HasStateRule openedTrunkERule;
    private HasStateRule openedTrunkNRule;
    private HasStateRule openedTrunkSRule;
    private HasStateRule openedWardrobe;
    private DoesNotHaveContainerRule freeKeyS;
    private DoesNotHaveContainerRule freeKeyN;
    private DoesNotHaveContainerRule freeKeyE;
    private DoesNotHaveContainerRule freeAntidote1;
    private DoesNotHaveContainerRule freeAntidote2;
    private DoesNotHaveContainerRule freeTreasure;
    private HasContainerRule holdsKeyS;
    private HasContainerRule holdsKeyN;
    private HasContainerRule holdsKeyE;
    private HasContainerRule holdsAntidote1;
    private HasContainerRule holdsAntidote2;
    private HasContainerRule holdsTreasure;
    private DoesNotHaveState characterIsntPoisoned;

    private IExpression keyEPickConditionRule;
    private IExpression keyNPickConditionRule;
    private IExpression keySPickConditionRule;
    private IExpression antidote1PickConditionRule;
    private IExpression antidote2PickConditionRule;
    private IExpression treasurePickConditionRule;
    private IExpression goThroughDoorEConditions;
    private IExpression goThroughDoorSConditions;
    private IExpression goThroughDoorNConditions;
    private IExpression goThroughDoorWCCondition;
    private IExpression goThroughDoorNCCondition;
    private IExpression unlockDoorECondition;
    private IExpression unlockDoorNCondition;
    private IExpression unlockDoorSCondition;
    private IExpression openBoxSCondition;
    private IExpression openBoxECondition;

    //Moves
    private Move goFromWestToCenter;
    private Move goFromEastToCenter;
    private Move goFromSouthToCenter;
    private Move goFromNorthToCenter;
    private Move goFromCenterToWest;
    private Move goFromCenterToEast;
    private Move goFromCenterToSouth;
    private Move goFromCenterToNorth;
    private Move pickKeyEast;
    private Move pickKeyNorth;
    private Move pickKeySouth;
    private Move pickAntidote1;
    private Move pickAntidote2;
    private Move pickTreasure;
    private Move unlockEastDoor;
    private Move unlockNorthDoor;
    private Move unlockSouthDoor;
    private Move dropKeySouth;
    private Move dropKeyNorth;
    private Move dropKeyEast;
    private Move dropAntidote1;
    private Move dropAntidote2;
    private Move openBoxW;
    private Move openBoxE;
    private Move openBoxS;
    private Move openBoxC;
    private Move openTrunkE;
    private Move openTrunkS;
    private Move openTrunkN;
    private Move openWardrobe;
    private Move useAntidote1;
    private Move useAntidote2;


    public void setElements() {
        createRooms();

        defineCharacter();

        createStates();

        createDoors();

        createItems();

        createRoomTransitionActions();

        createDoorActions();

        createItemActions();

        createRules();

        defineVictoryRule();

        createMoves();

        addMoves();
    }

    //DEFINE ELEMENTS
    private void createStates() {
        closedState = new ComplexElement(TreasureHuntConstants.closed);
        openState = new ComplexElement(TreasureHuntConstants.opened);
        poisonedState = new ComplexElement(TreasureHuntConstants.poisoned);
    }

    private void createRooms() {
        roomCenter = createAndAddElement(TreasureHuntConstants.roomCenter, null, null);
        roomWest = createAndAddElement(TreasureHuntConstants.roomWest, null, null);
        roomEast = createAndAddElement(TreasureHuntConstants.roomEast, null, null);
        roomSouth = createAndAddElement(TreasureHuntConstants.roomSouth, null, null);
        roomNorth = createAndAddElement(TreasureHuntConstants.roomNorth, null, null);
    }

    private void createDoors() {
        doorWtoC = createAndAddElement(TreasureHuntConstants.doorWC, roomWest, openState);
        doorEtoC = createAndAddElement(TreasureHuntConstants.doorEC, roomEast, openState);
        doorStoC = createAndAddElement(TreasureHuntConstants.doorSC, roomSouth, openState);
        doorNtoC = createAndAddElement(TreasureHuntConstants.doorNC, roomNorth, openState);

        doorCtoW = createAndAddElement(TreasureHuntConstants.doorCW, roomCenter, openState);
        doorCtoE = createAndAddElement(TreasureHuntConstants.doorCE, roomCenter, closedState);
        doorCtoS = createAndAddElement(TreasureHuntConstants.doorCS, roomCenter, closedState);
        doorCtoN = createAndAddElement(TreasureHuntConstants.doorCN, roomCenter, closedState);
    }

    private void createItems() {
        createWardrobe();
        createTrunks();
        createBoxes();
        createKeys();
        createAntidotes();
        createTreasure();
    }

    private void createWardrobe() {
        wardrobe = createAndAddElement(TreasureHuntConstants.wardrobe, roomSouth, closedState);
    }

    private void createTrunks() {
        trunkInNorthRoom = createAndAddElement(TreasureHuntConstants.trunkN, roomNorth, closedState);
        trunkInSouthRoom = createAndAddElement(TreasureHuntConstants.trunkS, roomSouth, closedState);
        trunkInEastRoom = createAndAddElement(TreasureHuntConstants.trunkE, roomEast, closedState);
    }

    private void createBoxes() {
        boxInWestRoom = createAndAddElement(TreasureHuntConstants.boxW, roomWest, closedState);
        boxInCenterRoom = createAndAddElement(TreasureHuntConstants.boxC, roomCenter, closedState);
        boxInTrunk = createAndAddElement(TreasureHuntConstants.boxE, trunkInEastRoom, closedState);
        boxInWardrobe = createAndAddElement(TreasureHuntConstants.boxS, wardrobe, closedState);
    }

    private void createKeys() {
        keySouth = createAndAddElement(TreasureHuntConstants.keyS, boxInCenterRoom, null);
        keyNorth = createAndAddElement(TreasureHuntConstants.keyN, boxInTrunk, null);
        keyEast = createAndAddElement(TreasureHuntConstants.keyE, trunkInSouthRoom, null);
    }

    private void createAntidotes() {
        antidote1 = createAndAddElement(TreasureHuntConstants.antidote1, trunkInSouthRoom, null);
        antidote2 = createAndAddElement(TreasureHuntConstants.antidote2, boxInWardrobe, null);
    }

    private void createTreasure() {
        treasure = createAndAddElement(TreasureHuntConstants.treasure, trunkInNorthRoom, null);
    }

    private void defineCharacter() {
        character = createAndAddElement(TreasureHuntConstants.character, roomWest, null);
        game.character = character;
    }

    //DEFINE ACTIONS
    private void createRoomTransitionActions() {
        passFromWestToCenter = buildChangeContainerAction(character, roomCenter);
        passFromEastToCenter = buildChangeContainerAction(character, roomCenter);
        passFromSouthToCenter = buildChangeContainerAction(character, roomCenter);
        passFromNorthToCenter = buildChangeContainerAction(character, roomCenter);

        passFromCenterToWest = buildChangeContainerAction(character, roomWest);
        passFromCenterToEast = buildChangeContainerAction(character, roomEast);
        passFromCenterToSouth = buildChangeContainerAction(character, roomSouth);
        passFromCenterToNorth = buildChangeContainerAction(character, roomNorth);
    }

    private void createDoorActions() {
        addOpenStateDoorE = buildAddStatesAction(doorCtoE, openState);
        removeClosedStateDoorE = buildRemoveStatesAction(doorCtoE, closedState);
        addOpenStateDoorN = buildAddStatesAction(doorCtoN, openState);
        removeClosedStateDoorN = buildRemoveStatesAction(doorCtoN, closedState);
        addOpenStateDoorS = buildAddStatesAction(doorCtoS, openState);
        removeClosedStateDoorS = buildRemoveStatesAction(doorCtoS, closedState);
    }

    private void createItemActions() {
        createWardrobeActions();
        createTrunkActions();
        createBoxActions();
        createKeyActions();
        createAntidoteActions();
        createPoisonActions();
        createTreasureActions();
    }

    private void createWardrobeActions() {
        addOpenStateWardrobe = buildAddStatesAction(wardrobe, openState);
        removeClosedStateWardrobe = buildRemoveStatesAction(wardrobe, closedState);
    }

    private void createTrunkActions() {
        addOpenStateTrunkEast = buildAddStatesAction(trunkInEastRoom, openState);
        removeClosedStateTrunkEast = buildRemoveStatesAction(trunkInEastRoom, closedState);
        addOpenStateTrunkNorth = buildAddStatesAction(trunkInNorthRoom, openState);
        removeClosedStateTrunkNorth = buildRemoveStatesAction(trunkInNorthRoom, closedState);
        addOpenStateTrunkSouth = buildAddStatesAction(trunkInSouthRoom, openState);
        removeClosedStateTrunkSouth = buildRemoveStatesAction(trunkInSouthRoom, closedState);
    }

    private void createBoxActions() {
        addOpenStateBoxWest = buildAddStatesAction(boxInWestRoom, openState);
        removeClosedStateBoxWest = buildRemoveStatesAction(boxInWestRoom, closedState);
        addOpenStateBoxCenter = buildAddStatesAction(boxInCenterRoom, openState);
        removeClosedStateBoxCenter = buildRemoveStatesAction(boxInCenterRoom, closedState);
        addOpenStateBoxTrunk = buildAddStatesAction(boxInTrunk, openState);
        removeClosedStateBoxTrunk = buildRemoveStatesAction(boxInTrunk, closedState);
        addOpenStateBoxWardrobe = buildAddStatesAction(boxInWardrobe, openState);
        removeClosedStateBoxWardrobe = buildRemoveStatesAction(boxInWardrobe, closedState);
    }

    private void createKeyActions() {
        addKeyEast = buildChangeContainerAction(keyEast, character);
        removeKeyEast = buildChangeContainerAction(keyEast, trunkInSouthRoom);
        addKeyNorth = buildChangeContainerAction(keyNorth, character);
        removeKeyNorth = buildChangeContainerAction(keyNorth, boxInTrunk);
        addKeySouth = buildChangeContainerAction(keySouth, character);
        removeKeySouth = buildChangeContainerAction(keySouth, boxInCenterRoom);
    }

    private void createAntidoteActions() {
        addAntidote1 = buildChangeContainerAction(antidote1, character);
        removeAntidote1 = buildChangeContainerAction(antidote1, trunkInSouthRoom);
        addAntidote2 = buildChangeContainerAction(antidote2, character);
        removeAntidote2 = buildChangeContainerAction(antidote2, boxInWardrobe);
    }

    private void createPoisonActions() {
        addPoisonState = buildAddStatesAction(character, poisonedState);
        removePoisonState = buildRemoveStatesAction(character, poisonedState);
    }

    private void createTreasureActions() {
        addTreasure = buildChangeContainerAction(treasure, character);
    }

    //DEFINE RULES
    private void createRules() {
        createPositionRules();
        createStateRules();
        createLocationRules();
        createPickRules();
        createOpenAndUnlockDoorsRules();
        createOpenItemRules();
    }

    private IExpression makeComplexRule(IExpression rule1, IExpression rule2, char op) {
        IExpression complexRule = null;
        try {
            complexRule = logicBuilder.build(rule1, rule2, op);
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }
        return complexRule;
    }

    private void createPositionRules() {
        characterIsInCenterRoom = checkContainerRule(character, roomCenter, TreasureHuntConstants.notReachable);
        characterIsInWestRoom = checkContainerRule(character, roomWest, TreasureHuntConstants.notReachable);
        characterIsInEastRoom = checkContainerRule(character, roomEast, TreasureHuntConstants.notReachable);
        characterIsInSouthRoom = checkContainerRule(character, roomSouth, TreasureHuntConstants.notReachable);
        characterIsInNorthRoom = checkContainerRule(character, roomNorth, TreasureHuntConstants.notReachable);
    }

    private void createStateRules() {
        closedCEDoorRule = checkStateRule(doorCtoE, closedState, TreasureHuntConstants.doorOpen);
        closedCNDoorRule = checkStateRule(doorCtoN, closedState, TreasureHuntConstants.doorOpen);
        closedCSDoorRule = checkStateRule(doorCtoS, closedState, TreasureHuntConstants.doorOpen);

        openedCEDoorRule = checkStateRule(doorCtoE, openState, TreasureHuntConstants.doorClosed);
        openedCNDoorRule = checkStateRule(doorCtoN, openState, TreasureHuntConstants.doorClosed);
        openedCSDoorRule = checkStateRule(doorCtoS, openState, TreasureHuntConstants.doorClosed);
        openedBoxCRule = checkStateRule(boxInCenterRoom, openState, TreasureHuntConstants.boxClosed);
        openedBoxERule = checkStateRule(boxInTrunk, openState, TreasureHuntConstants.boxClosed);
        openedBoxSRule = checkStateRule(boxInWardrobe, openState, TreasureHuntConstants.boxClosed);
        openedTrunkERule = checkStateRule(trunkInEastRoom, openState, TreasureHuntConstants.trunkClosed);
        openedTrunkNRule = checkStateRule(trunkInNorthRoom, openState, TreasureHuntConstants.trunkClosed);
        openedTrunkSRule = checkStateRule(trunkInSouthRoom, openState, TreasureHuntConstants.trunkClosed);
        openedWardrobe = checkStateRule(wardrobe, openState, TreasureHuntConstants.wardrobeClosed);

        characterIsntPoisoned = new DoesNotHaveState();
        characterIsntPoisoned.setElementToValidate(character);
        characterIsntPoisoned.setStateToValidate(poisonedState);
        characterIsntPoisoned.setFailMessage(TreasureHuntConstants.healthy);
    }

    private void createLocationRules() {
        freeKeyS = doesntHaveContainerRule(keySouth, character, TreasureHuntConstants.holdsKey);
        freeKeyE = doesntHaveContainerRule(keyEast, character, TreasureHuntConstants.holdsKey);
        freeKeyN = doesntHaveContainerRule(keyNorth, character, TreasureHuntConstants.holdsKey);
        freeAntidote1 = doesntHaveContainerRule(antidote1, character, TreasureHuntConstants.holdsAntidote);
        freeAntidote2 = doesntHaveContainerRule(antidote2, character, TreasureHuntConstants.holdsAntidote);
        freeTreasure = doesntHaveContainerRule(antidote2, character, TreasureHuntConstants.holdsTreasure);
        holdsKeyS = checkContainerRule(keySouth, character, TreasureHuntConstants.missingKey);
        holdsKeyE = checkContainerRule(keyEast, character, TreasureHuntConstants.missingKey);
        holdsKeyN = checkContainerRule(keyNorth, character, TreasureHuntConstants.missingKey);
        holdsAntidote1 = checkContainerRule(antidote1, character, TreasureHuntConstants.missingAntidote);
        holdsAntidote2 = checkContainerRule(antidote2, character, TreasureHuntConstants.missingAntidote);
        holdsTreasure = checkContainerRule(treasure, character, TreasureHuntConstants.missingTreasure);
    }

    //DEFINE COMPLEX RULES
    private void createPickRules() {
        IExpression keySAvailableRule = makeComplexRule(openedBoxCRule, freeKeyS, '&');
        keySPickConditionRule = makeComplexRule(keySAvailableRule, characterIsInCenterRoom, '&');

        IExpression keyEAvailableRule = makeComplexRule(openedTrunkSRule, freeKeyE, '&');
        keyEPickConditionRule = makeComplexRule(keyEAvailableRule, characterIsInSouthRoom, '&');

        IExpression keyNAvailableRule = makeComplexRule(openedBoxERule, freeKeyN, '&');
        keyNPickConditionRule = makeComplexRule(keyNAvailableRule, characterIsInEastRoom, '&');

        IExpression antidote1AvailableRule = makeComplexRule(openedTrunkSRule, freeAntidote1, '&');
        antidote1PickConditionRule = makeComplexRule(antidote1AvailableRule, characterIsInSouthRoom, '&');

        IExpression antidote2AvailableRule = makeComplexRule(openedBoxSRule, freeAntidote2, '&');
        antidote2PickConditionRule = makeComplexRule(antidote2AvailableRule, characterIsInSouthRoom, '&');

        IExpression treasureAvailableRule = makeComplexRule(openedTrunkNRule, freeTreasure, '&');
        treasurePickConditionRule = makeComplexRule(treasureAvailableRule, characterIsInNorthRoom, '&');
    }

    private void createOpenAndUnlockDoorsRules() {
        goThroughDoorEConditions = makeComplexRule(openedCEDoorRule, characterIsInCenterRoom, '&');
        goThroughDoorSConditions = makeComplexRule(openedCSDoorRule, characterIsInCenterRoom, '&');
        goThroughDoorNConditions = makeComplexRule(openedCNDoorRule, characterIsInCenterRoom, '&');
        goThroughDoorWCCondition = makeComplexRule(characterIsntPoisoned, characterIsInWestRoom, '&');
        goThroughDoorNCCondition = makeComplexRule(characterIsntPoisoned, characterIsInNorthRoom, '&');
        IExpression unlockDoorEAvailable = makeComplexRule(closedCEDoorRule, holdsKeyE, '&');
        unlockDoorECondition = makeComplexRule(unlockDoorEAvailable, characterIsInCenterRoom, '&');
        IExpression unlockDoorSAvailable = makeComplexRule(closedCSDoorRule, holdsKeyS, '&');
        unlockDoorSCondition = makeComplexRule(unlockDoorSAvailable, characterIsInCenterRoom, '&');
        IExpression unlockDoorNAvailable = makeComplexRule(closedCNDoorRule, holdsKeyN, '&');
        unlockDoorNCondition = makeComplexRule(unlockDoorNAvailable, characterIsInCenterRoom, '&');
    }

    private void createOpenItemRules() {
        openBoxSCondition = makeComplexRule(openedWardrobe, characterIsInSouthRoom, '&');
        openBoxECondition = makeComplexRule(openedTrunkERule, characterIsInEastRoom, '&');
    }

    private void defineVictoryRule() {
        IExpression victoryCondition = makeComplexRule(holdsTreasure, characterIsInWestRoom, '&');
        game.setVictoryCondition(victoryCondition);
    }

    //DEFINE MOVES
    private void createMoves() {
        createRoomMoves();
        createPickMoves();
        createDropMoves();
        createUseMoves();
        createUnlockMoves();
        createOpenItemMoves();
    }

    private void createRoomMoves() {
        goFromWestToCenter = moveWithActionsAndRules(TreasureHuntConstants.open, passFromWestToCenter,
                goThroughDoorWCCondition, TreasureHuntConstants.movedToCenter);
        goFromEastToCenter = moveWithActionsAndRules(TreasureHuntConstants.open, passFromEastToCenter,
                characterIsInEastRoom,TreasureHuntConstants.movedToCenter);
        goFromSouthToCenter = moveWithActionsAndRules(TreasureHuntConstants.open, passFromSouthToCenter,
                characterIsInSouthRoom, TreasureHuntConstants.movedToCenter);
        goFromNorthToCenter = moveWithActionsAndRules(TreasureHuntConstants.open, passFromNorthToCenter,
                goThroughDoorNCCondition, TreasureHuntConstants.movedToCenter);

        goFromCenterToWest = moveWithActionsAndRules(TreasureHuntConstants.open, passFromCenterToWest,
                characterIsInCenterRoom, TreasureHuntConstants.movedToWest);
        goFromCenterToEast = moveWithActionsAndRules(TreasureHuntConstants.open, passFromCenterToEast,
                goThroughDoorEConditions, TreasureHuntConstants.movedToEast);
        goFromCenterToSouth = moveWithActionsAndRules(TreasureHuntConstants.open, passFromCenterToSouth,
                goThroughDoorSConditions, TreasureHuntConstants.movedToSouth);
        goFromCenterToNorth = moveWithActionsAndRules(TreasureHuntConstants.open, passFromCenterToNorth,
                goThroughDoorNConditions, TreasureHuntConstants.movedToNorth);
    }
    
    private void createPickMoves() {
        pickKeySouth = moveWithActionsAndRules(TreasureHuntConstants.pick, addKeySouth,
                keySPickConditionRule, TreasureHuntConstants.pickKey);
        pickKeyEast = moveWithActionsAndRules(TreasureHuntConstants.pick, addKeyEast,
                keyEPickConditionRule, TreasureHuntConstants.pickKey);
        pickKeyNorth = moveWithActionsAndRules(TreasureHuntConstants.pick, addKeyNorth,
                keyNPickConditionRule, TreasureHuntConstants.pickKey);
        pickAntidote1 = moveWithActionsAndRules(TreasureHuntConstants.pick, addAntidote1,
                antidote1PickConditionRule, TreasureHuntConstants.pickAntidote);
        pickAntidote2 = moveWithActionsAndRules(TreasureHuntConstants.pick, addAntidote2,
                antidote2PickConditionRule, TreasureHuntConstants.pickAntidote);
        pickTreasure = moveWithActionsAndRules(TreasureHuntConstants.pick, addTreasure,
                treasurePickConditionRule, TreasureHuntConstants.pickTreasure);
    }

    private void createDropMoves() {
        dropKeySouth = moveWithActionsAndRules(TreasureHuntConstants.drop, removeKeySouth,
                holdsKeyS, TreasureHuntConstants.dropKey);
        dropKeyEast = moveWithActionsAndRules(TreasureHuntConstants.drop, removeKeyEast,
                holdsKeyE, TreasureHuntConstants.dropKey);
        dropKeyNorth = moveWithActionsAndRules(TreasureHuntConstants.drop, removeKeyNorth,
                holdsKeyN, TreasureHuntConstants.dropKey);
        dropAntidote1 = moveWithActionsAndRules(TreasureHuntConstants.drop, removeAntidote1,
                holdsAntidote1, TreasureHuntConstants.dropAntidote);
        dropAntidote2 = moveWithActionsAndRules(TreasureHuntConstants.drop, removeAntidote2,
                holdsAntidote2, TreasureHuntConstants.dropAntidote);
    }

    private void createUseMoves() {
        useAntidote1 = moveWithActionsAndRules(TreasureHuntConstants.use, removePoisonState,
                holdsAntidote1, TreasureHuntConstants.healed);
        useAntidote1.addAction(removeAntidote1);
        useAntidote2 = moveWithActionsAndRules(TreasureHuntConstants.use, removePoisonState,
                holdsAntidote2, TreasureHuntConstants.healed);
        useAntidote2.addAction(removeAntidote2);
    }

    private void createUnlockMoves() {
        unlockEastDoor = moveWithActionsAndRules(TreasureHuntConstants.unlock, addOpenStateDoorE,
                unlockDoorECondition, TreasureHuntConstants.doorUnlocked);
        unlockEastDoor.addAction(removeClosedStateDoorE);
        unlockNorthDoor = moveWithActionsAndRules(TreasureHuntConstants.unlock, addOpenStateDoorN,
                unlockDoorNCondition, TreasureHuntConstants.doorUnlocked);
        unlockNorthDoor.addAction(removeClosedStateDoorN);
        unlockSouthDoor = moveWithActionsAndRules(TreasureHuntConstants.unlock, addOpenStateDoorS,
                unlockDoorSCondition, TreasureHuntConstants.doorUnlocked);
        unlockSouthDoor.addAction(removeClosedStateDoorS);
    }

    private void createOpenItemMoves() {
        openBoxW = moveWithActionsAndRules(TreasureHuntConstants.open, addOpenStateBoxWest,
                characterIsInWestRoom, TreasureHuntConstants.openPoisonBox);
        openBoxW.addAction(removeClosedStateBoxWest);
        openBoxW.addAction(addPoisonState);
        openBoxE = moveWithActionsAndRules(TreasureHuntConstants.open, addOpenStateBoxTrunk,
                openBoxECondition, TreasureHuntConstants.openBox);
        openBoxE.addAction(removeClosedStateBoxTrunk);
        openBoxS = moveWithActionsAndRules(TreasureHuntConstants.open, addOpenStateBoxWardrobe,
                openBoxSCondition, TreasureHuntConstants.openBox);
        openBoxS.addAction(removeClosedStateBoxWardrobe);
        openBoxC = moveWithActionsAndRules(TreasureHuntConstants.open, addOpenStateBoxCenter,
                characterIsInCenterRoom, TreasureHuntConstants.openBox);
        openBoxC.addAction(removeClosedStateBoxCenter);
        openTrunkE = moveWithActionsAndRules(TreasureHuntConstants.open, addOpenStateTrunkEast,
                characterIsInEastRoom, TreasureHuntConstants.openTrunk);
        openTrunkE.addAction(removeClosedStateTrunkEast);
        openTrunkS = moveWithActionsAndRules(TreasureHuntConstants.open, addOpenStateTrunkSouth,
                characterIsInSouthRoom, TreasureHuntConstants.openTrunk);
        openTrunkS.addAction(removeClosedStateTrunkSouth);
        openTrunkN = moveWithActionsAndRules(TreasureHuntConstants.open, addOpenStateTrunkNorth,
                characterIsInNorthRoom, TreasureHuntConstants.openPoisonTrunk);
        openTrunkN.addAction(removeClosedStateTrunkNorth);
        openTrunkN.addAction(addPoisonState);
        openWardrobe = moveWithActionsAndRules(TreasureHuntConstants.open, addOpenStateWardrobe,
                characterIsInSouthRoom, TreasureHuntConstants.openWardrobe);
        openWardrobe.addAction(removeClosedStateWardrobe);
    }

    private void addMoves() {
        addDoorMoves();
        addPickDropUseMoves();
        addOpenItemMoves();
    }

    private void addDoorMoves() {
        doorWtoC.addMove(goFromWestToCenter);
        doorEtoC.addMove(goFromEastToCenter);
        doorStoC.addMove(goFromSouthToCenter);
        doorNtoC.addMove(goFromNorthToCenter);

        doorCtoW.addMove(goFromCenterToWest);
        doorCtoE.addMove(goFromCenterToEast);
        doorCtoS.addMove(goFromCenterToSouth);
        doorCtoN.addMove(goFromCenterToNorth);

        doorCtoE.addMove(unlockEastDoor);
        doorCtoN.addMove(unlockNorthDoor);
        doorCtoS.addMove(unlockSouthDoor);
    }

    private void addPickDropUseMoves() {
        keySouth.addMove(pickKeySouth);
        keyEast.addMove(pickKeyEast);
        keyNorth.addMove(pickKeyNorth);
        antidote1.addMove(pickAntidote1);
        antidote2.addMove(pickAntidote2);
        treasure.addMove(pickTreasure);

        keySouth.addMove(dropKeySouth);
        keyEast.addMove(dropKeyEast);
        keyNorth.addMove(dropKeyNorth);
        antidote1.addMove(dropAntidote1);
        antidote2.addMove(dropAntidote2);

        antidote1.addMove(useAntidote1);
        antidote2.addMove(useAntidote2);
    }

    private void addOpenItemMoves() {
        boxInWardrobe.addMove(openBoxS);
        boxInTrunk.addMove(openBoxE);
        boxInWestRoom.addMove(openBoxW);
        boxInCenterRoom.addMove(openBoxC);
        trunkInEastRoom.addMove(openTrunkE);
        trunkInSouthRoom.addMove(openTrunkS);
        trunkInNorthRoom.addMove(openTrunkN);
        wardrobe.addMove(openWardrobe);
    }

    @SuppressWarnings("CPD-END")

    public void setActions() {
        createAndAddSuportedAction(1, TreasureHuntConstants.open);
        createAndAddSuportedAction(1, TreasureHuntConstants.pick);
        createAndAddSuportedAction(1, TreasureHuntConstants.drop);
        createAndAddSuportedAction(1, TreasureHuntConstants.unlock);
        createAndAddSuportedAction(1, TreasureHuntConstants.use);
    }
}