### Method under test: executeCardAction()

inputs: Game game, Player user, Optional<Player> target
- BOOLEAN: user.hasDefuse()
- LIST: user.getHand()

output: Optional.empty() (Optional<List<Card>>)
- BOOLEAN: user.isAlive
- LIST: user.getHand()

- **TC1: executeCardAction_noDefuseEmptyHand_playerKilled ** ( :white_check_mark: )
    - **State of the system**: hand: []
    - **Expected output**: user.isAlive(): false, user.getHand(): []

- **TC2: executeCardAction_oneDefuseOneCard_playerLivesEmptyHand ** ( :white_check_mark: )
    - **State of the system**: hand: [DEFUSE]
    - **Expected output**: user.isAlive(): true, user.getHand(): []

- **TC3: executeCardAction_twoDefuses_playerLivesOneDefuse ** ( :white_check_mark: )
    - **State of the system**: hand: [DEFUSE, DEFUSE]
    - **Expected output**: user.isAlive(): true, user.getHand(): [DEFUSE]

- **TC4: executeCardAction_lastCardDefuseTwoCards_playerLivesOneCard ** ( :white_check_mark: )
    - **State of the system**: hand: [ATTACK, DEFUSE]
    - **Expected output**: user.isAlive(): true, user.getHand(): [ATTACK]

- **TC5: executeCardAction_firstCardDefuseThreeCards_playerLivesOneCard ** ( :white_check_mark: )
    - **State of the system**: hand: [DEFUSE, ATTACK, SKIP]
    - **Expected output**: user.isAlive(): true, user.getHand(): [ATTACK, SKIP]

- **TC6: executeCardAction_noDefuseTwoCards_playerKilled ** ( :white_check_mark: )
    - **State of the system**: hand: [ATTACK, SKIP]
    - **Expected output**: user.isAlive(): false, user.getHand(): [ATTACK, SKIP]