package it.polimi.ppap.graph;

import org.graphstream.graph.Graph;
import org.graphstream.stream.file.FileSink;
import org.graphstream.stream.file.FileSinkDGS;

import java.io.File;
import java.io.IOException;

public class ExportGSGraph {

    String outputFilePath;

    public ExportGSGraph(String outputFilePath){
        this.outputFilePath = outputFilePath;
    }

    public void exportGraph(Graph graph) throws IOException {
        FileSink fileSink = new FileSinkDGS();
        deletePreviousFile();
        fileSink.writeAll(graph, outputFilePath);
    }

    private void deletePreviousFile() throws IOException {
        File file = new File(outputFilePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
