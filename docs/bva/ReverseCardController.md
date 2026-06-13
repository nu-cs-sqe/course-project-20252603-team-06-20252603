# BVA Analysis for ReverseCardController class

### Method under test: `executeCardAction()`
- **TC1: Two Players (Minimum Valid Boundary)** ( :white_check_mark: )
    - **State of the system**: Game has exactly 2 alive players. Current index is 0.
    - **Expected output**: Turn order is reversed. Current index is updated to 1. Next index is updated to 0.

- **TC2: Three Players (Minimum + 1 Boundary)** ( x )
    - **State of the system**: Game has exactly 3 alive players. Current index is 1.
    - **Expected output**: Turn order is reversed. Current index is updated to 1. Next index is updated to 2.

- **TC3: Five Players (Maximum Valid Boundary)** ( x )
    - **State of the system**: Game has 5 alive players. Current index is 2.
    - **Expected output**: Turn order is reversed. Current index is updated to 2. Next index is updated to 3.