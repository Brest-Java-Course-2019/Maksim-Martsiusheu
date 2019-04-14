Course Project
==============
[![Build Status](https://travis-ci.org/Brest-Java-Course-2019/Maksim-Martsiusheu.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2019/Maksim-Martsiusheu)
[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2019/Maksim-Martsiusheu/badge.svg?branch=master)](https://coveralls.io/github/Brest-Java-Course-2019/Maksim-Martsiusheu?branch=master) 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
```
install git
install maven 3+
install openjdk 8
```

### Installing

Choose directory in which you want download project. After this, download project from github:

```
$ git clone https://github.com/Brest-Java-Course-2019/Maksim-Martsiusheu
```
### Build project
For build project you can use next commands: 
```
$ cd /<yout directory>/<downloaded project>/CourseProject/
$ mvn clean install
```

### Preparing reports
For preparing reports of project use:
````
$ mvn site
````
And open:
````
/<yout directory>/<downloaded project>/CourseProject/target/site/index.html
````
## Running WEB application on Jetty server

To run web-application use:
````
$ cd /<yout directory>/<downloaded project>/CourseProject/rest-app/
$ mvn jetty:run

$ cd /<yout directory>/<downloaded project>/CourseProject/web-app/
$ mvn jetty:run
````
After this you check web-application on http://localhost:8080
Or rest-application on  http://localhost:8088


## Author

**Maksim Martsiusheu** - [MaksimMartsiusheu](https://github.com/MaksimMartsiusheu)
## License

This project is licensed under the MIT License

