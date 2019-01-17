package it.polimi.ppap.graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

public class ExportSPLAGraph {

    String outputFilePath;
    BufferedWriter writer;

    private static Random r = new Random();

    public ExportSPLAGraph(String outputFilePath){
        this.outputFilePath = outputFilePath;
    }

    public void exportGraph(Graph graph) throws IOException {

        try {
            openFile();
            for (Iterator<Edge> it = graph.getEdgeIterator(); it.hasNext();) {
                Edge edge = it.next();
                writeToFile(edge.getNode0().getId() + "\t");
                writeToFile(edge.getNode1().getId() + "\t");
                writeToFile(Math.max(1, r.nextInt()) + "\n");
            }
            closeFile();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void openFile() throws IOException {
        File file = new File(outputFilePath);
        if(!file.exists())
            file.createNewFile();
        else {
            file.delete();
            file.createNewFile();
        }
        writer = new BufferedWriter(new FileWriter(file));
    }

    private void closeFile() throws IOException {
        if(writer != null)
            writer.close();
    }

    private void writeToFile(String line) throws IOException {
        if(writer != null) {
            writer.append(line);
        }
    }
}
