# Study Session Planner

Study Session Planner is a Java 21 command-line application for planning and reviewing study sessions. It was built as a portfolio project to practice a layered Java architecture, validation, repository abstractions, file persistence, and automated tests.

## Problem

Students often plan study time in scattered notes or mental lists, which makes it hard to see what is planned, what is complete, and how much time remains. This app keeps study sessions in one local CLI workflow so a user can create sessions, update them, complete them, and review planned study time by subject.

## Features

- Create a study session with a subject, goal, planned minutes, and status.
- List saved study sessions.
- Search sessions by subject or status.
- Update a session's subject, goal, planned minutes, or status.
- Mark a session as `COMPLETED`.
- Delete a session.
- Show planned-minute summaries by subject.
- Show the total remaining planned minutes.
- Persist sessions between app runs using a local text file.

## Session Data

Each session contains:

- `id`: generated UUID
- `subject`: study subject
- `goal`: goal for the session
- `planned minutes`: integer duration
- `status`: session state, such as `PLANNED` or `COMPLETED`

## Architecture

The project uses a small layered structure:

- `domain`: core session model and status enum.
- `repository`: repository interface plus in-memory and file-backed implementations.
- `repository.persistence`: text-file reader and writer classes.
- `service`: business operations that coordinate validation, updates, completion, and deletion.
- `exceptions`: custom exceptions for invalid sessions and missing sessions.
- `Main`: CLI menu and user input flow.

## Requirements

- Java 21
- Maven 3.9 or newer recommended

## Commands

Run the CLI with Maven:

```bash
mvn exec:java
```

Run tests:

```bash
mvn test
```

Build the project:

```bash
mvn package
```

Run the packaged JAR after building:

```bash
java -jar target/study-session-planner-1.0.0.jar
```

Alternative direct class run:

```bash
mvn compile
java -cp target/classes com.junior.roadmap.Main
```

## Persistence Behavior

The CLI reads from `sessions.txt` when the app starts and writes back to the same file whenever sessions are created, updated, completed, or deleted.

Each saved session is stored as one text line using this format:

```text
UUID | subject | goal | plannedMinutes | status
```

The file is local to the directory where the app is run. If `sessions.txt` does not exist or cannot be read, the app starts with an empty session list and prints a file-read warning.

## Limitations

- CLI-only interface; there is no GUI or web UI.
- Persistence uses a plain text file, not a database.
- Search is limited to the options implemented in the menu.
- Invalid persisted lines are skipped instead of repaired.
- The app assumes it is run from a writable directory so `sessions.txt` can be created or updated.

## Next Steps

- Improve file-read messaging when `sessions.txt` does not exist yet.
- Add a database-backed repository implementation.
- Add richer filtering by date, subject, and status.
- Add a simple GUI or web interface.
- Export summaries to CSV or another report format.
