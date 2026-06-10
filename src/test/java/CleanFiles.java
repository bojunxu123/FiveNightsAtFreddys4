DecimalFormat df = new DecimalFormat("000");
void main() throws IOException {
    dispatch(new File("src/main/resources"));

}

void dispatch(File parent) throws IOException {
    File[] children = parent.listFiles();
    ArrayList<TestFile> framesSorted = new ArrayList<>();
    for (File child : children) {
        if (child.isDirectory()) {
            if (!child.getName().equals("amLabel") && !child.getName().equals("audio") && !child.getName().equals("nightLabel")) {
                dispatch(child);
            }
        }
        System.out.println(child.getName());
        framesSorted.add(new TestFile(child));
    }
    framesSorted.sort(Comparator.comparingInt(f -> f.number));
    System.out.println(Arrays.toString(framesSorted.toArray()));
    for (int i = 0; i < framesSorted.size(); i++) {
        TestFile file = framesSorted.get(i);
        Files.move(file.file.toPath(), file.file.toPath().resolveSibling(df.format(i) + ".png"));
    }
}

class TestFile {

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