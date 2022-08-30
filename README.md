# LevelBinning

### 1. Set-up

1. Install Java 8 -aka 1.8- (you can check your version with `java -version`)

   - Unix: `sudo apt-get install openjdk-8-jre`
   - OSX: 
     - `brew tap AdoptOpenJDK/openjdk`
     - `brew cask install adoptopenjdk8`
     - If troubles, check: [how to install Java on Mac OS](https://mkyong.com/java/how-to-install-java-on-mac-osx/) 
   - Windows: [Java 8 for Windows](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) 

2. Install [Python 3.8](https://www.python.org/downloads/release/python-380/)

3. `pip install -r requirements.txt`

4. Create text file `my_python_path.txt` that contains the full path to your Python executable.

5. Compile the Java code with [Apache ANT](https://ant.apache.org/)

   - `ant -f build.xml`

6. The file `MMNEAT.jar` is created, and can be executed from the command line with `java -jar MMNEAT.jar`

7. The `batch` directory contains batch files that can run on Windows.

   - Windows: Inside `batch` directory with PowerShell or Command: `0-ZeldaPre-BinDungeons.bat`
   - OSX/Unix: From root project directory: `bash runscript.bash 0-MarioPre-BinLevels.bat`

8. Code can also be run from Eclipse. The main class is `MMNEAT` but command line parameters will be needed to do anything interesting (see batch files).

#### Note for M1 Mac Users: 

One way to install torch is by pretending you are on an x64 machine with Rosetta, and you might have luck creating a Rosetta integrated terminal in Visual Studio Code. You can create a Rosetta terminal by adding the following to your User or Workplace Settings (JSON):

 ```
 "rosetta": {
            "path": "arch",
            "args": ["-x86_64", "bash", "-l"],
            "overrideName": true
          }
```
