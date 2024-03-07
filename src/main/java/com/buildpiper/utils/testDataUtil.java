package com.buildpiper.utils;

import java.util.ArrayList;

public class testDataUtil {

	static ExcelUtility reader;

	public static ArrayList<Object[]> getMicroServiceData() {

		ArrayList<Object[]> MicroServiceData = new ArrayList<Object[]>();

		try {
			reader = new ExcelUtility();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int rowNum = 2; rowNum <= reader.getRowCount("MicroServiceData"); rowNum++) {

			String applicationName = reader.getCellData("MicroServiceData", "applicationName", rowNum);
			String envName = reader.getCellData("MicroServiceData", "envName", rowNum);
			String buildRadioButtonName = reader.getCellData("MicroServiceData", "buildRadioButtonName", rowNum);
			String JobTemplateValue = reader.getCellData("MicroServiceData", "JobTemplateValue", rowNum);
			String gitURL = reader.getCellData("MicroServiceData", "gitURL", rowNum);
			String BranchName = reader.getCellData("MicroServiceData", "BranchName", rowNum);
			String FilePath = reader.getCellData("MicroServiceData", "FilePath", rowNum);
			String DockerFilePath = reader.getCellData("MicroServiceData", "DockerFilePath", rowNum);
			String preHookPass = reader.getCellData("MicroServiceData", "preHookPass", rowNum);
			String AccessType = reader.getCellData("MicroServiceData", "AccessType", rowNum);
			String AccessName = reader.getCellData("MicroServiceData", "AccessName", rowNum);
			String portNumber = reader.getCellData("MicroServiceData", "portNumber", rowNum);
			String TargetPort = reader.getCellData("MicroServiceData", "TargetPort", rowNum);
			String configName = reader.getCellData("MicroServiceData", "configName", rowNum);
			String serviceName = reader.getCellData("MicroServiceData", "serviceName", rowNum);
			String deployEnvName = reader.getCellData("MicroServiceData", "deployEnvName", rowNum);
			String key1 = reader.getCellData("MicroServiceData", "key1", rowNum);
			String value1 = reader.getCellData("MicroServiceData", "value1", rowNum);
			String key2 = reader.getCellData("MicroServiceData", "key2", rowNum);
			String value2 = reader.getCellData("MicroServiceData", "value2", rowNum);
			String key3 = reader.getCellData("MicroServiceData", "key3", rowNum);
			String value3 = reader.getCellData("MicroServiceData", "value3", rowNum);
			String uploadTypeName = reader.getCellData("MicroServiceData", "uploadTypeName", rowNum);
			String deployBranchName = reader.getCellData("MicroServiceData", "deployBranchName", rowNum);
			String folderPath = reader.getCellData("MicroServiceData", "folderPath", rowNum);
			String helmReleaseName = reader.getCellData("MicroServiceData", "helmReleaseName", rowNum);
			String folderPathValue = reader.getCellData("MicroServiceData", "folderPathValue", rowNum);
			String imageName = reader.getCellData("MicroServiceData", "imageName", rowNum);

			Object ob[] = { applicationName, envName, buildRadioButtonName, JobTemplateValue, gitURL, BranchName,
					FilePath, DockerFilePath, preHookPass, AccessType, AccessName, portNumber, TargetPort, configName,
					serviceName, deployEnvName, key1, value1, key2, value2, key3, value3, uploadTypeName,
					deployBranchName, folderPath, helmReleaseName, folderPathValue, imageName };
			MicroServiceData.add(ob);
		}
		return MicroServiceData;
	}

}
