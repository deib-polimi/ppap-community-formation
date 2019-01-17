package it.polimi.ppap.graph;

import it.polimi.ppap.ui.RandomColorPicker;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.Viewer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ImportSPLACommunities {

    String communitiesInputFilePath;
    Scanner reader;

    public ImportSPLACommunities(String communitiesInputFilePath){
        this.communitiesInputFilePath = communitiesInputFilePath;
    }

    public void importCommunities(Graph graph, Viewer viewer) throws IOException {
        openFile();
        Map<String, Color> communityColor = new HashMap<>();
        while (reader.hasNext()) {
            String line = reader.nextLine();
            String [] terms = line.split(" ");
            String nodeId = terms[0];
            String communityId = terms[1];
            System.out.println(nodeId + "\t" + communityId);
            Node node = graph.getNode(nodeId);
            if(!communityColor.containsKey(communityId))
                communityColor.put(communityId, RandomColorPicker.pickRandomColor());
            String rgb = getColorRGB(communityColor.get(communityId));
            node.addAttribute("ui.style", "fill-color: " + rgb + ";");
        }
        closeFile();
    }

    private String getColorRGB(Color color){
        return "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    }

    private void openFile() throws IOException {
        File graphFile = new File(communitiesInputFilePath);
        reader = new Scanner(graphFile);
        //reader.useDelimiter(" ");
    }

    private void closeFile() throws IOException {
        reader.close();
    }
}
