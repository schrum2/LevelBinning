package edu.southwestern.tasks.megaman;

import java.io.FileNotFoundException;
import java.util.List;

import edu.southwestern.MMNEAT.MMNEAT;
import edu.southwestern.evolution.genotypes.Genotype;
import edu.southwestern.networks.Network;
import edu.southwestern.parameters.Parameters;
import edu.southwestern.tasks.BoundedTask;
import edu.southwestern.tasks.interactive.megaman.MegaManCPPNtoGANLevelBreederTask;
import edu.southwestern.tasks.megaman.gan.MegaManGANUtil;
import edu.southwestern.tasks.megaman.levelgenerators.MegaManOneGANGenerator;
import edu.southwestern.tasks.megaman.levelgenerators.MegaManSevenGANGenerator;
import edu.southwestern.util.datastructures.ArrayUtil;

public class MegaManCPPNtoGANLevelTask<T extends Network> extends MegaManLevelTask<T> implements BoundedTask {

	public MegaManCPPNtoGANLevelTask(){
		super();
		if(Parameters.parameters.booleanParameter("useMultipleGANsMegaMan")) MegaManGANUtil.setMegaManGANGenerator(new MegaManSevenGANGenerator());
			//megaManGenerator = new MegaManSevenGANGenerator();
		else  MegaManGANUtil.setMegaManGANGenerator(new MegaManOneGANGenerator());
			//megaManGenerator = new MegaManOneGANGenerator();
	}
	
	@Override
	public List<List<Integer>> getMegaManLevelListRepresentationFromGenotype(Genotype<T> individual, MegaManTrackSegmentType segmentTypeTracker) {
		List<List<Integer>> level = MegaManGANUtil.cppnToMegaManLevel(MegaManGANUtil.getMegaManGANGenerator(), individual.getPhenotype(), Parameters.parameters.integerParameter("megaManGANLevelChunks"), ArrayUtil.doubleOnes(MegaManCPPNtoGANLevelBreederTask.SENSOR_LABELS.length), segmentTypeTracker);
		return level;
	}
	
	public static void main(String[] args) {
		try {

			MMNEAT.main(new String[]{"runNumber:8","randomSeed:8","watch:true","trials:1","mu:10","base:megamancppntogan", "useMultipleGANsMegaMan:true",
					"log:MegaManCPPNtoGAN-DistPercent","saveTo:DistPercent","megaManGANLevelChunks:10",
					"megaManAllowsSimpleAStarPath:true", "megaManAllowsConnectivity:true", "megaManAllowsLeftSegments:true",
					"maxGens:500","io:true","netio:true","GANInputSize:5","mating:true","fs:false",
					"task:edu.southwestern.tasks.megaman.MegaManCPPNtoGANLevelTask","cleanOldNetworks:false",
					"allowMultipleFunctions:true","ftype:0","netChangeActivationRate:0.3","cleanFrequency:-1",
					"simplifiedInteractiveInterface:false","recurrency:false","saveAllChampions:true",
					"cleanOldNetworks:false","includeFullSigmoidFunction:true","includeFullGaussFunction:true",
					"includeCosineFunction:true","includeGaussFunction:false","includeIdFunction:true",
					"includeTriangleWaveFunction:true","includeSquareWaveFunction:true","includeFullSawtoothFunction:true",
					"includeSigmoidFunction:false","includeAbsValFunction:false","includeSawtoothFunction:false"});
		} catch (FileNotFoundException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Override
	public double[] getUpperBounds() {
		return MegaManGANLevelTask.getStaticUpperBounds();
	}

	@Override
	public double[] getLowerBounds() {
		return MegaManGANLevelTask.getStaticLowerBounds();
	}
}
