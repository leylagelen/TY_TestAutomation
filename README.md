##TRENDYOL AUTOMATION CASE

___

#### Test Case Steps:
* Select browser type from command line
* Go to trendyol login page.
* Enter mail address and passwor and click enter button
* Click first section and click others like one after another
* Go to random list of product 
* Check image loaded or not
* Go to random product
* Add this product to basket
* This is end of the test and close driver

#### Requirements: 
* Java8+
* Maven 3.6.1
* Firefox Driver
* Chrome Driver
* TestNG 6.10
* Selenium 3.141.59


`Used OS version = macOS Catalina 10.15.6`

For the install Maven packages, you can use this command.
```
mvn clean package
```

For the run these codes in command line, You should be in a directory that contains pom.xml file. And you can use this code
```
for exm.  mvn -Dtest=PackageName.ClassName -Dbrowser="browserType" test
mvn -Dtest=CheckImageAndSelectProduct.CheckImageAndSelectProduct -Dbrowser="firefox" test
mvn -Dtest=CheckImageAndSelectProduct.CheckImageAndSelectProduct -Dbrowser="chrome" test
```
