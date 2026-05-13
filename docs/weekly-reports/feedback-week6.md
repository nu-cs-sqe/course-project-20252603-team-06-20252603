# Week 6 Project Feedback by PM/TA

**Dedicated PM/TA**: Vandan Agrawal (vandanagrawal2026@u.northwestern.edu)

## How to Read This Feedback
> [!NOTE]
> **Purpose.** This feedback focuses on your team's progress and collaboration. It is meant as guidance, not judgement.

> [!IMPORTANT]
> **Scope.** For the BVA and TDD items, the PM/TA evaluates only the `main` branch. Ongoing work in feature branches will be evaluated after it is merged. If you'd like early feedback on work in progress, please reach out to your PM/TA directly.

> [!TIP]
> **Mistakes are expected :).** As the instructor mentioned in class, early mistakes are part of the learning process. As long as your team addresses the issues after you get the feedback, your grade will not suffer from them.

## Checklist
Status:
- ✅: All done/Good job!
- ⚠️: Attention needed
- ❌: Significant issue found
- ➖: No basis to evaluate

### Past Feedback
| # | Item                                                                                                 | Status | Reviewer Notes | Source Instructions or Resources |
|---|------------------------------------------------------------------------------------------------------|:------:|----------------|----------------------------------|
| 0 | The team has closed and merged the past Feedback PR(s), indicating that they have read the feedback. | ❌      | **Week 5 feedback PR #16** (`feedback-week5` → `main`) is still **open** on GitHub (`merged_at` null). Merge it after the team has read the feedback so Week 6 review can assume prior feedback was acknowledged. |                                  |

### Software Process Quality
| # | Item                                                                                                                                         | Status | Reviewer Notes | Source Instructions or Resources                                                                              |
|---|----------------------------------------------------------------------------------------------------------------------------------------------|:------:|----------------|---------------------------------------------------------------------------------------------------------------|
| 1 | Checkstyle: Checkstyle is set up or there is a to-do item planned in the Project Management board for this task. (needed for Letter Grade B) | ⚠️     | **Not in Gradle:** `build.gradle.kts` has no Checkstyle plugin or config file. **Planning:** open GitHub issue **#27** (“set up checkstyle and spotbugs”, assignee `danielachangnu`) shows intent; confirm it is visible on the **course project board** if the rubric requires the board specifically, then wire Checkstyle into the build and CI. | Week 6 Monday lecture (Lecture 11); the build script and config file in the repository for Lab: Code Coverage |
| 2 | SpotBugs: SpotBugs is set up or there is a to-do item planned in the Project Management board for this task. (needed for Letter Grade B)     | ⚠️     | Same as Checkstyle: **not configured** in `build.gradle.kts`. Issue **#27** covers both tools; implementation and (if required) explicit board tracking still needed. | Week 6 Monday lecture (Lecture 11); the build script and config file in the repository for Lab: Code Coverage |

### Planning & Progress Evaluation
| # | Item                                                                                                                                                         |  Status   | Reviewer Notes      | Source Instructions or Resources                                                  |
|---|--------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|-----------------------------------------------------------------------------------|
| 4 | The team documents every week’s planning and progress evaluation professionally. (needed for Letter Grade B)                                                 | ⚠️        | `docs/weekly-reports/report.md` includes a **Week 6** block, but it mostly repeats Week 5-style percentages, leaves “Play Exploding Kittens” as not started, and still lacks concrete deliverables, acceptance criteria, and links to issues/PRs for professional traceability. | Week 4 Wednesday Lecture (Lecture 08), Project grading rubrics                    |

### Progress & Collaboration
| # | Item                                                                                                                                                                                   |  Status   | Reviewer Notes      | Source Instructions or Resources                 |
|---|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|--------------------------------------------------|
| 5 | Overall development progress (recall the recommended order is: Game Setup Phase -> One turn of the game -> Multiple turns -> One win condition -> Other win conditions (if applicable) | ⚠️        | On `main`, progress remains **narrow**: `Card` / `CardType` / `Action` plus `CardTests` and `docs/bva/Card.md`. **Game setup** pieces (Deck, Player, Game) are still not present under `src/main/java/domain` (only `.gitkeep`), so the recommended sequence is only barely started relative to Week 6 expectations. | Canvas assignment Project: Week 4 and 5 Guidance |

### The following items are not checked by the reviewer this week as they were checked in the previous weeks
If your team wants the reviewer to check any of these for any reasons, please contact them or the instructor via either email or tag/mention them in the feedback PR.

| #   | Item                                                                                                                                                         |  Status   | Reviewer Notes      | Source Instructions or Resources                                                  |
|-----|--------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|-----------------------------------------------------------------------------------|
| 1   | GitHub repository branch protection rules are fully set up so that people cannot push into main without a pull request approval. (needed for Letter Grade C) | ➖        | Not re-evaluated this week per the section guidance; see Week 4 feedback record. | Canvas assignment Project: Setup, Project grading rubrics                         |
| 2   | Continuous Integration (CI) is fully set up from the beginning. (needed for Letter Grade B)                                                                  | ➖        | Not re-evaluated this week per the section guidance; see Week 4 feedback record. | Canvas assignment Project: Setup, Project grading rubrics                         |
| 3   | The team uses the project management board steadily and frequently, and the description of each task is detailed. (needed for Letter Grade B)                | ➖        | Not re-evaluated this week per the section guidance; see Week 4 feedback record. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.1 | Every functionality-related work item on the management board includes a user story, and optionally one or more use cases.                                   | ➖        | Not re-evaluated this week per the section guidance; see Week 4 feedback record. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.2 | The design is documented somewhere, either in the work item description, or in a separate design document.                                                   | ➖        | Not re-evaluated this week per the section guidance; see Week 4 feedback record. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.3 | Task assignments are documented clearly in the management board.                                                                                             | ➖        | Not re-evaluated this week per the section guidance; see Week 4 feedback record. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 4   | Each active feature branch has an open draft PR against main.                                                                                                | ➖        | Not re-evaluated this week per the section guidance; see Week 5 feedback record. | Week 4 Wednesday Lecture (Lecture 08)                                             |
| 5   | The team has a “definition of done” (BVA) fully documented for the part of the system that is done. (needed for Letter Grade D)                              | ➖        | Not re-evaluated this week per the section guidance; see Week 5 feedback record. | Project grading rubrics                                                           |
| 6   | GitHub commit history demonstrates evidence of a TDD/BDD workflow for all the non-UI code. (needed for Letter Grade C)                                       | ➖        | Not re-evaluated this week per the section guidance; see Week 5 feedback record. | Project grading rubrics                                                           |
| 7   | Collaboration: Quality of discussion in PR reviews and work item comments on the board.                                                                      | ➖        | Not re-evaluated this week per the section guidance; see Week 5 feedback record. |                                                                                   |

## Additional Comments
- **Priority:** Merge **PR #16 (Week 5 feedback)** once the team has read it so the “past feedback acknowledged” gate is satisfied before the next milestone.
- **Week 6 tooling:** Add Checkstyle and SpotBugs (or SpotBugs successor if the course allows) to Gradle with config files, and optionally run them in CI—issue #27 is a good umbrella but should close when the build actually enforces the tools.
- **Progress:** Prioritize getting **Deck / Player / Game** into `main` with tests and BVA so Week 6–7 grading can evaluate gameplay flow, not only `Card`.
- **Access note:** This write-up used the **local workspace** plus the **public GitHub API** (PR/issue state). Local `gradlew test` was not relied on here; align with CI on `main` after merges.
