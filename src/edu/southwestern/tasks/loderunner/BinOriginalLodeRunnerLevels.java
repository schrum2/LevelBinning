package edu.southwestern.tasks.loderunner;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.southwestern.MMNEAT.MMNEAT;
import edu.southwestern.evolution.mapelites.Archive;
import edu.southwestern.evolution.mapelites.MAPElites;
import edu.southwestern.parameters.Parameters;
import edu.southwestern.scores.Score;

/**
 * Bin original Lode Runner levels according to binning schemes 
 * 
 * @author schrum2
 *
 */
public class BinOriginalLodeRunnerLevels {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		String[] binningSchemes = new String[] {
				"LodeRunnerMAPElitesPercentGroundNumGoldAndEnemiesBinLabels", // Used in GECCO extended abstract
				"LodeRunnerMAPElitesPercentConnectedNumGoldAndEnemiesBinLabels",
				"LodeRunnerMAPElitesPercentConnectedGroundAndLaddersBinLabels"
		};
		// Collect levels
		List<ArrayList<List<Integer>>> allLevels = new ArrayList<>();
		File levelPath = new File(LodeRunnerVGLCUtil.LODE_RUNNER_LEVEL_PATH);
		for(File level : levelPath.listFiles()) {
			if(level.getName().endsWith(".txt")) {
				System.out.println(level);
				ArrayList<List<Integer>> oneLevelList = LodeRunnerVGLCUtil.convertLodeRunnerLevelFileVGLCtoListOfLevel(level.toString());
				//System.out.println(oneLevelList);
				allLevels.add(oneLevelList);
			}
		}
		
		// Apply binning
		for(String binningSchemeClassName : binningSchemes) {
			Parameters.initializeParameterCollections(new String[] {
					"base:dagstuhlloderunner","log:DagstuhlLodeRunner-"+binningSchemeClassName,"saveTo:"+binningSchemeClassName,
					"task:edu.southwestern.tasks.loderunner.LodeRunnerGANLevelTask",
					"LodeRunnerGANModel:LodeRunnerAllGround5LevelsEpoch20000_10_7.pth",
					"genotype:edu.southwestern.evolution.genotypes.BoundedRealValuedGenotype",
					"GANInputSize:10","aStarSearchBudget:100000",
					"allowWeirdLodeRunnerActions:false","lodeRunnerMaximizeEnemies:false",
					"lodeRunnerTSPBudget:0","lodeRunnerAllowsAStarConnectivityCombo:true",
					"mu:0", "io:true","netio:true","watch:false",
					"steadyStateIndividualsPerGeneration:1",
					"ea:edu.southwestern.evolution.mapelites.MAPElites", 
					"experiment:edu.southwestern.experiment.evolution.SteadyStateExperiment",
					"mapElitesBinLabels:edu.southwestern.tasks.loderunner.mapelites."+binningSchemeClassName});
			MMNEAT.loadClasses();

			LodeRunnerGANLevelTask lodeRunnerLevelTask = (LodeRunnerGANLevelTask) MMNEAT.task;
			@SuppressWarnings("rawtypes")
			MAPElites me = (MAPElites) MMNEAT.ea;
			
			System.out.println(binningSchemeClassName);
			for(List<List<Integer>> levelAsLists : allLevels) {
				HashMap<String,Object> map = new HashMap<>();
				System.out.println("Evaluate level");
				lodeRunnerLevelTask.evaluateOneLevel(levelAsLists, 0, MMNEAT.genotype, map);
				@SuppressWarnings("rawtypes")
				Archive archive = MMNEAT.getArchive();
				System.out.println(Arrays.toString(archive.getBinMapping().multiDimensionalIndices(map)));
				//System.out.println(map);
				//System.out.println(Arrays.toString( (((ArrayList<double[]>) map.get("Level Stats"))).get(0)));
				// No genotype or scores
				@SuppressWarnings({ "rawtypes" })
				Score score = new Score(map,MMNEAT.genotype,new double[0]);
				me.fileUpdates(archive.add(score));
			}   
			me.finalCleanup();
			MMNEAT.clearClasses();
		}
	}
	
}
