package edai.practices.tema3.filefinder;

public class FileSystemMock implements IFileSystem {

    private final Folder rootFolder;

    public FileSystemMock(Folder rootFolder){

        this.rootFolder = rootFolder;
    }

    @Override
    public Folder root() {
        return this.rootFolder;
    }
}
