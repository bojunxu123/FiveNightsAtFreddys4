void main() throws IOException {
    File resources = new File("src/main/resources");
    File[] animations = resources.listFiles();
    for (File animation : animations) {
        if (!animation.isDirectory()) continue;
        if (animation.getName().equals("audio") || animation.getName().equals("amLabel") || animation.getName().equals("nightLabel")) continue;
        File[] frames = animation.listFiles();
        System.out.println(animation.getName());
        ArrayList<TestFile> framesSorted = new ArrayList<>();
        for (int i = 0; i < frames.length; i++) {
            if (frames[i].isDirectory()) continue;
            TestFile tf = new TestFile(frames[i]);
            framesSorted.add(tf);
        }
        framesSorted.sort(Comparator.comparingInt(f -> f.number));
        for (TestFile file : framesSorted) {
            Files.move(file.file.toPath(), file.file.toPath().resolveSibling(file.number + ".png"));
        }
        System.out.println(Arrays.toString(framesSorted.toArray()));
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