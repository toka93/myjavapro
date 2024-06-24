package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest implements ITestListener {
	Logger log = LogManager.getLogger(ListenerTest.class);

	
	@Override		
    public void onTestFailure(ITestResult result) {					
        // TODO Auto-generated method stub	
		log.info("The test "+ result.getTestName() +" failed and the exception thrown is : " + result.getThrowable());
        		
    }	
	
	
	
}
