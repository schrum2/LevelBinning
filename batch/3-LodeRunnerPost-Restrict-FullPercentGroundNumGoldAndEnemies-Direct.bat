cd ..
java -jar MMNEAT.jar runNumber:%1 randomSeed:%1 parallelEvaluations:true threads:20 base:loderunnergte log:LodeRunnerGTE-FullDirect2GAN saveTo:FullDirect2GAN trials:1 experiment:edu.southwestern.experiment.post.CompareMAPElitesBinningSchemeExperiment mapElitesBinLabels:edu.southwestern.tasks.loderunner.mapelites.LodeRunnerMAPElitesPercentGroundNumGoldAndEnemiesBinLabels logLock:true io:false allowWeirdLodeRunnerActions:false lodeRunnerMaximizeEnemies:false lodeRunnerTSPBudget:0 lodeRunnerAllowsAStarConnectivityCombo:true lodeRunnerMinGroundPercentIndex:0 lodeRunnerMaxGroundPercentIndex:9 lodeRunnerMinTreasuresIndex:0 lodeRunnerMaxTreasuresIndex:9 lodeRunnerMinEnemiesIndex:0 lodeRunnerMinEnemiesIndex:2 discardFromBinOutsideRestrictedRange:true