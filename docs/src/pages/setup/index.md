---
layout: page
title:  Project Setup
---

Organizing projects with macros can be difficult due to a restriction called *separate compilation*. **Macros cannot be used in the same compilation unit as they are defined.**

## Separate Compilation in SBT

We can implement this in SBT by splitting code across multiple *projects* or *configurations*:

- [Projects] are the standard way of splitting a codebase. Typical use-cases include separating library code from application code, or re-using library code across multiple projects.

- [Configurations] are separate codebases within a project. `main` and `test` are examples of built-in configurations but we can equally define our own configurations.

In each case, SBT fulfills the requirement for separate compilation by compiling all of the code in one project or configuration before moving on to the next.

[projects]:       http://www.scala-sbt.org/0.13.2/docs/Getting-Started/Multi-Project.html
[configurations]: http://www.scala-sbt.org/0.12.1/docs/Dormant/Configurations.html

## Practical Code Structure

When writing a library that uses macros, separate compilation means we tend to prefer one of two possible code structures. We either use macros as a thin layer on top of a non-macro codebase, or we use them as a fundemental underpinning for the rest of our code.

 - Layering macros *on top* of the rest of our code means we don't have to pay special attention to separate compilation. Our library code defines macros but doesn't use them internally -- any use cases occur in a separate project or in unit tests (which are in a separate configuration).

 - Using macros *within* our library code requires us to split our codebase into multiple subprojects or configurations.
