package edai.practices.tema3.filefinder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class FileFinderTest {

    FileFinder fileUtils;

    FileSystemMock createFileSystemMock(){
        Folder root = new Folder("c:",
                new Entry[]{
                        new File("system.cfg"),
                        new File("autoexec.bat"),
                        new Folder("User", new Entry[]{
                                new Folder("Documents", new Entry[]{
                                        new File("EdaiLeakedExam.doc"),
                                        new File("MyCV.pdf"),
                                        new File("JavaTutorial.pdf")
                                }),
                                new Folder("Music", new Entry[]{
                                        new File("MySpeech.mp3"),
                                        new Folder("Daft Punk", new Entry[]{
                                                new File("playlist.m3u"),
                                                new File("Get Lucky.mp3"),
                                                new File("Within.mp3"),
                                                new File("Touch.mp3"),
                                        })
                                }),
                                new Folder("Portables", new Entry[]{
                                        new Folder("ZoomIt", new Entry[]{
                                                new File("ZoomIt.exe"),
                                                new File("ZoomIt_x64.exe")
                                        })
                                })
                        }),
                        new Folder("Windows", new Entry[]{
                                new Folder("system32", new Entry[]{
                                        new File("apphelp.dll"),
                                        new File("advapi32.dll"),
                                        new Folder("1028", new Entry[0]),
                                        new Folder("1029", new Entry[0]),
                                        new Folder("Boot", new Entry[]{
                                                new File("winload.exe")
                                        }),
                                })
                        })
                }
        );

        return new FileSystemMock(root);
    }

    @BeforeEach
    void setup() {
        fileUtils = new FileFinder(createFileSystemMock());
    }

    @ParameterizedTest
    @CsvSource({
            "autoexec.bat, c:/autoexec.bat",
            "Get Lucky.mp3, c:/User/Music/Daft Punk/Get Lucky.mp3",
            "winload.exe, c:/Windows/system32/Boot/winload.exe"
    })
    void findOneFilePath(String filename, String expectedPath) {
        String[] paths = fileUtils.findFiles(filename);

        assertEquals(1, paths.length);
        assertEquals(expectedPath, paths[0]);
    }

    @Test
    void fileAllMp3Files(){
        String[] paths = fileUtils.findFiles("*.mp3");

        String[] expectedFiles = new String[]{
                "c:/User/Music/MySpeech.mp3",
                "c:/User/Music/Daft Punk/Get Lucky.mp3",
                "c:/User/Music/Daft Punk/Within.mp3",
                "c:/User/Music/Daft Punk/Touch.mp3",
        };

       assertArrayContainsAll(expectedFiles, paths);
    }

    @Test
    void findAllExeFiles(){
        String[] paths = fileUtils.findFiles("*.exe");

        String[] expectedFiles = new String[]{
                "c:/User/Portables/ZoomIt/ZoomIt.exe",
                "c:/User/Portables/ZoomIt/ZoomIt_x64.exe",
                "c:/Windows/system32/Boot/winload.exe",

        };
        assertArrayContainsAll(expectedFiles, paths);
    }

    @Test
    void doesNotFindAnyBmp(){
        String[] paths = fileUtils.findFiles("*.bmp");
        assertArrayEquals(new String[0], paths);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "ZoomIt_x64.exe",
            "Get Lucky.mp3",
            "autoexec.bat"
    })
    void filePatternWithFullName(String fileName){
        assertTrue(FileFinder.checkPatternName(fileName, fileName));
    }

    @ParameterizedTest
    @CsvSource({
        "'*.mp3', 'Get Lucky.mp3'",
        "'*.exe', 'ZoomIt.exe'",
        "'*.cfg', 'idea.me.cfg'"
    })
    void filePatternWithSimpleAsterisk(String pattern, String filename){
        assertTrue(fileUtils.checkPatternName(filename, pattern));
    }

    private static void assertArrayContainsAll(String[] expected, String[] list) {
        assertEquals(expected.length, list.length);
        expected = expected.clone();

        for(String line: list){

            int index = safeIndexOf(expected, line);
            if(index < 0){
                fail(String.format("Item '%s' is not in expected list", line));
            }

            expected[index] = null;
        }
    }

    private static <T extends Comparable> int safeIndexOf(T[] list, T value){
        for(int i = 0; i < list.length; ++i){
            if(list[i] == null) continue;
            if(list[i].compareTo(value) == 0){
                return i;
            }
        }

        return -1;
    }

}
