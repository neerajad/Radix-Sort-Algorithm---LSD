package neeraja.algo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class RadixSort {

    private static Scanner in;

    public static void main(String[] args){
    	String inputFile = "f.txt";
        String outputFile = "g.txt";
        try {
        	inputFile = getFile(inputFile, "input");
            outputFile = getFile(outputFile, "output");
            
            String[] sortedArr = createStrArray(inputFile);
            writeToFile(outputFile, sortedArr);
            System.out.println("InputFile(" + inputFile + ") has been sorted and written to the OutputFile :- " + outputFile);
        } catch(FileNotFoundException fnfe) {
        	System.out.println("FileNotFoundException caught");
        } catch(IOException ioe) {
        	System.out.println("IOException caught");
        }
    }

    private static String[] createStrArray(String inputFile) throws FileNotFoundException {
    	
		StringBuffer stringBuffer = new StringBuffer();
		Scanner fileReader = new Scanner(new File(inputFile));
        
        while (fileReader.hasNext()) {
        	stringBuffer.append(fileReader.nextLine());
        }
        fileReader.close();
        
        String[] strArr = stringBuffer.toString().split(" +");
        strArr = checkForLength(strArr);
        
        return strArr;
    }
    
    private static String[] checkForLength(String[] strArr) {
    	int maxLen = getMaxLength(strArr);
    	strArr = adjustLength(strArr, maxLen);
    	
    	// Check Answer
    	//checkAns(strArr);
    	
    	strArr = radixSortArray(strArr, maxLen);
    	
    	for (int i = 0; i < strArr.length; i++) {
    		strArr[i] = strArr[i].replace(" ", "");
        }
    	
    	return strArr;
    }
    
    /*private static void checkAns(String[] strArr) {
    	String[] strArrCheck = new String[strArr.length];
    	strArrCheck = strArr;
    	Arrays.sort(strArrCheck);
    	for (String fruit : strArrCheck)
        {
          System.out.println(fruit);
        }
    }*/
    
    private static int getMaxLength(String[] strArr) {
    	int maxLength = 0;
    	
    	for (int  i = 0 ; i < strArr.length; i++) {
    		if (maxLength < strArr[i].length()) {
    			maxLength = strArr[i].length();
            }
    	}
    	return maxLength;
    }
    
    private static String[] adjustLength(String[] strArr, int maxLen) {
    	
    	for (int i = 0; i < strArr.length; i++) {
    		String str = strArr[i];
    		int strLength = str.length();
    		
    		if (strLength < maxLen) {
    			for (int  k = strLength; k < maxLen; k++){
    				str = str + " ";
    			}
    			strArr[i] = str;
    		}
    	}
    	return strArr;
    }
    
    public static String getFile(String fileName, String ipop) {
        String file = null;
        
        if ("input".equals(ipop)) {
        	file = "f.txt";
        } else {
        	file = "g.txt";
        }
        in = new Scanner(System.in);
        System.out.print("Do you want to Enter " + ipop + " file or use default (" + file + ")) [y/n] : ");
        char choice = in.next().charAt(0);
        in.nextLine();
        
        if (choice == 'y') {
            System.out.print("Please enter " + ipop + " filename (with \".txt\" extension ) : ");
            fileName = in.nextLine();
        }
        return fileName;
    }

    public static void writeToFile(String fileName, String[] data) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for (String s : data) {
            bw.write(s + System.getProperty("line.separator"));
        }
        bw.close();
    }

    public static String[] radixSortArray(String[] strArr, int maxLen) {
        for (int i = maxLen - 1; i >= 0; i--) {
            recSortArray(strArr, strArr.length, i);
        }
        return strArr;
    }

    public static void recSortArray(String[] strArr, int arrSize, int i) {
        if (arrSize == 0) {
            return;
        }
        recSortArray(strArr, arrSize - 1, i);
        int j = arrSize - 1;
        while (j > 0 && strArr[j].charAt(i) < strArr[j - 1].charAt(i)) {
            String temp = strArr[j - 1];
            strArr[j - 1] = strArr[j];
            strArr[j] = temp;
            j--;
        }
    }
}

