package edai.practices.tema3.filefinder;

import edai.collections.List;
import edai.collections.Stack;

public class FileFinder {

    private final IFileSystem fs;

    public FileFinder(IFileSystem fs) {
        this.fs = fs;
    }

    public static boolean checkPatternName(String filename, String pattern) {
        return WildcardPattern.isMatch(pattern, filename);
    }

    public String[] findFiles(String filePattern) {

        List<String> paths = new List<>();
        Stack<Folder> otherFolders = new Stack<>();
        otherFolders.push(fs.root());

        while(!otherFolders.isEmpty()){
            Folder currentFolder = otherFolders.pop();

            Entry[] entries = currentFolder.getEntries();
            for(int i = 0; i < entries.length; ++i)
            {
                Entry currentEntry = entries[i];

                if(currentEntry instanceof File){
                    if(checkPatternName(currentEntry.getName(), filePattern))
                    {
                        paths.insert(currentEntry.getPath(), -1);
                    }
                }else if(currentEntry instanceof Folder){
                    otherFolders.push((Folder) currentEntry);
                }
            }
        }

        return asStringArray(paths.listData());
    }

    private String[] asStringArray(Object[] list) {
        String[] output = new String[list.length];

        for(int i = 0; i < list.length; ++i){
            output[i] = (String) list[i];
        }

        return output;
    }
}
