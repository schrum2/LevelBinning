REM Usage  : launchMultiple.bat <batch file> <starting run> <ending run> 
REM example: launchMultiple.bat 1-Mario-FullPercentDecorateCoverageAndLeniency-Direct.bat 0 2
REM example: launchMultiple.bat 2-Mario-RestrictedPercentDecorateCoverageAndLeniency-Direct.bat 1 7
FOR /L %%A IN (%2,1,%3) DO (
  cd batch
  %1 %%A
)
ECHO "All done!"