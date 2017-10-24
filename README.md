I have used TestNG Framework in Selenium Webdriver for this project. 


Steps to achieve this environment-
1)	Install Java
2)	Set Path for java
3)	Download Eclipse
4)	Create new project Compare Numbers, create new package compareProject and add all the external libraries from the libraries from library folder in this project. Also add TestNG library to the project.
5)	Create a new TestNG class called Numbers and an xml file Crossin this package.


Steps to run the code-
1)	There is an excel file named Book3 in this project folder. This file contains all the test-cases which are passed to the Numbers.java file.
2)	Save this Book3 file in some location. You can find the source code ( Numbers.java, CrossBrowser.xml )in compareProject package in src folder of Compare Numbers project.
3)	Update the line 44 (FileInputStream fi = new FileInputStream("C:\\Users\\SHRUTI\\Desktop\\desktop\\Book3.xls");) in your program as per the Book3 file location.
4)	Also, change the lines 30 and 33 i.e  	System.setProperty("webdriver.gecko.driver","C:\\Users\\SHRUTI\\Downloads\\geckodriver.exe"); and System.setProperty("webdriver.chrome.driver", "C:\\Users\\SHRUTI\\Downloads\\chromedriver.exe"); in your code as per the geckodriver and chromedriver location in your system.
5)	Now run CrossBrowser.xml file as TestNG Suite to see the automation.


Explanation-
I have made use of data driven testing and keyword driven testing parameterization technique to pass the test cases data to the program and report the results back in excel file. Apache poi jar files are used to achieve this. The excel file Book3 has 2 columns for Firefox Results and Chrome Results. Whenever we run the xml file, the behavior of the application when run using Firefox browser i.e the output displayed on the screen gets displayed in the Firefox Results column and the output displayed by using Chrome browser gets displayed in the Chrome Results column. This way we can run this application to observe it’s behavior by adding any number of test-cases to the excel file. 
The xml file is used to perform cross-browser technique. We can perform cross browser testing on safari, internet explorer similarly.  I have used TestNG framework which can be used to give priority to tests and which generate detailed test reports when we run the application. Further, this code can be extended by using Page Object Model by creating a Repository class which stores all the functions and variables and can be called from Numbers class. This can avoid code repetition and make code maintenance easier for larger complex applications. I haven’t used it here as this is a simple single page to be tested and wouldn’t have benefitted much.
TestNG can perform load testing using multithreading technique. @Test(invocation count=3, threadPoolSize=3) The threadPoolSize tells TestNG to create a thread pool to run the test method via multiple threads. With thread pool, it decreases the running time of test method. The Test method runs 3 times and each receive it’s own thread. Thus we can perform load testing on small applications using TestNG. For larger complex applications, we can use Apache JMeter by integrating code from Selenium Junit directly. We can also use Selenium Grid for this purpose but I have used multithreading due to simple application and TestNG framework. 


Summary of tests to be performed-
1)	Functional Testing-
The functionality of buttons can be tested for this application using Numbers.java file. It handles all kind of user inputs, page loading, error and output message testing.
2)	Usability Testing-
This involves manual testing to check available tabs, text-boxes are long enough, visibility of messages, dialogue and error boxes, etc.
3)	Compatibility-
This involves testing the application working on different browsers. CrossBrowser.xml file serves this purpose in our case. It also involves manual testing to verify if the appearance of icons, images and text looks the same for different browsers.
4)	Integration Testing- 
It involves testing if this particular functionality works correctly when integrated with other functionalities of the application. This assignment just involves testing a single functionality so didn’t involve Integration testing in it.
5)	Performance Testing-
It can be achieved by creating multiple threads and invocations in our case. But we can use JMeter or Selenium Grid for complex applications.
6)	Appearance-
This involves manual testing to check if image is loaded properly, window resize works correctly when browser size is changed.
7)	Regression testing-
It involves running a test suite when more and more changes are made to an application to check if the new changes and code didn’t affect the previous code functionality.



