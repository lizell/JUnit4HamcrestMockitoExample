# JUnit4 / Hamcrest / Mockito example usage

This project contains example code that goes along with this
[Prezi](http://prezi.com/0lmuispevrgy/junit4-hamcrest-och-mockito/?auth_key=6557b2c60ec80e4485115ebf269c4be40346fa43).

The default spreadsheet used by the DisplayData class can be populated using
[this form](https://docs.google.com/a/athega.se/spreadsheet/viewform?hl=sv&formkey=dGRxZkpBUml1XzNhVDVNT1FKTXBPQmc6MQ#gid=0).

### Run the Game of Life

```shell
java -cp target/classes se.athega.lizell.gameoflife.Game
```

```shell
java -cp target/classes se.athega.lizell.gameoflife.Game [blinker|beehive|glider|die]
```

```shell
java -cp target/classes se.athega.lizell.gameoflife.Game ---\
***\
---
```

### Run the Google Spreadsheet Viewer

```shell
mvn exec:java -Dexec.mainClass=se.athega.lizell.gdata.DisplayData
```

```shell
mvn exec:java -Dexec.mainClass=se.athega.lizell.gdata.DisplayData -Dexec.args="<aKey>"
```

Feel free to adjust, expand, provide feedback or all of it! ;)

/Christian