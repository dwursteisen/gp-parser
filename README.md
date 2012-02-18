Guitar Pro file parser
======================

Main purpose
------------
This Java library is here to help you to parse Guitar Pro file (v4, ...)


How to use it ?
---------------
Add this library to your java project. Create a parser for your Guitar pro file

    GP4Parser parser = new GP4Parser();

Give it your own class which implement GP4ParserListener. This listener will be aware then the parser read the song name,
 when the parser read a note, .... With this information, you'll be able to retrieve informations that you'll
 need into your application

    parser.setListener(yourListener);

Then just read your Guitar Pro file :

    parser.openFile(yourFile);

Have you got an easy-to-use example ?
--------------------------------------
Just check the example project avaible in this repository


How to compile it ?
-------------------
Clone this repository, and perform this [maven](http://maven.apache.org/) command to compile/test/package it !

    mvn install

The packaged java library will be located into the target directory that will be created by [maven](http://maven.apache.org/).
