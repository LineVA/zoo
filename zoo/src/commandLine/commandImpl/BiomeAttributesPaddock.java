package commandLine.commandImpl;

import commandLine.Command;
import launch.Play;

/**
 *
 * @author doyenm
 */
public class BiomeAttributesPaddock implements Command {

        Play play;
    
    public BiomeAttributesPaddock(Play play){
        this.play = play;
    }
//    
    
    private Object[] attributesArray;

    @Override
    public String execute(String[] cmd) {
//        String msg = "";
//        try {
//            IPaddock pad = this.zoo.findPaddockByName(cmd[1]);
//            if (attributesArray[0] != null) {
//                pad.getAttributes().setNightTemperature(Double.parseDouble((String) attributesArray[0]));
//                msg += "The night temperature of the paddock '" + cmd[1] + "' is now " + attributesArray[0] + ". ";
//            }
//            if (attributesArray[1] != null) {
//                pad.getAttributes().setDayTemperature(Double.parseDouble((String) attributesArray[1]));
//                msg += "The day temperature of the paddock '" + cmd[1] + "' is now " + attributesArray[1] + ". ";
//            }
//            if (attributesArray[2] != null) {
//                pad.getAttributes().setPluviometry(Double.parseDouble((String) attributesArray[2]));
//                msg += "The pluviometry of the paddock '" + cmd[1] + "' is now " + attributesArray[2] + ". ";
//            }
//            if (attributesArray[3] != null) {
//                pad.getAttributes().setTreeDensity(Double.parseDouble((String) attributesArray[3]));
//                msg += "The tree density of the paddock '" + cmd[1] + "' is now " + attributesArray[3] + ". ";
//            }
//            if (attributesArray[4] != null) {
//                pad.getAttributes().setTreeHeight(Double.parseDouble((String) attributesArray[4]));
//                msg += "The tree height of the paddock '" + cmd[1] + "' is now " + attributesArray[4] + ". ";
//            }
//            if (attributesArray[5] != null) {
//                pad.getAttributes().setDrop(Double.parseDouble((String) attributesArray[5]));
//                msg += "The drop of the paddock '" + cmd[1] + "' is now " + attributesArray[5] + ". ";
//            }
//            if (attributesArray[6] != null) {
//                pad.getAttributes().setWaterSalinity(Double.parseDouble((String) attributesArray[6]));
//                msg += "The water salinity of the paddock '" + cmd[1] + "' is now " + attributesArray[6] + ". ";
//            }
//            if (attributesArray[7] != null) {
//                pad.getAttributes().setHumidity(Double.parseDouble((String) attributesArray[7]));
//                msg += "The humidity of the paddock '" + cmd[1] + "' is now " + attributesArray[7] + ". ";
//            }
//            return msg;
//        } catch (UnknownNameException | EmptyNameException | IncorrectDataException ex) {
//            return ex.getMessage();
//        }
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canExecute(String[] cmd) {
//        this.attributesArray = new Object[] {null, null, null, null, null, null, null, null};
//        boolean setAtt = false;
//        if (cmd.length% 2 == 0) {
//            if (cmd[0].equals("pad") || cmd[0].equals("paddock")) {
//                ArrayList<String> cmdList = new ArrayList(Arrays.asList(cmd));
//                if (cmdList.contains("nightTemperature")) {
//                    attributesArray[0] = cmdList.get(cmdList.indexOf("nightTemperature") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("dayTemperature")) {
//                    attributesArray[1] = cmdList.get(cmdList.indexOf("dayTemperature") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("pluviometry")) {
//                    attributesArray[2] = cmdList.get(cmdList.indexOf("pluviometry") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("treeDensity")) {
//                    attributesArray[3] = cmdList.get(cmdList.indexOf("treeDensity") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("treeHeight")) {
//                    attributesArray[4] = cmdList.get(cmdList.indexOf("treeHeight") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("drop")) {
//                    attributesArray[5] = cmdList.get(cmdList.indexOf("drop") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("waterSalinity")) {
//                    attributesArray[6] = cmdList.get(cmdList.indexOf("waterSalinity") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("humidity")) {
//                    attributesArray[7] = cmdList.get(cmdList.indexOf("humidity") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("-nT")) {
//                    attributesArray[0] = cmdList.get(cmdList.indexOf("-nT") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("-dT")) {
//                    attributesArray[1] = cmdList.get(cmdList.indexOf("-dT") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("-p")) {
//                    attributesArray[2] = cmdList.get(cmdList.indexOf("-p") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("-tD")) {
//                    attributesArray[3] = cmdList.get(cmdList.indexOf("-tD") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("-tH")) {
//                    attributesArray[4] = cmdList.get(cmdList.indexOf("-tH") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("-d")) {
//                    attributesArray[5] = cmdList.get(cmdList.indexOf("-d") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("-wSa")) {
//                    attributesArray[6] = cmdList.get(cmdList.indexOf("-wS") + 1);
//                    setAtt = true;
//                }
//                if (cmdList.contains("-h")) {
//                    attributesArray[7] = cmdList.get(cmdList.indexOf("-h") + 1);
//                    setAtt = true;
//                }
//            }
//        }
//        return setAtt;
        return false;
    }
}
