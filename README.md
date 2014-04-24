Cache Simulator for IV1350
==========================

Requirements
------------
Requires Ruby, Gem and Java to run.
Execute `# gem install rakejava` to install necessary build scripts

Make
----

- `rake` or `rake compile` - compile the Java-source code into classes/
- `rake jar` - compiles and creates _cachesim.jar_ jar-file
- `rake doc` - generate Javadoc files
- `rake run` - compile and run the application (won't break if compile breaks)
- `rake test` - compile and run tests for the application (beware: not 
sensitive for compile-failures)
- `rake compiletest` - compile tests for the application (also compiles 
everything else)
- `rake testdoc` - generate Javadoc files for tests as well as the application.
- `rake clean` - EXTERMINATE unnecessary files :smiling_imp:

Run
---
You need Java and run with `java -jar cachesim.jar`

