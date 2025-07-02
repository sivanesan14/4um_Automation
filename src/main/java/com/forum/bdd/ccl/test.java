package com.forum.bdd.ccl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.testng.annotations.Test;

//
@Test
public class test {

	String processName = "Vyapar.exe";

	public void tasklist() throws IOException {
		Process listTasksProcess = Runtime.getRuntime().exec("tasklist");
		BufferedReader tasksListReader = new BufferedReader(new InputStreamReader(listTasksProcess.getInputStream()));

		String tasksLine;

		while ((tasksLine = tasksListReader.readLine()) != null) {
			if (tasksLine.contains("Vyapar")) {
				Runtime.getRuntime().exec("taskkill /F /IM " + processName);
			}
		}

	}
}
//	try{
//	    Process p = Runtime.getRuntime()
//	    p.exec("taskkill /F /IM taskmgr.exe /T")
//	catch(*Enter applicable exception type here*  e){
//	    System.err.println("Caught Exception: " + e.getMessage());
//	}
//	
//}
