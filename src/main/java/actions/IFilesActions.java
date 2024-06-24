package actions;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IFilesActions {
	public void downloadFileInIE();

	public String getlastFileNameinDirectory(String dirPath) throws IOException;

	public File getlastFileinDirectory(File dire) throws InterruptedException;

	public String lastFileinDirectoryFullname(File dire) throws InterruptedException;

	public String readFileAsStringUsingPath(String filePath) throws IOException;

	public boolean moveFile(String originalPath, String destinationPath) throws IOException;;

	public List<File> getDirectoryFiles(File dir) throws IOException;

	public List<String> getFilesNames(File dir) throws IOException;

	public void CopyDirectoryToAnotherPath(File fromD, File toD) throws IOException;

	public int getNumberOfLinesInText(String fileLocation) throws IOException;

	public void unzip(String zipFilePath, String destDir) throws IOException;

	public String readFileLineUsingIndex(String FilePath, int LineIndex) throws IOException;

	public String readFileFirstLine(String FilePath) throws IOException;

}
