## Quotes and Quotes Extended

This application will make a call to the Ron Swanson API and display that to the console.  Additionally, it will save that quote to the JSON file in case there's no internet connection.  At that point, it will read a Json file and randomly print out quotes from the list of quotes.

## Feature Task
Make an API call to retrieve a quote.  In the process, save that quote to the JSON file of quotes.  Ensure that the process to randomly pick a quote will still work if the API call cannot be made.

Use the file [recentquotes.json](https://codefellows.github.io/code-401-java-guide/curriculum/08-oo-design-practice/recentquotes.json) to show random popular book quotes. Your program should use GSON to parse the .json file. 

The app needs no functionality other than showing the quote and the author when it is run. The app should choose one quote each time it is run.


## How to Run Application
All dependencies have been added to the application.
Just run <b>./gradlew run</b> to install proper dependencies.

## Resources
[GSON](https://github.com/google/gson/blob/master/UserGuide.md)  
[Reading From Scanner](https://stackoverflow.com/questions/13185727/reading-a-txt-file-using-scanner-class-in-java)  
[Parsing JSON File with GSON](https://stackoverflow.com/questions/29965764/how-to-parse-json-file-with-gson)
[Ron Swanson API](https://github.com/jamesseanwright/ron-swanson-quotes)
[Using FileWriter](http://zetcode.com/java/filewriter/)

## Authors
* [Bomi Bear](https://github.com/bomibear)
* [Perezm27](https://github.com/perezm27)