package commandLine;

import zoo.AlreadyUsedNameException;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class CommandLineParser {

    private Zoo zoo;

    public CommandLineParser() {
    }

    public String hello() {
        return "Hello world";
//  System.out.println("Hello world");
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

    private String paddock(String[] parse) {
        if (this.zoo != null) {
            switch (parse.length) {
                case 2:
                    if (parse[1].equals("ls")) {
                        return this.zoo.listPaddock();
                    } else {
                        return unknownCmd(parse, "paddock");
                    }
                case 3:
                    if (parse[1].equals("create")) {
                        try {
                            this.zoo.addPaddock(parse[2]);
                            return "";
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
    private String zoo(String[] parse) {
        // Both the commands, which begin with "zoo" only accept two parameters
        if (parse.length == 2) {
            if (parse[1].equals("map")) {
                throw new UnsupportedOperationException("zoo map is not supported yet.");
            } else {
                Zoo zoo = new Zoo(parse[1]);
                this.zoo = zoo;
                return " Your zoo is now " + parse[1];
            }
        } else {
            return unknownCmd(parse, "zoo");
        }
    }

    public String[] parse(String cmd) {
        return cmd.split(" ");
    }

    public String analyze(String[] parse) {
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
        String[] parse = this.parse(cmd);
        return this.analyze(parse);
    }
}
