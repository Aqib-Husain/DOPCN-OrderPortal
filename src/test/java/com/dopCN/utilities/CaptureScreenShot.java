package com.dopCN.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.dopCN.BaseClass.BaseClass;

public class CaptureScreenShot extends BaseClass{
	
	public static  void getScreenShot() {
		File scr =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scr, new File("./ScreenShots/failedTC.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
