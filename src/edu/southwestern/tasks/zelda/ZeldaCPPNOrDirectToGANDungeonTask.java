package edu.southwestern.tasks.zelda;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import edu.southwestern.MMNEAT.MMNEAT;
import edu.southwestern.evolution.genotypes.CPPNOrDirectToGANGenotype;
import edu.southwestern.evolution.genotypes.Genotype;
import edu.southwestern.networks.Network;
import edu.southwestern.parameters.Parameters;
import edu.southwestern.tasks.BoundedTask;
import edu.southwestern.tasks.gvgai.zelda.dungeon.Dungeon;
import edu.southwestern.tasks.gvgai.zelda.dungeon.DungeonUtil;
import edu.southwestern.tasks.interactive.gvgai.ZeldaCPPNtoGANLevelBreederTask;
import edu.southwestern.tasks.mario.gan.GANProcess;
import edu.southwestern.util.datastructures.ArrayUtil;

@SuppressWarnings("rawtypes")
public class ZeldaCPPNOrDirectToGANDungeonTask extends ZeldaDungeonTask implements BoundedTask {

	private int segmentLength;

	public ZeldaCPPNOrDirectToGANDungeonTask() {
		super();
		// These dungeons are generated by CPPN, not grammar
		DungeonUtil.NO_GRAMMAR_AT_ALL = true;
		GANProcess.type = GANProcess.GAN_TYPE.ZELDA;
		segmentLength = GANProcess.latentVectorLength()+ZeldaCPPNtoGANLevelBreederTask.numberOfNonLatentVariables();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Dungeon getZeldaDungeonFromGenotype(Genotype individual) {
		CPPNOrDirectToGANGenotype m = (CPPNOrDirectToGANGenotype) individual;
		if(m.getFirstForm()) {
			return ZeldaCPPNtoGANLevelBreederTask.cppnToDungeon((Network) individual.getPhenotype(), Parameters.parameters.integerParameter("zeldaGANLevelWidthChunks"), Parameters.parameters.integerParameter("zeldaGANLevelHeightChunks"), ArrayUtil.doubleOnes(ZeldaCPPNtoGANLevelBreederTask.SENSOR_LABELS.length));
		}else {
			return ZeldaGANDungeonTask.getZeldaDungeonFromDirectArrayList((ArrayList<Double>) individual.getPhenotype(), segmentLength, Parameters.parameters.integerParameter("zeldaGANLevelWidthChunks"), Parameters.parameters.integerParameter("zeldaGANLevelHeightChunks"));
		}
	}
	
	/**
	 * For testing.
	 * 
	 * @param args Ignored
	 * @throws FileNotFoundException
	 * @throws NoSuchMethodException
	 */
	public static void main(String[] args) throws FileNotFoundException, NoSuchMethodException{
		MMNEAT.main("runNumber:0 randomSeed:0 zeldaDungeonDistanceFitness:true zeldaCPPNtoGANAllowsPuzzleDoors:true zeldaCPPNtoGANAllowsRaft:true zeldaDungeonDistinctRoomFitness:true zeldaDungeonBackTrackRoomFitness:true zeldaDungeonFewRoomFitness:false zeldaDungeonTraversedRoomFitness:false zeldaDungeonRandomFitness:false watch:false trials:1 mu:10 makeZeldaLevelsPlayable:false base:zeldacppnthendirect log:ZeldaCPPNThenDirect-NewMAPElites saveTo:NewMAPElites zeldaGANLevelWidthChunks:5 zeldaGANLevelHeightChunks:5 zeldaGANModel:ZeldaFixedDungeonsAll_5000_10.pth maxGens:500 io:true netio:true GANInputSize:10 mating:true fs:false task:edu.southwestern.tasks.zelda.ZeldaCPPNOrDirectToGANDungeonTask cleanOldNetworks:false zeldaGANUsesOriginalEncoding:false cleanFrequency:-1 saveAllChampions:true indirectToDirectTransitionRate:0.5 genotype:edu.southwestern.evolution.genotypes.CPPNOrDirectToGANGenotype netChangeActivationRate:0.3".split(" "));
//		try {
//			//MMNEAT.main(new String[]{"runNumber:8","randomSeed:8","zeldaDungeonBackTrackRoomFitness:true","zeldaDungeonDistanceFitness:true","zeldaDungeonFewRoomFitness:false","zeldaDungeonTraversedRoomFitness:true","zeldaDungeonRandomFitness:false","watch:true","trials:1","mu:10","makeZeldaLevelsPlayable:false","base:zeldacppntogan","log:ZeldaCPPNtoGAN-Temp","saveTo:Temp","zeldaGANLevelWidthChunks:5","zeldaGANLevelHeightChunks:5","zeldaGANModel:ZeldaFixedDungeonsAll_5000_10.pth","maxGens:500","io:true","netio:true","GANInputSize:10","mating:true","fs:false","task:edu.southwestern.tasks.zelda.ZeldaCPPNtoGANDungeonTask","cleanOldNetworks:false", "zeldaGANUsesOriginalEncoding:false","allowMultipleFunctions:true","ftype:0","netChangeActivationRate:0.3","cleanFrequency:-1","simplifiedInteractiveInterface:false","recurrency:false","saveAllChampions:true","cleanOldNetworks:false","includeFullSigmoidFunction:true","includeFullGaussFunction:true","includeCosineFunction:true","includeGaussFunction:false","includeIdFunction:true","includeTriangleWaveFunction:true","includeSquareWaveFunction:true","includeFullSawtoothFunction:true","includeSigmoidFunction:false","includeAbsValFunction:false","includeSawtoothFunction:false"});
//
//			MMNEAT.main(new String[]{"runNumber:8","randomSeed:8", "zeldaCPPNtoGANAllowsPuzzleDoors:true","zeldaCPPNtoGANAllowsRaft:true","zeldaDungeonDistanceFitness:true","zeldaDungeonFewRoomFitness:false","zeldaDungeonTraversedRoomFitness:true","zeldaDungeonRandomFitness:false","zeldaDungeonDistinctRoomFitness:false","watch:true","trials:1","mu:10","makeZeldaLevelsPlayable:false","base:zeldacppntogan","log:ZeldaCPPNtoGAN-DistPercent","saveTo:DistPercent","zeldaGANLevelWidthChunks:5","zeldaGANLevelHeightChunks:5","zeldaGANModel:ZeldaFixedDungeonsAll_5000_10.pth","maxGens:500","io:true","netio:true","GANInputSize:10","mating:true","fs:false","task:edu.southwestern.tasks.zelda.ZeldaCPPNtoGANDungeonTask","cleanOldNetworks:false", "zeldaGANUsesOriginalEncoding:false","allowMultipleFunctions:true","ftype:0","netChangeActivationRate:0.3","cleanFrequency:-1","simplifiedInteractiveInterface:false","recurrency:false","saveAllChampions:true","cleanOldNetworks:false","includeFullSigmoidFunction:true","includeFullGaussFunction:true","includeCosineFunction:true","includeGaussFunction:false","includeIdFunction:true","includeTriangleWaveFunction:true","includeSquareWaveFunction:true","includeFullSawtoothFunction:true","includeSigmoidFunction:false","includeAbsValFunction:false","includeSawtoothFunction:false"});
//		} catch (FileNotFoundException | NoSuchMethodException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public double[] getUpperBounds() {
		return ZeldaGANDungeonTask.getStaticUpperBounds();
	}

	@Override
	public double[] getLowerBounds() {
		return ZeldaGANDungeonTask.getStaticLowerBounds();
	}

}