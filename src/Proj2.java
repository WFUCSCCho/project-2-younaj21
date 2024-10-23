import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Batting csv filtered down to just the 2015 season to help loading time

public class Proj2 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }


        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        ArrayList<BaseballBatter> batters = new ArrayList<BaseballBatter>();
        String currLine;
        String[] parts;

        // Declare all the player variables outside here
        String playerID;
        String yearID;
        int stint;
        String teamID;
        String lgID;
        int G;
        int AB;
        int R;
        int H;
        int Doubles;
        int Triples;
        int HR;
        int RBI;
        int SB;
        int CS;
        int BB;
        int SO;
        int IBB;
        int HBP;
        int SH;
        int SF;
        int GIDP;
        String nameGiven;
        String masterID;
        // For loop to iterate through the csv and all the BaseballBatter player classes into an ArrayList
        for (int i = 0; i < numLines; i++){
            //Read in the current line for a player in the csv
            currLine = inputFileNameScanner.nextLine();
            // Now split the values based on the commas to get all the variables
            parts = currLine.split(",");

            playerID = parts[0];
            yearID = parts[1];
            stint = Integer.parseInt(parts[2]);
            teamID = parts[3];
            lgID = parts[4];
            G = Integer.parseInt(parts[5]);
            AB = Integer.parseInt(parts[6]);
            R = Integer.parseInt(parts[7]);
            H = Integer.parseInt(parts[8]);
            Doubles = Integer.parseInt(parts[9]);
            Triples = Integer.parseInt(parts[10]);
            HR = Integer.parseInt(parts[11]);
            RBI = Integer.parseInt(parts[12]);
            SB = Integer.parseInt(parts[13]);
            CS = Integer.parseInt(parts[14]);
            BB = Integer.parseInt(parts[15]);
            SO = Integer.parseInt(parts[16]);
            IBB = Integer.parseInt(parts[17]);
            HBP = Integer.parseInt(parts[18]);
            SH = Integer.parseInt(parts[19]);
            SF = Integer.parseInt(parts[20]);
            GIDP = Integer.parseInt(parts[21]);
            nameGiven = parts[22];
            masterID = parts[23];
            // Now add the player to the array list
            batters.add(new BaseballBatter(playerID, yearID, stint, teamID, lgID, G, AB, R, H, Doubles,
                    Triples, HR, RBI, SB, CS, BB, SO, IBB, HBP, SH, SF, GIDP, nameGiven, masterID));


        }
        // Sort the arrayList
        Collections.sort(batters);
        ArrayList<BaseballBatter> battersSorted = batters;
        // Shuffle the arrayList
        Collections.shuffle(batters);
        ArrayList<BaseballBatter> battersShuffled = batters;

        // Set up the 4 different trees here
        BST<BaseballBatter> mybstSorted = new BST<BaseballBatter>();
        BST<BaseballBatter> mybstShuffled = new BST<BaseballBatter>();
        AVLTree<BaseballBatter> myavlSorted = new AVLTree<BaseballBatter>();
        AVLTree<BaseballBatter> myavlShuffled = new AVLTree<BaseballBatter>();

        //Setup the three nantime variable
        Long startTime;
        Long endTime;

        double elapsedMillis_SortedBST_Insert;
        double elapsedMillis_ShuffledBST_Insert;
        double elapsedMillis_SortedAVL_Insert;
        double elapsedMillis_ShuffledAVL_Insert;
        double elapsedMillis_SortedBST_Search;
        double elapsedMillis_ShuffledBST_Search;
        double elapsedMillis_SortedAVL_Search;
        double elapsedMillis_ShuffledAVL_Search;

        System.out.println();
        System.out.println("==============================Begin Inserting Nodes into the 4 different trees==============================");
        System.out.println("============================================================================================================");

        // Insert data into mybstSorted
        startTime = System.nanoTime();
        for (int i = 0; i < batters.size(); i++){
            mybstSorted.insert(battersSorted.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_SortedBST_Insert = (endTime - startTime) / 1_000_000.0;
        System.out.println("-----Sorted BST of " + numLines + " lines takes " + elapsedMillis_SortedBST_Insert + " milliseconds to insert all nodes.-----");
        System.out.println("============================================================================================================");

        // Insert data into mybstShuffled
        startTime = System.nanoTime();
        for (int i = 0; i < batters.size(); i++){
            mybstShuffled.insert(battersShuffled.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_ShuffledBST_Insert = (endTime - startTime) / 1_000_000.0;
        System.out.println("----Shuffled BST of " + numLines + " lines takes " + elapsedMillis_ShuffledBST_Insert + " milliseconds to insert all nodes.----");
        System.out.println("============================================================================================================");

        // Insert data into myavlSorted
        startTime = System.nanoTime();
        for (int i = 0; i < batters.size(); i++){
            myavlSorted.insert(battersSorted.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_SortedAVL_Insert = (endTime - startTime) / 1_000_000.0;
        System.out.println("-----Sorted AVL of " + numLines + " lines takes " + elapsedMillis_SortedAVL_Insert + " milliseconds to insert all nodes.-----");
        System.out.println("============================================================================================================");

        // Insert data into myavlShuffled
        startTime = System.nanoTime();
        for (int i = 0; i < batters.size(); i++){
            myavlShuffled.insert(battersShuffled.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_ShuffledAVL_Insert = (endTime - startTime) / 1_000_000.0;
        System.out.println("----Shuffled AVL of " + numLines + " lines takes " + elapsedMillis_ShuffledAVL_Insert + " milliseconds to insert all nodes.----");
        System.out.println("============================================================================================================");

        // Now perform the 4 searches here using the original batters ArrayList
        // Search data in mybstSorted
        startTime = System.nanoTime();
        for (int i = 0; i < batters.size(); i++){
            mybstSorted.search(batters.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_SortedBST_Search = (endTime - startTime) / 1_000_000.0;
        System.out.println("-----Sorted BST of " + numLines + " lines takes " + elapsedMillis_SortedBST_Search + " milliseconds to search all nodes.-----");
        System.out.println("============================================================================================================");

        // Search data in mybstShuffled
        startTime = System.nanoTime();
        for (int i = 0; i < batters.size(); i++){
            mybstShuffled.search(batters.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_ShuffledBST_Search = (endTime - startTime) / 1_000_000.0;
        System.out.println("----Shuffled BST of " + numLines + " lines takes " + elapsedMillis_ShuffledBST_Search + " milliseconds to search all nodes.----");
        System.out.println("============================================================================================================");

        // Search data in myavlSorted
        startTime = System.nanoTime();
        for (int i = 0; i < batters.size(); i++){
            myavlSorted.contains(batters.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_SortedAVL_Search = (endTime - startTime) / 1_000_000.0;
        System.out.println("-----Sorted AVL of " + numLines + " lines takes " + elapsedMillis_SortedAVL_Search + " milliseconds to search all nodes.-----");
        System.out.println("============================================================================================================");

        // Search data in myavlShuffled
        startTime = System.nanoTime();
        for (int i = 0; i < batters.size(); i++){
            myavlShuffled.contains(batters.get(i));
        }
        endTime = System.nanoTime();
        elapsedMillis_ShuffledAVL_Search = (endTime - startTime) / 1_000_000.0;
        System.out.println("----Shuffled AVL of " + numLines + " lines takes " + elapsedMillis_ShuffledAVL_Search + " milliseconds to search all nodes.----");
        System.out.println("============================================================================================================");
        System.out.println("============================================================================================================");

        //Write all of these results to the output.txt file
        writeToFile( numLines + ", " + elapsedMillis_SortedBST_Insert + ", " + elapsedMillis_ShuffledBST_Insert + ", " +
                elapsedMillis_SortedAVL_Insert + ", " + elapsedMillis_ShuffledAVL_Insert + ", " + elapsedMillis_SortedBST_Search + ", " +
                elapsedMillis_ShuffledBST_Search + ", " + elapsedMillis_SortedAVL_Search + ", " + elapsedMillis_ShuffledAVL_Insert + ",\n", "./src/output.txt");
    }


    // Generate the result file
    public static void writeToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(content);
            //writer.newLine();
        }
        catch (IOException e){ // Error handling
            e.printStackTrace();
        }
    }

}
