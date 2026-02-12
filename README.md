 Persistent Student Record System

## How to run (locally)
1. Download repo and keep `data/students.txt` in project root.
2. Compile packages (or remove package lines if online IDE doesn't support packages).
3. Example (local):
   javac -d bin src/model/*.java src/exceptions/*.java src/threads/*.java src/util/*.java src/service/*.java src/app/*.java
   java -cp bin app.Main

## Notes
- Menu supports Add/View/Search/Delete/Sort/Save.
- Data persists to data/students.txt.
