### Method under test: `executeCardAction()`

inputs: Game game, Player initiator, Optional<Player> target
- LIST: initiator.getHand()
- LIST: target.getHand()
- CARD: card target chooses, from UI

output: Optional.empty() (Optional<List<Card>>)
- LIST: initiator.getHand()
- LIST: target.getHand()

- **TC1: executeCardAction_targetOneCard_cardGiven** ( :x: or :white-check-mark: )
    - **State of the system**: initiator.getHand(): [], target.getHand(): [ATTACK], CARD: ATTACK
    - **Expected output**: initiator.getHand(): [ATTACK], target.getHand(): []

- **TC2: executeCardAction_targetTwoCards_cardGiven** ( :x: or :white-check-mark: )
    - **State of the system**: initiator.getHand(): [], target.getHand(): [ATTACK, SKIP], CARD: ATTACK
    - **Expected output**: initiator.getHand(): [ATTACK], target.getHand(): [SKIP]

- **TC3: executeCardAction_targetDuplicateCards_cardGiven** ( :x: or :white-check-mark: )
    - **State of the system**: initiator.getHand(): [], target.getHand(): [ATTACK, ATTACK], CARD: ATTACK
    - **Expected output**: initiator.getHand(): [ATTACK], target.getHand(): [ATTACK]

- **TC4: executeCardAction_initiatorDuplicateCards_cardGiven** ( :x: or :white-check-mark: )
    - **State of the system**: initiator.getHand(): [ATTACK], target.getHand(): [ATTACK], CARD: ATTACK
    - **Expected output**: initiator.getHand(): [ATTACK, ATTACK], target.getHand(): []

- **TC5: executeCardAction_targetFirstCard_cardGiven** ( :x: or :white-check-mark: )
    - **State of the system**: initiator.getHand(): [], target.getHand(): [SKIP, ATTACK, DEFUSE], CARD: SKIP
    - **Expected output**: initiator.getHand(): [SKIP], target.getHand(): [ATTACK, DEFUSE]

- **TC6: executeCardAction_targetLastCard_cardGiven** ( :x: or :white-check-mark: )
    - **State of the system**: initiator.getHand(): [], target.getHand(): [SKIP, ATTACK, DEFUSE], CARD: DEFUSE
    - **Expected output**: initiator.getHand(): [DEFUSE], target.getHand(): [SKIP, ATTACK]

- **TC7: executeCardAction_targetLastCard_cardGiven** ( :x: or :white-check-mark: )
    - **State of the system**: initiator.getHand(): [DEFUSE, SKIP], target.getHand(): [ATTACK], CARD: ATTACK
    - **Expected output**: initiator.getHand(): [DEFUSE, SKIP, ATTACK], target.getHand(): []

- **TC8: executeCardAction_cardNotInTarget_illegalArgumentException** ( :x: or :white-check-mark: )
    - **State of the system**: initiator.getHand(): [], target.getHand(): [ATTACK], CARD: DEFUSE
    - **Expected output**: illegalArgumentException, initiator.getHand(): [], target.getHand(): [ATTACK]


