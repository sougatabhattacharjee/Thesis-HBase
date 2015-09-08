```
Distribution      : Ubuntu 12.04 64bit, Apache Hadoop 2.5.0
Programming       : Java SDK 8, Apache Maven, Antlr 4.5
Development Tools : IntelliJ IDEA 14.0
NoSql Datastore   : HBase 0.98.7-hadoop2
Contributor       : @sougatabhattacharjee
Additional Note   : Master Thesis: "Views and Virtual Table Transformations for HBase"
```

## Overview
HBase is a wide-column store which offers a simple get and set API together with a full MapReduce support. In this Master Thesis, developing a language called "NotaQL" to define transformations over HBase tables which are executed as a MapReduce job. The result of a transformation is stored in a new HBase table and can be queried with the HBase shell, with the Java API, with MapReduce, or it can be used as an input for a new transformation. This makes chains of multiple transformations possible. In this Master Thesis, transformations should be made virtual. That means that a result is not really stored but can be used as an input for upcoming transformations. This makes it also possible to define views over HBase tables. Challenges in this work are optimization aspects, the question if MapReduce can still be used when transformation are virtual, the limits which views are supported, and the developement of a new HBase API and command-line shell.

## NotaQL
NotaQL Is Not a Query Language! It’s for Data Transformation on Wide-Column Stores. NotaQL is easy to use and powerful. Many MapReduce algorithms like ﬁltering, grouping, aggregation and even breadth-ﬁrst-search, PageRank and other graph and text algorithms can be expressed in two or three short lines of code.

## NotaQL Grammar Wiki
In order to understand the query structure, take a look at the NotaQL grammar in notaql/antlr4/NotaQL.g4. The grammar is written by using Antlr 4.5. 
The NotaQL Query examples can be found in [Wiki](https://github.com/sougatabhattacharjee/Thesis-HBase/wiki).
The Query Examples with results explained in more details [Here](https://github.com/sougatabhattacharjee/Thesis-HBase/wiki/Query-with-NotaQL).
