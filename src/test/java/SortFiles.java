import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Used for the gradle task of the same name
 * Sorts through all the file and numbers them all with the same number of digits so that they are loaded in the correct order
 * Its in the test package so its not included in the compiled jar
 */
public class SortFiles {

    // used to format the file names
    private static DecimalFormat df = new DecimalFormat("00");

    /**
     * used by the gradle task because JavaExec tasks require a main to run
     * @param args not used
     * @throws IOException ignore
     */
    public static void main(String[] args) throws IOException {

        System.out.println(SortFiles.class.getName());
        // starts a recursive method from the resources root
        dispatch(new File("src/main/resources"));
    }

    /**
     * Recurses through a directory sorting all files numerically
     * @param parent the directory being searched
     * @throws IOException
     */
    public static void dispatch(File parent) throws IOException {
        // makes a list of all files to be searched
        File[] children = parent.listFiles();
        // A list of all the files to be sorted
        ArrayList<TestFile> framesSorted = new ArrayList<>();
        // loops through all the child files
        for (File child : children) {
            // if its a directory
            if (child.isDirectory()) {
                // and doesnt already have special names then recurse
                if (!child.getName().equals("amLabel") && !child.getName().equals("audio") && !child.getName().equals("nightLabel")) {
                    dispatch(child);
                }
                continue;
            }
            // otherwise add it to the list
            System.out.println(child.getName());
            framesSorted.add(new TestFile(child));
        }
        // sort using a comparator
        framesSorted.sort(Comparator.comparingInt(f -> f.number));
        System.out.println(Arrays.toString(framesSorted.toArray()));
        // rename the files
        for (int i = 0; i < framesSorted.size(); i++) {
            TestFile file = framesSorted.get(i);
            Files.move(file.file.toPath(), file.file.toPath().resolveSibling(df.format(i) + ".png"));
        }
    }

    // static class used to store a file and its number
    static class TestFile {

        public File file;
        public int number;

        public TestFile(File f) {
            file = f;
            number = Integer.parseInt(f.getName().split("\\.")[0]);
        }

        @Override
        public String toString() {
            return number + ".png";
        }
    }

}
