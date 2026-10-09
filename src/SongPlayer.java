/*
 * Name:        Lesson 11: Writing Classes (SongPlayer.java)
 * Description: The program that uses your Song class. Don't change this
 *              file. It reads the input, creates two Song objects, and
 *              prints them using the methods from the UML diagram.
 * Created by:  Mr Kowalczewski
 * Last edited: 2026-10-06
 */
import java.util.Scanner;

public class SongPlayer {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String firstTitle = input.nextLine().trim();
        String firstArtist = input.nextLine().trim();
        int firstLength = Integer.parseInt(input.nextLine().trim());
        String secondTitle = input.nextLine().trim();
        String secondArtist = input.nextLine().trim();
        int secondLength = Integer.parseInt(input.nextLine().trim());
        int plays = Integer.parseInt(input.nextLine().trim());
        String newTitle = input.nextLine().trim();

        Song first = new Song(firstTitle, firstArtist, firstLength);
        Song second = new Song(secondTitle, secondArtist, secondLength);

        // play the first song, rename the second
        for (int i = 0; i < plays; i++) {
            first.play();
        }
        String oldTitle = second.getTitle();
        second.setTitle(newTitle);

        System.out.println(first.getTitle() + " by " + first.getArtist()
                + " (" + first.getLengthText() + "), plays: " + first.getPlays());
        System.out.println("Renamed \"" + oldTitle + "\" to \"" + second.getTitle() + "\"");
        System.out.println(second.getTitle() + " by " + second.getArtist()
                + " (" + second.getLengthText() + "), plays: " + second.getPlays());
    }
}
