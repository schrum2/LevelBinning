# LevelBinning

### 1. Set-up

1. Install Java 8 -aka 1.8- (you can check your version with `java -version`)

   - Unix: `sudo apt-get install openjdk-8-jre`
   - OSX: 
     - `brew tap AdoptOpenJDK/openjdk`
     - `brew cask install adoptopenjdk8`
     - If troubles, check: [how to install Java on Mac OS](https://mkyong.com/java/how-to-install-java-on-mac-osx/) 
   - Windows: [Java 8 for Windows](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) 

2. Install [Python 3.9](https://www.python.org/downloads/release/python-390/)

3. `pip install -r requirements.txt`

4. Create text file `my_python_path.txt` that contains the full path to your Python executable.

5. Compile the Java code with [Apache ANT](https://ant.apache.org/)

   - `ant -f build.xml`
