/*
 * SongTest.java -- unit tests for the Lesson 11 exercise
 * ICS 4U0 - Lesson 11 Exercise: Song
 *
 * These tests run SongPlayer.main() exactly as it will be graded: they type
 * the eight input lines on System.in and check the lines it prints to
 * System.out. SongPlayer only works if your Song class does, so every
 * failure here points at Song.java. You don't need to change this file --
 * just run the tests (see README.md) and fix Song.java until they all pass.
 */
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SongTest {

    private InputStream originalIn;
    private PrintStream originalOut;
    private Locale originalLocale;

    @BeforeEach
    public void redirectIoAndPinLocale() {
        originalIn = System.in;
        originalOut = System.out;
        originalLocale = Locale.getDefault();
        // printf follows the default locale -- this exercise doesn't print
        // decimals, but every test pins the locale before calling main() so
        // the harness behaves the same on every machine.
        Locale.setDefault(Locale.CANADA);
    }

    @AfterEach
    public void restoreIoAndLocale() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        Locale.setDefault(originalLocale);
    }

    /**
     * Feeds {@code input} to SongPlayer.main() on System.in and returns
     * everything it printed to System.out.
     */
    private String run(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured, true, StandardCharsets.UTF_8));

        SongPlayer.main(new String[0]);

        return captured.toString(StandardCharsets.UTF_8);
    }

    /**
     * Compares output line by line, trimming trailing whitespace on each
     * line and ignoring one blank line at the very end (println's final
     * newline). Everything else -- including every space, colon, and quote -- is
     * compared exactly.
     */
    private void assertOutputEquals(String expected, String actual) {
        String[] expectedLines = expected.stripTrailing().split("\n", -1);
        String[] actualLines = actual.stripTrailing().split("\n", -1);

        assertEquals(expectedLines.length, actualLines.length,
                "Expected " + expectedLines.length + " line(s) of output, got " + actualLines.length
                        + ".\n--- expected ---\n" + expected + "--- actual ---\n" + actual);

        for (int i = 0; i < expectedLines.length; i++) {
            assertEquals(expectedLines[i].stripTrailing(), actualLines[i].stripTrailing(),
                    "Line " + (i + 1) + " didn't match.\n--- expected ---\n" + expected
                            + "--- actual ---\n" + actual);
        }
    }

    @Test
    public void exampleFromReadme() {
        String input = "Northern Lights\nThe Maples\n185\nPaper Boats\nJuniper Lane\n270\n2\nPaper Boats (Live)\n";
        String expected = "Northern Lights by The Maples (3:05), plays: 2\n"
                + "Renamed \"Paper Boats\" to \"Paper Boats (Live)\"\n"
                + "Paper Boats (Live) by Juniper Lane (4:30), plays: 0\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void constructorStoresEveryValue() {
        // Writing title = title; (without this.) in the constructor assigns the
        // parameter to itself, and the attribute stays null. Use this.title = title;
        String input = "Static\nLow Tide\n200\nEcho\nHarbour Lights\n140\n0\nEcho\n";
        String expected = "Static by Low Tide (3:20), plays: 0\n"
                + "Renamed \"Echo\" to \"Echo\"\n"
                + "Echo by Harbour Lights (2:20), plays: 0\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void eachSongCountsItsOwnPlays() {
        // Only the first song is played. An attribute declared static is shared
        // by every Song, so the second song would show the same play count.
        String input = "Driftwood\nCedar & Stone\n215\nGlass House\nThe Lanterns\n198\n3\nGlass House\n";
        String expected = "Driftwood by Cedar & Stone (3:35), plays: 3\n"
                + "Renamed \"Glass House\" to \"Glass House\"\n"
                + "Glass House by The Lanterns (3:18), plays: 0\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void playAddsOneEachTime() {
        // play() must add one to plays each time it's called, not set it to 1.
        String input = "Overpass\nNine Rivers\n240\nSlow Burn\nAmber Coast\n300\n5\nSlow Burn\n";
        String expected = "Overpass by Nine Rivers (4:00), plays: 5\n"
                + "Renamed \"Slow Burn\" to \"Slow Burn\"\n"
                + "Slow Burn by Amber Coast (5:00), plays: 0\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void setTitleChangesTheTitle() {
        // title = title; (without this.) in the setter changes nothing.
        String input = "Lighthouse\nNorth Shore\n181\nDemo 4\nNorth Shore\n122\n1\nHomecoming\n";
        String expected = "Lighthouse by North Shore (3:01), plays: 1\n"
                + "Renamed \"Demo 4\" to \"Homecoming\"\n"
                + "Homecoming by North Shore (2:02), plays: 0\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void lengthTextPadsSecondsWithZero() {
        // 62 seconds is "1:02", not "1:2". 600 seconds is "10:00".
        String input = "Quick One\nPaper Kites Club\n62\nLong Road\nThe Wayfarers\n600\n0\nLong Road\n";
        String expected = "Quick One by Paper Kites Club (1:02), plays: 0\n"
                + "Renamed \"Long Road\" to \"Long Road\"\n"
                + "Long Road by The Wayfarers (10:00), plays: 0\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void lengthTextUnderOneMinute() {
        // Songs shorter than a minute still show the minutes: "0:45".
        String input = "Intro\nSilver Fern\n45\nInterlude\nSilver Fern\n59\n0\nInterlude\n";
        String expected = "Intro by Silver Fern (0:45), plays: 0\n"
                + "Renamed \"Interlude\" to \"Interlude\"\n"
                + "Interlude by Silver Fern (0:59), plays: 0\n";
        assertOutputEquals(expected, run(input));
    }
}
