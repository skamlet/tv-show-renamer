TV Show Renamer
=======

TV Show Renamer is a command line tool used to rapidly rename a bunch of TV show files.

###Example:

Files like these ones:  
  - FamousShow.S05E01.DVDRip.XviD-BLAH.avi  
  - FamousShow.S05E02.DVDRip.XviD-BLAH.avi  

Will be renamed like this:  
  - FamousShow - 05x01.avi  
  - FamousShow - 05x02.avi  

###How to use it:

```java -jar renamer.jar <name> <directory>```

### Supported Pattern:
  - S01E01 (FamousShow.S05E01.DVDRip.XviD-BLAH.avi)
  - 01x01 (FamousShow.05x01.DVDRip.XviD-BLAH.avi)
  - 1x01 (FamousShow.5x01.DVDRip.XviD-BLAH.avi)
  - 0101 (FamousShow.0501.DVDRip.XviD-BLAH.avi)
  - 101 (FamousShow.501.DVDRip.XviD-BLAH.avi)