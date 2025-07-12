package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputFilter.Config;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojo.Environment;
import com.ui.pojo.config;

public class JsonUtility {
	
	public static Environment readJson(Env env){
		
		
		Gson gson = new Gson();
		
		File jsonFile=new File(System.getProperty("user.dir")+"//config//config.json");
		
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		config config=gson.fromJson(fileReader, config.class);
		
		Environment environment= config.getEnvironment().get("QA");
		
		return environment;
		
		
	}

}
