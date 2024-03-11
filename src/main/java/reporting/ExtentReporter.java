package reporting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports extentReport;
	public static ExtentReports createInstance(String fileName, String reportname,String documentTitle) {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);
		extentSparkReporter.config().setReportName(reportname);	
		extentSparkReporter.config().setDocumentTitle(documentTitle);
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setEncoding("utf-8");
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(extentSparkReporter);
		return extentReport;
	}
	
	public static String getReportnameWithTimeStamp() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		String formattedTime = dateTimeFormatter.format(localDateTime);
		String reportName = "TestReport" + formattedTime +".html";
		return reportName;
	}
	
	public static void logPassDetails(String log) {
		Setup.extentTest.get().pass(MarkupHelper.createLabel(log,ExtentColor.GREEN));
		
	}
	
	public static void logFailureDetails(String log) {
		Setup.extentTest.get().fail(MarkupHelper.createLabel(log,ExtentColor.RED));
		
	}
	
	public static void logInfoDetails(String log) {
		Setup.extentTest.get().info(MarkupHelper.createLabel(log,ExtentColor.GREY));
		
	}
	
	public static void logWarningDetails(String log) {
		Setup.extentTest.get().warning(MarkupHelper.createLabel(log,ExtentColor.YELLOW));
		
	}
	
	public static void logJson(String json) {
		Setup.extentTest.get().info(MarkupHelper.createCodeBlock(json,CodeLanguage.JSON));
		
	}
}
