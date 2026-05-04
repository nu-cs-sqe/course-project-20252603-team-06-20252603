# BVA analysis for Card

### Method under test: Constructor

- constructor_ValidTypeAndAction_CreatesCard not_done (for cards with actions)
    - **State of the system**: fully populated constructor
    - **Expected output**: successful card creation 

- constructor_ValidTypeNullAction_CreatesCard done (for cards without actions)
    - **State of the system**: null action but valid card type
    - **Expected output**: successful card creation 

- constructor_NullTypeNullAction_ThrowsIllegalArgumentException done (for cards without actions)
    - **State of the system**: null action and null card type
    - **Expected output**: illegalArgumentException

- constructor_NullTypeAndAction_ThrowsIllegalArgumentException done (for cards with actions)
    - **State of the system**: valid action but null card type
    - **Expected output**: illegalArgumentException


### Method under test: play(GameState, Player)

- play_NullGameStateValidPlayer_ThrowsIllegalArgumentException not_done
    - **State of the system**: null GameState, valid Player
    - **Expected output**: illegalArgumentException

- play_NullPlayerValidGameState_ThrowsIllegalArgumentException not_done
    - **State of the system**: Valid GameState, null Player
    - **Expected output**: illegalArgumentException

- play_NullPlayerNullGameState_ThrowsIllegalArgumentException not_done
    - **State of the system**: Null GameState, null Player
    - **Expected output**: illegalArgumentException

- play_NullAction_DoesNothingSafely not_done (for cards without actions)
    - **State of the system**: Null action, valid Player, valid GameState
    - **Expected output**: valid return

- play_ValidAction_TriggersActionExecuteMethod not_done (for cards with actions)
    - **State of the system**: valid Player, valid GameState, valid action
    - **Expected output**: valid return. verify that the action's method is successfully executed


### Method under test: play(GameState, Player, Target) ONLY FOR CARDS WITH TARGETS + ACTIONS

- playTargeted_NullTarget_ThrowsIllegalArgumentException not_done
    - **State of the system**: valid Player, valid GameState, null target
    - **Expected output**: IllegalArgumentException

- playTargeted_TargetIsCurrentPlayer_ThrowsIllegalArgumentException not_done
    - **State of the system**: valid Player, valid GameState, invalid target
    - **Expected output**: IllegalArgumentException

- playTargeted_ValidInputs_TriggersActionExecuteWithTarget not_done
    - **State of the system**: valid Player, valid GameState, valid target
    - **Expected output**: valid return. verify that the action's method is successfully executed

- playTargeted_NullAction_ThrowsUnsupportedOperationException not_done
    - **State of the system**: valid Player, valid GameState, valid target, but card itself does NOT HAVE AN ACTION
    - **Expected output**: unsupportedOperationException