package edu.southwestern.tasks.loderunner.mapelites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.southwestern.evolution.mapelites.BaseBinLabels;
import edu.southwestern.parameters.Parameters;

public class LodeRunnerMAPElitesPercentGroundNumGoldAndEnemiesBinLabels extends BaseBinLabels {
	
	public static final int INDEX_GROUND_PERCENT = 0;
	public static final int INDEX_TREASURES = 1;
	public static final int INDEX_ENEMIES = 2;
	
	
	List<String> labels = null;
	public static final int BINS_PER_DIMENSION = 10; //[0%-10%][10%-20%].....[90%-100%]
	public static final int SCALE_BY_FIVE = 5; //makes groups of 5
	public static final int SCALE_BY_TWO = 2; //makes groups of 2
	
	@Override
	public List<String> binLabels() {
		if(labels==null) {
			int size = BINS_PER_DIMENSION*BINS_PER_DIMENSION*BINS_PER_DIMENSION; //10x10x10=1000
			labels = new ArrayList<String>(size);
			for(int i = 0; i < BINS_PER_DIMENSION; i++) { //ground percentage
				for(int j = 0; j < BINS_PER_DIMENSION*SCALE_BY_FIVE; j+=SCALE_BY_FIVE) { //number of treasure
					for(int k = 0; k < BINS_PER_DIMENSION*SCALE_BY_TWO; k+=SCALE_BY_TWO) { //number of enemies 
						labels.add("Ground"+i+"0-"+(i+1)+"0Treasure"+j+"-"+(j+SCALE_BY_FIVE)+"Enemies"+k+"-"+(k+SCALE_BY_TWO));
					}
				}
			}
		}
		return labels;
	}

	@Override
	public int oneDimensionalIndex(int[] multi) {
		int binIndex = (multi[0]*BINS_PER_DIMENSION + multi[1])*BINS_PER_DIMENSION + multi[2];
		return binIndex;
	}
	
	@Override
	public String[] dimensions() {
		return new String[] {"Ground Percent", "Treasures", "Enemies"};
	}

	@Override
	public int[] dimensionSizes() {
		return new int[] {BINS_PER_DIMENSION, BINS_PER_DIMENSION, BINS_PER_DIMENSION};
	}

	@Override
	public int[] multiDimensionalIndices(HashMap<String, Object> keys) {
		double numTreasure = (Double) keys.get("Treasures");
		double numEnemies = (Double) keys.get("Enemies");
		double percentGround = (Double) keys.get("Ground Percent");

		int groundIndex = Math.max(0, Math.min((int)((percentGround-0.1)*3*BINS_PER_DIMENSION), BINS_PER_DIMENSION-1));
		
		double treasureScale = 5.0; //scales bins to be in groups of 5, [0-5][5-10]...
		double enemyScale = 2.0; //scales bins to be in groups of 2, [0-2][2-4]...
		//gets correct indices for treasure and enemies
		int treasureIndex = (int) Math.min(numTreasure/treasureScale, BINS_PER_DIMENSION-1);
		int enemyIndex = (int) Math.min(numEnemies/enemyScale, BINS_PER_DIMENSION-1);
		return new int[] {groundIndex, treasureIndex, enemyIndex}; // ground percentage, number of treasures scaled, number of enemies scaled
	}
	
	// TODO: The methods below are copies of what are in BaseBinLabels, so no change in behavior yet.
	//       However, if I fill these out I can evolve with a restricted range in Lode Runner.
	
	@Override
	public boolean discard(HashMap<String, Object> behaviorMap) {
		if(!Parameters.parameters.booleanParameter("discardFromBinOutsideRestrictedRange")) return false;
		
		int[] multi = multiDimensionalIndices(behaviorMap);
		// Allow for discarding of solutions outside of a restricted range set at the command line
		boolean result = isOutsideRestrictedRange(multi);
		return result;
	}
	
	@Override
	public boolean isOutsideRestrictedRange(int[] multi) {
		boolean result =
				   multi[INDEX_GROUND_PERCENT] < Parameters.parameters.integerParameter("lodeRunnerMinGroundPercentIndex") ||
				   multi[INDEX_GROUND_PERCENT] > Parameters.parameters.integerParameter("lodeRunnerMaxGroundPercentIndex") ||
				   multi[INDEX_TREASURES] < Parameters.parameters.integerParameter("lodeRunnerMinTreasuresIndex") ||
				   multi[INDEX_TREASURES] > Parameters.parameters.integerParameter("lodeRunnerMaxTreasuresIndex") ||
				   multi[INDEX_ENEMIES] < Parameters.parameters.integerParameter("lodeRunnerMinEnemiesIndex") ||
				   multi[INDEX_ENEMIES] > Parameters.parameters.integerParameter("lodeRunnerMaxEnemiesIndex");
		return result;
	}
	
	/**
	 * Assume no restricted bounds exist, but this can be overridden
	 * @return String listing the restricted lower bounds in each dimension, separated by spaces
	 */
	@Override
	public String lowerRestrictedBounds() {
		return "";
	}
	
	/**
	 * Assume no restricted bounds exist, but this can be overridden
	 * @return String listing the restricted upper bounds in each dimension, separated by spaces
	 */
	@Override
	public String upperRestrictedBounds() {
		return "";
	}
}
