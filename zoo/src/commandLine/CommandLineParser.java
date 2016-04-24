package commandLine;

import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.UnknownNameException;
import gui.FormattingDisplay;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class CommandLineParser {

    private Zoo zoo;
    private FormattingDisplay formatting;

    public CommandLineParser() {
        formatting = new FormattingDisplay();
    }

    public String hello() {
        return "Hello world";
//  System.out.println("Hello world");
    }

    private String animal(String[] parse) {
        return "";
    }

    private String findMan(String[] parse) {
        switch (parse[1]) {
            case "man":
                return manMan();
            case "pad":
            case "paddock":
                return manPaddock();
            case "zoo":
                return manZoo();
            default:
                return unknownCmd(parse, "man");
        }
    }

    public String man(String[] parse) {
        switch (parse.length) {
            case 1:
                return "See 'man man' for more information.";
            case 2:
                return findMan(parse);
            default:
                return "See 'man man' for more information.";
        }
    }

    private String manMan() {
        return "man man";
    }

    private String manPaddock() {
        return "man paddock";
    }

    private String manZoo() {
        return "man zoo";
    }

    private String paddock(String[] parse) throws UnknownNameException {
        if (this.zoo != null) {
            switch (parse.length) {
                case 2:
                    if (parse[1].equals("ls")) {
                        return this.zoo.listPaddock();
                    } else {
                        return zoo.detailedPaddock(parse[1]);
                    }
                case 7:
                    if (parse[1].equals("create")) {
                        try {
                            this.zoo.addPaddock(parse[2],
                                    this.stringToInteger(parse[3]),
                                    this.stringToInteger(parse[4]),
                                    this.stringToInteger(parse[5]),
                                    this.stringToInteger(parse[6]));
                            return "This paddock has been successfully created.";
                        } catch (AlreadyUsedNameException ex) {
                            return ex.getMessage();
                        }
                    } else {
                        return unknownCmd(parse, "paddock");
                    }
                default:
                    return unknownCmd(parse, "paddock");
            }
        } else {
            return "You must create a zoo before.";
        }
    }

    public String unknownCmd(String[] parse, String cpt) {
        String info = "Unknown command line";
        if (cpt != null) {
            info += " : see 'man " + cpt + "' for more information.";
        }
        return info;
    }

    /**
     * Function to analyze the cmd lines beginning with zoo > zoo map to see the
     * map > zoo xyz to create a zoo called xyz
     *
     * @param parse
     * @return
     */
    private String zoo(String[] parse) throws IncorrectDimensionsException {
        // zoo map
        if (parse.length == 2) {
            if (parse[1].equals("map")) {
                return formatting.zooMap(zoo.getWidth(), zoo.getHeight());
            } else {
                return unknownCmd(parse, "zoo");
            }
            // zoo create name width height
        } else if (parse.length == 5) {
            if (parse[1].equals("create")) {
                int width = -1, height = -1;
                try {
                    width = this.stringToInteger(parse[3]);
                    height = this.stringToInteger(parse[4]);
                } catch (Exception ex) {

                }
                Zoo zooLocal = new Zoo(parse[2], width, height);
                this.zoo = zooLocal;
                return " Your zoo is now created.";
            } else {
                return unknownCmd(parse, "zoo");
            }
        } else {
            return unknownCmd(parse, "zoo");
        }
    }

    public int stringToInteger(String str) {
        return Integer.parseInt(str);
    }

    public String[] parse(String cmd) {
        return cmd.split(" ");
    }

    public String analyze(String[] parse) throws IncorrectDimensionsException, UnknownNameException {
        if (parse.length != 0) {
            switch (parse[0]) {
                case "man":
                    return man(parse);
                //      break;
                case "pad":
                case "paddock":
                    return paddock(parse);
                case "zoo":
                    return zoo(parse);
                default:
                    return unknownCmd(parse, null);
                //    break;
            }
        }
        return "empty command line";
    }

    public String parseAnalyzeAndAct(String cmd) {
        try {
            String[] parse = this.parse(cmd);
            return this.analyze(parse);
        } catch (IncorrectDimensionsException | UnknownNameException ex) {
            return ex.getMessage();
        }
    }
}
