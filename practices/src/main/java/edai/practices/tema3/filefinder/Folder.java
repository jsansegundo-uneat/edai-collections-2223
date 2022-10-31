package edai.practices.tema3.filefinder;

public class Folder extends Entry {

    private final Entry[] entries;

    public Folder(String name, Entry[] entries){
        super(name);
        this.entries = entries;

        for(Entry e: entries){
            e.setParent(this);
        }
    }

    public Entry[] getEntries() {
        return entries;
    }
}
