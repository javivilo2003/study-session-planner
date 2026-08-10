# study-session-planner
This is a small java based CLI project called Study Session Planner. 
It helps the user record and review planned study sessions.
The user will be able to:
 - Create study sessions.
 - List the sessions created in the past.
 - Update past sessions.
 - Change status of each session.
 - Delete a session.
 - Show a summary of the planned sessions.
 - Mark a session as COMPLETE.

A session contains: 
- Subject
- Goal of the session
- Date
- Planned minutes
- Status

No set up needed, only run main in terminal.

Commands for testing:
 - mvn test

Architecture: 
 - Domain.
 - Repository.
 - Service.
 - Exceptions.
 - App.

This is a CLI app. So it is kind of slow for a user to really use this program. No GUI makes this a boring app, but I used it to practice and refresh everything I learned through school. Although it still doesn't have a persistance layer or Data Base. So it is still not fully finished.
 
