package it.polimi.ppap;

import it.polimi.ppap.graph.ExportGSGraph;
import it.polimi.ppap.graph.ExportSPLAGraph;
import it.polimi.ppap.graph.ImportGSGraph;
import it.polimi.ppap.graph.ImportSPLACommunities;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomEuclideanGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import java.io.IOException;

public class Main {

    public static void main(String [] args){
        //generateGraph();
        importGraph();
    }

    private static void importGraph(){
        String rootPath = System.getProperty("user.dir");
        String inputGSFile = rootPath + "/gsGraph.dgs";
        String inputSPLACommunitiesFile = rootPath + "/../../../../math/GANXiS_v3.0.2/output/SLPAw_splaGraph_run1_r0.01_v3_T100.icpm.node-com.txt";

        try {
            ImportGSGraph importGSGraph = new ImportGSGraph(inputGSFile);
            ImportSPLACommunities importSPLACommunities = new ImportSPLACommunities((inputSPLACommunitiesFile));

            Graph graph = importGSGraph.importGraph("DorogovtsevMendes");
            Viewer viewer =  graph.display(false);

            importSPLACommunities.importCommunities(graph, viewer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateGraph(){
        Graph graph = new SingleGraph("DorogovtsevMendes");
        //Generator gen = new DorogovtsevMendesGenerator();
        //Generator gen = new BarabasiAlbertGenerator(1);
        Generator gen = new RandomEuclideanGenerator();
        gen.addSink(graph);
        gen.begin();
        for(int i=0; i<500; i++) {
            gen.nextEvents();
        }

        gen.end();
        graph.display(true);

        String rootPath = System.getProperty("user.dir");
        String outputSPLAFile = "/splaGraph.ipairs";
        String outputGSFile = "/gsGraph.dgs";
        try {
            ExportSPLAGraph exportSPLAGraph = new ExportSPLAGraph(rootPath + outputSPLAFile);
            ExportGSGraph exportGSGraph = new ExportGSGraph(rootPath + outputGSFile);
            exportGSGraph.exportGraph(graph);
            exportSPLAGraph.exportGraph(graph);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
