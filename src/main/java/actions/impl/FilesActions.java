package actions.impl;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import actions.IFilesActions;
import actions.IWaitActions;

/**
 * FilesActions class in Actions package that provides methods to perform
 * various file operations.
 */
public class FilesActions implements IFilesActions {

	IWaitActions waitActions= new WaitActions();
	Logger log = LogManager.getLogger(FilesActions.class); // A logger object for logging messages in the class.

	/**
	 * Method to download a file from the IE browser using the Robot class.
	 */
	public void downloadFileInIE() {
		try {
			Robot robot = new Robot(); // Create a Robot object to simulate key presses.
			robot.setAutoDelay(250); // Set an auto delay of 250ms.
			robot.keyPress(KeyEvent.VK_ALT); // Press the Alt key.
			robot.keyPress(KeyEvent.VK_S); // Press the S key.
			robot.keyRelease(KeyEvent.VK_ALT); // Release the Alt key.
			robot.keyRelease(KeyEvent.VK_S); // Release the S key.
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to get the name of the latest file in a directory.
	 * 
	 * @param dirPath The path of the directory.
	 * @return The name of the latest file in the directory.
	 */
	public String getlastFileNameinDirectory(String dirPath) {
		File dire = new File(dirPath); // Create a File object with the given directory path.
		File[] files = dire.listFiles(); // Get a list of all the files in the directory.
		File lastModifiedFile = files[0]; // Initialize the last modified file with the first file in the list.
		for (int i = 1; i < files.length; i++) {
			// Iterate through all the files in the list and find the one that was last
			// modified.
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		String fileName = lastModifiedFile.getName(); // Get the name of the last modified file.
		log.info("File: {}" , fileName); // Log the file name.
		return fileName;
	}
	
	/**
	 * Method to get the name of the latest file in a directory.
	 * 
	 * @param dirPath The path of the directory.
	 * @return the latest file in the directory.
	 */
	
	
	public File getlastFileinDirectory(File dire) throws InterruptedException {
		waitActions.wait(5);

		log.info("go to directory");
		// File dire = new File(DirPath);
		log.info("get list of files in the directory");
		File[] files = dire.listFiles();
		log.info("iterate in every file");
		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		log.info("get last modified file name");
		// String FileName = lastModifiedFile.getAbsolutePath();
		log.info("File: {}" ,lastModifiedFile.getName());
		return lastModifiedFile;
	}
	/**
	 * Method to get the full name of the latest file in a directory.
	 * 
	 * @param dire The path of the directory.
	 * @return the latest file full name in the directory.
	 */
	public String  lastFileinDirectoryFullname(File dire) throws InterruptedException {
		waitActions.wait(5);

		log.info("go to directory");
		// File dire = new File(DirPath);
		log.info("get list of files in the directory");
		File[] files = dire.listFiles();
		log.info("iterate in every file");
		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		log.info("get last modified file name");
		String FileName = lastModifiedFile.getAbsolutePath();
		log.info("File: {}" ,FileName);
		return FileName;
	}

	/**
	 * Method to read the contents of a file and return them as a string.
	 * 
	 * @param filePath The path of the file to read.
	 * @return The contents of the file as a string.
	 * @throws IOException If there is an error while reading the file.
	 */
	public String readFileAsStringUsingPath(String filePath) throws IOException {
		InputStream is = new FileInputStream(filePath); // Create an InputStream object to read the file.
		BufferedReader buf = new BufferedReader(new InputStreamReader(is)); // Create a BufferedReader object to read
																			// the file line by line.
		String line = buf.readLine(); // Read the first line of the file.
		StringBuilder sb = new StringBuilder();
		while (line != null) {
			sb.append(line).append("\n"); // Append each line of the file to the StringBuilder object.
			line = buf.readLine(); // Read the next line of the file.
		}

		String fileString = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8); // Read the																								// them in a
																											// string.
		buf.close(); // Close the BufferedReader object.
		return fileString;
	}

	/**
	 * Method to move a file from one location to another.
	 * 
	 * @param originalPath    The original path of the file.
	 * @param destinationPath The destination path for the file.
	 * @return true if the file was successfully moved, false otherwise.
	 * @throws IOException If an I/O error occurs.
	 */
	public boolean moveFile(String originalPath, String destinationPath) throws IOException

	{
		boolean flag = false;

		Path temp = Files.move(Paths.get(originalPath), Paths.get(destinationPath));

		if (temp != null) {
			flag = true;
			log.info("File Removed to :{} " , destinationPath);
		} else {
			flag = false;
			log.error("File failed to Removed to :{} " , destinationPath);
		}
		return flag;

	}
	
	/**
	 * Method to get Directory Files.
	 * 
	 * @param dir folder path.
	 * @return list of files.
	 * 
	 */
	public List<File> getDirectoryFiles(File dir)
	{
        List<File> resultList = new LinkedList<File>();

		try {

			File[] files = dir.listFiles();
			log.info  ("number of files :{}",files.length);

	        resultList.addAll(Arrays.asList(files));
		        
	        for (File file : files) {
		            if (file.isFile()) {
		            	log.info  ("file :{}",file.getName());

		            	
		            } else if (file.isDirectory()) {
		                resultList.addAll(getDirectoryFiles(file));
		            }
		        }
		    
		
	}catch (Exception e) {
		log.info  ("Exception : {0} ",e);
	}
		return resultList;
		
		}
	
	/**
	 * Method to get file names in directory.
	 * 
	 * @param dir    The folder/directory.
	 * @return List of string.
	 
	 */
	
	public List<String> getFilesNames( File dir) 
	{
		List<String> fileNames = new LinkedList<String>();
        List<File> resultList = new LinkedList<File>();

        resultList= getDirectoryFiles(dir);
		if(fileNames != null)
		{
		for (File file : resultList) {
            if (file.isFile()) 
            	fileNames.add(file.getName());
            }

		}		return fileNames;
		
	}
	
	
	/**
	 * Method to copy directory to another path.
	 * 
	 * @param fromD    The folder/directory original path.
	 * @param toD  The new path.
	 */
	
	
	public void CopyDirectoryToAnotherPath(File fromD, File toD) {

		try {
			FileUtils.copyDirectory(fromD, toD);
			log.info("Directory moved successfully.");

		} catch (IOException ex) {
			ex.printStackTrace();
			log.info("exception: {0}",  ex);

		}
	}
	/**
	 * Method to get NumberOfLinesInText.
	 * 
	 * @param fileLocation  file path.
	 * @return the number of lines.
	 */
	public int getNumberOfLinesInText(String fileLocation) throws IOException {
		FileReader input = new FileReader(fileLocation);
		LineNumberReader count = new LineNumberReader(input);

		int lines = (int) count.lines().count() + 1;

		count.close();

		log.info(lines);
		return lines;

	}
	/**
	 * Method to unzip compressed/zipped.
	 * 
	 * @param zipFilePath path of zipped file.
	 * @param destDir destination to unzip it.
	 */
	
	public void unzip(String zipFilePath, String destDir) {
		File dir = new File(destDir);
		if (!dir.exists())
			dir.mkdirs();
		FileInputStream fis;
		byte[] buffer = new byte[1024];
		try {
			fis = new FileInputStream(zipFilePath);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(destDir + File.separator + fileName);
				log.info("Unzipping to :{}" , newFile.getAbsolutePath());
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Method to read File Line Using Index.
	 * 
	 * @param FilePath path of  file.
	 * @param LineIndex index of line.
	 * @return String of line
	 */
	
	
	public String readFileLineUsingIndex(String FilePath, int LineIndex) throws IOException {
		log.info("read file using path :{}" , FilePath);
		InputStream is = new FileInputStream(FilePath);
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));

		String extractedLine = Files.readAllLines(Paths.get(FilePath)).get(LineIndex);
buf.close();
		log.info("read line With Index : {}" , LineIndex);
		return extractedLine;

	}
	/**
	 * Method to read File first Line .
	 * 
	 * @param FilePath path of  file.
	 * @return String of line
	 */
	
	
	
	public String readFileFirstLine(String FilePath) throws IOException {
		log.info("read file using path ");
		InputStream is = new FileInputStream(FilePath);
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		String line = buf.readLine();
buf.close();
		log.info("read First line ");
		return line;

	}
	
	
}