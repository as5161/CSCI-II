package simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Simulates a jukebox that plays random songs until a duplicate occurs.
 * Tracks song play counts, first five unique songs played, and generates statistics.
 */
public class Arnolds {
    private List<Song> jukeboxSongs;
    private final Map<Song, Integer> playCounts;
    private final Random random;
    private int totalSongsPlayed;
    private final List<Song> firstFive;

    /**
     * Constructs an Arnolds jukebox simulator by loading songs from a file.
     * Removes duplicate songs while maintaining order and prioritizes specific tracks.
     *
     * @param filename the file containing song data
     * @throws FileNotFoundException if the file is not found
     */
    public Arnolds(String filename) throws FileNotFoundException {
        System.out.println("Loading the jukebox with songs:");
        System.out.println("\tReading songs from " + filename + " into jukebox...");
        List<Song> rawSongs = new ArrayList<>();
        Scanner in = new Scanner(new File(filename));
        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            String[] parts = line.split("<SEP>", -1);
            if (parts.length < 4) continue;
            rawSongs.add(new Song(parts[2].trim(), parts[3].trim()));
        }
        in.close();
        LinkedHashSet<Song> uniqueSongs = new LinkedHashSet<>(rawSongs);
        jukeboxSongs = new ArrayList<>(uniqueSongs);
        Optional<Song> josephSong = jukeboxSongs.stream()
                .filter(s -> s.getArtist().equals("Joseph Locke") && s.getTitle().equals("Goodbye"))
                .findFirst();
        josephSong.ifPresent(song -> {
            jukeboxSongs.remove(song);
            jukeboxSongs.add(0, song);
        });
        playCounts = new HashMap<>();
        jukeboxSongs.forEach(song -> playCounts.put(song, 0));
        System.out.println("\tJukebox is loaded with " + jukeboxSongs.size() + " songs");
        System.out.println("\tFirst song in jukebox: " + jukeboxSongs.get(0));
        System.out.println("\tLast song in jukebox: " + jukeboxSongs.get(jukeboxSongs.size()-1));

        random = new Random(42);
        totalSongsPlayed = 0;
        firstFive = new ArrayList<>();
    }

    /**
     * Runs the simulation, playing songs randomly until a duplicate is encountered.
     * Records song play counts and logs the first five unique songs played.
     */
    public void runSimulations() {
        System.out.println("Running the simulation.  The jukebox starts rockin'!");
        Set<Song> seenSongs = new HashSet<>();
        int simulationCount = 0;
        while (simulationCount < 200000) {
            Set<Song> currentRun = new HashSet<>();
            while (true) {
                int index = random.nextInt(jukeboxSongs.size());
                Song song = jukeboxSongs.get(index);

                if (currentRun.contains(song)) {
                    break;
                }

                currentRun.add(song);
                playCounts.put(song, playCounts.get(song) + 1);
                totalSongsPlayed++;

                if (firstFive.size() < 5 && !seenSongs.contains(song)) {
                    firstFive.add(song);
                    seenSongs.add(song);
                }
            }
            simulationCount++;
        }
        System.out.println("\tPrinting first 5 songs played...");
        firstFive.forEach(song -> System.out.println("\t\tArtist: " + song.getArtist() + ", Title: " + song.getTitle()));
    }

    /**
     * Generates and displays statistical results from the simulation.
     *
     * @param elapsedSeconds the time taken for the simulation to execute
     */
    public void generateStatistics(double elapsedSeconds) {
        System.out.println("Displaying simulation statistics:");
        System.out.println("\tNumber of simulations run: 200000");
        System.out.println("\tTotal number of songs played: " + totalSongsPlayed);
        double average = totalSongsPlayed / 200000.0;
        System.out.printf("\tAverage number of songs played per simulation to get duplicate: %.0f%n", average);
        Song mostPlayedSong = playCounts.entrySet().stream()
                .max(Entry.comparingByValue())
                .map(Entry::getKey)
                .orElseThrow();
        System.out.println("\tMost played song: \"" + mostPlayedSong.getTitle() + "\" by \"" + mostPlayedSong.getArtist() + "\"");
        String targetArtist = mostPlayedSong.getArtist();
        List<Song> artistSongs = new ArrayList<>();
        jukeboxSongs.stream()
                .filter(song -> song.getArtist().equals(targetArtist))
                .forEach(artistSongs::add);
        artistSongs.sort(Comparator.comparing(Song::getTitle));
        System.out.println("\tAll songs alphabetically by \"" + targetArtist + "\":");
        artistSongs.forEach(song -> System.out.println("\t\t\"" + song.getTitle() + "\" with " + playCounts.get(song) + " plays"));

        Map<String, Integer> artistPlays = new HashMap<>();
        playCounts.forEach((song, count) -> artistPlays.merge(song.getArtist(), count, Integer::sum));
        String mostPlayedArtist = artistPlays.entrySet().stream()
                .max(Entry.comparingByValue())
                .map(Entry::getKey)
                .orElseThrow();
        int mostPlayedArtistPlays = artistPlays.get(mostPlayedArtist);
        System.out.println("\tMost played artist: \"" + mostPlayedArtist + "\" with " + mostPlayedArtistPlays + " plays");
    }

    /**
     * Main method to run the simulation.
     *
     * @param args command-line arguments (expects a filename)
     * @throws FileNotFoundException if the file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.out.println("Usage: java Arnolds filename");
            System.exit(1);
        }
        Arnolds arnolds = new Arnolds(args[0]);
        long startTime = System.currentTimeMillis();
        arnolds.runSimulations();
        long endTime = System.currentTimeMillis();
        double elapsedSeconds = (endTime - startTime) / 1000.0;
        System.out.println("\tSimulation took " + elapsedSeconds + " second/s to run");
        arnolds.generateStatistics(elapsedSeconds);
    }
}
