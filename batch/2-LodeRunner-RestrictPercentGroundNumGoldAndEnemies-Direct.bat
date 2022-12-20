cd ..
java -jar MMNEAT.jar runNumber:%1 randomSeed:%1 base:loderunnergte log:LodeRunnerGTE-RestrictDirect2GAN saveTo:RestrictDirect2GAN LodeRunnerGANModel:LodeRunnerAllGround150LevelsEpoch20000_10_7.pth watch:false GANInputSize:10 trials:1 mu:100 maxGens:50000 io:true netio:true genotype:edu.southwestern.evolution.genotypes.BoundedRealValuedGenotype mating:true fs:false task:edu.southwestern.tasks.loderunner.LodeRunnerGANLevelTask cleanFrequency:-1 saveAllChampions:true cleanOldNetworks:false logTWEANNData:false logMutationAndLineage:false steadyStateIndividualsPerGeneration:100 aStarSearchBudget:100000 mapElitesBinLabels:edu.southwestern.tasks.loderunner.mapelites.LodeRunnerMAPElitesPercentGroundNumGoldAndEnemiesBinLabels ea:edu.southwestern.evolution.mapelites.MAPElites experiment:edu.southwestern.experiment.evolution.SteadyStateExperiment allowWeirdLodeRunnerActions:false lodeRunnerMaximizeEnemies:false lodeRunnerTSPBudget:0 lodeRunnerAllowsAStarConnectivityCombo:true lodeRunnerMinGroundPercentIndex:0 lodeRunnerMaxGroundPercentIndex:9 lodeRunnerMinTreasuresIndex:0 lodeRunnerMaxTreasuresIndex:9 lodeRunnerMinEnemiesIndex:0 lodeRunnerMinEnemiesIndex:2 discardFromBinOutsideRestrictedRange:true




