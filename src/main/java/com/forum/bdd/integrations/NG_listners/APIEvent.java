package com.forum.bdd.integrations.NG_listners;

import java.lang.reflect.Method;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.forum.bdd.integrations.report_utils.ReportManager;

public class APIEvent implements ITestListener {

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("+++++++++++++++++++++onTestStart++++++++++++++++++++");
   //ReportManager.startTestAPI(arg0.getMethod().getMethodName(),"API");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {

	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {

		System.out.println("+++++++++++++++++++++onTestFailure++++++++++++++++++++");
		ReportManager.logFailAPI(iTestResult.getThrowable().toString());

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext arg0) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public static List<String> getParameterNames(Method method) {
		Parameter[] parameters = method.getParameters();
		List<String> parameterNames = new ArrayList<>();

		for (Parameter parameter : parameters) {
			if (!parameter.isNamePresent()) {
				throw new IllegalArgumentException("Parameter names are not present!");
			}

			String parameterName = parameter.getName();
			parameterNames.add(parameterName);
		}

		return parameterNames;
	}

}
