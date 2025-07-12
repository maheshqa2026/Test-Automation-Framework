package com.ui.listners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JsonUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer {

	private static final int MAX_NUM_OF_ATTEMTS = Integer
			.parseInt(PropertiesUtil.readProperty(Env.QA, "MAX_NUM_OF_ATTEMTS"));
//private static final int MAX_NUM_OF_ATTEMTS = JsonUtility.readJson(Env.QA).getMAX_NUM_OF_ATTEMTS();
	private static int current_attempt = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (current_attempt <= MAX_NUM_OF_ATTEMTS) {
			current_attempt++;
			return true;
		}

		return false;
	}

}
