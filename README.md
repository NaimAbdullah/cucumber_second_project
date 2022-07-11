I used Maven as my build tool. I stored all of my dependencies in my pom.xml file. I have a configuration.properties file to change any global
information such a browser and my wait times. I am using a cucumber BDD framework with selenium WebDriver. I have a pages package where I store
all my locators for my elements to maintain a Page object model design. I have a runner class where I can run all my tests. I also have a steps 
package where I write all the code for each of the testing steps. Lastly, I have a util package to store all my methods such as Waiters or 
Dropdown handlers.

I have a features package where I have my requirements written in Gherkin language. In my runner class I can define which tests to run like Smoke
or Regression. Maven also allows me to use commands to run tests as well and get reports and screenshots.