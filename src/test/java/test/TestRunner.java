package test;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] { 
        	Tests.class,Tests2.class
        		});
        testng.addListener(tla);
        testng.run();
        
        if (testng.hasFailure()) {
            System.out.println("Test failed.");
            System.exit(1);
          } else {
            System.out.println("Test finished successfully.");
            System.exit(0);
          }
        
        //or using xml suites files
        
        /*
          List<String> suites = Lists.newArrayList();

suites.add("testng.xml");//path to xml..in src
suites.add("suite.xml");

testng.setTestSuites(suites);

testng.run();
         */
        
	}

}
