# ICS 4U0 — Lesson 11: Writing Classes

## Exercise

You're writing the `Song` class for a music player app. The player itself is already written, in `src/SongPlayer.java`. It reads the input, creates two `Song` objects, plays the first one, renames the second one, and prints both. **It only works once your `Song` class does.**

Write the class in `src/Song.java` to match this UML diagram:

```
┌─────────────────────────────────────┐
│ Song                                │
├─────────────────────────────────────┤
│ - title : String                    │
│ - artist : String                   │
│ - lengthSeconds : int               │
│ - plays : int                       │
├─────────────────────────────────────┤
│ + Song(title : String,              │
│        artist : String,             │
│        lengthSeconds : int)         │
│ + getTitle() : String               │
│ + getArtist() : String              │
│ + getLengthSeconds() : int          │
│ + getPlays() : int                  │
│ + setTitle(title : String) : void   │
│ + play() : void                     │
│ + getLengthText() : String          │
└─────────────────────────────────────┘
```

Everything you need is from Lesson 11:

- **private attributes**, declared inside the class but outside every method,
- a **constructor** that uses `this.` to set every attribute,
- **getters** and a **setter**, and
- **other methods** that use the attributes.

| Method | What it does |
|---|---|
| `Song(title, artist, lengthSeconds)` | Sets the three attributes from the parameters. Every new song starts with 0 plays. |
| `getTitle()`, `getArtist()`, `getLengthSeconds()`, `getPlays()` | Return the matching attribute. |
| `setTitle(title)` | Replaces the title. |
| `play()` | Adds 1 to the play count. |
| `getLengthText()` | Returns the length as `minutes:seconds`, with two digits for the seconds. `185` returns `"3:05"`, `45` returns `"0:45"`, `600` returns `"10:00"`. |

- `Song.java` already has every method header from the diagram, so the project compiles from the start. Add the attributes and fill in each method body.
- **Don't change `SongPlayer.java`**, and don't rename or remove any method in `Song.java`. `SongPlayer` calls them by name.
- `getLengthText()` needs integer division (`/`) and the remainder (`%`).

---

## Input

Eight lines from the keyboard (`SongPlayer` reads them for you):

| Line | Value | Type |
|------|-------|------|
| 1 | First song's title | text |
| 2 | First song's artist | text |
| 3 | First song's length in seconds | whole number |
| 4 | Second song's title | text |
| 5 | Second song's artist | text |
| 6 | Second song's length in seconds | whole number |
| 7 | Number of times to play the first song | whole number |
| 8 | New title for the second song | text |

Example input:

```
Northern Lights
The Maples
185
Paper Boats
Juniper Lane
270
2
Paper Boats (Live)
```

---

## Output

Exactly three lines:

```
<title> by <artist> (<length text>), plays: <plays>
Renamed "<old title>" to "<new title>"
<title> by <artist> (<length text>), plays: <plays>
```

For the example input above, the program must print **exactly**:

```
Northern Lights by The Maples (3:05), plays: 2
Renamed "Paper Boats" to "Paper Boats (Live)"
Paper Boats (Live) by Juniper Lane (4:30), plays: 0
```

`SongPlayer` prints these lines, so every difference comes from your `Song` class. `(3:05)` and `(3:5)` are not the same answer.

---

## Testing

- Test your code yourself first.
- Open the **Testing** panel from the sidebar (flask icon) and click ▶ **Run Tests**.

A green check next to a test means it passed; a red X means it failed and will show you the expected vs. actual output.
